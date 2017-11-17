<template>
		<v-container fluid fill-width fill-height class="pa-0">
			<v-layout row wrap>
				<v-flex xs12>
					<div id = "content">
						<v-layout row wrap>
							<div id = "option-bar">
								<h2>
									TODO OPTIONS PANEL
								</h2>
							</div>

							<div id = "calendar-pkg">
								<div id = "my-calendar">
									<!-- calendar -->
									<full-calendar
										:event-sources="eventSources"
										:config="config"
										id="calendar">
									</full-calendar>
								</div>
									
								<!-- floating add shift button showCreateShift = true -->
								<div id="add-button"> 
									<v-card-text style="height: 1px; position: relative" @click.stop="showCreateShift = true">
										<v-btn absolute dark fab top right color="blue" :disabled="shiftSubmitted">
											<v-icon>
												add
											</v-icon>
										</v-btn>
									</v-card-text>
								</div>

								<!-- bottom bar for submission (footer) -->
								<div id="submit-bar">
									<v-card class="text-xs-center" dark color="white" >

										<v-card-actions>
											<v-btn dark color="blue" block @click.stop="saveLocallyConfirmation = true" :disabled="shiftSubmitted">
												Save Locally
											</v-btn>

											<v-btn dark color="red" block @click.stop="deleteLocallyConfirmation = true" :disabled="shiftSubmitted">
												Delete Locally
											</v-btn>

											<v-btn color="primary" block @click.stop="showConfirmSubmission = true" :disabled="shiftSubmitted">
												Submit
											</v-btn>
										</v-card-actions>
									</v-card>
								</div>

							</div>
						</v-layout>
					</div>
			

					<!-- popup editor for clicking on shift, dragging event shift, clicking + button -->
					<v-layout row justify-center>
						<v-dialog v-model="showCreateShift" max-width="400px" lazy full-width>
							<v-card>
								<v-card-title>
									<span class="headline">Shift Info</span>
								</v-card-title>
								<v-card-text>
									<v-container grid-list-xs>
                                        <v-layout wrap>

                                            <!-- date  picker -->
                                            <v-flex xs6>
                                                <v-dialog v-model="dateStartModal" lazy full-width>
                                                    <v-text-field
                                                        slot="activator"
                                                        label="Date"
                                                        hint="Date of shift"
                                                        v-model="shift.date"
                                                        prepend-icon="event"
                                                        readonly
                                                        required
                                                        persistent-hint
                                                        :disabled="shiftSubmitted">
                                                    </v-text-field>
                                                    <v-date-picker
                                                        v-model="shift.date"
                                                        first-day-of-week="1">
                                                        <template scope="{ save, cancel }">
                                                            <v-card-actions>
                                                                <v-btn flat color="primary" @click="save" :disabled="shiftSubmitted">
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


                                            <!-- time start picker -->
                                            <v-flex xs6s>
                                                <v-dialog v-model="timeStartModal" lazy full-width>
                                                    <v-text-field
                                                        slot="activator"
                                                        label="Time Start"
                                                        hint="Start time of shift"
                                                        v-model="shift.timeStart"
                                                        prepend-icon="access_time"
                                                        readonly
                                                        required
                                                        persistent-hint
                                                        :disabled="shiftSubmitted">
                                                    </v-text-field>
                                                    <v-time-picker
                                                        v-model="shift.timeStart"
                                                        :allowed-hours="allowedTimes.hours"
                                                        :allowed-minutes="allowedTimes.minutes"
                                                        actions>
                                                        <template scope="{ save, cancel }">
                                                            <v-card-actions>
                                                                <v-btn flat color="primary" @click="save" :disabled="shiftSubmitted">
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


                                            <!-- time end picker -->
                                            <v-flex xs6>
                                                <v-dialog v-model="timeEndModal" lazy full-width>
                                                    <v-text-field
                                                        slot="activator"
                                                        label="Time End"
                                                        hint="Time start of shift"
                                                        v-model="shift.timeEnd"
                                                        prepend-icon="access_time"
                                                        readonly
                                                        required
                                                        persistent-hint
                                                        :disabled="shiftSubmitted">
                                                    </v-text-field>
                                                    <v-time-picker
                                                        v-model="shift.timeEnd"
                                                        :allowed-hours="allowedTimes.hours"
                                                        :allowed-minutes="allowedTimes.minutes"
                                                        actions>
                                                        <template scope="{ save, cancel }">
                                                            <v-card-actions>
                                                                <v-btn flat color="primary" @click="save" :disabled="shiftSubmitted">
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

                                            <!-- Campus -->
                                            <v-flex xs6>
                                                <v-select
                                                    v-bind:items="campusList"
                                                    v-model="shift.campus"
                                                    label="Campus"
                                                    hint="Campus of shift"
                                                    item-value="text"
                                                    single-line
                                                    required
                                                    persistent-hint
                                                    :disabled="shiftSubmitted">
                                                </v-select>
                                            </v-flex>

                                        </v-layout>
									</v-container>
								</v-card-text>
								<v-card-actions>
									<v-spacer></v-spacer>
									<v-btn color="primary" flat @click.stop="shiftCreation(shift)" :disabled="shiftSubmitted, showEditShift">
										Create
									</v-btn>
									<v-btn color="primary" flat @click.stop="showCreateShift = false">
										Close
									</v-btn>
								</v-card-actions>
							</v-card>
						</v-dialog>
					</v-layout>


					<!-- popup editor for clicking on shift, dragging event shift, clicking + button -->
					<v-layout row justify-center>
						<v-dialog v-model="showEditShift" max-width="400px" lazy full-width>
							<v-card>
								<v-card-title>
									<span class="headline">Shift Info</span>
								</v-card-title>
								<v-card-text>
									<v-container grid-list-xs>
                                        <v-layout wrap>

                                            <!-- date  picker -->
                                            <v-flex xs6>
                                                <v-dialog v-model="dateStartModal" lazy full-width>
                                                    <v-text-field
                                                        slot="activator"
                                                        label="Date"
                                                        hint="Date of shift"
                                                        v-model="shift.date"
                                                        prepend-icon="event"
                                                        readonly
                                                        required
                                                        persistent-hint
                                                        :disabled="shiftSubmitted">
                                                    </v-text-field>
                                                    <v-date-picker
                                                        v-model="shift.date"
                                                        first-day-of-week="1">
                                                        <template scope="{ save, cancel }">
                                                            <v-card-actions>
                                                                <v-btn flat color="primary" @click="save" :disabled="shiftSubmitted">
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

                                            <!-- time start picker -->
                                            <v-flex xs6s>
                                                <v-dialog v-model="timeStartModal" lazy full-width>
                                                    <v-text-field
                                                        slot="activator"
                                                        label="Time Start"
                                                        hint="Start time of shift"
                                                        v-model="shift.timeStart"
                                                        prepend-icon="access_time"
                                                        readonly
                                                        required
                                                        persistent-hint
                                                        :disabled="shiftSubmitted">
                                                    </v-text-field>
                                                    <v-time-picker
                                                        v-model="shift.timeStart"
                                                        :allowed-hours="allowedTimes.hours"
                                                        :allowed-minutes="allowedTimes.minutes"
                                                        actions>
                                                        <template scope="{ save, cancel }">
                                                            <v-card-actions>
                                                                <v-btn flat color="primary" @click="save" :disabled="shiftSubmitted">
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

                                            <!-- time end picker -->
                                            <v-flex xs6>
                                                <v-dialog v-model="timeEndModal" lazy full-width>
                                                    <v-text-field
                                                        slot="activator"
                                                        label="Time End"
                                                        hint="Time start of shift"
                                                        v-model="shift.timeEnd"
                                                        prepend-icon="access_time"
                                                        readonly
                                                        required
                                                        persistent-hint
                                                        :disabled="shiftSubmitted">
                                                    </v-text-field>
                                                    <v-time-picker
                                                        v-model="shift.timeEnd"
                                                        :allowed-hours="allowedTimes.hours"
                                                        :allowed-minutes="allowedTimes.minutes"
                                                        actions>
                                                        <template scope="{ save, cancel }">
                                                            <v-card-actions>
                                                                <v-btn flat color="primary" @click="save" :disabled="shiftSubmitted">
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

                                            <!-- Campus -->
                                            <v-flex xs6>
                                                <v-select
                                                    v-bind:items="campusList"
                                                    v-model="shift.campus"
                                                    label="Campus"
                                                    hint="Campus of shift"
                                                    item-value="text"
                                                    single-line
                                                    required
                                                    persistent-hint
                                                    :disabled="shiftSubmitted">
                                                </v-select>
                                            </v-flex>

                                        </v-layout>
									</v-container>
								</v-card-text>
								<v-card-actions>
									<v-spacer></v-spacer>
									<v-btn color="primary" flat @click.stop="shiftEdit(shift, currentSelectedEvent)" :disabled="shiftSubmitted">
										Edit
									</v-btn>
									<v-btn color="primary" flat @click.stop="shiftDeletion(shift, currentSelectedEvent)" :disabled="shiftSubmitted">
										Delete
									</v-btn>
									<v-btn color="primary" flat @click.stop="showEditShift = false">
										Close
									</v-btn>
								</v-card-actions>
							</v-card>
						</v-dialog>
					</v-layout>


					<!-- popup for when save locally is clicked -->
					<v-dialog v-model="saveLocallyConfirmation">
						<v-card>
							<v-container grid-list-md>
								<v-layout wrap>
									<v-card-text>
										<v-flex xs12>
											<v-card-text class="text-xs-center">
												Confirm Save of shift Shift locally?
											</v-card-text>
										</v-flex>
									</v-card-text>
								</v-layout wrap>
							</v-container grid-list-md>
							<v-card-actions>
								<v-btn color="primary" block @click.stop="saveShiftListLocally">
									Save
								</v-btn>
								<v-spacer></v-spacer>
								<v-btn color="primary" block @click.stop="saveLocallyConfirmation = false">
									Cancel
								</v-btn>
							</v-card-actions>
						</v-card>
					</v-dialog>


					<!-- popup for when delete locally is clicked -->
					<v-dialog v-model="deleteLocallyConfirmation">
						<v-card>
							<v-container grid-list-md>
								<v-layout wrap>
									<v-card-text>
										<v-flex xs12>
											<v-card-text class="text-xs-center">
												Confirm Deletion of shift Shift locally?
											</v-card-text>
										</v-flex>
									</v-card-text>
								</v-layout wrap>
							</v-container grid-list-md>
							<v-card-actions>
								<v-btn color="primary" flat @click.stop="deleteShiftListLocally">
									Delete
								</v-btn>
								<v-spacer></v-spacer>
								<v-btn color="primary" flat @click.stop="deleteLocallyConfirmation = false">
									Cancel
								</v-btn>
							</v-card-actions>
						</v-card>
					</v-dialog>


					<!-- popup for when submitshift is clicked -->
					<v-dialog v-model="showConfirmSubmission">
						<v-card>
							<v-container grid-list-md>
								<v-layout wrap>
									<v-card-text>
										<v-flex xs12>
											<v-card-text class="text-xs-center">
												Confirm Submission of Shift?
											</v-card-text>
										</v-flex>
									</v-card-text>
								</v-layout wrap>
							</v-container grid-list-md>
							<v-card-actions>
								<v-btn color="primary" flat @click.stop="shiftSubmitComplete">
									Submit
								</v-btn>
								<v-spacer></v-spacer>
								<v-btn color="primary" flat @click.stop="showConfirmSubmission = false">
									Cancel
								</v-btn>
							</v-card-actions>
						</v-card>
					</v-dialog>


				</v-flex>
			</v-layout>
		</v-container>
