<template>
    <div id = "profile">
        <img :src="userPhoto" alt="" id= "user-photo">
        <ul id= "user-info">
            <li>
                First Name: {{ userData.firstName }}
            </li>
            <li>
                Last Name:  {{ userData.lastName }}
            </li>
            <li>
                Teams:
                <ul id = "team-names">
                    <li v-for="team in userTeams">
                        {{ team.name }}
                    </li>
                </ul>
            <li>
                Role: {{ userData.role }}
            </li>
            <li>
                Contact Email: {{ userData.contactEmail }}
            </li>
            <li>
                SFU Email: {{ userData.sfuEmail }}
            </li>
        </ul>
        <div class="buttons">
            <button id="edit-user" @click="showEditUser = true">Edit User</button>
            <edit-user v-if="showEditUser" @close="showEditUser = false" @edit="onClickEdit"
                       v-bind:teams="teams"
                       v-bind:userTeams="userTeams"
                       v-bind:userData="userData">
            </edit-user>
            <button id="disable-user" @click="showDisableUser = true">Disable User</button>
            <disable-user v-if="showDisableUser" @close="showDisableUser = false" @disable="onClickDisable">
            </disable-user>
        </div>
    </div>
</template>

<script>
    import EditUser from "./EditUser.vue";
    import DisableUser from "./DisableUser.vue";
    import axios from 'axios';

    export default {
        name: 'user-profile',
        data(){
            return {
                showEditUser: false,
                showDisableUser: false,
                userPhoto: 'http://lorempixel.com/100/100/people',
                userTeams: [],
                userData: {
                    firstName: "",
                    lastName: "",
                    role: "",
                    contactEmail: "",
                    sfuEmail: "",
                },
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
            onClickEdit () {
                alert("edited successfully");
                this.showEditUser = false;
            },
            onClickDisable () {
                alert('user disabled');
                this.showDisableUser = false;
            },
            populateUserData (response) {
                this.userData = response.data;
            },
            populateTeam (response) {
                this.userTeams = response.data;
            }
        },
        components: {
            "edit-user": EditUser,
            "disable-user": DisableUser,
        },
        props: {
            teams: {
                type: Array,
                required: true
            },

        },

        created: function() {
            // run axios call to get userdata and then populate userdata
            // populate the userdata and display in the user-info list.
            axios.get('/assets/1.json')
                .then(this.populateUserData)
                .catch(function (error) {
                    console.log(error);
                });
            axios.get('/assets/1-team.json')
                .then(this.populateTeam)
                .catch(function (error) {
                    console.log(error);
                });
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
    li  {
        list-style:none;
    }
</style>