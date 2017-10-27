<template>
    <div id = "userlist">
        <select v-model ="team">
            <option selected = "team">all teams</option>
            <option v-for="t in teams">{{t}}</option>
        </select>
        <span>Selected: {{ team }}</span>
        <button id="add-user" @click="showAddUser = true">Add User</button>
        <add-user v-if="showAddUser" @close="showAddUser = false">
        </add-user>
        <ul id = "scroll">
            <listed-user v-for= "user in filteredUsers"
                         @clicked="onClickListElement"
                         v-bind:user="user">
            </listed-user>
        </ul>
    </div>
</template>

<script>
    import AddUser from "./AddUser.vue";
    import ListedUser from "./ListedUser.vue";
    let usersList = [
        {
            first: "Billy",
            last: "Jimbob",
            teams: ["surrey"],
            userid: 1
        },
        {
            first: "Kevin",
            last: "Bacon",
            teams: ["surrey", "burnaby", "vancouver"],
            userid: 2
        },
        {
            first: "Kevin",
            last: "Hart",
            teams: ["vancouver"],
            userid: 3
        },
        {
            first: "Russell",
            last: "Peters",
            teams: ["surrey", "vancouver"],
            userid: 4
        },
        {
            first: "Elinor",
            last: "Hocutt",
            teams: ["burnaby", "vancouver"],
            userid: 5
        },
    ];
    export default {
        name: 'scroll',
        data(){
            return {
                showAddUser: false,
                users: [],
                filteredUsers: [],
                teams: ["surrey", "vancouver", "burnaby"],
                teamValue: 'all teams'
            }
        },
        methods: {
            toggleAddUser() {
                this.showAddUser = !(this.showAddUser)
            },

            onClickListElement (value) {
                this.$emit('clicked', value);
            },
        },
        components: {
            "listed-user": ListedUser,
            "add-user": AddUser
        },
        computed: {
            team: {
                get(){
                    return this.teamValue;
                },
                set(v) {
                    this.teamValue = v;
                    alert(v);
                    this.filteredUsers = this.users.filter(user => v == "all teams" || user.teams.indexOf(v) > -1);
                },
            },
        },
        created: function() {
            // perform ajax request here
            //alert("userlist created");
            this.users = usersList;
            this.filteredUsers = usersList;
        },
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