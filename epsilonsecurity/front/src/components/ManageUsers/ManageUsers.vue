<template>
    <div id="manage-users">
        <userlist   id="userlist"
                    ref="userlist"
                    v-bind:teams="teams"
                    @clicked="onClickUserListViewItem">
        </userlist>
        <div    id="profile-main"
                v-if='userID != -1'>
            <profile    id="profile"
                        v-bind:id='userID'
                        v-bind:teams="teams"
                        @edited="$refs.userlist.requestUsers()">
            </profile>
            <qualifications id="qualifications"></qualifications>
        </div>
    </div>
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
    $user-list-width: 20%;
    #manage-users {
        background: white;
        width: 100%;
        position: relative;
        display: flex;
    }

    #profile-main {
        height: 100%;
        width: 100% - $user-list-width;
    }

    #userlist {
        height: 100%;
        width: $user-list-width;
        background: white;
        flex-flow: row nowrap;
    }

    #profile, #qualifications {
        height: 50%;
        width: 100%;
        flex-flow: row nowrap;
    }
    #profile {
        background: white;
    }

    #qualifications {
        background: white;
    }


</style>
