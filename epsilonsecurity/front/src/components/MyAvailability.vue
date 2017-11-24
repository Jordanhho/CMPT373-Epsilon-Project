<template>
	<v-container fill-width fill-height class="pa-0">
		<v-layout row wrap>

			<v-flex xs12 id = "approval-bar">
					<!-- Primary background if approved -->
					<v-card v-if="availabilityStatus == 'OPEN'" dark color="blue" >
						<v-card-text class="text-xs-center">
							Status:
							{{ availabilityStatus }}
						</v-card-text>
					</v-card>

					<!-- Red background if Unapproved -->
					<v-card v-else-if="availabilityStatus == 'SUBMITTED'" dark color="red" >
						<v-card-text class="text-xs-center">
							Status:
							{{ availabilityStatus }}
						</v-card-text>
					</v-card>

					<!-- Green background if approved -->
					<v-card v-else-if="availabilityStatus == 'APPROVED'" dark color="green" >
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
				<full-calendar
					:event-sources="eventSources"
					:config="config"
					id="calendar">
				</full-calendar>
				<v-card-text
					style="height: 1px;
					position: relative"
					 @click.stop="showEditorWithCreate">
					<v-btn absolute dark fab top right color="blue" :disabled="availabilitySubmitted">
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
			<v-dialog v-model="showEditorWindow" max-width="400px" persistent lazy full-width>
				<v-card>
					<v-card-title>
						<span class="headline">Availability Info</span>
					</v-card-title>
					<v-card-text>
						<v-container grid-list-xs>
							<v-layout wrap>

								<!-- Campus -->
								<v-flex xs12>
									<v-select
										label="Campus"
										v-bind:items="campusList"
										v-model="availability.campus"
										single-line
										item-value="text"
										prepend-icon="map"
										:rules="[() => !!availability.campus || 'You must pick a campus']"
										required
										:disabled="availabilitySubmitted">
									</v-select>
								</v-flex>

								<!-- date picker -->
								<v-flex xs12>
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
											actions>
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
								<v-flex xs12>
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
										</v-time-picker>this.showEditorWithCreate();
									</v-dialog>
								</v-flex>

								<!-- time end picker -->
								<v-flex  xs12>
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
							<v-btn  v-if="showCreateOptions" color="primary" flat @click.stop="availabilityCreate" :disabled="availabilitySubmitted">
								Create
							</v-btn>
							<v-btn v-if="showEditOptions" color="primary" flat @click.stop="availabilityEdit()" :disabled="availabilitySubmitted">
								Edit
							</v-btn>
							<v-btn v-if="showEditOptions" color="primary" flat @click.stop="availabilityDelete" :disabled="availabilitySubmitted">
								Delete
							</v-btn>
							<v-btn color="primary" flat @click.stop="hideEditor">
								Close
							</v-btn>
						</v-card-actions>
					</v-card-text>
				</v-card>
			</v-dialog>
		</v-layout>

		<!-- popup for error message -->
		<v-dialog v-model="showTimeError" persistent lazy full-width>
			<v-card>
				<v-container grid-list-xs>
					<v-layout wrap>
						<v-card-text>
							<v-flex xs12>
								<v-card-text class="text-xs-center">
									<b> {{ timeErrorMessage }} </b>
								</v-card-text>
							</v-flex>
						</v-card-text>
					</v-layout wrap>
				</v-container grid-list-xs>
				<v-card-actions>
					<v-spacer></v-spacer>
					<v-btn color="primary" flat @click.stop="showTimeError = false">
						Ok
					</v-btn>
					<v-spacer></v-spacer>
				</v-card-actions>
			</v-card>
		</v-dialog>

		<!-- popup for when submit availability is clicked -->
		<v-dialog v-model="showSubmitWindow" persistent lazy full-width>
			<v-card>
				<v-container grid-list-xs>
					<v-layout wrap>
						<v-card-text>
							<v-flex xs12>
								<v-card-text class="text-xs-center">
									Confirm Submission of Availability Schedule?
								</v-card-text>
							</v-flex>
						</v-card-text>
					</v-layout wrap>
				</v-container grid-list-xs>
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
			//----------------- Submission ---------------------------
			//TODO: status of availability, must set it at initialization from database
			availabilityStatus: "OPEN",
			availabilitySubmitted: false,

			//------------------- USER ---------------------------
			//current user Id
			userId: 2,

			 //TODO have to set this later
			//teamId: null,

			// ----------------- MODALS --------------------------------
			showEditorWindow: false, //editor window popup
			showCreateOptions: false, //show the create button for the editor window
			showEditOptions: false, //show the edit and delete buttons for the editor window
			showSubmitWindow: false, //submission availabiliy popup

			//time picker/date picker modals
			dateStartModal: false,
			timeStartModal: false,
			timeEndModal: false,

			//--------------- error checking for time pickers ----------------

			//error msg modal and popup
			timeErrorMessage: "",
			showTimeError: false,

			//check if time conflicts/out of bounds
			timeBoundsCheck: false,
			timeConflictCheck: false,

			//---------------- Time, Date ----------------------------
			//ranged of allowed dates for date picker
			allowedDates: null,

			//restrict it to allowed days and time from min to max
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
			campusList: [],

			//campus colors
			burnabyCampus: "red",
			surreyCampus: "blue",
			vancouverCampus: "teal",
			noCampus: "purple",

			//---------------- availability type and list ------------------s
			availabilityList: [],

			availability: {
				eventObj: null,
				eventId: -1,
				title: "",
				date: this.getNextWeekMondayMoment(),
				timeStart: this.getDefaultStartTime(),
				timeEnd: this.getDefaultEndTime(),
				campus: "",
			},

			//-------------------- event id assignment -------------------------------
			assignEventId: 0,

			//--------------- calendar config ------------------------------
			config: {
				header: {
					left: 'prev, today, next,',
					center: 'title',
					right: 'month,agendaWeek,agendaDay'
				},

				defaultView: 'agendaWeek',
				height: 'parent',
				allDaySlot: false,
				timezone: 'local',
				slotDuration: '00:15:00', //15 minute increment
				scrollTime: '00:00:00', // scroll calendar to the specified time (12 AM)
				nowIndicator: true, //shows today
				firstDay: 1, //sets first day of week to monday
				weekNumberCalculation: "ISO", //sets week number calculation to ISO

				//----------------- selection of events --------------------------

				eventClick: this.availabilityClickEvent, //triggered with an event is clicked
				editable: false, //allows resizing of events
				selectable: false, //prevents dragging on calendar

				//restrict event to maximum one day
				eventConstraint: {
					start: "00:00",
					end: "24:00",
				},
			},

			eventSources: [
				{
					//TODO add events
				}
			],
		}
	},

	methods: {
		//------------------- initialize calendar -----------------------

		//set the default viewed week for calendar
		initializeCalendarView() {
			$('#calendar').fullCalendar('gotoDate', this.getNextWeekMondayMoment());
		},

		getDefaultStartTime: function() {
			var momentNextMonday = this.getNextWeekMondayMoment();
			var momentStartObj = moment().set({'years': moment(momentNextMonday).get('year'),
												'month': moment(momentNextMonday).get('month'),
												'day': moment(momentNextMonday).get('day'),
												'hour': 0,
												'minute': 30});
			momentStartObj = moment(momentStartObj).format("h:mma");
			return momentStartObj;
		},

		getDefaultEndTime: function() {
			var momentNextMonday = this.getNextWeekMondayMoment();
			var momentEndObj = moment().set({'years': moment(momentNextMonday).get('year'),
												'month': moment(momentNextMonday).get('month'),
												'day': moment(momentNextMonday).get('day'),
												'hour': 1,
												'minute': 30});
			momentEndObj = moment(momentEndObj).format("h:mma");
			return momentEndObj;
		},


		//------------------------------ modal show/close -------------------

		showEditorWithEdit: function() {
			this.showEditOptions = true;
			this.showEditorWindow = true;
		},

		showEditorWithCreate: function() {
			this.showCreateOptions = true;
			this.showEditorWindow = true;
		},

		hideEditor: function() {
			this.showEditOptions = false;
			this.showCreateOptions = false;
			this.showEditorWindow = false;
		},

		//------------------------- time/date picker range calendar check -------------------------

		//check bounds on timepicker
		checkTimePickerBounds: function() {
			var momentStartObj = moment(this.availability.timeStart, ["h:mma"]);
			var momentEndObj = moment(this.availability.timeEnd, ["h:mma"]);

			//compare if they are equal in minutes and hours
			if((moment(momentStartObj).get('hour') == moment(momentEndObj).get('hour')) && (moment(momentStartObj).get('minute') == moment(momentEndObj).get('minute'))) {
				this.showTimeError = true;
				this.timeErrorMessage = "[Invalid Time] The Time End is the same as Time Start";
				this.timeBoundsCheck = true;
				return true;
			}
			else if(momentEndObj < momentStartObj) {
				this.showTimeError = true;
				this.timeErrorMessage = "[Invalid Time] The Time End is before Time Start";
				this.timeBoundsCheck = true;
				return true;
			}
			else {
				this.timeBoundsCheck = false;
				return false;
			}
		},


		//compares the current availability to any preexisting availability if their time conflicts
		checkIfAvailabilityConflicts(action) {

			//parse/set the availability start and end moment object's date and time
		    var momentStartObj = moment(this.availability.date + " " + this.availability.timeStart, ["YYYY-MM-DD h:mma"]);
		    var momentEndObj = moment(this.availability.date + " " + this.availability.timeEnd, ["YYYY-MM-DD h:mma"]);
		    var parseStart = moment().set({'date': moment(momentStartObj).get('date'), 'hour': moment(momentStartObj).get('hour'), 'minute': moment(momentStartObj).get('minute')});
		    var parseEnd = moment().set({'date': moment(momentEndObj).get('date'), 'hour': moment(momentEndObj).get('hour'), 'minute': moment(momentEndObj).get('minute')});
		    var currMomentStart = moment(parseStart);
		    var currMomentEnd = moment(parseEnd);

		    //get local list of events
		    var eventList = $('#calendar').fullCalendar('clientEvents');
		    for(var i = 0; i < eventList.length; i++) {
		        var currEvent = eventList[i];

				//if same object only on edit
				if((action == "edit") && (currEvent.id == this.availability.eventId)) {
					continue;
				}
				//if not same day, continue
				if(moment(currEvent.start).get('day') != moment(currMomentStart).get('day')) {
					continue;
				}
				//compare time in hours and minutes with current event object and currently selected availabilty
				if((currEvent.start <= currMomentEnd) && (currEvent.end >= currMomentStart)) {
					this.showTimeError = true;
					this.timeErrorMessage = "[Invalid Time] The Time range conflicts with another availability";
					this.timeConflictCheck = true;
					return true;
				}
		    }
			this.timeConflictCheck = false;
		    return false;
		},

		//create a moment object that is the starting monday of the current day and next week
		getNextWeekMondayMoment: function() {
			var nextMondayDate = moment().startOf('isoweek').add(7, 'days').format("YYYY-MM-DD");
			return nextMondayDate;
		},


		//create a moment object that is the starting sunday of the current day and next week
		getNextWeekSundayMoment: function(){
			var nextSundayDate = moment().endOf('isoweek').add(7, 'days').format("YYYY-MM-DD");
			return nextSundayDate;
		},

		//----------------------- campus color ----------------------------

		//returns the color string for the campus colors
		getCampusColor: function(campusName) {
			if(campusName == "BURNABY") {
				return this.burnabyCampus;
			}
			//blue for surrey
			else if(campusName == "SURREY") {
				return this.surreyCampus;
			}
			//Yellow for vancouver
			else if(campusName == "VANCOUVER") {
				return this.vancouverCampus;
			}
			//purple if not specified -> for debug
			else {
				return this.noCampus;
			}
		},


		//--------------------- event Id assignment ------------------------------------------

		//gets a new event id and increment the eventid counter
		getNewEventId: function() {
			var newEventId = this.assignEventId;
			this.assignEventId = this.assignEventId + 1;
			return newEventId;
		},

		//---------------------create, delete, edit calendar operations ----------------------

		//TODO handle creation of an availability
		availabilityCreate: function() {

		    //override the local availability data
		    var momentStartObj = moment(this.availability.date + " " + this.availability.timeStart, ["YYYY-MM-DD h:mma"]);
		    var momentEndObj = moment(this.availability.date + " " + this.availability.timeEnd, ["YYYY-MM-DD h:mma"]);
		    this.availability.date = moment(momentStartObj).format("YYYY-MM-DD");
		    this.availability.timeStart = moment(momentStartObj).format("h:mma");
		    this.availability.timeEnd = moment(momentEndObj).format("h:mma");

		    //all conditions are met for creating this availability
		    if(this.checkTimePickerBounds() == false && this.checkIfAvailabilityConflicts("create") == false) {
		        //create new object for event with the newly created moment objects
		        var event = {
		            id: this.getNewEventId(),
		            start: momentStartObj,
		            end: momentEndObj,
		            title: this.availability.campus,
		        }

		        //change color for event based on campus
		        event.color = this.getCampusColor(this.availability.campus);

		        //change textcolor
		        event.textColor = "white"

		        //render event
		        $('#calendar').fullCalendar('renderEvent', event, true);

		        //close window
		        this.hideEditor();
		    }
		    else {
		        //TODO: make some sort of error indication that it the availability you are trying to make is incorrect
		    }
		},

		//handles user clicking on event
		availabilityClickEvent: function(event, jsEvent, view) {

			//set currently selected availability to this event's data
			this.availability.eventObj = event;
			this.availability.eventId = event.id;
			this.availability.title = event.title;
			this.availability.date = moment(event.start, ["YYYY-MM-DD"]).format("YYYY-MM-DD");
			this.availability.timeStart = moment(event.start, ["h:mma"]).format("h:mma");
			this.availability.timeEnd = moment(event.end, ["h:mma"]).format("h:mma");
			this.availability.campus = event.title;

			//show edit availability
			this.showEditorWithEdit();
		},

		//TODO handle edit of availability
		availabilityEdit: function() {

			//override the local availability data
			var momentStartObj = moment(this.availability.date + " " + this.availability.timeStart, ["YYYY-MM-DD h:mma"]);
			var momentEndObj = moment(this.availability.date + " " + this.availability.timeEnd, ["YYYY-MM-DD h:mma"]);
			this.availability.date = moment(momentStartObj).format("YYYY-MM-DD");
			this.availability.timeStart = moment(momentStartObj).format("h:mma");
			this.availability.timeEnd = moment(momentEndObj).format("h:mma");

			//all conditions are met for creating this availability
			if(this.checkTimePickerBounds() == false && this.checkIfAvailabilityConflicts("edit") == false) {

				//set edited datafields of event
				this.availability.eventObj.id = this.availability.eventId;
				this.availability.eventObj.start = momentStartObj;
				this.availability.eventObj.end = momentEndObj;
				this.availability.eventObj.title = this.availability.campus;

				//change color for event based on campus
				this.availability.eventObj.color = this.getCampusColor(this.availability.campus);

				//update event
				$('#calendar').fullCalendar('updateEvent', this.availability.eventObj);

				//close window
				this.hideEditor();
			}
		},


		//Deletes the event
		availabilityDelete: function(availability, event) {
			$('#calendar').fullCalendar('removeEvents', this.availability.eventId);

			//close window
			this.hideEditor();
		},

		//---------------------- calendar submission --------------------------------

		//blocks out most user interaction when availability is submitted
		availabilitySubmitComplete: function() {

			//TODO submit get availability list from the local calendar storage
			this.availabilityList = this.getAvailabilityList();

			//set value of all availability list to submitted
			this.availabilityStatus = "SUBMITTED";
			this.availabilitySubmitted = true;

			//close popup
			this.showSubmitWindow = false;
		},


		//retrives all events on local calendar
		getAvailabilityList: function() {
			this.availabilityList = $('#calendar').fullCalendar('clientEvents');
			return this.availabilityList;
		},


		//-------------- Helper functions for mounted -----------------------------

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

		//--------------------------- axios get and post -----------------------------

		//populate this user's team list
		populateUserTeamList(response) {
			this.campusList = response.data.map(x => x.name);
		},


		//initialize this user's availabilty's status
		populateAvailabilityStatus(response) {
			this.availabilityStatus = response.data;
		}
	},

	created: function() {
		//TODO initialize the status of availability submission


		//initialize local list of teams the user belongs to
		axios.get('/api/users/' + this.userId + '/teams')
		.then(this.populateUserTeamList)
		.catch(function (error) {
			console.log(error);
		});

		//TODO initialize list of availabilities assigned currently for next week

		//http://api/users/1/teams/1/onetimeavailabilites?start=10249812&end=2938109
		//api/users/$userId<[^/]+>/teams/$teamId<[^/]+>/onetimeavailabilites?start=TIMESTART&end=TIMEENDco
		//TODO: get state of user's availability: open, submitted, approved
		// axios.get('/api/users/' + this.userId + '/teams' + this.teamId + '/onetimeavailabilites?start=' + timeStart + "&end=" + timeEnd)
		// .then(this.populateAvailabilityStatus)
		// .catch(function (error) {
		// 	console.log(error);
		// });


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

#option-bar {
	height: 4em;
	width: 100%;
}
</style>
