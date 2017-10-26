<template>
	<div id="my-schedule">
		<full-calendar 
			:events="events" 
			:config="config"
			id="calendar">
		</full-calendar>
	</div>
</template>

<script>
import Vue from 'vue'
import FullCalendar from 'vue-full-calendar'
// import moment from 'moment'

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
				eventColor: '#f00',

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
					end: '2017-10-23 02:30:00'
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
			]
		}
	},
	methods: {
		// eventSelected: function(calEvent, jsEvent, view) {
		// 	console.log(`Event title: ${calEvent.title}`)
		// 	console.log(`Click location: (${jsEvent.pageX},${jsEvent.pageY})`)
		// 	console.log(`View name: ${view.name}`)
		// },
		handleEventClick: function(calEvent, jsEvent, view){
			console.log("Shift was clicked.")
		},
		handleEventSelection: function(start, end, jsEvent, view) {
			console.log("Shift selection ended.")
		},
		handleEventRender: function(event, element, view) {
			// element.qtip({
			// 	content: event.description
			// });
			// must also remove plugin on eventDestroy()
		}
	}
}
</script>

<style scoped>
@import '~fullcalendar/dist/fullcalendar.css';

#my-schedule {
	background: white;
	width: 100%;
	position: relative;
}
#my-schedule h1 {
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
}
</style>
