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
				<v-dialog v-model="showEditorAvailability" max-width="400px" persistent lazy full-width>
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
												:allowed-dates="allowedDates">
												<template scope="{ save, cancel }">
													<v-card-actions>
														<v-btn flat color="primary" @click="save">
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

							<v-card-actions>
								<v-spacer></v-spacer>
								<v-btn v-if="showCreateAvailability" color="primary" flat @click.stop="availabilityCreation(availability)" :disabled="availabilitySubmitted, showEditAvailability">
									Create
								</v-btn>
								<v-btn v-if="showEditAvailability"  color="primary" flat @click.stop="availabilityEdit(availability, currentSelectedEvent)" :disabled="availabilitySubmitted">
									Edit
								</v-btn>
								<v-btn color="primary" flat @click.stop="availabilityDeletion(availability, currentSelectedEvent)" :disabled="availabilitySubmitted">
									Delete
								</v-btn>
								<v-btn color="primary" flat @click.stop="showCreateAvailability = false">
									Close
								</v-btn>
							</v-card-actions>
						</v-card-text>
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
			// ----------------- TOAST MSG ---------------------------
			//toast msg
			toastMsg: "Nothing here",
			showToast: false,

			//------------------ options -------------------------------
			saveLocallyConfirmation: false,
			deleteLocallyConfirmation: false,

			// ----------------- MODALS --------------------------------
			//editor window trigger
			trigger: null,

			//editor window
			showEditorAvailability: false,

			//creation of availability window
			showCreateAvailability: false,

			//edit of availability window
			showEditAvailability: false,

			//status of availability
			availabilityStatus: null,

			// ----------------- Submission ---------------------------
			//boolean for if avail is submitted
			availabilitySubmitted: false,

			//submission availabiliy popup
			showConfirmSubmission: false,

			//---------------- Time, Date ----------------------------
			//time picker/date picker modals
			dateStartModal: false,
			timeStartModal: false,
			timeEndModal: false,
			timeBoundCheck: false,

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
			campusList: [],

			//---------------- availability type and list ------------------
			//current event
			currentSelectedEvent: null,

			//Data contains list of avaliablities
			availabilityList: [],

			availability: {
				title: "",
				date: this.getNextWeekMonday(),
				timeStart: this.getTodayMomentTime(0),
				timeEnd: this.getTodayMomentTime(60),
				campus: null,
			},

			//--------------- calendar config ------------------------------
			config: {
				defaultView: 'agendaWeek',
				height: 'parent',
				allDaySlot: false,
				timezone: 'local',

				header: {
					left: 'prev,today, next,',
					center: 'title',
					right: 'month,agendaWeek,agendaDay'
				},

				slotDuration: '00:15:00', //15 minute increment
				scrollTime: '00:00:00', // scroll calendar to the specified time (12 AM)
				nowIndicator: true, //shows today

				firstDay: 1, //sets first day of week to monday
				weekNumberCalculation: "ISO", //sets week number calculation to ISO

				//----------------- selection of events --------------------------

				editable: false, //allows resizing of events
				selectable: true, //allows dragging on calendar.
				selectHelper: true, //allows to make an event on calendar
				selectOverlap: false, //do not allow event overlap selection
				eventClick: this.availabilityClickEvent, //triggered with an event is clicked
				select: this.availabilityCreateSelect, //triggered after a selection is made, i.e user stops dragging.
				selectMinDistance: 5, //minimum distance click has to move inorder to detect as event PREVENT MISCLICKS
				selectLongPressDelay: 1000, //minimum miliseconds user holds down before it counts as a selectable

				selectConstraint: { //restrict selection of an event to maximxum one day
					start: "00:00",
					end: "24:00"
				},

				eventConstraint: { //restrict event to maximum one day
					start: "00:00",
					end: "24:00",
				},

				updateEvent: this.availabilityEdit,
				removeEvents: this.availabilityDeletion,
			},

			eventSources: [
				{
					//TODO add events
				}
			],

			clickedAvailability: {
			},
		}
	},

	computed: {
	},

	methods: {

		//------------------- calendar functions -----------------------

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


		//handles user clicking on event
		availabilityClickEvent: function(event, jsEvent, view) {

			//set currently selected availability to this event's data
			this.currentSelectedEvent = event;
			this.availability.title = event.title;
			this.availability.date = moment(event.start, ["YYYY-MM-DD"]).format("YYYY-MM-DD");
			this.availability.timeStart = moment(event.start, ["HH:mma"]).format("HH:mma");
			this.availability.timeEnd = moment(event.end, ["HH:mma"]).format("HH:mma");
			this.availability.campus = event.title;

			//show edit availability
			this.$emit('edit');
			this.showEditAvailability = true;
			this.showEditorAvailability = true;
		},



		//handles drag selection of availability on calendar
		availabilityCreateSelect: function(start, end, jsEvent, view) {
			console.log("click event")
			//only allows deletion before submission
			if (this.availabilitySubmitted == false) {

				//set the start and end of this availability selection
				this.availability.date = start.format("YYYY-MM-DD");
				this.availability.timeStart = start.format("HH:mma");
				this.availability.timeEnd = end.format("HH:mma");

				//show add avaialbility
				this.$emit('create');
				this.showCreateAvailability = true;
				this.showEditorAvailability = true;
			}
			else {
				//do nothing as submission is denied after submission of availability
			}
		},


		//check bounds on timepicker
		checkTimePickerBounds: function() {
			var testStartTime = moment(availability.timeStart);
			var testEndTime = moment(availability.timeEnd);

			if(testStartTime < testEndTime) {
				this.timeBoundCheck = true;
			}
			else {
				this.timeBoundCheck = false;
			}
		},

		//TODO compares the current availability to any preexisting availability if their time conflicts
		checkIfAvailabilityConflicts() {

		},

		//TODO handle creation of an availability
		availabilityCreation: function(availability) {

			//check if timeEnd is before timeStart
			if(!this.checkTimePickerBounds()) {

				//make a toast msg
				toastMsg: "Error, availability timeEnd is before availability timeStart";
				showToast: true;

				//do not close window until user fixes their error
				this.showCreateAvailability = true;
			}
			//TODO check if the new availability conflicts with an existing availability
			// else if() {
			//
			// }

			//all conditions passed, create the new availability
			else {
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
				if(availability.campus == 1) {
					eventItem.color = "red";
				}
				//blue for surrey
				else if(availability.campus == 2) {
					eventItem.color = "blue";
				}
				//Yellow for vancouver
				else if(availability.campus == 3) {
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
				toastMsg: "Created Availability!";
				showToast: true;
				this.showCreateAvailability = false;
			}
		},



		//TODO handle creation of an availability
		availabilityEdit: function(availability, event) {

			//check if timeEnd is before timeStart
			if(!this.checkTimePickerBounds()) {

				//make a toast msg
				toastMsg: "Error, availability timeEnd is before availability timeStart";
				showToast: true;

				//do not close window until user fixes their error
				this.showEditAvailability = true;
			}
			//TODO check if the new availability conflicts with an existing availability
			// else if() {
			//
			// }

			else {
				//combine the moment objects with date and time
				var momentStartObj = moment(availability.date + " " + availability.timeStart, ["YYYY-MM-DD HH:mma"]);
				var momentEndObj = moment(availability.date + " " + availability.timeEnd, ["YYYY-MM-DD HH:mma"]);

				event.start = momentStartObj;
				event.end = momentEndObj;
				event.title = availability.campus;

				//change color for event based on campus
				//red for burnaby
				if(availability.campus == 1) {
					event.color = "red";
				}
				//blue for surrey
				else if(availability.campus == 2) {
					event.color = "blue";
				}
				//Yellow for vancouver
				else if(availability.campus == 3) {
					event.color = "teal";
				}
				//purple if not specified for debug
				else {
					event.color = "purple";
				}

				//change text color for white
				event.textColor = "white"

				//update event
				$('#calendar').fullCalendar('updateEvent', this.event);

				toastMsg: "Edited Availability!";
				showToast: true;

				//close window
				this.showEditAvailability = false;
			}
		},

		//Deletes the event if button for deletion is pressed
		availabilityDeletion: function(availability, event) {

			//TODO deleting an event
			$('#calendar').fullCalendar('removeEvents', event.id);

			//turn off popup after deletion
			this.showEditAvailability = false;
		},

		//blocks out most user interaction when availability is submitted
		availabilitySubmitComplete: function() {

			//get availability list from the local calendar storage
			this.getAvailabilityList();

			//disables resizing of events, editing of events, selection of new events
			$('#calendar').fullCalendar('editable', false);
			$('#calendar').fullCalendar('selectable', false);
			$('#calendar').fullCalendar('selectHelper', false);

			//set value of all availabilty list to submitted
			//this.availabilityStatus = ;
			this.availabilitySubmitted = true;

			//close popup
			this.showConfirmSubmission = false;
		},

		//---------------------------- moment helpers -----------------------------

		//return next week monday moment object
		getNextWeekMonday: function() {
			// //create a moment object that is the starting monday of the current day and next week
			var nextMondayDate = moment().startOf('isoweek').add(7, 'days').format("YYYY-MM-DD");
			return nextMondayDate;
		},

		//returns next week sunday moment object
		getNextWeekSunday: function(){
			// //create a moment object that is the starting sunday of the current day and next week
			var nextSundayDate = moment().endOf('isoweek').add(7, 'days').format("YYYY-MM-DD");
			return nextSundayDate;
		},

		//returns next week monday date object
		getNextWeekMondayDate: function() {
			var currentDate = new Date();

			//monday
			var firstDay = currentDate.getDate() - currentDate.getDay(); // First day is the day of the month - the day of the week

			//set it to next week and add 1 as first day of week is sunday currently
			var mondayDate = new Date(currentDate.setDate(firstDay + 7 + 1));
			return mondayDate;
		},

		//returns next week sunday date object
		getNextWeekSundayDate: function() {
			var currentDate = new Date();

			//monday
			var firstDay = currentDate.getDate() - currentDate.getDay();

			//set it to sunday by adding 6 days to monday and + 1 for starting date of sunday to shift to monday
			//add 7 for next week
			var sundayDate = new Date(currentDate.setDate(firstDay + 6 + 7 + 1));
			return sundayDate;
		},

		//returns todays moment time object
		getTodayMomentTime: function(addedTime) {
			//round each moment object to nearest 15 minute increments
			var roundMinutes = moment().minutes();
			var remainder = 15 - (roundMinutes % 15);
			var roundedTime = moment().add("minutes", (remainder + addedTime)).format("LT");
			return  roundedTime;
		},


		//--------------------------- axios requests, gets -----------------------------

		// //TODO save locally
		// saveAvailabilityListLocally: function() {
		//         console.log("Avail list: " + this.availabilityList);
		//         this.saveLocallyConfirmation = false;
		// },
		//
		// //TODO delete locally
		// deleteAvailabilityListLocally: function() {
		//         this.saveLocallyConfirmation = false;
		// },

		//retrives all events on local calendar
		getAvailabilityList: function() {
			this.availabilityList = $('#calendar').fullCalendar('clientEvents');
			console.log("list of events: " + this.availabilityList);
			return this.availabilityList;
		},

		//populate this user's past week availability list
		populateAvailabilityList(response) {
			this.availabilityList = response.data;
		},

		//populate this user's team list
		populateUserTeamList(response) {
			this.campusList = response.data;
		},

		//---------------------- package into json and send to database ---------------------

		//request availlability
		requestAvailability() {
			var availabilityURL = '';
			availabilityURL = '/api/onetimeavailabilites'
		},

		//create availabilites from locally ccreated availability list
		initializeAvailabilitiesList: function() {
			axios.post('/api/onetimeavailabilites', )
			.then(response => this.requestAvailability)
			.catch(function (error) {
				console.log(error);
			});
		},
	},

	//initialize the last weeks availabilites incremented by a week, initialize the current user's list of teams
	computed: {

	},

	components: {
	},

	//TODO creates to avail database
	created: function() {

		//TODO : get all availabilities from a user id from database
		// console.log("get availabilities from database~!");
		// axios.get('api/onetimeavailabilites')
		// 	.then(this.populateAvailabilityList)
		// 	.catch(function (error) {
		// 	console.log(error);
		// });

		console.log("availabilities: " + this.availabilityList);

		//get all team ids from userid
		console.log("get list of teams this user belongs to!");
		axios.get('/api/users/' + this.userId + '/teams')
		.then(this.populateUserTeamList)
		.catch(function (error) {
			console.log(error);
		});

		console.log("campus list: " + this.campusList);
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

//#add-button {
//	height: 0.5em;
//	width: 100%;
//}

#option-bar {
	height: 4em;
}

</style>
