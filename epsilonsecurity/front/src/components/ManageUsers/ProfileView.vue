<template>
<v-container clipped enable-resize-watcher class='hack-height'>
    <router-link v-if='$vuetify.breakpoint.mdAndDown' xs12 exact to="/manage-users/" class="listed-user" tag="li">
        <v-btn color="primary" dark><icon name='arrow-left' class='back'></icon></v-btn>
    </router-link>
    <v-layout row id='main-profile'>
        <v-flex xs4 md3 lg4 xl3 class='profile-item'>
            <img :src="userPhoto" alt="" id= "user-photo">
        </v-flex>
        <v-flex xs6 md6 lg6 xl6 class='profile-item'>
            <ul id="user-info">
                <li class='username'>
                    {{ userData.firstName }} {{ userData.lastName }}
                </li>
                <li>
                    {{ userData.contactEmail }}
                </li>
                <li>
                    {{ userData.phoneNumber }}
                </li>
            </ul>
        </v-flex>
        <v-flex xs2 md3 lg2 xl3 id="buttons" class='profile-item'>
            <v-container>
                <v-layout wrap row>
                    <v-flex class="edit-button" xs12 >
                        <edit-user
                                    @edit="onClickEdit"
                                    v-bind:teams="teams"
                                    v-bind:userTeams="userTeams"
                                    v-bind:userData="userData">
                        </edit-user>
                    </v-flex>
                    <v-flex class="edit-button" xs12 >
                        <disable-user
                                    v-bind:enabled="userData.enabled"
                                    @disable="onClickDisable">
                        </disable-user>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-flex>
    </v-layout>
    <v-layout class='secondary-user-info'>
        <v-flex xs2>
            <ul>
                <li class='info-tag'>role</li>
                <li class='info-tag'>sfu email</li>
                <li class='info-tag'>teams:</li>
            </ul>
        </v-flex>
        <v-flex id="other-data">
        <ul>
            <!-- <li>volunteer</li> -->
            <li>{{ semanticRoleName }}</li>
            <li>{{ userData.sfuEmail }}</li>
            <!-- TODO handle multiple teams in layout -->
            <li>
                <ul id="team-names">
                    <li v-for="team in userTeams"
                        class='team-list'>
                        {{ team.name }}
                    </li>
                </ul>
            </li>
        </ul>
        </v-flex>
    </v-layout>
    <qualifications></qualifications>
</v-container>


</template>

<script>
    import EditUser from "./EditUser.vue";
    import DisableUser from "./DisableUser.vue";
    import QualificationsView from './QualificationsView.vue'
    import axios from 'axios';
    import Icon from 'vue-awesome/components/Icon.vue'

    export default {
        name: 'user-profile',
        data(){
            return {
                userPhoto: 'http://lorempixel.com/100/100/people',
                userTeams: [],
                semanticRoleName: "",
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
            onClickEdit (teamIDs) {
                axios.put('/api/users/' + this.userData.id, this.userData)
                    .then(function(response) {return})
                    .catch(function (error) {
                        console.log(error);
                    });
                axios.post('/api/users/' + this.userData.id + '/teams', {teamIdList: teamIDs})
                    .then(this.emitIfLargeScreen)
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            emitIfLargeScreen(response) {
                if (!this.$vuetify.breakpoint.mdAndDown) {
                    this.$emit('edited');
                }
                axios.get('/api/users/' + this.userData.id + '/teams')
                    .then(this.populateTeam)
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
                axios.get('/api/roles/' + this.userData.roleId)
                    .then(this.setSemanticRoleName)
                    .catch(function (error) {
                        console.log(error);
                });
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
            },
            setSemanticRoleName(response) {
                this.semanticRoleName = response.data.name;
            }
        },
        components: {
            "edit-user": EditUser,
            "disable-user": DisableUser,
            'qualifications': QualificationsView,
            Icon
        },
        watch: {
            id: function(val) {
                this.sendRequests(val);
            }
        },
        mounted: function () {
            this.sendRequests(this.id);
            this.$emit('updated');
        },
        updated: function() {
            this.$emit('updated');
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
        font-size: 1em;
        list-style-type: none;
    }

    #other-data {
        padding-left: 2em;
    }

    #user-photo {
        width: 10em;
        height: auto;
        border-radius: 50%;
    }

    #user-info {
        list-style: none;
        text-align: left;
        font-size: 1.5em;
    }

    .hack-height {
        height: 20em;
    }

    .username {
        font-size: 1.5em;
    }

    ul {
        padding-left: 0;
    }
    li  {
        list-style: none;
    }
    .info-header {
        text-align: right;
    }

    .edit-button {
        max-height: 3em;
    }

    #main-profile, .profile-item {
        max-height: 15em;
    }

    .info-tag {
        text-align: right;
        color: grey;
    }

    .secondary-user-info {
        font-size: 1.3em;
        max-height: 6em;
    }
    .back{
        font-size: 25em;
        color: white;
    }

</style>
