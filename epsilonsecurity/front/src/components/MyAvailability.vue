<template>
	<v-container fluid fill-width fill-height class="pa-0">
		<v-layout column wrap>
			<v-flex xs12>

				<!-- toast for submitted, creating /editing availability -->
				<v-snackbar
					:timeout="1000"
					:top="'top'"
					v-model="showToast"
				>
					<v-btn flat color="white" class="text-xs-center"> {{ toastMsg }} </v-btn>
				</v-snackbar>

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

				<!-- center app -->
				<div id = "my-calendar">
					<!-- calendar -->
					<full-calendar
						:event-sources="eventSources"
						:config="config"
						ref="calendar"
						id="calendar">
					</full-calendar>

					<!-- floating add availbility button -->
					<!--
					<v-card-text style="height: 1px; position: relative" @click.stop="showCreateAvailability = true">
						<v-btn
							absolute
							dark
							fab
							top
							right
							color="blue"
							:disabled="availabilitySubmitted"
						>
							<v-icon>
								add
							</v-icon>
						</v-btn>
					</v-card-text>
					-->
				</div>

				<!-- bottom bar for submission (footer) -->
				<div id="submission-bar">
					<v-card class="text-xs-center" dark color = "white">
						<v-card-actions>
							<v-btn color="primary" block @click.stop="AvailabilitySubmitConfirmation" :disabled="availabilitySubmitted">
								Submit
							</v-btn>
						</v-card-actions>
					</v-card>
				</div>

				<!-- popup creating availability from + button -->
				<v-layout row justify-center>
					<v-dialog v-model="showCreateAvailability" persistent max-width="500px">
						<v-card>
        					<v-card-title>
								<span class="headline">New Availability</span>
        					</v-card-title>
							<v-card-text>
								<v-container grid-list-md>

									<!-- date start picker -->
									<v-flex xs12 sm6 md4>
										<v-dialog
											persistent
											v-model="dateStartModal"
											lazy
											full-width
										>
											<v-text-field
												slot="activator"
												label="Date Start"
												v-model="availability.dateStart"
												prepend-icon="event"
												readonly
												:disabled="availabilitySubmitted"
											>
											</v-text-field>
											<v-date-picker v-model="availability.dateStart" scrollable actions>
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
									<v-flex xs12 sm6 md4>
										<v-dialog
											persistent
											v-model="timeStartModal"
											lazy
											full-widthmodal
										>
											<v-text-field
												slot="activator"
												label="Time Start"
												v-model="availability.timeStart"
												prepend-icon="access_time"
												readonly
												:disabled="availabilitySubmitted"
											>
											</v-text-field>
											<v-time-picker v-model="availability.timeStart" actions>
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

									<!-- date end picker -->
									<v-flex xs12 sm6 md4>
										<v-dialog
											persistent
											v-model="dateEndModal"
											lazy
											full-width
										>
											<v-text-field
												slot="activator"
												label="Date End"
												v-model="availability.dateEnd"
												prepend-icon="event"
												readonly
												:disabled="availabilitySubmitted"
											>
											</v-text-field>
											<v-date-picker v-model="availability.dateEnd" scrollable actions>
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

									<!-- time end picker -->
									<v-flex xs12 sm6 md4>
										<v-dialog
											persistent
											v-model="timeEndModal"
											lazy
											full-width
										>
											<v-text-field
												slot="activator"
												label="Time End"
												v-model="availability.timeEnd"
												prepend-icon="access_time"
												readonly
												:disabled="availabilitySubmitted"
											>
											</v-text-field>
											<v-time-picker v-model="availability.timeEnd" actions>
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
									<v-flex xs12 sm6 md4>
										<v-select
											v-bind:items="campusList"
											v-model="availability.campus"
											label="Campus"
											item-value="text"
											single-line
											:disabled="availabilitySubmitted"
										>
										</v-select>
									</v-flex>

								</v-container>
							</v-card-text>
							<v-card-actions>
								<v-spacer></v-spacer>
								<v-btn color="primary" flat @click.stop="AvailabilityCreation(availability)" :disabled="availabilitySubmitted">
									Create
								</v-btn>
								<v-btn color="primary" flat @click.stop="showCreateAvailability = false">
									Close
								</v-btn>
							</v-card-actions>
						</v-card>
					</v-dialog>
				</v-layout>

				<!-- popup for when clicked on existing availability or dragging to create an availability -->
				<v-layout row justify-center>
					<v-dialog v-model="showEditAvailability" persistent max-width="500px">
						<v-card>
							<v-card-title>
								<span class="headline">Availability Info</span>
							</v-card-title>
							<v-card-text>
								<v-container grid-list-md>
									<v-flex xs12 sm6 md4>
										<v-text-field label="Time Start" v-model="availability.timeStart" :disabled="availabilitySubmitted"></v-text-field>
										<v-text-field label="Time End" v-model="availability.timeEnd" :disabled="availabilitySubmitted"></v-text-field>
									</v-flex>
									<v-flex xs12 sm6 md4>
										<v-select
											v-bind:items="campusList"
											v-model="availability.campus"
											label="Campus"
											item-value="text"
											single-line
											:disabled="availabilitySubmitted"
										>
										</v-select>
									</v-flex>
								</v-container grid-list-md>
							</v-card-text>
							<v-card-actions>
								<v-spacer></v-spacer>
								<v-btn color="primary" flat @click.stop="AvailabilityEdit(availability)" :disabled="availabilitySubmitted">
									Add
								</v-btn>
								<v-btn color="primary" flat @click.stop="AvailabilityDeletion(availability)" :disabled="availabilitySubmitted">
									Delete
								</v-btn>
								<v-btn color="primary" flat @click.stop="showEditAvailability = false">
									Close
								</v-btn>
							</v-card-actions>
						</v-card>
					</v-dialog>
				</v-layout>

				<!-- popup for when submit availbility is clicked -->
				<v-dialog v-model="showConfirmSubmission">
					<v-card>
						<v-container grid-list-md>
							<v-layout wrap>
								<v-card-text>
									<v-flex xs12>
										<v-card-text>
											Confirm Submission of Availability Schedule?
										</v-card-text>
									</v-flex>
								</v-card-text>
							</v-layout wrap>
						</v-container grid-list-md>
						<v-card-actions>
							<v-spacer></v-spacer>
							<v-btn color="primary" flat @click.stop="AvailabilitySubmitComplete">
								Submit
							</v-btn>
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
import store from '../store/store'
import VToolbar from "vuetify/es5/components/VToolbar/VToolbar";

