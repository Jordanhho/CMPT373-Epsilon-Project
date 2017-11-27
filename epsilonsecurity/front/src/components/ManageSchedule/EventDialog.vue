<template>
    <v-layout row justify-center>
        <v-dialog v-model="dialog" persistent max-width="90vw">
            <v-card-text
                style="height: 1px;
                position: relative;"
                slot='activator'>
                <v-btn absolute dark fab top right color="blue" >
                    <v-icon> add </v-icon>
                </v-btn>
            </v-card-text>

            <v-card>
                <v-card-title>
                    <span class="headline">edit event</span>
                </v-card-title>
                <v-card-text>

                    <v-container grid-list-xs>
                        <v-layout fill-height row wrap>
                            <v-flex xs6>
                                <v-container fluid>
                                    <v-layout row wrap>
                                        <!-- TeamId -->
                                        <v-flex xs12>
                                            <v-text-field
                                                label="Team"
                                                :value="teamName"
                                                disabled>
                                            </v-text-field>
                                        </v-flex>

                                        <!-- shiftType -->
                                        <v-flex xs12>
                                            <v-select   v-bind:items='shiftDropDown'
                                                        v-model='shiftObj.shiftTypeId'
                                                        prepend-icon="map"
                                                        @input='queryUsers'>
                                            </v-select>
                                        </v-flex>

                                        <!-- day -->
                                        <v-flex xs12>
                                            <v-flex xs12>
                                                <!-- might need to be its own component -->
												<v-dialog v-model="dateModal" persistent lazy full-width>
													<v-text-field
														slot="activator"
														label="Date"
														v-model="shiftObj.date"
														prepend-icon="event"
														readonly
														required>
													</v-text-field>
													<v-date-picker
														v-model="shiftObj.date"
														first-day-of-week="1"
														actions>
														<template scope="{ save, cancel }">
															<v-card-actions>
																<v-btn flat color="primary" @click="save" @click.stop='queryUsers'>
																	Save
																</v-btn>
																<v-spacer></v-spacer>
																<v-btn flat color="primary" @click="cancel">
																	Cancel
																</v-btn>
															</v-card-actions>
														</template>
													</v-date-picker>
												</v-dialog>
											</v-flex>
                                        </v-flex>
                                        <!-- start -->
                                        <v-flex xs6>
                                            <v-dialog v-model="timeStartModal" persistent lazy full-width>
                                                <v-text-field
                                                    slot="activator"
                                                    label="Time Start"
                                                    v-model="shiftObj.timeStart"
                                                    prepend-icon="access_time"
                                                    readonly
                                                    required>
                                                </v-text-field>
                                                <v-time-picker
                                                    v-model="shiftObj.timeStart"
                                                    :allowed-hours="allowedTimes.hours"
                                                    :allowed-minutes="allowedTimes.minutes"
                                                    actions>
                                                    <template scope="{ save, cancel }">
                                                        <v-card-actions>
                                                            <v-btn flat color="primary" @click="save" @click.stop='queryUsers'>
                                                                Save
                                                            </v-btn>
                                                            <v-spacer></v-spacer>
                                                            <v-btn flat color="primary" @click="cancel">
                                                                Cancel
                                                            </v-btn>
                                                        </v-card-actions>
                                                    </template>
                                                </v-time-picker>
                                            </v-dialog>
                                        </v-flex>

                                        <!-- end -->
                                        <v-flex xs6>
                                            <v-dialog v-model="timeEndModal" persistent lazy full-width>
                                                <v-text-field
                                                    slot="activator"
                                                    label="Time End"
                                                    v-model="shiftObj.timeEnd"
                                                    prepend-icon="access_time"
                                                    readonly
                                                    required>
                                                </v-text-field>
                                                <v-time-picker
                                                    v-model="shiftObj.timeEnd"
                                                    :allowed-hours="allowedTimes.hours"
                                                    :allowed-minutes="allowedTimes.minutes"
                                                    actions>
                                                    <template scope="{ save, cancel }">
                                                        <v-card-actions>
                                                            <v-btn flat color="primary" @click="save" @click.stop='queryUsers'>
                                                                Save
                                                            </v-btn>
                                                            <v-spacer></v-spacer>
                                                            <v-btn flat color="primary" @click="cancel">
                                                                Cancel
                                                            </v-btn>
                                                        </v-card-actions>
                                                    </template>
                                                </v-time-picker>
                                            </v-dialog>
                                        </v-flex>

                                    </v-layout>
                                </v-container>
                            </v-flex >

                            <v-flex xs6>
                                <v-container class='cap-height scroll-y'>
                                    <v-layout row wrap>
                                        <v-flex xs12>
                                            <v-checkbox
                                                v-for='user in users'
                                                :label='user.firstName + " " + user.lastName'
                                                :value='user.id'
                                                v-model='selectedUsers'>
                                            </v-checkbox>
                                        </v-flex>
                                    </v-layout>
                                </v-container>
                            </v-flex>
                        </v-layout>
                    </v-container>


                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" flat @click.native="addShift">Add</v-btn>
                    <v-btn color="blue darken-1" flat @click.native="toggleDialog">Close</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-layout>
