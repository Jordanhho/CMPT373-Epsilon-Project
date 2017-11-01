<template>
	<v-container fluid fill-height class="pa-0">
		<v-layout column wrap>

			<v-flex>
				<full-calendar
					:events="events"
					:config="config"
					id="calendar">
				</full-calendar>
			</v-flex>

			<v-toolbar :color="statusColor" dark>
				<v-toolbar-title>Status: {{ status }}</v-toolbar-title>
				<v-spacer></v-spacer>
				<v-btn flat @click.stop="handleSubmitAvailability" v-if="canEdit">
					Submit
				</v-btn>
			</v-toolbar>

			<v-dialog v-model="showCreateAvail" persistent maxWidth="500px">
				<v-card>
					<v-card-title>
						<span>Slot Details</span>
					</v-card-title>
					<v-card-text>
						<v-container grid-list-md>
							<v-layout wrap>
								<v-flex md6> <!--start date-->
									<v-menu full-width>
										<v-text-field slot="activator" label="Start Date" required readonly v-model="formattedStartDate"></v-text-field>
										<v-date-picker v-model="selectedStartDate"
										               :date-format="date => new Date(date).toLocaleDateString()"
										               :formatted-value.sync="formattedStartDate"
										               no-title scrollable actions>
											<template scope="{ save, cancel }">
												<v-card-actions>
													<v-spacer></v-spacer>
													<v-btn flat color="primary" @click="cancel">Cancel</v-btn>
													<v-btn flat color="primary" @click="save">OK</v-btn>
												</v-card-actions>
											</template>
										</v-date-picker>
									</v-menu>
								</v-flex>
								<v-flex md6> <!--start time-->
									<v-menu full-width :close-on-content-click="false">
										<v-text-field slot="activator" label="Start Time" required readonly v-model="selectedStartTime"></v-text-field>
										<v-time-picker v-model="selectedStartTime" autosave></v-time-picker>
									</v-menu>
								</v-flex>
								<v-flex md6> <!--end date-->
									<v-menu full-width>
										<v-text-field slot="activator" label="End Date" required readonly v-model="formattedEndDate"></v-text-field>
										<v-date-picker v-model="selectedEndDate"
										               :date-format="date => new Date(date).toLocaleDateString()"
										               :formatted-value.sync="formattedEndDate"
										               no-title scrollable actions>
											<template scope="{ save, cancel }">
												<v-card-actions>
													<v-spacer></v-spacer>
													<v-btn flat color="primary" @click="cancel">Cancel</v-btn>
													<v-btn flat color="primary" @click="save">OK</v-btn>
												</v-card-actions>
											</template>
										</v-date-picker>
									</v-menu>
								</v-flex>
								<v-flex md6> <!--end time-->
									<v-menu full-width :close-on-content-click="false">
										<v-text-field slot="activator" label="End Time" required readonly v-model="selectedEndTime"></v-text-field>
										<v-time-picker v-model="selectedEndTime" autosave></v-time-picker>
									</v-menu>
								</v-flex>
								<v-flex xs12> <!-- campus -->
									<v-select label="Campus" required :items="locations" v-model="selectedLocation"></v-select>
								</v-flex>
							</v-layout>
						</v-container>
					</v-card-text>
					<v-card-actions>
						<v-spacer></v-spacer>
						<v-btn color="primary" flat @click.stop="saveSlot">Save</v-btn>
						<v-btn color="primary" flat @click.stop="showCreateAvail = false">Cancel</v-btn>
					</v-card-actions>
				</v-card>
			</v-dialog>

			<!-- for creating a single availability slot -->
			<v-dialog>
				<v-card>
					<v-card-title>
						<span>Create Availability</span>
					</v-card-title>
					<v-card-text>
						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Architecto cum, earum eos excepturi, impedit numquam odit repellat similique sit, suscipit ut voluptatem. Aliquam atque maxime placeat voluptatum. Expedita, laborum sunt.
					</v-card-text>
					<v-card-actions>
						<v-btn color="primary" flat @click.stop="handleAvailCreation">Save</v-btn>
						<v-spacer></v-spacer>
						<v-btn color="secondary" flat @click.stop="showCreateAvail = false">Cancel</v-btn>
					</v-card-actions>
				</v-card>
			</v-dialog>

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
		    showTimePicker: false,
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

				clickedAvail: {},

		    //----------------

		    status: "approved",
				locations: [ 'Burnaby', 'Surrey', 'Vancouver' ],
				selectedStartDate: null, formattedStartDate: null,
				selectedStartTime: null,
				selectedEndDate: null, formattedEndDate: null,
				selectedEndTime: null,
		    selectedLocation: null,
			}
	},
	computed: {
		statusColor: function() {
			const stat = this.status || "pending"

			if (stat == "pending") {
				return "red"
			} else if (stat == "approved") {
				return "green"
			} else if (stat == "open") {
				return "blue"
			} else {
				return "secondary"
			}
		},
		canEdit: function() {
			return this.status == "open"
		}
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

		handleAddAvailability: function() {
			this.showCreateAvail = true;
		},

		//--

		handleEventSelection: function(start, end, jsEvent, view) {
			const startDate = new Date(start)
			const endDate = new Date(end)

			this.selectedStartDate = startDate
			this.selectedStartTime = startDate.toLocaleTimeString()

			this.selectedEndDate = endDate
			this.selectedEndTime = endDate.toLocaleTimeString()

			this.formattedStartDate = startDate.toLocaleDateString()
			this.formattedEndDate = endDate.toLocaleDateString()
			this.showCreateAvail = true
		},

		saveSlot: function() {
			const availSlot = {
				start: this.selectedStartDate,
				end: this.selectedEndDate,
				campus: this.selectedLocation
			}
			// note: 'start' must be a combination of date and time.
			console.log("TODO: create this availability slot.")
			console.log(JSON.stringify(availSlot, null, 2))
		}

		//--
	}
}
</script>

<style scoped lang="stylus">

@import '~fullcalendar/dist/fullcalendar.css';
@import '../stylus/main';

</style>
