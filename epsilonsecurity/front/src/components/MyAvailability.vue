<template>
	<v-container fluid >
		<v-layout column wrap>
			<v-flex xs12>

				<!-- toast for submitted /editting availbitly -->
				<v-snackbar
						:timeout="1000"
						:top="y === 'top'"
						:bottom="y === 'bottom'"
						:right="x === 'right'"
						:left="x === 'left'"
						:multi-line="mode === 'multi-line'"
						:vertical="mode === 'vertical'"
						v-model="showToast"
				>
					{{ text }}
					<v-btn flat color="primary"> {{ toastMsg }} </v-btn>
				</v-snackbar>



				<!-- submit availability status bar -->
				<v-card dark color="primary">
					<v-card-text> Status:  {{ availStatus }}</v-card-text>
				</v-card>


				<!-- calendar -->
				<full-calendar
						:event-sources="eventSources"
						:events="events"
						:config="config"
						id="calendar">
				</full-calendar>


				<!-- floating add availbility button -->
				<v-card-text style="height: 1px; position: relative" @click.stop="handleAvailClick">
					<v-btn
							absolute
							dark
							fab
							top
							right
							color="purple"
					>
						<v-icon>add</v-icon>
					</v-btn>
				</v-card-text>


				<!-- bottom bar for submission -->

				<v-container dark color="primary">
					<p>
						Submission Bar
					</p>
					<v-btn color="primary" flat @click.stop="handleSubmitAvailability">
						Submit
					</v-btn>
				</v-container>


				<!-- popup for when availability/add button is clicked -->
				<v-dialog v-model="showCreateEditAvail">
					<v-card>
						<v-card-title>
							<span class="headline">Availability Info</span>
						</v-card-title>
						<v-card-text>
							<v-container grid-list-md>
								<v-layout wrap>
									<v-flex xs12>
										<li>
											TODO:
											Availility Time Start
											Availability  Time End
										</li>
										<p> Campus: </p>
										<v-flex xs6>
											<v-select
													v-bind:items="items"
													v-model="select"
													label="Select"
													single-line
													item-text="campus"
													return-object
											></v-select>
										</v-flex>
									</v-flex>
								</v-layout>
							</v-container>
						</v-card-text>
						<v-card-actions>
							<v-spacer></v-spacer>
							<v-btn color="primary" flat @click.stop="showCreateEditAvail = false">
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
						<v-card-text>
							<v-container grid-list-md>
								<v-layout wrap>
									<v-flex xs12>
										<v-card-text>
											Are you sure you want to submit?
										</v-card-text>
									</v-flex>
								</v-layout>
							</v-container>
						</v-card-text>
						<v-card-actions>
							<v-spacer></v-spacer>
							<v-btn color="primary" flat @click.stop="handleSubmittedAvail">
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
import Vue from 'vue'
import FullCalendar from 'vue-full-calendar'
import moment from 'moment'
import axios from 'axios'
import store from '../store/store'
import VToolbar from "vuetify/es5/components/VToolbar/VToolbar";

Vue.use(FullCalendar) // add the vue-full-calendar plugin to Vue
window.jQuery = window.$ = require('jquery') // we need jquery too

export default {

    data: function () {
    return {

        //Data

        //for selection of campus
        select: { campus: 'Campus'},
        items: [
            { campus: 'Burnaby'},
            { campus: 'Surrey'},
            { campus: 'Vancouver'},
        ],

        //submission availabiliy popup
        showConfirmSubmission: false,

        //creation of availability window
        showCreateEditAvail: false,

        //status of availability
        availStatus: "Open",

        //toast msg
        toastMsg: "Nothing here",

        config: {
                defaultView: 'agendaWeek',
                timezone: 'local',
                header: {
                    left: 'prev,next today',
                    // hide title for now, until I can figure out how to controll its size.
                    center: '',
                    right: 'month,agendaWeek,agendaDay'
                },

                height: 'parent',
                allDaySlot: false,

                //Time view:
                //------------------
                //15 minute increment
                slotDuration: '00:15:00',

                // scroll calendar to the specified time (12 AM)
                scrollTime: '00:00:00',

                //shows today
                nowIndicator: true,

                //editing avail:
                //---------------------
                //allows resizing of events
                editable: false,

                // allows dragging on calendar.
                selectable: true,

                // allows to make an event on calendar
                selectHelper: true,

                // triggered with an event is clicked
                eventClick: this.handleAvailClick,
                // triggered after a selection is made, i.e user stops dragging.
                select: this.handleEventSelection,
                // triggered before an event is rendered - our chance to enhance the event.
                eventRender: this.handleEventRender,

				//register when user first drags
				eventDragStart: this.handleEventStartDrag,

				//register when user ends drags
                eventDragEnd: this.handleEventEndDrag,
			},

			eventSources: [
				// 1st event source

                {
                    events: function(start, end, timezone, callback) {

                        //todo: able to edit the availbilty

                    },
                    color: "blue",
                    textColor: "white"
                }
            ],

			//do something on clicking avaialbility
			clickedAvail: {

			}
		}
	},

	computed: {

	},

	methods: {

		//todo handle creation of an availability
        handleAvailCreation: function(timeStart, timeEnd, campus) {
            //make an availability
            this.showCreateAvail = false;
            this.toastMsg = "created Availability";
            this.showToast = true;
		},


		//submit availability
        handleSubmitAvailability: function() {
			this.showConfirmSubmission = true;
		},

		//blocks out most user interaction when availability is submitted
		handleSubmittedAvail: function() {

			//disables resizing of events, editing of events, selection of new events
            this.editable = false;
            this.selectable = false;
            this.selectHelper = false;
            this.availStatus = "Pending";

			//close popup
            this.showConfirmSubmission = false;

			//toast msg
            this.toastMsg = "Submitted Availability";
            this.showToast = true;

        },

		//catches user start drag event for calendar
        handleEventStartDrag: function(event, jsEvent, ui, view) {
			alert("started draggng!\n");
		},

        //catches user end drag event for calendar
		handleEventEndDrag: function(event, jsEvent, ui, view) {
            alert("ended dragging!\n");
		},

        // handles user click event
		handleAvailClick: function(calEvent, jsEvent, view) {
            //loads availbility before displaying

			//todo able to edit availability
			this.clickedAvail = calEvent
			this.showCreateEditAvail = true
		},

		//handles event selection
		handleEventSelection: function(start, end, jsEvent, view) {

		    //todo able to add an event after drag select
			alert("selected event!\n");
		},
	},

    components: {
        VToolbar
	},
}
</script>

<style scoped lang="stylus">

@import '~fullcalendar/dist/fullcalendar.css';
@import '../stylus/main';

#my-availability
	background: white
	width: 100%
	position: relative
	box-sizing: border-box
	max-height: 100%


</style>
