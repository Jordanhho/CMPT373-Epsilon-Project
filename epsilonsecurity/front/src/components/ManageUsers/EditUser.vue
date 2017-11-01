<template>
    <v-layout row justify-center>
        <v-dialog v-model="dialog" persistent max-width="500px">
            <v-btn color="primary" dark slot="activator" >Edit</v-btn>
            <v-card>
                <v-card-title>
                    <span class="headline">Edit User</span>
                </v-card-title>
                <v-card-text>
                    <v-container grid-list-md>
                        <v-layout wrap>
                            <v-flex xs12 sm6>
                                <v-text-field label="First Name" required
                                              v-model="userData.firstName"></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6>
                                <v-text-field label="Last name" required
                                              v-model="userData.lastName"></v-text-field>
                            </v-flex>
                            <v-flex xs12>
                                <v-text-field label="Role" required
                                              v-model="userData.roleId"></v-text-field>
                            </v-flex>
                            <v-flex xs12>
                                <v-text-field label="SFU Email" required
                                              v-model="userData.sfuEmail"></v-text-field>
                            </v-flex>
                            <v-flex xs12>
                                <v-text-field label="Contact Email" required
                                              v-model="userData.contactEmail"></v-text-field>
                            </v-flex>
                            <v-flex xs12>
                                <v-text-field label="Phone Number" required
                                              v-model="userData.phoneNumber"></v-text-field>
                            </v-flex>
                            <v-flex xs12>
                                Teams:
                            </v-flex>
                            <v-flex xs12 v-for='team in teams'>
                                <v-checkbox :label="team.name" v-model="listOfTeamIDsForUser":id="team.name" :value="team.id"></v-checkbox>
                            </v-flex>

                        </v-layout>
                    </v-container>
                    <small>*indicates required field</small>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" flat @click.native="confirm">Edit</v-btn>
                    <v-btn color="blue darken-1" flat @click.native="closeDialog">Close</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-layout>
</template>


<script>

    export default {
        data () {
            return {
                listOfTeamIDsForUser: [],
                dialog: false,
            }
        },
        components: {

        },
        props: {
            teams: {
                type: Array,
                required: true
            },
            userTeams: {
                type: Array,
                required: true
            },
            userData: {
                type: Object,
                required: true
            }
        },
        methods: {
            displayAllTeams() {
                alert(this.listOfTeamIDsForUser);
            },
            closeDialog() {
              this.dialog = false;
            },
            confirm() {
                this.$emit('edit');
                this.closeDialog();
            }
        },
        watch: {
            userTeams: function(val) {
                this.listOfTeamIDsForUser = val.map(x => x.id);
            //console.log(JSON.stringify(this.userTeams, null, 2));
        }
        }
    }
</script>

<style scoped>
    .modal-mask {
        position: fixed;
        z-index: 9998;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, .5);
        display: table;
        transition: opacity .3s ease;
    }

    .modal-wrapper {
        display: table-cell;
        vertical-align: middle;
    }

    .modal-container {
        width: 300px;
        margin: 0 auto;
        padding: 20px 30px;
        background-color: #fff;
        border-radius: 2px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
        transition: all .3s ease;
        font-family: Helvetica, Arial, sans-serif;
    }

    .modal-header h3 {
        margin-top: 0;
        color: #42b983;
    }

    .modal-body {
        margin: 20px 0;
    }

    /*
     * The following styles are auto-applied to elements with
     * transition="modal" when their visibility is toggled
     * by Vue.js.
     *
     * You can easily play with the modal transition by editing
     * these styles.
     */

    .modal-enter {
        opacity: 0;
    }

    .modal-leave-active {
        opacity: 0;
    }

    .modal-enter .modal-container,
    .modal-leave-active .modal-container {
        -webkit-transform: scale(1.1);
        transform: scale(1.1);
    }

</style>
