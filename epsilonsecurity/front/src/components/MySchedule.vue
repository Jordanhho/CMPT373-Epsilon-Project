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

Vue.use(FullCalendar) // add the vue-full-calendar plugin to Vue
window.jQuery = window.$ = require('jquery') // we need jquery too

export default {
	data: function () {
    return {
			config: {
				height: 'parent',
				allDaySlot: false,
				editable: false,
				// smallest increment of a shift
				slotDuration: '00:15:00', // (15 minute)
				nowIndicator: true,
				// TODO: prevent dragging event.
				// scroll calendar to the specified time
				scrollTime: '00:00:00', // (12 AM)
			},
			events: [
				{
					title: 'My First Overlapping Shift',
					start: '2017-10-16 01:00:00',
					end: '2017-10-16 02:30:00'
				},
				{
					title: 'My Second Overlapping Shift',
					start: '2017-10-16 02:00:00',
					end: '2017-10-16 03:30:00'
				},
				{
					title: 'My Weekday Shift 1',
					start: '2017-10-19 03:00:00',
					end: '2017-10-19 04:00:00'
				},
				{
					title: 'My Weekdend Shift',
					start: '2017-10-21 01:00:00',
					end: '2017-10-21 01:30:00'
				}
			]
		}
	},
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
