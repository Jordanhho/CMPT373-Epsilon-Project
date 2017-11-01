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
                    <li v-for="team in userTeams"
                        class='team-list'>
                        {{ team.name }}
                    </li>
                </ul>
            <li>
                Role: {{ userData.roleId }}
            </li>
            <li>
                Contact Email: {{ userData.contactEmail }}
            </li>
            <li>
                SFU Email: {{ userData.sfuEmail }}
            </li>
            <li>
                Phone Number: {{ userData.phoneNumber }}
            </li>
        </ul>
        <div class="buttons">
            <edit-user
                        @edit="onClickEdit"
                        v-bind:teams="teams"
                        v-bind:userTeams="userTeams"
                        v-bind:userData="userData">
            </edit-user>
            <button id="disable-user"
                    @click="showDisableUser = true">
                {{ userData.enabled ? "disable": "enable" }}
            </button>
            <disable-user   v-if="showDisableUser"
                            @close="showDisableUser = false"
                            @disable="onClickDisable">
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
                showDisableUser: false,
                userPhoto: 'http://lorempixel.com/100/100/people',
                userTeams: [],
                userData: {
                    firstName: "",
                    lastName: "",
                    roleId: "",
                    contactEmail: "",
                    sfuEmail: "",
                    phoneNumber: "",
                    photoURL: "",
                    enabled: true,
                },
            }
        },
        methods: {
            toggleDisableUser() {
                this.showDisableUser = !(this.showDisableUser)
            },
            onClickListElement (value) {
                this.$emit('clicked', value);
            },
            onClickEdit () {
                axios.put('/api/users/' + this.userData.id, this.userData)
                    .then(response => this.$emit('edited'))
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            onClickDisable () {
                this.showDisableUser = false;
                axios.put('/api/users/' + this.userData.id + '/' + !this.userData.enabled)
                    .then(response => this.userData.enabled = !this.userData.enabled)
                    .catch(function (error) {
                    console.log(error);
                });
            },
            populateUserData (response) {
                this.userData = response.data;
            },
            populateTeam (response) {
                this.userTeams = response.data;
            },
            sendRequests(id) {
                axios.get('/api/users/' + id)
                    .then(this.populateUserData)
                    .catch(function (error) {
                        console.log(error);
                    });
                axios.get('/api/users/' + id + '/teams')
                    .then(this.populateTeam)
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        },
        components: {
            "edit-user": EditUser,
            "disable-user": DisableUser,
        },
        watch: {
            id: function(val) {
                this.sendRequests(val);
            }
        },
        mounted: function () {
            this.sendRequests(this.id);
        },
        props: {
            teams: {
                type: Array,
                required: true
            },
            id: {
                type: String,
                required: true
            }

        }
    }
</script>

<style scoped lang='scss'>
    .team-list {
        padding-left: 1em;
    }

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
