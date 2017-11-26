<template>
		<v-container fluid fill-width fill-height class="pa-0">
			<v-layout column wrap>
				<v-flex xs12>
					<div id = "option-bar">
						<h2>
							TODO OPTIONS PANEL
						</h2>
					</div>

					<div id = "calendar-pkg">
						<v-layout row wrap>
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
									<v-btn absolute dark fab top right color="blue">
										<v-icon>
											add
										</v-icon>
									</v-btn>
								</v-card-text>
							</v-flex>
						<v-layout row wrap>
					</div>

					<!-- popup editor for clicking on shift, dragging event shift, clicking + button -->
					<v-layout row justify-center>
						<v-dialog v-model="showEditorWindow" max-width="400px" persistent lazy full-width>
							<v-card>
								<v-card-title>
									<span class="headline">Shift Info</span>
								</v-card-title>
								<v-card-text>
									<v-container grid-list-xs>
										<v-layout wrap>

											<!-- Campus -->
											<v-flex xs12>
												<v-select
													label="Campus"
													v-bind:items="teamList"
													v-model="shift.teamName"
													single-line
													item-text="teamName"
													item-value="teamId"
													prepend-icon="map"
													required>
												</v-select>
											</v-flex>

											<!-- date picker -->
											<v-flex xs12>
												<v-dialog v-model="dateStartModal" persistent lazy full-width>
													<v-text-field
														slot="activator"
														label="Date"
														v-model="shift.date"
														prepend-icon="event"
														readonly
														required>
													</v-text-field>
													<v-date-picker
														v-model="shift.date"
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
														v-model="shift.timeStart"
														prepend-icon="access_time"
														readonly
														required>
													</v-text-field>
													<v-time-picker
														v-model="shift.timeStart"
														:allowed-hours="allowedTimes.hours"
														:allowed-minutes="allowedTimes.minutes"
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
													</v-time-picker>this.showEditorWithCreate();
												</v-dialog>
											</v-flex>

											<!-- time end picker -->
											<v-flex  xs12>
												<v-dialog v-model="timeEndModal" persistent lazy full-width>
													<v-text-field
														slot="activator"
														label="Time End"
														v-model="shift.timeEnd"
														prepend-icon="access_time"
														readonly
														required>
													</v-text-field>
													<v-time-picker
														v-model="shift.timeEnd"
														:allowed-hours="allowedTimes.hours"
														:allowed-minutes="allowedTimes.minutes"
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
													</v-time-picker>
												</v-dialog>
											</v-flex>
										</v-layout>
									</v-container>
									<v-card-actions>
										<v-spacer></v-spacer>
										<v-btn  v-if="showCreateOptions" color="primary" flat @click.stop="shiftCreate">
											Create
										</v-btn>
										<v-btn v-if="showEditOptions" color="primary" flat @click.stop="shiftEdit()">
											Edit
										</v-btn>
										<v-btn v-if="showEditOptions" color="primary" flat @click.stop="shiftDelete">
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

			//----------------- Submission ---------------------------

			//------------------- USER ---------------------------
			//current user Id
			userId: 2,

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
			restrictDayRange: {
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
			teamList: [],
			team: {
				teamName: "",
				teamId: -1,
			},

			//campus colors
			burnabyCampus: "red",
			surreyCampus: "blue",
			vancouverCampus: "teal",
			noCampus: "purple",

			//---------------- shift type and list ------------------s
			shiftList: [],

			shift: {
				eventObj: null,
				eventId: -1,
				date: null,
				timeStart: null,
				timeEnd: null,
				teamName: "",
				teamId: -1,
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
				navLinks: true, //allows clicking on a day

				//----------------- selection of events --------------------------
				editable: false, //allows resizing of events
				selectable: true, //allows dragging on calendar.
				selectHelper: true, //allows to make an event on calendar
				selectOverlap: false, //do not allow event overlap selection

				selectMinDistance: 5, //minimum distance click has to move inorder to detect as event PREVENT MISCLICKS
				selectLongPressDelay: 1000, //minimum miliseconds user holds down before it counts as a selectable

				//triggered with an event is clicked
				eventClick: this.shiftClickEvent,

				//triggered after a selection is made, i.e user stops dragging.
				select: this.shiftCreateDrag,

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
		}
	},

	methods: {

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
			var momentStartObj = moment(this.shift.timeStart, ["h:mma"]);
			var momentEndObj = moment(this.shift.timeEnd, ["h:mma"]);

			//compare if they are equal in minutes and hours
			if((moment(momentStartObj).get('hour') == moment(momentEndObj).get('hour')) && (moment(momentStartObj).get('minute') == moment(momentEndObj).get('minute'))) {
				this.showTimeError = true;
				this.timeErrorMessage = "The Time End is the same as Time Start";
				this.timeBoundsCheck = true;
				return true;
			}
			else if(momentEndObj < momentStartObj) {
				this.showTimeError = true;
				this.timeErrorMessage = "The Time End is before Time Start";
				this.timeBoundsCheck = true;
				return true;
			}
			else {
				this.timeBoundsCheck = false;
				return false;
			}
		},


		//compares the current shift to any preexisting shift if their time conflicts
		checkIfShiftConflicts(action) {

			//parse/set the shift start and end moment object's date and time
			var momentStartObj = moment(this.shift.date + " " + this.shift.timeStart, ["YYYY-MM-DD h:mma"]);
			var momentEndObj = moment(this.shift.date + " " + this.shift.timeEnd, ["YYYY-MM-DD h:mma"]);
			var currMomentStart = moment().set({'date': moment(momentStartObj).get('date'), 'hour': moment(momentStartObj).get('hour'), 'minute': moment(momentStartObj).get('minute')});
			var currMomentEnd = moment().set({'date': moment(momentEndObj).get('date'), 'hour': moment(momentEndObj).get('hour'), 'minute': moment(momentEndObj).get('minute')});

			//get local list of events
			var eventList = $('#calendar').fullCalendar('clientEvents');
			for(var i = 0; i < eventList.length; i++) {
				var currEvent = eventList[i];

				//if same object only on edit
				if((action == "edit") && (currEvent.id == this.shift.eventId)) {
					continue;
				}
				//if not same day, continue
				if(moment(currEvent.start).get('day') != moment(currMomentStart).get('day')) {
					continue;
				}
				//compare time in hours and minutes with current event object and currently selected shift
				if((currEvent.start <= currMomentEnd) && (currEvent.end >= currMomentStart)) {
					this.showTimeError = true;
					this.timeErrorMessage = "Time range conflicts with another shift";
					this.timeConflictCheck = true;
					return true;
				}
			}
			this.timeConflictCheck = false;
			return false;
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



		//---------------------create, delete, edit calendar operations ----------------------
		shiftCreateDrag: function(start, end, jsEvent, view) {
			this.shift.date = moment(start).format("YYYY-MM-DD");
			this.shift.timeStart = moment(start).format("h:mma");
			this.shift.timeEnd = moment(end).format("h:mma");
			this.showEditorWithCreate();
		},


		//handle creation of an shift
		shiftCreate: function() {

			//override the local shift data
			var momentStartObj = moment(this.shift.date + " " + this.shift.timeStart, ["YYYY-MM-DD h:mma"]);
			var momentEndObj = moment(this.shift.date + " " + this.shift.timeEnd, ["YYYY-MM-DD h:mma"]);
			this.shift.date = moment(momentStartObj).format("YYYY-MM-DD");
			this.shift.timeStart = moment(momentStartObj).format("h:mma");
			this.shift.timeEnd = moment(momentEndObj).format("h:mma");

			//all conditions are met for creating this shift
			if(this.checkTimePickerBounds() == false && this.checkIfShiftConflicts("create") == false) {
				//create new object for event with the newly created moment objects
				var newEventId = this.getNewEventId();newEventId
				this.shift.eventId = newEventId;
				var event = {
					id: newEventId,
					start: momentStartObj,
					end: momentEndObj,
					title: this.shift.teamName,
				}

				//change color for event based on team
				event.color = this.getCampusColor(this.shift.teamName);

				//change textcolor
				event.textColor = "white"

				//render event
				$('#calendar').fullCalendar('renderEvent', event, true);

				//add the new shift to the local shift list
				var newShift = {
					eventObj: event,
					eventId: newEventId,
					date: this.shift.date,
					timeStart: momentStartObj,
					timeEnd: momentEndObj,
					teamName: this.shift.teamName,
					teamId: this.getTeamIdFromTeamName(this.shift.teamName),
				}

				console.log("newShift=[eventId=" + newShift.eventId + "], date=[" + moment(newShift.date, ["YYYY-MM-DD"]).format("YYYY-MM-DD h:mma") + "], timeStart=[" + moment(newShift.timeStart, ["h:mma"]).format("YYYY-MM-DD h:mma") + "], timeEnd=[" + moment(newShift.timeEnd, ["h:mma"]).format("YYYY-MM-DD h:mma") + "], teamName=" + newShift.teamName + "], teamId=[" + newShift.teamId + "]");
				this.shiftList.push(newShift);

				//close window
				this.hideEditor();
			}
		},

		//handles user clicking on event
		shiftClickEvent: function(event, jsEvent, view) {

			//set currently selected shift to this event's data
			this.shift.eventObj = event;
			this.shift.eventId = event.id;
			this.shift.title = event.title;
			this.shift.date = moment(event.start, ["YYYY-MM-DD"]).format("YYYY-MM-DD");4
			this.shift.timeStart = moment(event.start, ["h:mma"]).format("h:mma");
			this.shift.timeEnd = moment(event.end, ["h:mma"]).format("h:mma");
			this.shift.teamName = event.title;

			//show edit shift
			this.showEditorWithEdit();
		},

		//TODO handle edit of shift
		shiftEdit: function() {

			//override the local shift data
			var momentStartObj = moment(this.shift.date + " " + this.shift.timeStart, ["YYYY-MM-DD h:mma"]);
			var momentEndObj = moment(this.shift.date + " " + this.shift.timeEnd, ["YYYY-MM-DD h:mma"]);
			this.shift.date = moment(momentStartObj).format("YYYY-MM-DD");
			this.shift.timeStart = moment(momentStartObj).format("h:mma");
			this.shift.timeEnd = moment(momentEndObj).format("h:mma");

			//all conditions are met for creating this shift
			if(this.checkTimePickerBounds() == false && this.checkIfShiftConflicts("edit") == false) {

				//set edited datafields of event
				this.shift.eventObj.id = this.shift.eventId;
				this.shift.eventObj.start = momentStartObj;
				this.shift.eventObj.end = momentEndObj;
				this.shift.eventObj.title = this.shift.teamName;

				//change color for event based on campus
				this.shift.eventObj.color = this.getCampusColor(this.shift.teamName);

				//update shift on the local shift eventList
				for(var i = 0; i < this.shiftList.length; i++) {
					if(this.shiftList[i].eventId == this.shift.eventId) {
						this.shiftList[i].date = this.shift.date;
						this.shiftList[i].timeStart = this.shift.timeStart;
						this.shiftList[i].timeEnd = this.shift.timeEnd;
						this.shiftList[i].teamName = this.shift.teamName;
						this.shiftList[i].eventObj = this.shift.eventObj;
						this.shiftList[i].teamId = this.getTeamIdFromTeamName(this.shift.teamName);
						break;
					}
				}

				//update event on calendar
				$('#calendar').fullCalendar('updateEvent', this.shift.eventObj);

				//close window
				this.hideEditor();
			}
		},


		//Deletes the event
		shiftDelete: function(shift, event) {
			$('#calendar').fullCalendar('removeEvents', this.shift.eventId);

			//deletes shift inside shift eventList//update shift on the local shift eventList
			for(var i = 0; i < this.shiftList.length; i++) {
				if(this.shiftList[i].eventId == this.shift.eventId) {
					this.shiftList.splice(i, 1);
					break;
				}
			}

			//close window
			this.hideEditor();
		},

		//---------------------- helper methods for date, time pickers ------------

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

		//-------------------- Database requests ----------------------


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
		//set the time and date restrictions
		this.restrictDayRange.min = this.getTodayMomentTime();
		this.allowedDates = this.restrictDayRange;
		this.allowedTimes = this.restrictTimeIncrements;
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
}

#option-bar {
	width: 35%;
	height: 100%;
	background: white;
}

#calendar-pkg {
	width: 65%;
	height: 90%;
}

#my-calendar {
    width: 100%
	height: 100%;
}

</style>
