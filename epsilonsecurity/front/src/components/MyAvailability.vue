<template>
		<v-container fluid fill-width fill-height class="pa-0">
			<v-layout column wrap>
				<v-flex xs12>
			
					<!-- toast for submitted, creating /editing availability -->
					<v-snackbar :timeout="1000" :top="'top'" v-model="showToast">
						<v-btn flat color="white" class="text-xs-center"> {{ toastMsg }} </v-btn>
					</v-snackbar>


										
					<div id = "center-content">

						<!-- submit availability status bar (Header) -->
						<div id = "approval-bar">
							<!-- Primary background if approved -->
							<span v-if="availabilityStatus == 'Open'">
								<v-card dark color="blue" >
									<v-card-text class="text-xs-center"> 
										Status: 
										{{ availabilityStatus }} 
									</v-card-text>
								</v-card>
							</span>

							<!-- Red background if Unapproved -->
							<span label v-else-if="availabilityStatus == 'Pending'">
								<v-card dark color="red" >
									<v-card-text class="text-xs-center"> 
										Status: 
										{{ availabilityStatus }} 
									</v-card-text>
								</v-card>
							</span>

							<!-- Green background if approved -->
							<span v-else-if="availabilityStatus == 'Approved'">
								<v-card dark color="green" >
									<v-card-text class="text-xs-center"> 
										Status: 
										{{ availabilityStatus }} 
									</v-card-text>
								</v-card>
							</span>
							
							<!-- Primary background if Error // probably no need for this -->
							<span v-else>
								<v-card dark color="primary" >
									<v-card-text class="text-xs-center"> 
										Status: 
										{{ availabilityStatus }} 
									</v-card-text>
								</v-card>
							</span>
						</div>

						<div id = "my-calendar">
							<!-- calendar -->
							<full-calendar
								:event-sources="eventSources"
								:config="config"
								ref="calendar"
								id="calendar">
							</full-calendar>
						</div>
							

						<!-- floating add availbility button printDayWeek showCreateAvailability = true -->
						<div id="add-button"> 
							<v-card-text style="height: 1px; position: relative" @click.stop="showCreateAvailability = true">
								<v-btn absolute dark fab top right color="blue" :disabled="availabilitySubmitted">
									<v-icon>
										add
									</v-icon>
								</v-btn>
							</v-card-text>
						</div>


						<!-- bottom bar for submission (footer) -->
						<div id="option-bar">
							<v-card class="text-xs-center" dark color="white" >

								<v-card-actions>
									<v-btn dark color="blue" block @click.stop="saveLocallyConfirmation = true" :disabled="availabilitySubmitted">
										Save Locally
									</v-btn>

									<v-btn dark color="red" block @click.stop="deleteLocallyConfirmation = true" :disabled="availabilitySubmitted">
										Delete Locally
									</v-btn>

									<v-btn color="primary" block @click.stop="showConfirmSubmission = true" :disabled="availabilitySubmitted">
										Submit
									</v-btn>
								</v-card-actions>
							</v-card>
						</div>
					</div>

					<!-- popup editor for clicking on availability, dragging event availability, clicking + button -->
					<v-layout row justify-center>
						<v-dialog v-model="showCreateAvailability" max-width="400px" persistent lazy full-width>
							<v-card>
								<v-card-title>
									<span class="headline">Availability Info</span>
								</v-card-title>
								<v-card-text>
									<v-container grid-list-xs>
                                        <v-layout wrap>

                                            <!-- date  picker -->
                                            <v-flex xs6>
                                                <v-dialog v-model="dateStartModal" persistent lazy full-width>
                                                    <v-text-field
                                                        slot="activator"
                                                        label="Date"
                                                        hint="Date of availability"
                                                        v-model="availability.date"
                                                        prepend-icon="event"
                                                        readonly
                                                        required
                                                        persistent-hint
                                                        :disabled="availabilitySubmitted">
                                                    </v-text-field>
                                                    <v-date-picker
                                                        v-model="availability.date"
                                                        first-day-of-week="1"
                                                        :allowed-dates="allowedDates"
                                                    >
                                                        <template scope="{ save, cancel }">
                                                            <v-card-actions>
                                                                <v-btn flat color="primary" @click="save" :disabled="availabilitySubmitted">
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
                                                <v-dialog v-model="timeStartModal" persistent lazy full-width>
                                                    <v-text-field
                                                        slot="activator"
                                                        label="Time Start"
                                                        hint="Start time of availability"
                                                        v-model="availability.timeStart"
                                                        prepend-icon="access_time"
                                                        readonly
                                                        required
                                                        persistent-hint
                                                        :disabled="availabilitySubmitted">
                                                    </v-text-field>
                                                    <v-time-picker
                                                        v-model="availability.timeStart"
                                                        :allowed-hours="allowedTimes.hours"
                                                        :allowed-minutes="allowedTimes.minutes"
                                                        actions>
                                                        <template scope="{ save, cancel }">
                                                            <v-card-actions>
                                                                <v-btn flat color="primary" @click="save" :disabled="availabilitySubmitted">
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
                                                <v-dialog v-model="timeEndModal" persistent lazy full-width>
                                                    <v-text-field
                                                        slot="activator"
                                                        label="Time End"
                                                        hint="Time start of availability"
                                                        v-model="availability.timeEnd"
                                                        prepend-icon="access_time"
                                                        readonly
                                                        required
                                                        persistent-hint
                                                        :disabled="availabilitySubmitted">
                                                    </v-text-field>
                                                    <v-time-picker
                                                        v-model="availability.timeEnd"
                                                        :allowed-hours="allowedTimes.hours"
                                                        :allowed-minutes="allowedTimes.minutes"
                                                        actions>
                                                        <template scope="{ save, cancel }">
                                                            <v-card-actions>
                                                                <v-btn flat color="primary" @click="save" :disabled="availabilitySubmitted">
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
                                                    v-model="availability.campus"
                                                    label="Campus"
                                                    hint="Campus of availability"
                                                    item-value="text"
                                                    single-line
                                                    required
                                                    persistent-hint
                                                    :disabled="availabilitySubmitted">
                                                </v-select>
                                            </v-flex>

                                        </v-layout>
									</v-container>
								</v-card-text>
								<v-card-actions>
									<v-spacer></v-spacer>
									<v-btn color="primary" flat @click.stop="availabilityCreation(availability)" :disabled="availabilitySubmitted, showEditAvailability">
										Create
									</v-btn>
									<v-btn color="primary" flat @click.stop="showCreateAvailability = false">
										Close
									</v-btn>
								</v-card-actions>
							</v-card>
						</v-dialog>
					</v-layout>


					<!-- popup editor for clicking on availability, dragging event availability, clicking + button -->
					<v-layout row justify-center>
						<v-dialog v-model="showEditAvailability" max-width="400px" persistent lazy full-width>
							<v-card>
								<v-card-title>
									<span class="headline">Availability Info</span>
								</v-card-title>
								<v-card-text>
									<v-container grid-list-xs>
                                        <v-layout wrap>

                                            <!-- date  picker -->
                                            <v-flex xs6>
                                                <v-dialog v-model="dateStartModal" persistent lazy full-width>
                                                    <v-text-field
                                                        slot="activator"
                                                        label="Date"
                                                        hint="Date of availability"
                                                        v-model="availability.date"
                                                        prepend-icon="event"
                                                        readonly
                                                        required
                                                        persistent-hint
                                                        :disabled="availabilitySubmitted"
                                                    >
                                                    </v-text-field>
                                                    <v-date-picker
                                                        v-model="availability.date"
                                                        first-day-of-week="1"
                                                        :allowed-dates="allowedDates"
                                                    >
                                                        <template scope="{ save, cancel }">
                                                            <v-card-actions>
                                                                <v-btn flat color="primary" @click="save" :disabled="availabilitySubmitted">
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
                                                <v-dialog v-model="timeStartModal" persistent lazy full-width>
                                                    <v-text-field
                                                        slot="activator"
                                                        label="Time Start"
                                                        hint="Start time of availability"
                                                        v-model="availability.timeStart"
                                                        prepend-icon="access_time"
                                                        readonly
                                                        required
                                                        persistent-hint
                                                        :disabled="availabilitySubmitted">
                                                    </v-text-field>
                                                    <v-time-picker
                                                        v-model="availability.timeStart"
                                                        :allowed-hours="allowedTimes.hours"
                                                        :allowed-minutes="allowedTimes.minutes"
                                                        actions>
                                                        <template scope="{ save, cancel }">
                                                            <v-card-actions>
                                                                <v-btn flat color="primary" @click="save" :disabled="availabilitySubmitted">
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
                                                <v-dialog v-model="timeEndModal" persistent lazy full-width>
                                                    <v-text-field
                                                        slot="activator"
                                                        label="Time End"
                                                        hint="Time start of availability"
                                                        v-model="availability.timeEnd"
                                                        prepend-icon="access_time"
                                                        readonly
                                                        required
                                                        persistent-hint
                                                        :disabled="availabilitySubmitted">
                                                    </v-text-field>
                                                    <v-time-picker
                                                        v-model="availability.timeEnd"
                                                        :allowed-hours="allowedTimes.hours"
                                                        :allowed-minutes="allowedTimes.minutes"
                                                        actions>
                                                        <template scope="{ save, cancel }">
                                                            <v-card-actions>
                                                                <v-btn flat color="primary" @click="save" :disabled="availabilitySubmitted">
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
                                                    v-model="availability.campus"
                                                    label="Campus"
                                                    hint="Campus of availability"
                                                    item-value="text"
                                                    single-line
                                                    required
                                                    persistent-hint
                                                    :disabled="availabilitySubmitted">
                                                </v-select>
                                            </v-flex>

                                        </v-layout>
									</v-container>
								</v-card-text>
								<v-card-actions>
									<v-spacer></v-spacer>
									<v-btn color="primary" flat @click.stop="availabilityEdit(availability, currentSelectedEvent)" :disabled="availabilitySubmitted">
										Edit
									</v-btn>
									<v-btn color="primary" flat @click.stop="availabilityDeletion(availability, currentSelectedEvent)" :disabled="availabilitySubmitted">
										Delete
									</v-btn>
									<v-btn color="primary" flat @click.stop="showEditAvailability = false">
										Close
									</v-btn>
								</v-card-actions>
							</v-card>
						</v-dialog>
					</v-layout>

					<!-- popup for when save locally is clicked -->
					<v-dialog v-model="saveLocallyConfirmation" persistent lazy full-width>
						<v-card>
							<v-container grid-list-md>
								<v-layout wrap>
									<v-card-text>
										<v-flex xs12>
											<v-card-text class="text-xs-center">
												Confirm Save of availability Schedule locally?
											</v-card-text>
										</v-flex>
									</v-card-text>
								</v-layout wrap>
							</v-container grid-list-md>
							<v-card-actions>
								<v-btn color="primary" block @click.stop="saveAvailabilityListLocally">
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
					<v-dialog v-model="deleteLocallyConfirmation" persistent lazy full-width>
						<v-card>
							<v-container grid-list-md>
								<v-layout wrap>
									<v-card-text>
										<v-flex xs12>
											<v-card-text class="text-xs-center">
												Confirm Deletion of availability Schedule locally?
											</v-card-text>
										</v-flex>
									</v-card-text>
								</v-layout wrap>
							</v-container grid-list-md>
							<v-card-actions>
								<v-btn color="primary" flat @click.stop="deleteAvailabilityListLocally">
									Delete
								</v-btn>
								<v-spacer></v-spacer>
								<v-btn color="primary" flat @click.stop="deleteLocallyConfirmation = false">
									Cancel
								</v-btn>
							</v-card-actions>
						</v-card>
					</v-dialog>

					<!-- popup for when submit availbility is clicked -->
					<v-dialog v-model="showConfirmSubmission" persistent lazy full-width>
						<v-card>
							<v-container grid-list-md>
								<v-layout wrap>
									<v-card-text>
										<v-flex xs12>
											<v-card-text class="text-xs-center">
												Confirm Submission of Availability Schedule?
											</v-card-text>
										</v-flex>
									</v-card-text>
								</v-layout wrap>
							</v-container grid-list-md>
							<v-card-actions>
								<v-btn color="primary" flat @click.stop="availabilitySubmitComplete">
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
			//creation of availability window
			showCreateAvailability: false,

			//edit of availability window
			showEditAvailability: false,

			//status of availability
			availabilityStatus: "Open",

			// ----------------- Submission ---------------------------
			//boolean for if avail is submitted
			availabilitySubmitted: false,

			//submission availabiliy popup
			showConfirmSubmission: false,

			// ----------------- TOAST MSG ---------------------------
			//toast msg
			toastMsg: "Nothing here",
			showToast: false,

			//---------------- Time, Date ----------------------------
			//time picker/date picker modals
			dateStartModal: false,
			timeStartModal: false,
			timeEndModal: false,

			visibleRange: {
                start: this.getNextWeekMonday(),
            	end: this.getNextWeekSunday(),
            },


			//ranged of allowed dates for date picker
			allowedDates: null,

			//restrict it to allowed days from min to max
			nextWeekRange: {
					min: null,
					max: null,
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

			//-------------------- Campus selection -----------------------
			//for selection of campus
			campusList: [
				{ text: 'Burnaby'},
				{ text: 'Surrey'},
				{ text: 'Vancouver'},
			],

			//---------------- availability type and list ------------------
			//current event
			currentSelectedEvent: null,

			//Data contains list of avaliablities
			availabilityList: [
			],

			availability: {
				title: "",
				date: this.getNextWeekMonday(),
				timeStart: this.getTodayMomentTime(0),
				timeEnd: this.getTodayMomentTime(60),
				campus: "Burnaby"
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

				//do not allow event overlap selection
				selectOverlap: false,

				//triggered with an event is clicked
				eventClick: this.availabilityClick,

				//triggered after a selection is made, i.e user stops dragging.
				select: this.availabilitySelection,

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
					//todo add events
				}
			],

			clickedAvailability: {
			},
		}
	},

	computed: {
	},

	methods: {

        //handles user clicking on event
		availabilityClick: function(event, jsEvent, view) {
			//todo loads availbility before displaying

			console.log("This event title: " + event.title + "\n" +
					 "time start: " + event.start + "\n" +
					 "time end: " + event.end + "\n" 
			);

			//todo able to edit availability
			this.clickedAvailability = event;

			//set currently selected availability to this event's data
			this.availability.title = event.title;
			this.availability.date = moment(event.start, ["YYYY-MM-DD"]).format("YYYY-MM-DD");
			this.availability.timeStart = moment(event.start, ["HH:mma"]).format("HH:mma");
			this.availability.timeEnd = moment(event.end, ["HH:mma"]).format("HH:mma");
			this.availability.campus = event.title;

			console.log("Avail title " + this.availability.title + "\n" +
					"date: " + this.availability.date + "\n" +
					 "time start: " + this.availability.timeStart + "\n" +
					 "time end: " + this.availability.timeEnd + "\n" +
					 "campus: " + this.availability.campus
			);

			this.currentSelectedEvent = event;

			this.showEditAvailability = true;
		},	

		//handles drag selection of availability on calendar
		availabilitySelection: function(start, end, jsEvent, view) {
			console.log("click event")
			//only allows deletion before submission
			if (this.availabilitySubmitted == false) {

				//set the start and end of this availability selection
				this.availability.date = start.format("YYYY-MM-DD");
				this.availability.timeStart = start.format("HH:mma");
				this.availability.timeEnd = end.format("HH:mma");

				//show edit avaialbility
				this.showCreateAvailability = true;
			}
			else {
				//do nothing as submission is denied after submission of availability
			}
		},

		//todo handle creation of an availability
        availabilityCreation: function(availability) {
            //debug print
			console.log("New Avail date:", availability.date);
			console.log("New Avail time start:", availability.timeStart);
			console.log("New Avail time end:", availability.timeEnd);
            console.log("New Avail time campus:", availability.campus);
			
			//combine the moment objects with date and time
			var momentStartObj = moment(availability.date + " " + availability.timeStart, ["YYYY-MM-DD HH:mma"]);
			var momentEndObj = moment(availability.date + " " + availability.timeEnd, ["YYYY-MM-DD HH:mma"]);

			//create new object for event with the newly created moment objects
			var eventItem = {
				id: -1,
				start: momentStartObj,
				end: momentEndObj,
				title: availability.campus,
			}
			//change color for event based on campus
			//red for burnaby
			if(availability.campus == "Burnaby") {
				eventItem.color = "red";
			}
			//blue for surrey
			else if(availability.campus == "Surrey") {
				eventItem.color = "blue";
			}
			//Yellow for vancouver
			else if(availability.campus == "Vancouver") {
				eventItem.color = "teal";
			}
			//purple if not specified -> for debug
			else {
				eventItem.color = "purple";
			}

			//change textcolor
			eventItem.textColor = "white"

			//render event
			$('#calendar').fullCalendar('renderEvent', eventItem, true);

            //close window
            this.showAvailabilityEditor = false;
			this.showCreateAvailability = false;

			//show msg that it has been created
			this.toastMsg = "Created Availability";
            this.showToast = true;
		},

		//todo handle creation of an availability
        availabilityEdit: function(availability, event) {

			//combine the moment objects with date and time
			var momentStartObj = moment(availability.date + " " + availability.timeStart, ["YYYY-MM-DD HH:mma"]);
			var momentEndObj = moment(availability.date + " " + availability.timeEnd, ["YYYY-MM-DD HH:mma"]);

			event.start = momentStartObj;
			event.end = momentEndObj;
			event.title = availability.campus;

			//change color for event based on campus
			//red for burnaby
			if(availability.campus == "Burnaby") {
				event.color = "red";
			}
			//blue for surrey
			else if(availability.campus == "Surrey") {
				event.color = "blue";
			}
			//Yellow for vancouver
			else if(availability.campus == "Vancouver") {
				event.color = "teal";
			}
			//purple if not specified for debug
			else {
				event.color = "purple";
			}

			//change text color for white
			event.textColor = "white"

			 $('#calendar').fullCalendar('updateEvent', event);

			//close window
			this.showEditAvailability = false;

			//show msg that it has been created
            this.toastMsg = "Edited Availability";
            this.showToast = true;
		},

		//Deletes the event if button for deletion is pressed
		availabilityDeletion: function(availability, event) {
			//TODO deleting an event

			//delete event
			// this.$refs.calendar.$emit('remove-event', event);
			// this.clickedAvailability = {};

			//delete event
			// console.log("destroying event: " + event);
			// $('#calendar').fullCalendar('eventDestroy', event);

			//turn off popup after deletion
			this.showEditAvailability = false;
		},

		//blocks out most user interaction when availability is submitted
		availabilitySubmitComplete: function() {

			this.getAvailabilityList();

			//disables resizing of events, editing of events, selection of new events
			$('#calendar').fullCalendar('editable', false);
			$('#calendar').fullCalendar('selectable', false);
			$('#calendar').fullCalendar('selectHelper', false);

            //set values
            this.availabilityStatus = "Pending";
			this.availabilitySubmitted = true;

			//close popup
            this.showConfirmSubmission = false;

			//toast msg
            this.toastMsg = "Submitted Availability";
            this.showToast = true;
		},

		getNextWeekMonday: function() {
			// //create a moment object that is the starting monday of the current day and next week
			var nextMondayDate = moment().startOf('isoweek').add(7, 'days').format("YYYY-MM-DD");
			return nextMondayDate;
		},

 
		getNextWeekSunday: function(){
			// //create a moment object that is the starting sunday of the current day and next week
			var nextSundayDate = moment().endOf('isoweek').add(7, 'days').format("YYYY-MM-DD");
			return nextSundayDate;
		},

		getNextWeekMondayDate: function() {
			var currentDate = new Date(); 

			//monday
			var firstDay = currentDate.getDate() - currentDate.getDay(); // First day is the day of the month - the day of the week 
			
			//set it to next week and add 1 as first day of week is sunday currently
			var mondayDate = new Date(currentDate.setDate(firstDay + 7 + 1)); 
			return mondayDate;
		},

		getNextWeekSundayDate: function() {
			var currentDate = new Date(); 

			//monday
			var firstDay = currentDate.getDate() - currentDate.getDay();

			//set it to sunday by adding 6 days to monday and + 1 for starting date of sunday to shift to monday
			//add 7 for next week
			var sundayDate = new Date(currentDate.setDate(firstDay + 6 + 7 + 1));
			return sundayDate;
		},

		getTodayMomentTime: function(addedTime) {
			//round each moment object to nearest 15 minute increments
			var roundMinutes = moment().minutes();
			var remainder = 15 - (roundMinutes % 15);
			var roundedTime = moment().add("minutes", (remainder + addedTime)).format("LT");
			return  roundedTime;
		},


		initializeCalendarView() {
			
			$('#calendar').fullCalendar('gotoDate', this.getNextWeekMonday());

			//constrain calendar view 
			$('#calendar').fullCalendar({
				validRange: {
					start: this.getNextWeekMonday(),
					end: this.getNextWeekSunday()
				}
			});

			// $('#calendar').fullCalendar({
			// 	validRange: function() {
			// 		return {
			// 			start: this.getNextWeekMondayDate(),
			// 			end: this.getNextWeekSundayDate()
			// 		};
			// 	}
			// });


			// //set visible dates for calendar
			// $('#calendar').fullCalendar({
			// 	visibleRange: function() {
			// 		return {
			// 			start: this.getNextWeekMondayDate(),
			// 			end: this.getNextWeekSundayDate()
			// 		};
			// 	}
			// });


		},

		populateAvailabilityList: function() {
			//grab all generated client events
			this.availabilityList = $('#calendar').fullCalendar('clientEvents');
			console.log("list of events: " + this.availabilityList);
			
			
			//this.availabilityList = response.data;
		},


		getAvailabilityList: function() {
			this.availabilityList = $('#calendar').fullCalendar('clientEvents');
			console.log("list of events: " + this.availabilityList);
			return this.availabilityList;
		},


		saveAvailabilityListLocally: function() {

		},

		deleteAvailabilityListLocally: function() {

		},


		populateAvailabilityListFromDatabase: function() {

		},


		initializeAvailabilitiesList: function() {
				axios.post('/api/onetimeavailabilites', )
				.then(response => this.requestAvailability)
				.catch(function (error) {
					console.log(error);
				});
		},

		requestAvailability() {
			var availabilityURL = '';
			availabilityURL = '/api/onetimeavailabilites'
		}

	},

    components: {
	},

	//todo creates to avail database
	created: function() {
		axios.get('api/onetimeavailabilites')
			.then(this.populateAvailabilityList)
			.catch(function (error) {
			console.log(error);
		});
	},



	mounted () {

		//initialize calendar view
		this.initializeCalendarView();

		//set the day restrictions
		this.nextWeekRange.min = this.getNextWeekMondayDate();
		this.nextWeekRange.max = this.getNextWeekSundayDate();
		this.allowedDates = this.nextWeekRange;

		//set the time restrictions
		this.allowedTimes = this.restrictTimeIncrements
	}
}

</script>

<style scoped lang="stylus">
@import '../../node_modules/fullcalendar/dist/fullcalendar.css';
@import '../stylus/main';

#my-availability {
	background: white;
	width: 100%;
	height: 100%;
	//display: flex;
	//flex-flow: column nowrap;
}

#center-content {
	width: 100%;
	height: 100%;
}

#approval-bar {
	height: 4em;
	width: 100%;
}

#my-calendar {
	height: 80%;
	width: 100%;
}

#add-button {
	height: 0.5em;
	width: 100%;
}

#option-bar {
	height: 4em;
}

</style>
