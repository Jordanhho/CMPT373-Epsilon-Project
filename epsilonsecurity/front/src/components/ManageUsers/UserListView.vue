<template>
    <div id = "userlist">
        <select v-model ="team">
            <option selected = "team">all teams</option>
            <option v-for="t in teams">{{t}}</option>
        </select>
        <span>Selected: {{ team }}</span>
        <button id="adduser" @click="showAddUser = true">Add User</button>
        <adduser v-if="showAddUser" @close="showAddUser = false">

        </adduser>
        <ul id = "scroll">
            <li v-for= "name in names">
                {{name.first}}, {{name.last}}
            </li>
        </ul>
    </div>
</template>

<script>
    import AddUser from "./AddUser.vue";
    export default {
        name: 'scroll',
        data(){
            return {
                showAddUser: false,
                names: [
                    {
                        first: "Elinor",
                        last: "Hocutt",
                        teams: ["surrey", "burnaby", "vancouver"]
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },{
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },{
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },{
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },{
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },{
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },{
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },{
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    },
                    {
                        first: "Elinor",
                        last: "Hocutt"
                    }
                ],
                teams: ["surrey", "vancouver", "burnaby"],
                teamValue: 'all teams'
            }
        },
        methods: {
            toggleAddUser() {
                this.showAddUser = !(this.showAddUser)
            },
            getNewList() {
                // ask backend for a list of users on a current team
                // set list to response from backend
            }
        },
        components: {
            "adduser": AddUser
        },
        computed: {
            team: {
                get(){
                    return this.teamValue;
                },
                set(v) {
                    this.teamValue = v;
                    alert(v);
                },
            }
        },
    }
</script>

<style scoped>
    #scroll {
        overflow-y: scroll;
        text-align: left;
        font-size: 1.5em;
        height: 100%;
    }


</style>