<template>
    <v-container>
      <v-layout>
      	<full-calendar
      		:event-sources="eventSources"
      		:config="config"
      		id="calendar">
      	</full-calendar>

      </v-layout>
      <v-layout>
          <v-flex xs1 offset-xs11>
              <eventDialog  v-bind:teamId='teamId'
                            v-bind:shiftTypes='shiftTypes'>
              </eventDialog>
          </v-flex>
      </v-layout>
  </v-container>
</template>

<script>
    import EventDialog from "./EventDialog.vue";

    import Vue from 'vue';
    import moment from 'moment';
    import axios from 'axios';
    import FullCalendar from 'vue-full-calendar';

    Vue.use(FullCalendar); // add the vue-full-calendar plugin to Vue
    window.jQuery = window.$ = require('jquery'); // we need jquery too

    export default {
        data: function() {
            return {

                shiftList: [],

                shift: {
                    eventObj: null,
                    eventId: -1,
                    date: null,
                    timeStart: null,
                    timeEnd: null,
                    teamId: -1,
                    shiftTypeId: -1,
                },

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
    				navLinks: true, //allows clicking on a day

    				//----------------- selection of events --------------------------
    				editable: false, //allows resizing of events
    				selectable: true, //allows dragging on calendar.
    				selectHelper: true, //allows to make an event on calendar
    				selectOverlap: false, //do not allow event overlap selection

    				selectMinDistance: 5, //minimum distance click has to move inorder to detect as event PREVENT MISCLICKS
    				selectLongPressDelay: 1000, //minimum miliseconds user holds down before it counts as a selectable

    				//triggered with an event is clicked
    				//eventClick: this.shiftClickEvent,

    				//triggered after a selection is made, i.e user stops dragging.
    				//select: this.shiftCreateDrag,

    				//restrict selection of an event to maximxum one day
    				selectConstraint: {
    					start: "00:00",
    					end: "24:00"
    				},

    				//restrict event to maximum one day
    				eventConstraint: {
    					start: "00:00",
    					end: "24:00",
    				},
                },
                eventSources: [
                    { }
                ],
            }
        },
        props: {
            teamId: {
                type: String,
                required: true,
            },
            shiftTypes: {
                type: Array,
                required: true
            }
        },
        components: {
            "eventDialog": EventDialog,
        },
        methods: {


            createShift: function(shiftObj) {
                
            },



            // shiftCreateDrag: function(start, end, jsEvent, view) {
    		// 	this.shift.date = moment(start).format("YYYY-MM-DD");
    		// 	this.shift.timeStart = moment(start).format("h:mma");
    		// 	this.shift.timeEnd = moment(end).format("h:mma");
    		// 	this.showEditorWithCreate();
    		// },
      },
    }
</script>

<style scoped lang='scss'>
@import '../../../node_modules/fullcalendar/dist/fullcalendar.css';

#calendar {
    height: 80vh;
}
</style>
