<template>
	<v-container fluid fill-height>
		<v-layout column wrap>
			<v-flex xs12>

				<!-- toast for submitted /editing availability -->
				<v-snackbar
					:timeout="1000"
					:top="'top'"
					v-model="showToast"
				>
					<v-btn flat color="white class="text-xs-center""> {{ toastMsg }} </v-btn>
				</v-snackbar>


				<!-- submit availability status bar -->
				<div id = "approval-bar">
					<v-card dark color="primary " >
						<v-card-text class="text-xs-center"> Status:  {{ availabilityStatus }}</v-card-text>
					</v-card>
				</div>


				<div id = "my-calendar">
					<!-- calendar -->
					<full-calendar
						:event-sources="eventSources"
						:config="config"
						id="calendar">
					</full-calendar>

					<!-- floating add availbility button -->
					<v-card-text style="height: 1px; position: relative" @click.stop="AvailabilityClick">
						<v-btn
							absolute
							dark
							fab
							top
							right
							color="blue"
						>
							<v-icon>add</v-icon>
						</v-btn>
					</v-card-text>
				</div>

				<!-- bottom bar for submission -->
				<div id="submission-bar">
					<v-card class="text-xs-center">
						<v-card-text >
							Submission Bar
						</v-card-text>
						<v-card-actions>
							<v-btn color="primary" flat @click.stop="SubmitAvailability">
								Submit
							</v-btn>
						</v-card-actions>
					</v-card>
				</div>

				<!-- popup for when availability/add button is clicked -->
				<v-dialog v-model="showCreateEditAvailability">
					<v-card>
						<v-card-title>
							<span class="headline">Availability Info</span>
						</v-card-title>
						<v-card-text>
						<v-container grid-list-md>
							<v-layout wrap>	
								<v-flex xs12>

									<p> Time Start: <input placeholder="edit me" v-model="availability.timeStart"></p>
									<p> Time End: <input placeholder="edit me" v-model="availability.timeEnd"></p>
									
									<v-flex xs6>
										<v-select
											v-bind:items="campusList"
											v-bind:error-messages="['Please select an option']"
											v-model="availability.campus"
											label="Campus"
											class="input-group--focused"
											item-text="text"
										>
										</v-select>
									</v-flex>
									
								</v-flex>
							</v-layout wrap>
						</v-container grid-list-md>
						</v-card-text>
						<v-card-actions>
							<v-spacer></v-spacer>
							<v-btn color="primary" flat @click.stop="AvailabilityCreation(availability)">
								Add
							</v-btn>
							<v-btn color="primary" flat @click.stop="showCreateEditAvailability = false">
								Close
							</v-btn>
						</v-card-actions>
					</v-card>
				</v-dialog>

				<!-- popup for when submit availbility is clicked -->
				<v-dialog v-model="showConfirmSubmission">
					<v-card>
						<v-card-title>
							<span class="headline">Availability Submission</span>
						</v-card-title>
						<v-container grid-list-md>
							<v-layout wrap>
								<v-card-text>
									<v-flex xs12>
										<v-card-text>
											Are you sure you want to submit?
										</v-card-text>
									</v-flex>
								</v-card-text>
							</v-layout wrap>
						</v-container grid-list-md>
						<v-card-actions>
							<v-spacer></v-spacer>
							<v-btn color="primary" flat @click.stop="SubmittedAvailability">
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

			//Data contains list of avaliablities
			availabilityList: [
			],

			availability: {
				title: 'N/A',
				timeStart: 0,
				timeEnd: 0,
				campus: ""
			},

			//for selection of campus
			selectCampus: { campusList: ''},

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
			showCreateEditAvailability: false,

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
				eventRender: this.EventRender,

				// triggered after a selection is made, i.e user stops dragging.
				select: this.AvailabilitySelection,
				
				//trigger when attemping to delete an event
				eventDestroy: this.AvailabilityDeletion,
			},

			eventSources: [
				{	
					events: function(start, end, timezone, callback) {
							
						//todo: able to edit  the availbilty

					},
					color: "blue",
					textColor: "white"
				}
			],

			clickedAvailability: {
			},

			selectedAvailability: {
			}
		}
	},

	computed: {

	},

	methods: {

        // handles user click event
		AvailabilityClick: function(calEvent, jsEvent, view) {
            //todo loads availbility before displaying

			//todo able to edit availability
			this.clickedAvailability = calEvent;
			this.showCreateEditAvailability = true;
		},	

		//handles selection of availability on calendar
		AvailabilitySelection: function(start, end, jsEvent, view) {
			console.log("drag event start:", start);
			console.log("drag event end:", end);
			
			//set the start and end of this availability
			this.availability.timeStart = start;
			this.availability.timeEnd = end;
			
			//todo create the event

			//show edit avaialbility
			this.showCreateEditAvailability = true;
		},

		//todo handle creation of an availability
        AvailabilityCreation: function(availability) {
            //debug print
            console.log("Availability time start:", availability.timeStart);
            console.log("Availability time start:", availability.timeEnd);
            console.log("Availability time start:", availability.campus);

            //add user to list
			this.availabilityList.push({availability});
			
			//todo render event

            //close window
			this.showCreateEditAvailability = false;

			//show msg that it has been created
            this.toastMsg = "created Availability";
            this.showToast = true;
		},

		EventRender: function(event, element, view) {
			// todo must also remove plugin on eventDestroy()
		},

		//TODO handles event destroy element is the Jqueiry element to be removed
		AvailabilityDeletion(event, element, view) {

		},

		//submit availability
        SubmitAvailability: function() {
			this.showConfirmSubmission = true;
		},

		//blocks out most user interaction when availability is submitted
		SubmittedAvailability: function() {

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
	},

    components: {
        VToolbar
	},

	//todo creates to avail database
	// created: function() {
    //     axios.get('api/onetimeavailabilites')
	// 		.then()
	// 		.catch(function (error) {
    //         console.log(error);
    //     });
	// }
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
}

#my-calendar {
	width: 100%;
	height: 75%;
	flex-flow: column nowrap;
}

#submission-bar {
	width: 100%;
	height: 3em;
	flex-flow: column nowrap;
}

#approval-bar {
	width: 100%;
	height: 3em;
	flex-flow: column nowrap;
}
</style>
