<template>
	<v-container fluid fill-height class="pa-0">
	<!-- <div id="my-schedule"> -->
		<full-calendar
			:event-sources="eventSources"
			:config="config"
			id="calendar">
		</full-calendar>

		<v-dialog v-model="dialog">
      <v-card>
        <v-card-title>
					<span class="headline">Shift Info</span>
				</v-card-title>
        <v-card-text>
          <v-container grid-list-md>
						<v-layout wrap>
							<v-flex xs12>
								<p>{{shiftTitle}}</p>
								<p>{{shiftDate}}</p>
								<p>{{shiftTime}}</p>
								<p>{{shiftCampus}}</p> 
								<p>{{shiftDescription}}</p> 
							</v-flex>
						</v-layout>
					</v-container>
        </v-card-text>
        <v-card-actions>
					<v-spacer></v-spacer>
          <v-btn color="primary" flat @click.stop="dialog = false">
						Close
					</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

	<!-- </div> -->
	</v-container>
</template>

<script>
import Vue from 'vue'
import FullCalendar from 'vue-full-calendar'
import moment from 'moment'
import axios from 'axios'
import { store } from '../store'

Vue.use(FullCalendar) // add the vue-full-calendar plugin to Vue
window.jQuery = window.$ = require('jquery') // we need jquery too

export default {
	data: function () {
    return {
			config: {
				// https://fullcalendar.io/docs/views/Available_Views/
				defaultView: 'agendaWeek', 
				// local timezone is very important
				timezone: 'local',
				header: {
					left: 'prev,next today',
					// hide title for now, until I can figure out how to controll its size.
					center: '',
					right: 'month,agendaWeek,agendaDay'
				},
				height: 'parent',
				allDaySlot: false,
				editable: false,
				// smallest increment of a shift (15 minute)
				slotDuration: '00:15:00',
				nowIndicator: true,
				// scroll calendar to the specified time (12 AM)
				scrollTime: '00:00:00',
				// prevent dragging on the calendar.
				selectable: false,
				// don't draw placeholder event while user drags
				selectHelper: false,
				// triggered with an event is clicked
				eventClick: this.handleEventClick,
				// triggered after a selection is made, i.e user stops dragging.
				select: this.handleEventSelection,
				// triggered before an event is rendered - our chance to enhance the event.
				eventRender: this.handleEventRender,
				// viewRender: function(view) {
				// 	var title = view.title;
				// 	console.log(view)
				// 	console.log(`>>> ${title}`)
				// 	$("#externalTitle").html(title);
				// }
			},
			eventSources: [
				// 1st event source
				{
					events: function(start, end, timezone, callback) {
						// todo: use userId when api is ready.
						const userId = store.getters.uid
						axios.get(`/api/users/${userId}/shifts`)
						.then(response => {
							// console.log(JSON.stringify(response.data,null,2))
							callback(response.data)
						})
						.catch(error => {
							// use dummy events if backend fails.
							const events = [
								{
									id: 1,
									title: "Security Presence",
									start: moment().weekday(0).hour(8).format(),
									end: moment().weekday(0).hour(9).format(),
									campus: 'Vancouver',
									description: null
								},
								{
									id: 2,
									title: "Library Patrol",
									start: moment().weekday(2).hour(13).format(),
									end: moment().weekday(2).hour(15).format(),
									campus: 'Burnaby',
									description: "Take any unattended Macbook home, so that the owner learns a lesson."
								},
								{
									id: 3,
									title: "Parkade Patrol (Mock)",
									start: moment().weekday(4).hour(16).format(),
									end: moment().weekday(4).hour(16).add(30, 'minutes').format(),
									campus: 'Surrey',
									description: "If you see a nerd wearing a t-shirt with the number '42' inscribed, please bow respectfully."
								},
								{
									id: 4,
									title: "Bike Presence (Mock)",
									start: moment().weekday(6).hour(12).format(),
									end: moment().weekday(6).hour(14).format(),
									campus: 'Burnaby',
									description: "Lookout for a huge Sasquach, notorious for scaring people."
								}
							]
							callback(events)
							console.log(error)
						})
					},
					color: "black",
					textColor: "white"
				}
			],
			dialog: false,
			clickedShift: {}
		}
	},
	computed: {
		shiftTitle: function() {
			return this.clickedShift.title
		},
		shiftDate: function() {
			const start = this.clickedShift.start // type moment()
			return moment(start).format("LL")
		},
		shiftTime: function() {
			const start = moment(this.clickedShift.start).format("LT")
			const end = moment(this.clickedShift.end).format("LT")
			const duration = `${start} - ${end}`
			return duration
		},
		shiftCampus: function() {
			return this.clickedShift.campus || "Burnaby"
		},
		shiftDescription: function() {
			return this.clickedShift.description || ""
		}
	},
	methods: {
		handleEventClick: function(calEvent, jsEvent, view) {
			console.log("Shift was clicked.")
			this.clickedShift = calEvent
			this.dialog = true
		},
		handleEventSelection: function(start, end, jsEvent, view) {
			console.log("Shift selection ended.")
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

#my-schedule
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
