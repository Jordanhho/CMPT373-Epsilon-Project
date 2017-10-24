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
                showSidenav: true
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

<style scoped>
    #manage-users {
        background: white;
        width: 100%;
        position: relative;
        display: flex;
    }

    #profile-main {
        height: 100%;
        width: 70%
    }

    #userlist {
        height: 100%;
        width: 30%;
        background: black;
        flex-flow: row nowrap;
    }

    #profile {
        height: 50%;
        width: 100%;
        background: red;
        flex-flow: row nowrap;
    }

    #qualifications {
        height: 50%;
        width: 100%;
        background: yellow;
        flex-flow: row nowrap;
    }


</style>