</template>

<script>
    import axios from 'axios';
    import moment from 'moment';

    export default {
        data () {
            return {
                //modals
                dateModal: false,
                timeStartModal: false,
                timeEndModal: false,

                dialog: false,
                teamName: "",
                shiftType: -1,
                users: [],
                selectedUsers: [],

                shiftObj: {
                    teamId: false,
                    shiftTypeId: 0,
                    userId: false,
                    date: null,
                    timeStart: null,
                    timeEnd: null,
                },


                allowedTimes: {
                    hours: null,
                    minutes: null,
                },

                //restrict it to 15 minutes increments, do not change hours
                restrictTimeIncrements: {
                    hours: function(value) {
                        return value
                    },
                    minutes: function(value) {
                        return value % 15 === 0
                    }
                },
            }
        },
        components: {

        },
        methods: {
            toggleDialog() {
                this.dialog = !this.dialog;
            },
            addShift() {

            },
            setTeamName(response) {
                this.teamName = response.data.name;
            },
            populateUsers(response) {
                console.log(JSON.stringify(response.data, null, 2));
                this.users = response.data;
            },
            queryUsers() {
                this.shiftObj.teamId = this.teamId;
                if (this.shiftObj.teamId &&
                    this.shiftObj.shiftTypeId &&
                    this.shiftObj.date &&
                    this.shiftObj.timeStart &&
                    this.shiftObj.timeEnd) {

                    var team = this.shiftObj.teamId;
                    var shift = this.shiftObj.shiftTypeId;
                    var start = 1506933000;
                    var end = 1507314600;
                    axios.get('/api/users/teams/' + team)
                    //axios.get('/api/users/teams/' + 1 + '/shifts/'+ start + '/' + end)
                    .then(this.populateUsers)
                    .catch(function (error) {
                        console.log(error);
                    });
                    // /api/users/teams/:teamId/shifts?start=TIMESTART&end=TIMEEND

                }
            }
        },
        props: {
            teamId: {
                type: String,
                required: true,
            },
            shiftTypes: {
                type: Array,
                required: true,
            },
        },
        watch: {
            shiftTypes: function(val) {
                if(val.length >= 1) {
                    this.shiftType = val[0];
                }
            },
        },
        updated: function() {
            axios.get('/api/teams/' + this.teamId)
            .then(this.setTeamName)
            .catch(function (error) {
                console.log(error);
            });
        },
        computed: {
            shiftDropDown: function() {
                return this.shiftTypes.map(
                    function(shift) {
                        return {
                            text: shift.name,
                            value: shift.id
                        }
                    }
                )
            },
        },
        mounted: function() {
            this.allowedTimes = this.restrictTimeIncrements;

            this.shiftObj.teamId = parseInt(this.teamId);

            this.queryUsers();
        },
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
        margin: 0px auto;
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

    .modal-default-button {
        float: right;
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

    .cap-height{
        height: 60vh;
    }

</style>