//calendar
import FullCalendar from 'vue-full-calendar'
Vue.use(FullCalendar) // add the vue-full-calendar plugin to Vue
window.jQuery = window.$ = require('jquery') // we need jquery too

export default {
    data: function () {
		return {

			//time picker/date picker modals
			dateStartModal: false,
			timeStartModal: false,
			dateEndModal: false,
			timeEndModal: false,

			//Data contains list of avaliablities
			availabilityList: [
			],

			availability: {
				title: 'Event',
				timeStart: this.getTodaysMomentTime,
				dateStart: this.getTodaysMomentDate,
				timeEnd: this.getTodaysMomentTime,
				dateEnd: this.getTodaysMomentDate,
				campus: "Burnaby"
			},

			//for selection of campus
			campusList: [
				{ text: 'Burnaby'},
				{ text: 'Surrey'},
				{ text: 'Vancouver'},
			],

			//boolean for if avail is submitted
			availabilitySubmitted: false,

			//submission availabiliy popup
			showConfirmSubmission: false,

			//creation of availability window
			showCreateAvailability: false,

			//edit of availability window
			showEditAvailability: false,

			//status of availability
			availabilityStatus: "Open",

			//toast msg
			toastMsg: "Nothing here",
			showToast: false,

			config: {

				defaultView: 'agendaWeek',
				height: 'parent',
				allDaySlot: false,
				timezone: 'local',
		
				header: {
					left: 'prev,next,today',
					center: '',
					right: 'month,agendaWeek,agendaDay'
				},

				//15 minute increment
				slotDuration: '00:15:00',

				// scroll calendar to the specified time (12 AM)
				scrollTime: '00:00:00',

				//shows today
				nowIndicator: true,

				//allows resizing of events
				editable: false,

				// allows dragging on calendar.
				selectable: true,

				// allows to make an event on calendar
				selectHelper: true,

				// triggered with an event is clicked
				eventClick: this.AvailabilityClick,

				// triggered before an event is rendered - our chance to enhance the event.
				renderEvent: this.AvailabilityRender,

				// triggered after a selection is made, i.e user stops dragging.
				select: this.AvailabilitySelection,
				
				//todo trigger when attemping to delete an event
				//eventDestroy: this.AvailabilityDeletion,

				//minimum distance click has to move inorder to detect as event PREVENT MISCLICKS
				selectMinDistance: 5,
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

		getAvailabilityTitle: function() {
			return this.clickedAvailability.title;
		},

		getAvailabilityDateStart: function() {
			const start = this.clickedAvailability.start; // type moment()
			return moment(start).format("LL");
		},
		getAvailabilityDateEnd: function() {
			const end = this.clickedAvailability.end; // type moment()
			return moment(end).format("LL");
		},

		getAvailabilityTimeStart: function() {
			const start = moment(this.clickedAvailability.start).format("LT");
			return start;
		},

		getAvailabilityTimeEnd: function() {
			const end = moment(this.clickedAvailability.end).format("LT");
			return end;
		},

		getAvailabilityTimeTotal: function() {
			const start = moment(this.clickedAvailability.start).format("LT");
			const end = moment(this.clickedAvailability.end).format("LT");
			const duration = `${start} - ${end}`;
			return duration
		},

		getTodaysMomentDate: function() {
			var dateMoment = moment().format('LL'); 
			return dateMoment;
		},

		getTodaysMomentTime: function() {
			var timeMoment = moment().format('LT');
			return timeMoment;
		}
	},

	methods: {

        // handles user clicking on event
		AvailabilityClick: function(calEvent, jsEvent, view) {
			//todo loads availbility before displaying

			//todo able to edit availability
			this.clickedAvailability = calEvent;
			this.showEditAvailability = true;
		},	

		//handles drag selection of availability on calendar
		AvailabilitySelection: function(start, end, jsEvent, view) {

			console.log("drag event start:", start);
			console.log("drag event end:", end);

			//only allows deletion before submission
			if (this.availabilitySubmitted == false) {
				//set the start and end of this availability
				this.availability.timeStart = start;
				this.availability.timeEnd = end;

				//show edit avaialbility
				this.showEditAvailability = true;
			}
			else {
				//do nothing as submission is denied after submission of availability
			}
		},

		//creates an event
		AvailabilityRender: function(event) {
			// todo must also remove plugin on eventDestroy()
		},

		//todo handle creation of an availability
        AvailabilityCreation: function(availability) {
            //debug print
			console.log("New Avail date start:", availability.dateStart);
			console.log("New Avail time start:", availability.timeStart);
			console.log("New Avail date end:", availability.dateEnd);
			console.log("New Avail time start:", availability.timeStart);
            console.log("New Avail time campus:", availability.campus);
			
            //add availability list to be later added to database
			//this.availabilityList.push({availability});
			
			//combine the moment objects with date and time
			var momentStartObj = moment();
			momentStartObj.moment().format(availability.dateStart, availability.timeStart);

			var momentEndObj = moment();
			momentEndObj.moment().format(availability.dateEnd, availability.timeEnd);

			//create new object for event with the newly created moment objects
			var eventItem = {
				id: -1,
				start: momentStartObj.start,
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
				eventItem.color = "Yellow";
			}
			//purple if not specified -> for debug
			else {
				eventItem.color = "purple";
			}

			//change textcolor
			eventItem.textColor = "white"

			//render event
			$('#calendar').fullCalendar('renderEvent', eventItem, true);

			//This function seems to be defined such that the above only works
			//AvailabilityRender(eventItem);

            //close window
			this.showCreateAvailability = false;

			//show msg that it has been created
            this.toastMsg = "Created Availability";
            this.showToast = true;
		},

		//todo handle creation of an availability
        AvailabilityEdit: function(availability) {
            //debug print
            console.log("Edit Avail time start:", availability.timeStart);
            console.log("Edit Avail ime end:", availability.timeEnd);
            console.log("Edit Avail time campus:", availability.campus);

            //add availability list to be later added to database
			//this.availabilityList.push({availability});
			
			//create new object for event
			var eventItem = {
				id: -1, //id init as -1 for now
				start: availability.timeStart,
				end: availability.timeEnd,
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
			//purple if not specified for debug
			else {
				eventItem.color = "purple";
			}

			//change text color for white
			eventItem.textColor = "white"

			//render event
			$('#calendar').fullCalendar('renderEvent', eventItem, true);

            //close window
			this.showEditAvailability = false;

			//show msg that it has been created
            this.toastMsg = "Edited Availability";
            this.showToast = true;
		},

		//Deletes the event if button for deletion is pressed
		AvailabilityDeletion: function() {
			//delete event
			this.$refs.calendar.$emit('remove-event', this.clickedAvailability);
			this.clickedAvailability = {};

			//turn off popup after deletion
			this.showEditAvailability = false;
		},

		//submit availability
        AvailabilitySubmitConfirmation: function() {
			this.showConfirmSubmission = true;
		},

		//blocks out most user interaction when availability is submitted
		AvailabilitySubmitComplete: function() {

			//disables resizing of events, editing of events, selection of new events
            this.editable = false;
            this.selectable = false;
            this.selectHelper = false;

            //set values
            this.availabilityStatus = "Pending";
			this.availabilitySubmitted = true;

			//close popup
            this.showConfirmSubmission = false;

			//toast msg
            this.toastMsg = "Submitted Availability";
            this.showToast = true;
		},

		populateAvailabilityList: function() {

			//grab all generated client events
			availabilityList = $('#calendar').fullCalendar('clientEvents');
			this.availabilityList = response.data;
		}

	},

    components: {
        VToolbar
	},

	//todo creates to avail database
	created: function() {
		axios.get('api/onetimeavailabilites')
			.then(this.populateAvailabilityList)
			.catch(function (error) {
			console.log(error);
		});
	}
}
</script>

<style scoped lang="stylus">
@import '~fullcalendar/dist/fullcalendar.css';
@import '../stylus/main';

#my-availability {
	background: white;
	width: 100%;
	max-height: 100%;
	position: relative;
	box-sizing: border-box;
	display: flex;
	flex-flow: column nowrap;
	overflow: hidden;
}

#approval-bar {
	width: 100%;
	height: 3em;
	flex-flow: column nowrap;
}

#my-calendar {
	width: 100%;
	height: 40em;
	flex-flow: column nowrap;
}

#submission-bar {
	width: 100%;
	height: 3em;
	flex-flow: column nowrap;
}
</style>
