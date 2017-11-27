<template>
    <v-layout class="manage-users">
        <v-flex v-if='renderLeft()' xs12 lg4 class='scroll-y'>
            <userlist   class="userlist"
                        ref="userlist"
                        v-bind:teams="teams"
                        v-bind:roles='roles'>
            </userlist>
        </v-flex>
        <v-flex v-if='renderRight()' xs12 lg8 class='scroll-y'>
            <router-view    class="profile"
                            v-bind:teams="teams"
                            v-bind:roles='roles'
                            @edited="$refs.userlist.requestUsers()">
            </router-view>
        </v-flex>
    </v-layout>
</template>

<script>
    import UserListView from './UserListView.vue';
    import ProfileView from './ProfileView.vue';
    import Icon from 'vue-awesome/components/Icon.vue';
    import axios from 'axios';

    export default {
        name: 'manage-users',
        data() {
            return {
                teams: [],
                roles: [],
            }
        },
        methods: {
            populateTeamList(response) {
                this.teams = response.data;
            },
            populateRoleList(response) {
                this.roles = response.data;
            },
            renderLeft() {
                return this.$vuetify.breakpoint.lgAndUp ||
                    (this.$vuetify.breakpoint.mdAndDown && !this.focused());
            },
            renderRight() {
                return this.$vuetify.breakpoint.lgAndUp ||
                    (this.$vuetify.breakpoint.mdAndDown && this.focused());
            },
            focused() {
                return this.$route.name == 'userManagementProfile';
            }
        },
        components: {
            "userlist": UserListView,
            'profile': ProfileView,
            Icon
        },
        created: function () {
            axios.get('/api/teams')
            .then(this.populateTeamList)
            .catch(function (error) {
                console.log(error);
            });

            axios.get('/api/roles')
            .then(this.populateRoleList)
            .catch(function (error) {
                console.log(error);
            });
        }
    }
</script>

<style scoped lang='scss'>
    #manage-users {
        background: white;
        width: 100%;
        position: relative;
        display: flex;
        height: 100%;
    }

    .userlist {
        background: white;
        height: inherit;
        padding: 2em;
    }

    .profile {
        background: white;
        height: 100%;
        padding: 2em;
        overflow-y: visible;
    }
    .hide {
        display: none;
    }

</style>
