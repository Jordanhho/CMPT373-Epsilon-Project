<template>
	<v-container fill-width fill-height class="pa-0">
		<v-layout column wrap>

			<v-flex xs12 id="option-sideMenu">
				<h2> TODO OPTIONS PANEL </h2>
			</v-flex>

			<v-flex xs id="calendar-pkg">
				<v-layout row wrap>
					<v-flex xs12 id="my-calendar">
		                <full-calendar
		                    :event-sources="eventSources"
		                    :config="config"
		                    id="calendar">
		                </full-calendar>
					</v-flex>
				</v-layout>
			</v-flex>

			<!-- popup info show for clicking on availability, dragging event availability, clicking + button -->
			<v-layout row justify-center>
				<v-dialog v-model="showAvailability" max-width="400px" persistent lazy full-width>
					<v-card>
						<v-card-title>
							<span class="headline">Availability Info</span>
						</v-card-title>
						<v-card-text>
							<v-container grid-list-xs>
								<v-layout wrap>

									<!-- Campus -->
									<v-flex xs12>
                                        <v-text-field
                                            label="Campus"
                                            v-model="availability.campus"
                                            prepend-icon="map"
                                            disabled>
                                        </v-text-field>
									</v-flex>

									<!-- date -->
									<v-flex xs12>
                                        <v-text-field
                                            label="Date"
                                            v-model="availability.date"
                                            prepend-icon="event"
                                            disabled>
                                        </v-text-field>
									</v-flex>

									<!-- time start -->
									<v-flex xs12>
                                        <v-text-field
                                            label="Time Start"
                                            v-model="availability.timeStart"
                                            prepend-icon="access_time"
                                            disabled>
                                        </v-text-field>
									</v-flex>

									<!-- time end picker -->
									<v-flex  xs12>
                                        <v-text-field
                                            label="Time End"
                                            v-model="availability.timeEnd"
                                            prepend-icon="access_time"
                                            disabled>
                                        </v-text-field>
									</v-flex>
								</v-layout>
							</v-container>
							<v-card-actions>
								<v-spacer></v-spacer>
								<v-btn v-if="showAvailability" color="primary" flat @click.stop="availabilityApprove">
									Approve
								</v-btn>
								<v-btn v-if="showAvailability" color="primary" flat @click.stop="availabilityDelete">
								    Deny
								</v-btn>
								<v-btn color="primary" flat @click.stop="hideEditor">
									Close
								</v-btn>
							</v-card-actions>
						</v-card-text>
					</v-card>
				</v-dialog>
			</v-layout>
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
			showAvailability: false,

			//---------------- availability type and list ------------------s
			availabilityList: [],

			availability: {
				eventObj: null,
				eventId: -1,
				title: "",
				date: null,
				timeStart: null,
				timeEnd: null,
				campus: "",
			},

            //campus colors
            burnabyCampus: "red",
            surreyCampus: "blue",
            vancouverCampus: "teal",
            noCampus: "purple",


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
				editable: false, //disallows resizing of events
				selectable: false, //prevents dragging on calendar
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

		//create a moment object that is the starting monday of the current day and next week
		getNextWeekMondayMoment: function() {
			var nextMondayDate = moment().startOf('isoweek').add(7, 'days').format("YYYY-MM-DD");
			return nextMondayDate;
		},

		//set the default viewed week for calendar
		initializeCalendarView() {
			$('#calendar').fullCalendar('gotoDate', this.getNextWeekMondayMoment());
		},

		//------------------------------ modal show/close -------------------

		showAvailabilityWindow: function() {
			this.showAvailability = true;
		},

		hideEditor: function() {
			this.showAvailability = false;
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

		//--------------------- view event calendar operations ----------------------


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

			//show availability
			this.showAvailabilityWindow();
		},

		//TODO handle edit of availability
		availabilityApprove: function() {

			//TODO set status of this availability object to approved

            //set the calendar's event color for something to determine it is approved
            this.availability.eventObj.color = "green";

            //update event
            $('#calendar').fullCalendar('updateEvent', this.availability.eventObj);

            this.hideEditor();

		},


		//Deletes the event
		availabilityDelete: function(availability, event) {
			$('#calendar').fullCalendar('removeEvents', this.availability.eventId);

            //TODO delete request to database to remove this availability

			//close window
			this.hideEditor();
		},

		//---------------------- calendar submission --------------------------------


		//retrives all events on local calendar
		getAvailabilityList: function() {
			this.availabilityList = $('#calendar').fullCalendar('clientEvents');
			return this.availabilityList;
		},

	},

	created: function() {
		//TODO initialize local list of availabiltiies FOR ALL USERS
	},

	mounted () {

		//initialize calendar view
		this.initializeCalendarView();
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
	width: 100%;
	height: 100%;
}

</style>
