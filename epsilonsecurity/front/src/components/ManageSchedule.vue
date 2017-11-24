<template>
	<v-container fill-width fill-height class="pa-0">
		<v-layout column wrap>

			<v-flex xs12 id="option-sideMenu">
				<h2> TODO OPTIONS PANEL </h2>
			</v-flex>

			<v-flex xs12 id="calendar-pkg">
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
				</v-layout>
			</v-flex>

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

									<!-- User -->
									<v-flex xs12>
										<v-select
											label="ShiftType"
											v-bind:items="userList"
											v-model="shift.user"
											single-line
											item-value="text"
											prepend-icon="map"
											required>
										</v-select>
									</v-flex>

									<!-- ShiftType -->
									<v-flex xs12>
										<v-select
											label="ShiftType"
											v-bind:items="shiftTypeList"
											v-model="shift.shiftType"
											single-line
											item-value="text"
											prepend-icon="map"
											required>
										</v-select>
									</v-flex>

									<!-- Campus -->
									<v-flex xs12>
										<v-select
											label="Campus"
											v-bind:items="campusList"
											v-model="shift.campus"
											single-line
											item-value="text"
											prepend-icon="map"
											required>
										</v-select>
									</v-flex>

									<!-- date  picker -->
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
		</v-layout>
	</v-container>
</template>

<script>
import Vue from 'vue'
import moment from 'moment'
import axios from 'axios'
import FullCalendar from 'vue-full-calendar'
Vue.use(FullCalendar)
window.jQuery = window.$ = require('jquery')

export default {
	data: function () {
		return {
			// ----------------- MODALS --------------------------------
			showEditorWindow: false, //editor window popup
			showCreateOptions: false, //show the create button for the editor window
			showEditOptions: false, //show the edit and delete buttons for the editor window

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

			//Lists
			userList: [],
			campusList: [],
			shiftTypeList: [],

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
				shiftType: null,
				userId: -1,
				title: "",
				date: this.getNextWeekMondayMoment(),
				timeStart: this.getDefaultStartTime(),
				timeEnd: this.getDefaultEndTime(),
				campus: "",
			},

			assignEventId: 0, //assigns id to event

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

				eventClick: this.shiftClickEvent, //triggered with an event is clicked
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
			var momentStartObj = moment(this.shift.timeStart, ["h:mma"]);
			var momentEndObj = moment(this.shift.timeEnd, ["h:mma"]);

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

		//TODO handle creation of an shift
		shiftCreate: function() {

		    //override the local shift data
		    var momentStartObj = moment(this.shift.date + " " + this.shift.timeStart, ["YYYY-MM-DD h:mma"]);
		    var momentEndObj = moment(this.shift.date + " " + this.shift.timeEnd, ["YYYY-MM-DD h:mma"]);
		    this.shift.date = moment(momentStartObj).format("YYYY-MM-DD");
		    this.shift.timeStart = moment(momentStartObj).format("h:mma");
		    this.shift.timeEnd = moment(momentEndObj).format("h:mma");

		    //all conditions are met for creating this shift
		    if(this.checkTimePickerBounds() == false) {
		        //create new object for event with the newly created moment objects
		        var event = {
		            id: this.getNewEventId(),
		            start: momentStartObj,
		            end: momentEndObj,
		            title: this.shift.campus,
		        }

		        //change color for event based on campus
		        event.color = this.getCampusColor(this.shift.campus);

		        //change textcolor
		        event.textColor = "white"

		        //render event
		        $('#calendar').fullCalendar('renderEvent', event, true);

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
			this.shift.date = moment(event.start, ["YYYY-MM-DD"]).format("YYYY-MM-DD");
			this.shift.timeStart = moment(event.start, ["h:mma"]).format("h:mma");
			this.shift.timeEnd = moment(event.end, ["h:mma"]).format("h:mma");
			this.shift.campus = event.title;

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
			if(this.checkTimePickerBounds() == false && this.checkIfScheduleConflicts("edit") == false) {

				//set edited datafields of event
				this.shift.eventObj.id = this.shift.eventId;
				this.shift.eventObj.start = momentStartObj;
				this.shift.eventObj.end = momentEndObj;
				this.shift.eventObj.title = this.shift.campus;

				//change color for event based on campus
				this.shift.eventObj.color = this.getCampusColor(this.shift.campus);

				//update event
				$('#calendar').fullCalendar('updateEvent', this.shift.eventObj);

				//close window
				this.hideEditor();
			}
			else {
				//TODO: make some sort of error indication that it the shift you are trying to edit is incorrect
			}
		},


		//Deletes the event
		shiftDelete: function(shift, event) {
			$('#calendar').fullCalendar('removeEvents', this.shift.eventId);

			//close window
			this.hideEditor();
		},

		//---------------------- calendar submission --------------------------------


		//retrives all events on local calendar
		getShiftList: function() {
			this.shiftList = $('#calendar').fullCalendar('clientEvents');
			return this.shiftList;
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

	},

	created: function() {
		//TODO requests

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

#my-shift {
	background: white;
	width: 100%;
	height: 100%;
}

#option-sideMenu {
	width: 35%;
	height: 100%;
	background: white;
}

#calendar-pkg {
	width: 65%;
	height: 100%;
}

#my-calendar {
	height: 97%;
	width: 100%;
}
</style>
