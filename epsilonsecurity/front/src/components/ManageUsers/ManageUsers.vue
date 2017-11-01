<template>
    <v-container fill-height>
        <v-layout id="manage-users">
            <v-flex xs4>
                <userlist   id="userlist"
                            ref="userlist"
                            v-bind:teams="teams"
                            @clicked="onClickUserListViewItem">
                </userlist>
            </v-flex>
            <v-flex xs8>
                <router-view    id="profile"
                                v-bind:teams="teams"
                                @edited="$refs.userlist.requestUsers()">
                </router-view>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import UserListView from './UserListView.vue';
    import QualificationsView from './QualificationsView.vue';
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
            'qualifications': QualificationsView,
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
        height: 100%;
        top: 0;
        position: relative;
        display: flex;
    }

    #profile-main {
        height: 100%;
    }

    #userlist {
        height: 100%;
        background: white;
        flex-flow: row nowrap;
    }

    #profile, #qualifications {
        height: 50%;
        width: 100%;
        flex-flow: row nowrap;
    }
    #profile {
        display: flex;
        background: white;
    }

    #qualifications {
        background: white;
    }


</style>
