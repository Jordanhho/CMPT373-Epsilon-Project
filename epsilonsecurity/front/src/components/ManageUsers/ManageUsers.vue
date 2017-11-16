<template>
    <v-layout class="manage-users">
        <v-flex xs4 class='scroll-y'>
            <userlist   class="userlist"
                        ref="userlist"
                        v-bind:teams="teams"
                        @clicked="onClickUserListViewItem">
            </userlist>
        </v-flex>
        <v-flex xs8 class='scroll-y'>
            <router-view    class="profile"
                            v-bind:teams="teams"
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
                userID: -1,
            }
        },
        methods: {
            onClickUserListViewItem(value) {
                this.userID = value;
            },
            populateTeamList(response) {
                this.teams = response.data;
            },
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

</style>
