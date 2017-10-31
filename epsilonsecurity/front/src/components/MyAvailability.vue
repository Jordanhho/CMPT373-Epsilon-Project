<template>
	<v-container fluid fill-width>
		<v-layout column wrap>

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
			<v-btn flat color="grey"> {{ toastMsg }} </v-btn>
		</v-snackbar>



		<!-- submit availability status bar -->
		<v-header dark color="primary">
			<p> Status:

				<!--TODO GRAB status of availability open-->
				<span v-if="status == 'Open'">
				<span style="color: blue;">

					{{ status }}
				</span>
			</span>


				<!--TODO GRAB status of availability Pending-->
				<span v-else-if="status == 'Pending'">
				<span style="color: red;">
					{{ status }}
				</span>
			</span>

				<!--TODO GRAB status of availability APproved-->
				<span v-else="status == 'Approved'">
				<span style="color: green;">
					{{ status }}
				</span>

            </span>

			</p>
		</v-header>


		<!-- calendar -->
		<full-calendar
			:event-sources="eventSources"
			:events="events"
			:config="config"
			id="calendar">
		</full-calendar>



		<!-- floating add button -->
		<v-card-text style="height: 100px; position: relative" @click.stop="handleAddAvailability">
			<v-btn
					absolute
					dark
					fab
					top
					right
					color="pink"
			>
				<v-icon>add</v-icon>
			</v-btn>
		</v-card-text>




		<!-- bottom bar for submission -->
			<v-footer class="pa-3">
				<p>
					Submission Bar
				</p>
				<v-btn color="primary" flat @click.stop="handleSubmitAvailability">
					Submit
				</v-btn>
			</v-footer>










			<!-- pop for date, picker -->
			<!-- TODO -->








			<!-- pop for date picker -->
			<v-dialog v-model="showTimePicker">
				<v-card>
					<v-card-title>
						<span class="headline">Availability Time</span>
					</v-card-title>
					<v-card-text>
						<v-container grid-list-md>
							<v-layout wrap>
								<v-flex xs12>
									<v-btn
											:color="allowedTimes === everyOtherValue ? 'primary' : ''"
											:class="{ 'white--text': allowedTimes === everyOtherValue }"
											@click.native="allowedTimes = everyOtherValue"
									>Function</v-btn>
									<v-btn
											:color="allowedTimes === randomValues ? 'primary' : ''"
											:class="{ 'white--text': allowedTimes === randomValues }"
											@click.native="allowedTimes = randomValues"
									>Array</v-btn>
									<v-btn
											:color="allowedTimes === minMaxValues ? 'primary' : ''"
											:class="{ 'white--text': allowedTimes === minMaxValues }"
											@click.native="allowedTimes = minMaxValues"
									>Object</v-btn>
									<v-time-picker
											class="mt-3"
											scrollable
											v-model="date"
											:allowed-hours="allowedTimes.hours"
											:allowed-minutes="allowedTimes.minutes"
									></v-time-picker>

								</v-flex>
							</v-layout>
						</v-container>
					</v-card-text>
					<v-card-actions>
						<v-spacer></v-spacer>
						<v-btn color="primary" flat @click.stop="handleGetTimeFromPicker">
							Yes
						</v-btn>

						<v-btn color="primary" flat @click.stop="showTimePicker = false">
							No
						</v-btn>
					</v-card-actions>
				</v-card>
			</v-dialog>













			<!-- popup for when you create an availability is clicked -->
			<v-dialog v-model="showCreateAvail">
				<v-card>
					<v-card-title>
						<span class="headline">Availability Info</span>
					</v-card-title>
					<v-card-text>
						<v-container grid-list-md>
							<v-layout wrap>
								<v-flex xs12>
									<p>
										Availility Time Start
										Availability  Time End

									</p>
									<p> Campus: </p>
									<v-flex xs6>
										<v-select
												v-bind:items="items"
												v-model="select"
												label="Select"
												single-line
												item-text="campus"
												return-object
												:hint="`${select.campus}`"
												persistent-hint
										></v-select>
									</v-flex>

								</v-flex>
							</v-layout>
						</v-container>
					</v-card-text>
					<v-card-actions>
						<v-spacer></v-spacer>
						<v-btn color="primary" flat @click.stop="handleAvailCreation">
							Create
						</v-btn>
						<v-btn color="primary" flat @click.stop="showCreateAvail = false">
							Cancel
						</v-btn>
					</v-card-actions>
				</v-card>
			</v-dialog>





		<!-- popup for when availability is clicked -->
		<v-dialog v-model="showEventInfo">
			<v-card>
				<v-card-title>
					<span class="headline">Availability Info</span>
				</v-card-title>
				<v-card-text>
					<v-container grid-list-md>
						<v-layout wrap>
							<v-flex xs12>
								<p>
									Availility Time Start
									Availability  Time End

								</p>
								<p> Campus: </p>
								<v-flex xs6>
									<v-select
											v-bind:items="items"
											v-model="select"
											label="Select"
											single-line
											item-text="campus"
											return-object
											:hint="`${select.campus}`"
											persistent-hint
									></v-select>
								</v-flex>

							</v-flex>
						</v-layout>
					</v-container>
				</v-card-text>
				<v-card-actions>
					<v-spacer></v-spacer>
					<v-btn color="primary" flat @click.stop="showEventInfo = false">
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
								<p>
									Are you sure you want to submit?
								</p>

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
    components: {VToolbar},
    data: function () {
    return {
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
                eventClick: this.handleEventClick,
                // triggered after a selection is made, i.e user stops dragging.
                select: this.handleEventSelection,
                // triggered before an event is rendered - our chance to enhance the event.
                eventRender: this.handleEventRender,

				//register when user first drags
				eventDragStart: this.handleEventStartDrag,

				//register when user ends drags
                eventDragEnd: this.handleEventEndDrag,



                // viewRender: function(view) {
                // 	var title = view.title;
                // 	console.log(view)
                // 	console.log(`>>> ${title}`)
                // 	$("#externalTitle").html(title);
                // }
			},


			//for selection of campus
			select: { campus: 'Campus'},
			items: [
				{ campus: 'Burnaby'},
				{ campus: 'Surrey'},
				{ campus: 'Vancouver'},
			],




			//data
			//event edit popup
			showEventInfo: false,

			//submission availabiliy popup
        	showConfirmSubmission: false,

			//creation of availability window
			showCreateAvail: false,

			//status of availability
			availStatus: "Open",

			//toast msg
        	toastMsg: "Nothing here",




			//for time picker
			date: null,
			allowedTimes: {
				hours: null,
				minutes: null
			},
			everyOtherValue: {
				hours: function (value) {
					return value % 2 === 0
				},
				minutes: function (value) {
					return value % 15 === 0
				}
			},
			randomValues: {
				hours: [],
				minute: []
			},

			minMaxValues: {
				hours: {
					min: '12AM',
					max: '11:59PM'
				},
				minutes: {
				    //increments of 15
					min: 15,
					max: 60
				}
			},






			events: [
				{
					//id: 1,
					title: 'AMAZING DAY',
					start: '2017-10-31 01:00:00',
					end: '2017-10-31 02:30:00'
				},

			],








			eventSources: [
				// 1st event source

                {
                    events: function(start, end, timezone, callback) {

                        //todo: able to edit the availbilty




//                        const sfuEmail = store.getters.currentUserEmail
//                        axios.get(`/api/users/${sfuEmail}/shifts`)
//                            .then(response => {
//                                // console.log(JSON.stringify(response.data,null,2))
//                                callback(response.data)
//                            })
//                            .catch(error => {
//                                console.log(error)
//                            })
                    },
                    color: "black",
                    textColor: "white"
                }
            ],


			clickedAvail: {}
		}
	},
	computed: {

	},
	methods: {

        handleShowTimePicker: function() {
            this.showTimePicker = true;
		},

		//todo grab time from picker
        handleGetTimeFromPicker: function() {

			this.showTimePicker = false;
        },


            //todo handle creation of an availability
        handleAvailCreation: function(timeStart, timeEnd, campus) {

            //make an availability

            this.showCreateAvail = false;
            toastMsg = "created Availability";
            showToast = true;
		},


        handleAddAvailability: function() {
            this.showCreateAvail = true;
        },



        handleSubmitAvailability: function() {
			this.showConfirmSubmission = true;
		},


		handleSubmittedAvail: function() {

			//disables resizing of events, editing of events, selection of new events
            editable = false;
			selectable = false;
			selectHelper = false;
			availStatus = "Pending";

			//close popup
            showConfirmSubmission = false;

			toastMsg = "Submitted Availability";
            showToast = true;

        },

        handleEventStartDrag: function(event, jsEvent, ui, view) {
			alert("started draggng!\n");


		},

		handleEventEndDrag: function(event, jsEvent, ui, view) {
            alert("ended dragging!\n");
		},


		handleEventClick: function(calEvent, jsEvent, view) {
            alert("clicked event!\n")

			//todo able to edit availability





			this.clickedAvail = calEvent
			this.showEventInfo = true
		},


		handleEventSelection: function(start, end, jsEvent, view) {

		    //todo able to add an event after drag select
			alert("selected event!\n");



		},


		handleEventRender: function(event, element, view) {
			// element.qtip({
			// 	content: event.description
			// });
			// must also remove plugin on eventDestroy()

			// todo: i tried but failed to use my class instead.
			// element.attr("class", "event-styles")
			// element.addClass("event-styles")

			// element.addClass("primary defaultEventTextColor--text")
		}
	}
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
	// height:
	max-height: 100%
	overflow: hidden

.event-styles
	color: green
	background-color: green

</style>
