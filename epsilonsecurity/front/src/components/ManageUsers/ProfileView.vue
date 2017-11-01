<template>
        <div>
    <div id = "profile">
            <img :src="userPhoto" alt="" id= "user-photo">
            <ul id= "user-info">
                <li>
                    {{ userData.firstName }} {{ userData.lastName }}
                </li>
                <li>
                    {{ userData.contactEmail }}
                </li>
                <li>
                    {{ userData.phoneNumber }}
                </li>
            </ul>
           <!-- <span id="spacer"></span>-->
            <div id="buttons">
                <edit-user  id="edit-user"
                            @edit="onClickEdit"
                            v-bind:teams="teams"
                            v-bind:userTeams="userTeams"
                            v-bind:userData="userData">
                </edit-user>
                <disable-user
                            id="disable-user"
                            v-bind:enabled="userData.enabled"
                            @disable="onClickDisable">
                </disable-user>
            </div>
        </div>
    <ul id="other-data">
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
            SFU Email: {{ userData.sfuEmail }}
        </li>
    </ul>
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
        flex-flow: column;
    }

    #user-info, #user-photo {
        flex-flow: row;
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
        display: flex;
    }

    #other-data {
        flex-flow: column;
    }

    #edit-user, #disable-user {
        flex-flow: nowrap row;
        height: 15%;
    }
    li  {
        list-style: none;
    }
    #spacer {
        flex-grow: 1;
    }
</style>
