<template>
    <div id = "profile">
        <img :src="userPhoto" alt="" id= "user-photo">
        <ul id = "user-info">
            <li v-for= "info in infoList">
                {{info.text}}
            </li>
        </ul>
        <div class="buttons">
            <button id="edit-user" @click="showEditUser = true" @clicked="onClickButton">Edit User</button>
            <edit-user v-if="showEditUser" @close="showEditUser = false">
            </edit-user>
            <button id="disable-user" @click="showDisableUser = true" @clicked="onClickButton">Disable User</button>
            <disable-user v-if="showDisableUser" @close="showDisableUser = false">
            </disable-user>
        </div>
    </div>
</template>

<script>
    import EditUser from "./EditUser.vue";
    import DisableUser from "./DisableUser.vue";

    export default {
        name: 'user-profile',
        data(){
            return {
                showEditUser: false,
                showDisableUser: false,
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

            toggleDisableUser() {
                this.showDisableUser = !(this.showDisableUser)
            },
            onClickListElement (value) {
                this.$emit('clicked', value);
            },
            onClickButton (value) {
                alert(value);
                this.$emit('clicked', value);
            },
        },
        components: {
            "edit-user": EditUser,
            "disable-user": DisableUser,
        },
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

    #buttons {
        float: right;
    }
</style>