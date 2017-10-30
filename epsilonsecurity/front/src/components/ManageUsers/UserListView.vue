<template>
    <div id = "userlist">
        <select v-model="selectedTeam" @change='requestUsers'>
            <option selected="selectedTeam"
                    value='all teams'>
                all teams
            </option>
            <option v-for="team in teams"
                    :value='team.id'>
                {{ team.name }}
            </option>
        </select>

        <!-- <span>Selected: {{ team }}</span> -->

        <button id="add-user"
                @click="showAddUser = true">
            Add User
        </button>
        <add-user   v-if="showAddUser"
                    @close="showAddUser = false"
                    @add="onClickAdd"
                    v-bind:teams="teams">
        </add-user>

        <ul id = "scroll">
            <listed-user v-for= "user in users"
                         @clicked="onClickListElement"
                         v-bind:user="user">
            </listed-user>
        </ul>

    </div>
</template>

<script>
    import AddUser from "./AddUser.vue";
    import ListedUser from "./ListedUser.vue";
    import axios from 'axios';
    export default {
        name: 'scroll',
        data(){
            return {
                showAddUser: false,
                users: [],
                selectedTeam: 'all teams'
            }
        },
        methods: {
            toggleAddUser() {
                this.showAddUser = !(this.showAddUser)
            },

            onClickListElement (value) {
                this.$emit('clicked', value);
            },
            onClickAdd (user) {
                alert(user.firstName);
                axios.post('/api/users', user)
                    .then(response => this.requestUsers())
                    .catch(function (error) {
                        console.log(error);
                    });
                this.showAddUser = false;
            },
            populateUsers (response) {
                this.users = response.data;
                //console.log(JSON.stringify(this.users, null, 2));
            },
            populateTeams (response) {
                this.teams = respones.data;
            },
            requestUsers() {
                var usersURL = ''
                if (this.selectedTeam > 0) {
                    usersURL = '/api/users/teams/' + this.selectedTeam;
                } else {
                    usersURL = '/api/users';
                }

                axios.get(usersURL)
                .then(this.populateUsers)
                .catch(function (error) {
                    console.log(error);
                });
            }
        },
        components: {
            "listed-user": ListedUser,
            "add-user": AddUser
        },
        created: function() {
            // perform ajax request here
            //alert("userlist created");
            this.requestUsers();

            axios.get('/api/teams')
            .then(this.popluateTeams)
            .catch(function (error) {
                console.log(error);
            });
        },
        props: {
            teams: {
                type: Array,
                required: true
            },
        }
    };


</script>

<style scoped>
    #scroll {
        overflow-y: scroll;
        text-align: left;
        font-size: 1.5em;
        height: 100%;
    }


</style>
