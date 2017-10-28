<template>
    <div id="manage-users">
        <userlist id="userlist" v-bind:teams="teams" @clicked="onClickUserListViewItem">
        </userlist>
        <div id="profile-main">
            <profile id="profile"  v-bind:teams="teams"></profile>
            <qualifications id="qualifications"></qualifications>
        </div>
    </div>
</template>

<script>
    import UserListView from './UserListView.vue'
    import QualificationsView from './QualificationsView.vue'
    import ProfileView from './ProfileView.vue'
    import Icon from 'vue-awesome/components/Icon.vue'

    export default {
        name: 'manage-users',
        data() {
            return {
                teams: [
                    {
                        name: 'surrey',
                        id: 1,
                    },
                    {
                        name: 'burnaby',
                        id: 2,
                    },
                    {
                        name: 'vancouver',
                        id: 3,
                    }
                ],
            }
        },
        methods: {
            onClickUserListViewItem(value) {
                alert(value);
            }
        },
        components: {
            "userlist": UserListView,
            'qualifications': QualificationsView,
            'profile': ProfileView,
            Icon
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