</template>


<script>
//imports
import Vue from 'vue'
import moment from 'moment'
import axios from 'axios'

//calendar
import FullCalendar from 'vue-full-calendar'
Vue.use(FullCalendar) // add the vue-full-calendar plugin to Vue
window.jQuery = window.$ = require('jquery') // we need jquery too

export default {
    data: function () {
		return {

			//------------------ options -------------------------------
			saveLocallyConfirmation: false,
			deleteLocallyConfirmation: false,

			// ----------------- MODALS --------------------------------
			//creation of shift window
			showCreateShift: false,

			//edit of shift window
			showEditShift: false,

			// ----------------- Submission ---------------------------
			//boolean for if shift is submitted
			shiftSubmitted: false,

			//submission shift popup
			showConfirmSubmission: false,

			//---------------- Time, Date ----------------------------
			//time picker/date picker modals
			dateStartModal: false,
			timeStartModal: false,
			timeEndModal: false,

            //restrict it to 15 minutes increments, do not change hours
            allowedTimes: {
				hours: null,
				minutes: null,
            },
            
			restrictTimeIncrements: {
				hours: function(value) {
					return value
				},
				minutes: function(value) {
					return value % 15 === 0
				}
			},

            //-------------------- Campus selection -----------------------
            
			//for selection of campus
			campusList: [
				{ text: 'Burnaby'},
				{ text: 'Surrey'},
				{ text: 'Vancouver'},
			],

			//---------------- shift type and list ------------------
			//current event
			currentSelectedEvent: null,

			//Data contains list of avaliablities
			shiftList: [
			],

			shift: {
				title: "",
				date: this.getTodayMoment(),
				timeStart: this.getTodayMomentTime(0),
				timeEnd: this.getTodayMomentTime(60),
				campus: null,
			},

			//--------------- calendar config ------------------------------
			config: {

				// ------------ calendar view ----------------------
				defaultView: 'agendaWeek',
				height: 'parent',
				allDaySlot: false,
				timezone: 'local',
		
				header: {
					left: 'prev,today, next,',
					center: 'title',
					right: 'month,agendaWeek,agendaDay'
				},

				//15 minute increment
				slotDuration: '00:15:00',

				// scroll calendar to the specified time (12 AM)
				scrollTime: '00:00:00',

				//shows today
				nowIndicator: true,

				//restrict calendar view to next week only

				//sets first day of week to monday
				firstDay: 1,

				//sets week number calculation to ISO
				weekNumberCalculation: "ISO",

				//----------------- selection of events --------------------------
				//allows resizing of events
				editable: false,

				//allows dragging on calendar.
				selectable: true,

				//allows to make an event on calendar
				selectHelper: true,

				//do allow event overlap selection
				selectOverlap: true,

				//triggered with an event is clicked
				eventClick: this.shiftClick,

				//triggered after a selection is made, i.e user stops dragging.
				select: this.shiftSelection,

				//minimum distance click has to move inorder to detect as event PREVENT MISCLICKS
				selectMinDistance: 5,

				//minimum miliseconds user holds down before it counts as a selectable
				selectLongPressDelay: 1000,

				//restrict selection of an event to maximxum one day
				selectConstraint: {
					start: "00:00",
					end: "24:00"
				},

				//restrict event to maximum one day
				eventConstraint: {
					start: "00:00",
					end: "24:00",
				},
			},

			eventSources: [
				{	
					
				}
			],

			clickedShift: {
			},
		}
	},

	computed: {
	},


	methods: {

        //handles user clicking on event
		shiftClick: function(event, jsEvent, view) {
			//todo loads shift before displaying

			console.log("This event title: " + event.title + "\n" +
					 "time start: " + event.start + "\n" +
					 "time end: " + event.end + "\n" 
			);

			//todo able to edit shift
			this.clickedShift = event;

			//set currently selected shift to this event's data
			this.shift.title = event.title;
			this.shift.date = moment(event.start, ["YYYY-MM-DD"]).format("YYYY-MM-DD");
			this.shift.timeStart = moment(event.start, ["HH:mma"]).format("HH:mma");
			this.shift.timeEnd = moment(event.end, ["HH:mma"]).format("HH:mma");
			//this.shift.campus =;

			this.currentSelectedEvent = event;

			this.showEditShift = true;
		},	

		//handles drag selection of shift on calendar
		shiftSelection: function(start, end, jsEvent, view) {
			console.log("click event")
			//only allows deletion before submission
			if (this.shiftSubmitted == false) {

				//set the start and end of this shift selection
				this.shift.date = start.format("YYYY-MM-DD");
				this.shift.timeStart = start.format("HH:mma");
				this.shift.timeEnd = end.format("HH:mma");

				//show edit avaialbility
				this.showCreateShift = true;
			}
			else {
				//do nothing as submission is denied after submission of shift
			}
		},

		//todo handle creation of an shift
        shiftCreation: function(shift) {

			//combine the moment objects with date and time
			var momentStartObj = moment(shift.date + " " + shift.timeStart, ["YYYY-MM-DD HH:mma"]);
			var momentEndObj = moment(shift.date + " " + shift.timeEnd, ["YYYY-MM-DD HH:mma"]);

			//create new object for event with the newly created moment objects
			var eventItem = {
				id: -1,
				start: momentStartObj,
				end: momentEndObj,
				title: shift.campus,
			}
			//change color for event based on campus
			//red for burnaby
			// if(shift.campus == "Burnaby") {
			// 	eventItem.color = "red";
			// }
			// //blue for surrey
			// else if(shift.campus == "Surrey") {
			// 	eventItem.color = "blue";
			// }
			// //Yellow for vancouver
			// else if(shift.campus == "Vancouver") {
			// 	eventItem.color = "teal";
			// }
			// //purple if not specified -> for debug
			// else {
			// 	eventItem.color = "purple";
			// }

			//change textcolor
			eventItem.textColor = "white"

			//render event
			$('#calendar').fullCalendar('renderEvent', eventItem, true);

            //close window
			this.showCreateShift = false;
		},

		//todo handle creation of an shift
        shiftEdit: function(shift, event) {

			//combine the moment objects with date and time
			var momentStartObj = moment(shift.date + " " + shift.timeStart, ["YYYY-MM-DD HH:mma"]);
			var momentEndObj = moment(shift.date + " " + shift.timeEnd, ["YYYY-MM-DD HH:mma"]);

			event.start = momentStartObj;
			event.end = momentEndObj;
			event.title = shift.campus;
			//change color for event based on campus
			//red for burnaby
			// if(shift.campus == "Burnaby") {
			// 	eventItem.color = "red";
			// }
			// //blue for surrey
			// else if(shift.campus == "Surrey") {
			// 	eventItem.color = "blue";
			// }
			// //Yellow for vancouver
			// else if(shift.campus == "Vancouver") {
			// 	eventItem.color = "teal";
			// }
			// //purple if not specified -> for debug
			// else {
			// 	eventItem.color = "purple";
			// }


			//change text color for white
			event.textColor = "white"

			 $('#calendar').fullCalendar('updateEvent', event);

			//close window
			this.showEditShift = false;
		},

		//Deletes the event if button for deletion is pressed
		shiftDeletion: function(shift, event) {
			//TODO deleting an event

			//delete event
			// this.$refs.calendar.$emit('remove-event', event);
			// this.clickedShift = {};

			//delete event
			// console.log("destroying event: " + event);
			// $('#calendar').fullCalendar('eventDestroy', event);

			//turn off popup after deletion
			this.showEditShift = false;
		},

		//blocks out most user interaction when shift is submitted
		shiftSubmitComplete: function() {

			this.getShiftList();

			//disables resizing of events, editing of events, selection of new events
			$('#calendar').fullCalendar('editable', false);
			$('#calendar').fullCalendar('selectable', false);
			$('#calendar').fullCalendar('selectHelper', false);

            //set values
            this.shiftStatus = "Pending";
			this.shiftSubmitted = true;

			//close popup
            this.showConfirmSubmission = false;

		},


        getTodayDateObj: function() {
            var currentDate = new Date(); 
            return currentDate;
        },

        getTodayMoment: function() {
            var currentMoment = moment().startOf('isoweek').format("YYYY-MM-DD"); 
            return currentMoment;
        },

		getTodayMomentTime: function(addedTime) {
			//round each moment object to nearest 15 minute increments
			var roundMinutes = moment().minutes();
			var remainder = 15 - (roundMinutes % 15);
			var roundedTime = moment().add("minutes", (remainder + addedTime)).format("LT");
			return  roundedTime;
		},


		initializeCalendarView() {
            
            //set calendar to go to a particular date
			//$('#calendar').fullCalendar('gotoDate', this.getTodayMoment());

			//constrain calendar view 
			// $('#calendar').fullCalendar({
			// 	validRange: {
			// 		start: this.getTodayMomentTime(),
			// 	}
			// });
		},

		populateShiftList: function() {
			//grab all generated client events
			this.shiftList = $('#calendar').fullCalendar('clientEvents');
			console.log("list of events: " + this.shiftList);
			
			//this.shiftList = response.data;
		},


		getShiftList: function() {
			this.shiftList = $('#calendar').fullCalendar('clientEvents');
			console.log("list of events: " + this.shiftList);
			return this.shiftList;
		},


		saveShiftListLocally: function() {

		},

		deleteShiftListLocally: function() {

		},


		populateShiftListFromDatabase: function() {

		},


        //init existing shift list
		initializeShiftList: function() {
				axios.post('/api/shift', )
				.then(response => this.requestShift)
				.catch(function (error) {
					console.log(error);
				});
		},

		requestShift() {
			var shiftURL = '';
			shiftURL = '/api/shift'
		}

	},

    components: {
	},

	//todo creates to shift  database
	created: function() {
		axios.get('api/shift')
			.then(this.populateShiftList)
			.catch(function (error) {
			console.log(error);
		});
	},



	mounted () {

		//initialize calendar view
		this.initializeCalendarView();

		//set the time restrictions
		this.allowedTimes = this.restrictTimeIncrements
	}
}

</script>

<style scoped lang="stylus">
@import '../../node_modules/fullcalendar/dist/fullcalendar.css';
@import '../stylus/main';

#Manage-Schedule {
	background: white;
	width: 100%;
	height: 100%;
	//display: flex;
	//flex-flow: row nowrap;
}

#option-bar {
	width: 35%;
	height: 100%;
	background: white;
}

#content {
	width: 100%;
	height: 100%;
}

#calendar-pkg {
	width: 65%;
	height: 90%;
}

#submit-bar {
	width: 100%;
	height: 4em;
}

#my-calendar {
    width: 100%
	height: 100%;
}

#add-button {
	height: 0.5em;
}


</style>