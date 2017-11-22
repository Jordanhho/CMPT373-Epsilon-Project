<template>
	<v-container fill-width fill-height class="pa-0">
		<v-layout row wrap>

			<v-flex xs12 id = "approval-bar">
					<!-- Primary background if approved -->
					<v-card v-if="availabilityStatus == 'Open'" dark color="blue" >
						<v-card-text class="text-xs-center">
							Status:
							{{ availabilityStatus }}
						</v-card-text>
					</v-card>

					<!-- Red background if Unapproved -->
					<v-card v-else-if="availabilityStatus == 'Pending'" dark color="red" >
						<v-card-text class="text-xs-center">
							Status:
							{{ availabilityStatus }}
						</v-card-text>
					</v-card>

					<!-- Green background if approved -->
					<v-card v-else-if="availabilityStatus == 'Approved'" dark color="green" >
						<v-card-text class="text-xs-center">
							Status:
							{{ availabilityStatus }}
						</v-card-text>
					</v-card>

					<!-- Primary background if Error // probably no need for this -->
					<v-card  v-else dark color="primary" >
						<v-card-text class="text-xs-center">
							Status:
							{{ availabilityStatus }}
						</v-card-text>
					</v-card>
			</v-flex>

			<v-flex xs12 id = "my-calendar">
				<!-- calendar -->
				<full-calendar
					:event-sources="eventSources"
					:config="config"
					id="calendar">
				</full-calendar>
			</v-flex>

			<v-flex xs12 id="add-button">
				<v-card-text
					style="height: 1px;
					position: relative"
					 @click.stop="showEditorWindow = true, showCreateOptions = true">
					<v-btn
						absolute
						dark
						fab
						top
						right
						color="blue"
						:disabled="availabilitySubmitted">
						<v-icon>
							add
						</v-icon>
					</v-btn>
				</v-card-text>
			</v-flex>

			<v-flex xs12 id="option-bar">
				<v-card class="text-xs-center" dark color="white" >
					<v-card-actions>
						<v-btn color="primary" block @click.stop="showSubmitWindow = true" :disabled="availabilitySubmitted">
							Submit
						</v-btn>
					</v-card-actions>
				</v-card>
			</v-flex>
		</v-layout>

		<!-- popup editor for clicking on availability, dragging event availability, clicking + button -->
		<v-layout row justify-center>
			<v-dialog v-model="showEditorWindow" max-width="500px" persistent lazy full-width>
				<v-card>
					<v-card-title>
						<span class="headline">Availability Info</span>
					</v-card-title>
					<v-card-text>
						<v-container grid-list-xs>
							<v-layout wrap>

								<!-- Campus -->
								<v-flex xs6>
									<v-select
										v-bind:items="campusList"
										v-model="availability.campus"
										label="Campus"
										item-value="text"

										required
										:disabled="availabilitySubmitted">
									</v-select>
								</v-flex>

								<!-- date  picker -->
								<v-flex xs6>
									<v-dialog v-model="dateStartModal" persistent lazy full-width>
										<v-text-field
											slot="activator"
											label="Date"
											v-model="availability.date"
											prepend-icon="event"
											readonly
											required
											:disabled="availabilitySubmitted">
										</v-text-field>
										<v-date-picker
											v-model="availability.date"
											first-day-of-week="1"
											:allowed-dates="allowedDates"
											:scrollable=false
											actions>
											<template scope="{ save, cancel }">
												<v-card-actions>
													<v-btn flat color="primary" @click="save, timeStartModal">
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
											v-model="availability.timeStart"
											prepend-icon="access_time"
											readonly
											required
											:disabled="availabilitySubmitted">
										</v-text-field>
										<v-time-picker
											v-model="availability.timeStart"
											:allowed-hours="allowedTimes.hours"
											:allowed-minutes="allowedTimes.minutes"
											:scrollable=false
											actions>
											<template scope="{ save, cancel }">
												<v-card-actions>
													<v-btn flat color="primary" @click="save, timeEndModal" :disabled="availabilitySubmitted">
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
											v-model="availability.timeEnd"
											prepend-icon="access_time"
											readonly
											required
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

							</v-layout>
						</v-container>

						<v-card-actions>
							<v-spacer></v-spacer>
							<v-btn  v-if="showCreateOptions" color="primary" flat @click.stop="availabilityCreation" :disabled="availabilitySubmitted">
								Create
							</v-btn>
							<v-btn v-if="showEditOptions" color="primary" flat @click.stop="availabilityEdit(availability, currentSelectedEvent)" :disabled="availabilitySubmitted">
								Edit
							</v-btn>
							<v-btn v-if="showEditOptions" color="primary" flat @click.stop="availabilityDeletion(availability, currentSelectedEvent)" :disabled="availabilitySubmitted">
								Delete
							</v-btn>
							<v-btn color="primary" flat @click.stop="showEditorWindow = false">
								Close
							</v-btn>
						</v-card-actions>
					</v-card-text>
				</v-card>
			</v-dialog>
		</v-layout>

		<!-- popup for when submit availbility is clicked -->
		<v-dialog v-model="showSubmitWindow" persistent lazy full-width>
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
					<v-btn color="primary" flat @click.stop="showSubmitWindow = false">
						Cancel
					</v-btn>
				</v-card-actions>
			</v-card>
		</v-dialog>

	</v-container>
