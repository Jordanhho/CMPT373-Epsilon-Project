<template>
    <div id = "profile">
        <button id="edituser" @click="showEditUser = true">Edit User</button>
        <edituser v-if="showEditUser" @close="showEditUser = false">
        </edituser>
        <button id="usersettings" @click="showUserSettings = true">User Settings</button>
        <usersettings v-if="showUserSettings" @close="showUserSettings = false">
        </usersettings>
        <img :src="userPhoto" alt="" id= "user-photo">
        <ul id = "user-info">
            <li v-for= "info in infoList">
                {{info.text}}
            </li>
        </ul>
    </div>
</template>

<script>
    export default {
        name: 'user-profile',
        data(){
            return {
                userPhoto: 'http://lorempixel.com/100/100/people',
                infoList: [
                    {text: 'first name, last name'},
                    {text: 'team'},
                    {text: 'role'},
                    {text: 'email'},
                    {text: 'phone'}

                ]
            }
        },
        methods: {
            toggleEditUser() {
                this.showEditUser = !(this.showEditUser)
            },

            toggleAddUser() {
                this.showUserSettings = !(this.showUserSettings)
            },
        }
    }
</script>

<style scoped lang='scss'>
    #profile {
        display: flex;
    }

    #user-info, #user-photo {
        flex-flow: nowrap row;
    }

    #user-photo {
        width: 150px;
        height: 150px;
        border-radius: 50%;
    }

    #user-info {
        list-style: none;
        text-align: left;
    }




</style>