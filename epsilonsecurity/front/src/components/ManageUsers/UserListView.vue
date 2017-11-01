<template>
    <div id="userlist">
        <!--<select v-model="selectedTeam" @change='requestUsers'>-->
        <!--&lt;!&ndash;<option selected="selectedTeam"&ndash;&gt;-->
        <!--&lt;!&ndash;value='all teams'>&ndash;&gt;-->
        <!--&lt;!&ndash;all teams&ndash;&gt;-->
        <!--&lt;!&ndash;</option>&ndash;&gt;-->
        <!--<option v-for="team in teams"-->
        <!--:value='team.id'>-->
        <!--{{ team.name }}-->
        <!--</option>-->
        <!--</select>-->

        <div id="buttons">
            <v-select id="dropdown" v-bind:items="teamList" v-model="selectedTeam" @input="requestUsers">
            </v-select>
            <!-- <span>Selected: {{ team }}</span> -->
            <add-user id="add-user"
                      @add="onClickAdd"
                      v-bind:teams="teams">
            </add-user>
        </div>

        <ul id="scroll">
            <listed-user v-for="user in users"
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
        data() {
            return {
                users: [],
                selectedTeam: -1,
                all: {
                    id: -1,
                    name: "all teams",
                },
            }
        },
        methods: {
            onClickListElement(value) {
                this.$emit('clicked', value);
            },
            onClickAdd(user) {
                axios.post('/api/users', user)
                    .then(response => this.requestUsers())
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            populateUsers(response) {
                this.users = response.data;
            },
            requestUsers() {
                var usersURL = '';
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
        created: function () {
            this.requestUsers();
        },
        props: {
            teams: {
                type: Array,
                required: true
            },
        },
        computed: {
            teamList: function () {
                let list = [this.all].concat(this.teams);
                return list.map(function (team) {
                    let item = {};
                    item.value = team.id;
                    item.text = team.name;
                    return item;
                });
            }
        },
    };


</script>

<style lang="scss" scoped>
    #scroll {
        overflow-y: scroll;
        overflow-x: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        max-width: 100%;
        text-align: left;
        font-size: 1.5em;
        max-height: 85%;
        padding: 0;
        flex-flow: column;
    }

    #buttons {
        display: flex;
        width: 100%;
    }

    #add-user, #dropdown {
    }

</style>
