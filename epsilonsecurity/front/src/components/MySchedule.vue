<template>
	<div id="my-schedule">
		<full-calendar 
			:events="events" 
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

	</div>
</template>

<script>
import Vue from 'vue'
import FullCalendar from 'vue-full-calendar'
import moment from 'moment'

Vue.use(FullCalendar) // add the vue-full-calendar plugin to Vue
window.jQuery = window.$ = require('jquery') // we need jquery too

export default {
	data: function () {
    return {
			config: {
				// https://fullcalendar.io/docs/views/Available_Views/
				defaultView: 'agendaWeek', 
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
				eventRender: this.handleEventRender
			},
			events: [
				{
					title: 'My First Overlapping Shift',
					start: '2017-10-23 01:00:00',
					end: '2017-10-23 02:30:00',
					//todo: campus,
				},
				{
					title: 'My Second Overlapping Shift',
					start: '2017-10-23 02:00:00',
					end: '2017-10-23 03:30:00'
				},
				{
					title: 'My Weekday Shift 1',
					start: '2017-10-25 03:00:00',
					end: '2017-10-25 04:00:00'
				},
				{
					title: 'My Weekdend Shift',
					start: '2017-10-26 01:00:00',
					end: '2017-10-26 01:30:00'
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
			element.addClass("primary defaultEventTextColor--text")
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
	height: 100%
	overflow: auto

.event-styles
	color: green
	background-color: green

</style>
