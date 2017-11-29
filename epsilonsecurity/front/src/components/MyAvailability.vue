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
					<v-card v-else-if="availabilityStatus == 'Submitted'" dark color="red" >
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
				<center>
					<v-flex xs10 class="text-xs-center">
						<v-card class="text-xs-center" dark color="white">
							<v-card-actions>
								<v-btn color="primary" block @click.stop="showSubmitWindow = true" :disabled="availabilitySubmitted">
									Submit
								</v-btn>
							</v-card-actions>
						</v-card>
					</v-flex>
				</center>
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
										v-bind:items="teamNameList"
										v-model="availability.teamName"
										label="Team"
										prepend-icon="map"
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
							<v-btn v-if="showEditOptions" color="primary" flat @click.stop="availabilityDelete" :disabled="availabilitySubmitted">
								Delete
							</v-btn>
							<v-spacer></v-spacer>
							<v-btn  v-if="showCreateOptions" color="primary" flat @click.stop="availabilityCreate" :disabled="availabilitySubmitted">
								Create
							</v-btn>
							<v-btn v-if="showEditOptions" color="primary" flat @click.stop="availabilityEdit()" :disabled="availabilitySubmitted">
								Edit
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
			availabilityStatus: "",
			availabilitySubmitted: false,

			//------------------- USER ---------------------------
			//current user Id
			loggedInUserId: 5,

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
			userTeamIdList: [],
			teamNameList: [],
			teamIdList: [],

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
				date: this.getNextWeekMondayMoment(),
				timeStart: this.getDefaultStartTime(),
				timeEnd: this.getDefaultEndTime(),
				teamName: "",
				teamId: -1,
			},

			//-------------------- event id assignment -------------------------------
			assignEventId: 0,

			//--------------- calendar config ------------------------------
			config: {
				header: {
					left: 'prev,today,next',
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
				navLinks: true, //allows clicking on a day

				//----------------- event click  --------------------------
				editable: false, //allows resizing of events
				eventClick: this.availabilityClickEvent, //triggered with an event is clicked

				//------------------ event drag selection
				selectable: false, //allows dragging on calendar.
				selectOverlap: false, //do not allow event overlap selection
				selectHelper: true, //allows to make an event on calendar
				selectMinDistance: 5, //minimum distance click has to move inorder to detect as event PREVENT MISCLICKS
				selectLongPressDelay: 1000, //minimum miliseconds user holds down before it counts as a selectable
				select: this.availabilityCreateDrag, //triggered after a selection when user stops dragging.

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
					//TODO add events
				}
			],
		}
	},

	methods: {
		//------------------- initialize calendar -----------------------

		//set the default viewed week for calendar
		defaultCalendarView: function() {
			//move calendar to default date
			$('#calendar').fullCalendar('gotoDate', this.getNextWeekMondayMoment());

			//change calendar to default view
			$('#calendar').fullCalendar('changeView', 'agendaWeek');
		},

		//------------------- get Time methods -----------------------

		getDefaultStartTime: function() {
			var momentNextMonday = this.getNextWeekMondayMoment();
			var momentStartObj = moment().set({'years': moment(momentNextMonday).get('year'),
												'month': moment(momentNextMonday).get('month'),
												'day': moment(momentNextMonday).get('day'),
												'hour': 0,
												'minute': 0});
			momentStartObj = moment(momentStartObj).format("h:mma");
			return momentStartObj;
		},

		getDefaultEndTime: function() {
			var momentNextMonday = this.getNextWeekMondayMoment();
			var momentEndObj = moment().set({'years': moment(momentNextMonday).get('year'),
												'month': moment(momentNextMonday).get('month'),
												'day': moment(momentNextMonday).get('day'),
												'hour': 1,
												'minute': 0});
			momentEndObj = moment(momentEndObj).format("h:mma");
			return momentEndObj;
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

		//returns epoch seconds for next monday
		getEpochNextWeekMonday: function() {
			var epochNextMonday = moment(this.getNextWeekMondayMoment()).set({'hour': 0, 'minute': 0});
			return moment(epochNextMonday).format("X");
		},

		//returns epoch seconds for next sunday
		getEpochNextWeekSunday: function() {
			var epochNextSunday = moment(this.getNextWeekSundayMoment()).set({'hour': 23, 'minute': 59});
			return moment(epochNextSunday).format("X");
		},


		//returns next week monday date object
		getNextWeekMondayDate: function() {
			var nextMonday = moment(this.getNextWeekMondayMoment(), ["YYYY-MM-DD"]).startOf('isoweek').toDate();
			return nextMonday;
		},

		//returns next week sunday date object
		getNextWeekSundayDate: function() {
			var nextSunday = moment(this.getNextWeekSundayMoment(), ["YYYY-MM-DD"]).endOf('isoweek').toDate();
			return nextSunday;
		},

		//-------------- Helper functions -----------------------------

		//return the string teamName for that teamID
		getTeamNameFromTeamId: function(checkTeamId) {
			for(var i = 0; i < this.teamIdList.length; i++) {
				if(checkTeamId == this.teamIdList[i]) {
					return this.teamNameList[i];
				}
			}
			return "invalid teamName";
		},

		//return the teamID from the string TeamName
		getTeamIdFromTeamName: function(checkTeamName) {
			for(var i = 0; i < this.teamNameList.length; i++) {
				if(checkTeamName == this.teamNameList[i]) {
					return this.teamIdList[i];
				}
			}
			return "invalid teamId";
		},

		//----------------------- team color ----------------------------

		//returns the color string for the team colors
		getCampusColor: function(teamName) {
			if(teamName == "BURNABY") {
				return this.burnabyCampus;
			}
			//blue for surrey
			else if(teamName == "SURREY") {
				return this.surreyCampus;
			}
			//Yellow for vancouver
			else if(teamName == "VANCOUVER") {
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


		//------------------------------ modal show/close -------------------

		showEditorWithEdit: function() {
			this.showEditOptions = true;
			this.showEditorWindow = true;
		},

		showEditorWithCreate: function() {
			//go to default view
			this.defaultCalendarView();
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
				this.timeErrorMessage = "INVALID TIME: The Time End is the same as Time Start";
				return true;
			}
			else if(momentEndObj < momentStartObj) {
				this.showTimeError = true;
				this.timeErrorMessage = "INVALID TIME: The Time End is before Time Start";
				return true;
			}
			else {
				return false;
			}
		},


		//compares the current availability to any preexisting availability if their time conflicts
		checkIfAvailabilityConflicts(action) {

			//parse/set the availability start and end moment object's date and time
			var momentStartObj = moment(this.availability.date + " " + this.availability.timeStart, ["YYYY-MM-DD h:mma"]);
			var momentEndObj = moment(this.availability.date + " " + this.availability.timeEnd, ["YYYY-MM-DD h:mma"]);

			for(var i = 0; i <  this.availabilityList.length; i++) {
				var currEvent = this.availabilityList[i];

				//if same object only on edit
				if((action == "edit") && (currEvent.eventId == this.availability.eventId)) {
					continue;
				}
				//if not the same day, skip this object
				if(moment(currEvent.timeStart).isSame(momentStartObj, 'day') == false) {
					continue;
				}
				//compare time in hours and minutes with current event object and currently selected availabilty
				if(moment(currEvent.timeStart).isBefore(momentEndObj, 'minute') && moment(currEvent.timeEnd).isAfter(momentStartObj, 'minute')) {
					this.showTimeError = true;
					this.timeErrorMessage = "INVALID TIME: Time range conflicts with another availability";
					return true;
				}
			}
			return false;
		},

		//---------------------create, delete, edit calendar operations ----------------------

		availabilityCreateDrag: function(start, end, jsEvent, view) {
			if(this.availabilitySubmitted == true) {
				$('#calendar').fullCalendar('editable', false);
				$('#calendar').fullCalendar('selectable', false);
				$('#calendar').fullCalendar('selectHelper', false);
			}
			else {
				this.availability.date = moment(start).format("YYYY-MM-DD");
				this.availability.timeStart = moment(start).format("h:mma");
				this.availability.timeEnd = moment(end).format("h:mma");
				this.showEditorWithCreate();
			}
		},

		//handle creation of an availability
		availabilityCreate: function() {

			//override the local availability data
		    var momentStartObj = moment(this.availability.date + " " + this.availability.timeStart, ["YYYY-MM-DD h:mma"]);
		    var momentEndObj = moment(this.availability.date + " " + this.availability.timeEnd, ["YYYY-MM-DD h:mma"]);

			//sets local fields
 		    this.availability.date = moment(momentStartObj).format("YYYY-MM-DD");
 		    this.availability.timeStart = moment(momentStartObj).format("h:mma");
 		    this.availability.timeEnd = moment(momentEndObj).format("h:mma");

		    //all conditions are met for creating this availability
		    if(this.checkTimePickerBounds() == false && this.checkIfAvailabilityConflicts("create") == false) {

		        //create new object for event with the newly created moment objects
				var newEventId = this.getNewEventId();
				this.availability.eventId = newEventId;
			    var event = {
		            id: newEventId,
		            start: momentStartObj,
		            end: momentEndObj,
		            title: this.availability.teamName,
					backgroundColor:  this.getCampusColor(this.availability.teamName),
					textColor:  "white",
					overlap: false,
					borderColor: "black",
		        }

		        //render event
		        $('#calendar').fullCalendar('renderEvent', event, true);

				//add the new availability to the local availability list
				var newAvailability = {
					eventObj: event,
					eventId: newEventId,
					date: this.availability.date,
					timeStart: momentStartObj,
					timeEnd: momentEndObj,
					teamName: this.availability.teamName,
					teamId: this.getTeamIdFromTeamName(this.availability.teamName),
				}
				//push to local avail list
				this.availabilityList.push(newAvailability);

		        //close window
		        this.hideEditor();
		    }
		},

		//handles user clicking on event
		availabilityClickEvent: function(event, jsEvent, view) {

			//set currently selected availability to this event's data
			this.availability.eventObj = event;
			this.availability.eventId = event.id;
			this.availability.title = event.title;
			this.availability.date = moment(event.start, ["YYYY-MM-DD"]).format("YYYY-MM-DD");4
			this.availability.timeStart = moment(event.start, ["h:mma"]).format("h:mma");
			this.availability.timeEnd = moment(event.end, ["h:mma"]).format("h:mma");
			this.availability.teamName = event.title;

			//show edit availability
			this.showEditorWithEdit();
		},

		//handle edit of availability
		availabilityEdit: function() {
			//override the local availability data
			var momentStartObj = moment(this.availability.date + " " + this.availability.timeStart, ["YYYY-MM-DD h:mma"]);
			var momentEndObj = moment(this.availability.date + " " + this.availability.timeEnd, ["YYYY-MM-DD h:mma"]);

			//set local data
			this.availability.date = moment(momentStartObj).format("YYYY-MM-DD");
			this.availability.timeStart = moment(momentStartObj).format("h:mma");
			this.availability.timeEnd = moment(momentEndObj).format("h:mma");

			//all conditions are met for creating this availability
			if(this.checkTimePickerBounds() == false && this.checkIfAvailabilityConflicts("edit") == false) {

				//set edited datafields of event
				this.availability.eventObj.id = this.availability.eventId;
				this.availability.eventObj.start = momentStartObj;
				this.availability.eventObj.end = momentEndObj;
				this.availability.eventObj.title = this.availability.teamName;

				//change color for event based on campus
				this.availability.eventObj.color = this.getCampusColor(this.availability.teamName);

				//update availability on the local availability eventList
				for(var i = 0; i < this.availabilityList.length; i++) {
					if(this.availabilityList[i].eventId == this.availability.eventId) {
						this.availabilityList[i].date = this.availability.date;
						this.availabilityList[i].timeStart = this.availability.timeStart;
						this.availabilityList[i].timeEnd = this.availability.timeEnd;
						this.availabilityList[i].teamName = this.availability.teamName;
						this.availabilityList[i].eventObj = this.availability.eventObj;
						this.availabilityList[i].teamId = this.getTeamIdFromTeamName(this.availability.teamName);
						this.availabilityList[i].eventObj.backgroundColor = this.getCampusColor(this.availability.teamName);
						break;
					}
				}

				//update event on calendar
				$('#calendar').fullCalendar('updateEvent', this.availability.eventObj);

				//close window
				this.hideEditor();
			}
		},


		//Deletes the event
		availabilityDelete: function(availability, event) {
			$('#calendar').fullCalendar('removeEvents', this.availability.eventId);

			//deletes availability inside availability eventList//update availability on the local availability eventList
			for(var i = 0; i < this.availabilityList.length; i++) {
				if(this.availabilityList[i].eventId == this.availability.eventId) {
					this.availabilityList.splice(i, 1);
					break;
				}
			}

			//close window
			this.hideEditor();
		},

		//---------------------- calendar submission --------------------------------

		//blocks out most user interaction when availability is submitted
		availabilitySubmitComplete: function() {

			//disables resizing of events, editing of events, selection of new events
			$('#calendar').fullCalendar('editable', false);
			$('#calendar').fullCalendar('selectable', false);
			$('#calendar').fullCalendar('selectHelper', false);

			//set value of all availability list to submitted
			this.availabilityStatus = "Submitted";
			this.availabilitySubmitted = true;
			this.createAvailabilityToDb();

			//close popup
			this.showSubmitWindow = false;
		},

		//-------------------------- axios post/create -----------------------


		getUserTeamIdFromTeamId: function(teamId) {
			for(var i = 0; i < this.teamIdList.length; i++) {
				if(this.teamIdList[i] === teamId) {
					return this.userTeamIdList[i];
				}
			}
			return -1;
		},

		getTeamIdFromUserTeamId: function(userTeamId) {
			for(var i = 0; i < this.userTeamIdList.length; i++) {
				if(this.userTeamIdList[i] === userTeamId) {
					return this.teamIdList[i];
				}
			}
			return -1;
		},

		createAvailabilityToDb: function() {
			for(var i = 0; i < this.availabilityList.length; i++) {
				var postData = {};
				var currAvail = this.availabilityList[i];
				var epochStart = moment(currAvail.timeStart).format("X");
				var epochEnd = moment(currAvail.timeEnd).format("X");

				//data fields
				postData.userTeamId = this.getUserTeamIdFromTeamId(currAvail.teamId);
				postData.timeStart = epochStart;
				postData.timeEnd = epochEnd;
				postData.userId = this.loggedInUserId;
				postData.status = "Submitted";

				//send to database
				axios.post('/api/onetimeavailabilites', postData)
	                .catch(function (error) {
	                    console.log(error);
	                });
			}
		},


		//--------------------------- axios get -----------------------------

		populateUserTeamId(response) {
			this.userTeamIdList.push(response.data);
		},

		addUserTeamId: function(targetTeamId) {
			 axios.get('/api/users/' + this.loggedInUserId + '/teams/' + targetTeamId)
			 .then(this.populateUserTeamId)
			 .catch(function (error) {
				 console.log(error);
			 });
		},


		getTeamIdFromUserTeamId: function(targetUserTeamId) {
			for(var i = 0; i < this.userTeamIdList.length; i++) {
				if(targetUserTeamId == this.userTeamIdList[i]) {
					return this.teamIdList[i];
				}
			}
			return -1;
		},

		//populate this user's team list
		populateTeamList(response) {
			var localTeamIdList = response.data.map(teamId => teamId.id);
			var localTeamNameList = response.data.map(teamName => teamName.name);

			for(var i = 0; i < response.data.length; i++) {
				if(i = 0) {
					//set default team name
					this.availability.teamName = this.teamList[i].teamName;
				}
				var localTeamId = localTeamIdList[i];
				var localTeamName = localTeamNameList[i];
				this.teamNameList.push(localTeamName);
				this.teamIdList.push(localTeamId);
			}
			//sets default team
			this.availability.teamName = this.teamNameList[0];

			//returns list of userTeamIds
			for(var i = 0; i < this.teamIdList.length; i++) {
				this.addUserTeamId(this.teamIdList[i]);
			}

			//initialize the status of availability submission
			axios.get('/api/users/' + this.loggedInUserId + '/teams/' + this.teamIdList[0] +'/onetimeavailabilites/' + this.getEpochNextWeekMonday() + "/" + this.getEpochNextWeekSunday())
			.then(this.populateAvailabilityStatus)
			.catch(function (error) {
				console.log(error);
			});

			//initialize all existing availabilities for this user {
			axios.get('/api/onetimeavailabilites/user/' + this.loggedInUserId)
			.then(this.populateExistingAvailabilities)
			.catch(function (error) {
				console.log(error);
			});

			//initialize the existing availabilities for this availabiity weekday
			// axios.get('/api/users/' + this.loggedInUserId + '/onetimeavailabilites/' + this.getEpochNextWeekMonday() + "/" + this.getEpochNextWeekSunday())
			// 	.then(this.populateExistingAvailabilities)
			// 	.catch(function (error) {
			// 		console.log(error);
			// });
		},

		//initialize this user's availabilty's status
		populateAvailabilityStatus(response) {
			this.availabilityStatus = response.data;
			if(this.availabilityStatus == "Submitted") {
				this.availabilitySubmitted = true;
			}
		},

		//initalize this user's availability list that exists for this availability week
		populateExistingAvailabilities(response) {
			var localUserTeamIdList = response.data.map(userTeamId => userTeamId.userTeamId);
			var localTimeStartList = response.data.map(timeStartItem => timeStartItem.timeStart);
			var localTimeEndList = response.data.map(timeEndItem => timeEndItem.timeEnd);
			var localTeamIdList = [];
			var localTeamNameList = [];

			//sets local list of team id
			for(var i = 0; i < localUserTeamIdList.length; i++) {
				localTeamIdList.push(this.getTeamIdFromUserTeamId(localUserTeamIdList[i]));
			}

			//adds retrieved data to local availability list
			for(var i = 0; i < response.data.length; i++) {
				var newTeamName = this.getTeamNameFromTeamId(localTeamIdList[i]);
				var newEventId = this.getNewEventId();

				//format moment objects from epoch
				var startMomentObj = moment(localTimeStartList[i], ["X"]);
				var endMomentObj = moment(localTimeEndList[i], ["X"]);

				var event = {
				   id: newEventId,
				   start: startMomentObj,
				   end: endMomentObj,
				   title: newTeamName,
				   backgroundColor:  this.getCampusColor(newTeamName),
				   textColor:  "white",
				   overlap: false,
				   borderColor: "black",
			   	}

				var localAvailability = {
					eventObj: event,
					eventId: newEventId,
					date: moment(startMomentObj).format("YYYY-MM-DD"),
					timeStart: moment(startMomentObj).format("h:mma"),
					timeEnd: moment(endMomentObj).format("h:mma"),
					teamName: newTeamName,
					teamId: localUserTeamIdList[i],
				}
				//push local list
				this.availabilityList.push(localAvailability);

				//render the event
				 $('#calendar').fullCalendar('renderEvent', event, true);
			}
		},
	},


	created: function() {

		//initialize local list of teams the user belongs to
		axios.get('/api/users/' + this.loggedInUserId + '/teams')
		.then(this.populateTeamList)
		.catch(function (error) {
			console.log(error);
		});
	},

	mounted () {
		//initialize calendar view
		this.defaultCalendarView();

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
