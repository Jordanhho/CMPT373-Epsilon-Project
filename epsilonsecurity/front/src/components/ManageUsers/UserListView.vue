<template>
    <div id = "userlist">
        <select v-model ="selected">
            <option selected = "selected">all campuses</option>
            <option v-for="campus in campuses">{{campus}}</option>
        </select>
        <span>Selected: {{ selected }}</span>
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
                campuses:
                ["surrey", "vancouver", "burnaby"],
                selectedString: 'all campuses'
            }
        },
        methods: {
            toggleAddUser() {
                this.showAddUser = !(this.showAddUser)
            }
        },
        components: {
            "adduser": AddUser
        },
        computed: {
            selected: {
                get(){
                    return this.selectedString;
                },
                set(v) {
                    this.selectedString = v;
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