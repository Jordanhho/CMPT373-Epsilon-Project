<template>
    <div id = "userlist">
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
                ]
            }
        },
        methods: {
            toggleAddUser() {
                this.showAddUser = !(this.showAddUser)
            }
        },
        components: {
            "adduser": AddUser
        }
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