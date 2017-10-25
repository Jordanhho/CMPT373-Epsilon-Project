<template>
    <div id="manage-users">
        <userlist id="userlist">
        </userlist>
        <div id="profile-main">
            <profile id="profile"></profile>
            <qualifications id="qualifications"></qualifications>
        </div>
    </div>
</template>

<script>
    import UserListView from './UserListView.vue'
    import QualificationsView from './QualificationsView.vue'
    import ProfileView from './ProfileView.vue'

    export default {
        name: 'manage-users',
        data() {
            return {

            }
        },
        methods: {

        },
        components: {
            "userlist": UserListView,
            'qualifications': QualificationsView,
            'profile': ProfileView
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
        background: red;
    }

    #qualifications {
        background: yellow;
    }


</style>