</template>

<script>
import Vue from 'vue'
import moment from 'moment'
import axios from 'axios'
import FullCalendar from 'vue-full-calendar'
Vue.use(FullCalendar) // add the vue-full-calendar plugin to Vue
window.jQuery = window.$ = require('jquery') // we need jquery too

export default {
	data: function () {
		return {
			//------------------- USER ---------------------------
			userId: 2, //current user Id

			//------------------ options -------------------------------
			saveLocallyConfirmation: false,
			deleteLocallyConfirmation: false,

			// ----------------- MODALS --------------------------------
			showEditorWindow: false, //editor window popup
			showCreateOptions: false, //show the create button for the editor window
			showEditOptions: false, //show the edit and delete buttons for the editor window
			showSubmitWindow: false, //submission availabiliy popup

			//time picker/date picker modals
			dateStartModal: false,
			timeStartModal: false,
			timeEndModal: false,


			//--------------- Show TextField boolean values
			showDateText: false,
			showTimeStartText: false,
			showTimeEndText: false,

			//----------------- Submission ---------------------------
			availabilityStatus: null, //status of availability, must set it at initialization from database
			availabilitySubmitted: false, //boolean for if avail is submitted

			//---------------- Time, Date ----------------------------
			allowedDates: null, //ranged of allowed dates for date picker

			nextWeekRange: { //restrict it to allowed days and time from min to max
				min: null,
				max: null,
			},

			allowedTimes: {
				hours: null,
				minutes: null,
			},

			restrictTimeIncrements: { //restrict it to 15 minutes increments, do not change hours
				hours: function(value) {
					return value
				},
				minutes: function(value) {
					return value % 15 === 0
				}
			},

			//-------------------- Campus selection -----------------------
			campusList: [], //for selection of campus

			//---------------- availability type and list ------------------
			availabilityList: [], //Data contains list of avaliablities

			availability: {
				title: "",
				date: this.getNextWeekMondayMoment(),
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

	methods: {

		//------------------- calendar functions -----------------------

		initializeCalendarView() {

			$('#calendar').fullCalendar('gotoDate', this.getNextWeekMondayMoment());

			//constrain calendar view
			// $('#calendar').fullCalendar({
			// 	validRange: {
			// 		start: this.getNextWeekMondayMoment(),
			// 		end: this.getNextWeekSundayMoment()
			// 	}
			// });

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
			this.availability.title = event.title;
			this.availability.date = moment(event.start, ["YYYY-MM-DD"]).format("YYYY-MM-DD");
			this.availability.timeStart = moment(event.start, ["HH:mma"]).format("HH:mma");
			this.availability.timeEnd = moment(event.end, ["HH:mma"]).format("HH:mma");
			this.availability.campus = event.title;

			//show edit availability
			this.showEditOptions = true;
			this.showEditorWindow = true;
		},

		//handles drag selection of availability on calendar
		availabilityCreateSelect: function(start, end, jsEvent, view) {

			//only allows deletion before submission
			if (this.availabilitySubmitted == false) {

				//set the start and end of this availability selection
				this.availability.date = start.format("YYYY-MM-DD");
				this.availability.timeStart = start.format("HH:mma");
				this.availability.timeEnd = end.format("HH:mma");

				//show add avaialbility
				this.$emit('create');
				this.showCreateOptions = true;
				this.showEditorWindow = true;
			}
			else {
				//do nothing as submission is denied after submission of availability
			}
		},

		//check bounds on timepicker
		checkTimePickerBounds: function() {
			var testStartMoment = moment(this.availability.timeStart, ["HH:mma"]);
			var testEndMoment = moment(this.availability.timeEnd, ["HH:mma"]);
			return (testStartMoment < testEndMoment);
		},

		//TODO compares the current availability to any preexisting availability if their time conflicts
		checkIfAvailabilityConflicts() {
			//get local list of events
			var eventList = $('#calendar').fullCalendar('clientEvents');
			for(i = 0; i < eventList.length; i ++) {
				//if(eventList[i].)
				return false;
			}

			return true;
		},

		//TODO handle creation of an availability
		availabilityCreation: function() {

			//check if timeEnd is before timeStart
			if(this.checkTimePickerBounds() == false) {

				//do not close window until user fixes their error
				this.showCreateOptions = true;
			}

			//TODO check if the new availability conflicts with an existing availability
			// else if(checkIfAvailabilityConflicts() == false) {
			//
			// }

			//all conditions passed, create the new availability
			else if(correctTimeBounds == true) {
				//combine the moment objects with date and time
				var momentStartObj = moment(this.availability.date + " " + this.availability.timeStart, ["YYYY-MM-DD HH:mma"]);
				var momentEndObj = moment(this.availability.date + " " + this.availability.timeEnd, ["YYYY-MM-DD HH:mma"]);

				//create new object for event with the newly created moment objects
				var eventItem = {
					id: -1,
					start: momentStartObj,
					end: momentEndObj,
					title: this.availability.campus,
				}

				//change color for event based on campus
				//red for burnaby
				if(this.availability.campus == 1) {
					eventItem.color = "red";
				}
				//blue for surrey
				else if(this.availability.campus == 2) {
					eventItem.color = "blue";
				}
				//Yellow for vancouver
				else if(this.availability.campus == 3) {
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
				this.showCreateOptions = false;
				this.showEditorWindow = false;
			}
		},



		//TODO handle creation of an availability
		availabilityEdit: function(availability, event) {

			//check if timeEnd is before timeStart
			if(!this.checkTimePickerBounds()) {
				this.showEditOptions = true; //do not close window until user fixes their error
			}
			//TODO check if the new availability conflicts with an existing availability
			// else if() {
			//
			// }

			else {
				//combine the moment objects with date and time
				var momentStartObj = moment(this.availability.date + " " + this.availability.timeStart, ["YYYY-MM-DD HH:mma"]);
				var momentEndObj = moment(this.availability.date + " " + this.availability.timeEnd, ["YYYY-MM-DD HH:mma"]);

				event.start = momentStartObj;
				event.end = momentEndObj;
				event.title = this.availability.campus;

				//change color for event based on campus
				event.textColor = "white" //change text color for white

				if(availability.campus == 1) { 	//red for burnab
					event.color = "red";
				}
				else if(availability.campus == 2) { //blue for surrey
					event.color = "blue";
				}
				else if(availability.campus == 3) { //Yellow for vancouver
					event.color = "teal";
				}
				else { //purple if not specified for debug
					event.color = "purple";
				}

				$('#calendar').fullCalendar('updateEvent', this.event); //update event

				this.showEditOptions = false; //close window
				this.showEditorWindow = false;
			}
		},

		//Deletes the event if button for deletion is pressed
		availabilityDeletion: function(availability, event) {

			$('#calendar').fullCalendar('removeEvents', event.id); //TODO deleting an event
			this.showEditOptions = false; //turn off popup after deletion
		},

		//blocks out most user interaction when availability is submitted
		availabilitySubmitComplete: function() {

			//TODO submit get availability list from the local calendar storage
			this.getAvailabilityList();

			//disables resizing of events, editing of events, selection of new events
			$('#calendar').fullCalendar('editable', false);
			$('#calendar').fullCalendar('selectable', false);
			$('#calendar').fullCalendar('selectHelper', false);

			//set value of all availabilty list to submitted
			//this.availabilityStatus = ;
			this.availabilitySubmitted = true;

			//close popup
			this.showSubmitWindow = false;
		},

		//---------------------------- time picker Restrictions -----------------------------

		//return next week monday moment object
		getNextWeekMondayMoment: function() {
			//create a moment object that is the starting monday of the current day and next week
			var nextMondayDate = moment().startOf('isoweek').add(7, 'days').format("YYYY-MM-DD");
			return nextMondayDate;
		},

		//returns next week sunday moment object
		getNextWeekSundayMoment: function(){
			//create a moment object that is the starting sunday of the current day and next week
			var nextSundayDate = moment().endOf('isoweek').add(7, 'days').format("YYYY-MM-DD");
			return nextSundayDate;
		},

		//returns next week monday date object
		getNextWeekMondayDate: function() {
			var currentDate = new Date();
			var firstDay = currentDate.getDate() - currentDate.getDay(); // First day is the day of the month - the day of the week

			//set it to next week and add 1 as first day of week is sunday currently
			var mondayDate = new Date(currentDate.setDate(firstDay + 7 + 1));
			return mondayDate;
		},

		//returns next week sunday date object
		getNextWeekSundayDate: function() {
			var currentDate = new Date();
			var firstDay = currentDate.getDate() - currentDate.getDay(); //Monday

			//set it to sunday by adding 6 days to monday and + 1 for starting date of sunday to shift to monday, add 7 for next week
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
			this.campusList = response.data.map(x => x.name);
			console.log("campus list: " + this.campusList);
		},
	},

	//initialize the last weeks availabilites incremented by a week, initialize the current user's list of teams

	//TODO creates to avail database
	created: function() {

		//TODO : get all availabilities from a user id from database

		//get all team ids from userid
		axios.get('/api/users/' + this.userId + '/teams')
		.then(this.populateUserTeamList)
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
	height: 0;
	width: 100%;
}

#option-bar {
	height: 4em;
}

</style>
