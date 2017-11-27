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
    Vue.use(FullCalendar);
    window.jQuery = window.$ = require('jquery');

    export default {
        data: function() {
            return {
                loggedInUserId: 2,

                shiftObjList: [],

                shift: {
                    eventObj: null,
                    eventId: -1,
                    date: null,
                    timeStart: null,
                    timeEnd: null,
                    teamId: -1,
                    shiftTypeId: -1,
                },

                //-------------------- Campus selection -----------------------
                //campus colors
                burnabyCampus: "red",
                surreyCampus: "blue",
                vancouverCampus: "teal",
                noCampus: "purple",

                //-------------------- event id assignment -------------------------------
    			assignEventId: 0,

                //-------------------- calendar config -------------------------------
                config: {
                    header: {
    					left: 'prev,today,next,',
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
    				selectable: false, //allows dragging on calendar.
    				//selectHelper: true, //allows to make an event on calendar
                    eventClick: this.shiftClickEvent, //triggered with an event is clicked
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
            //-------------- Helper functions -----------------------------

            //gets a new event id and increment the eventid counter
    		getNewEventId: function() {
    			var newEventId = this.assignEventId;
    			this.assignEventId = this.assignEventId + 1;
    			return newEventId;
    		},

            //returns the color string for the team colors
            getCampusColor: function(teamName) {
                if(teamName == "BURNABY") {
                    return this.burnabyCampus;
                }
                //blue for surrey
                else if(teamName == "SURREY") {
                    return this.surreyCampus;
                }
                //Yellow for vancouver
                else if(teamName == "VANCOUVER") {
                    return this.vancouverCampus;
                }
                //purple if not specified -> for debug
                else {
                    return this.noCampus;
                }
            },

            //---------------------create, delete, edit, rerender calendar operations ----------------------

            //handles user clicking on event
    		shiftObjClickEvent: function(event, jsEvent, view) {

    			//set currently selected shiftObj to this event's data
    			this.shiftObj.eventObj = event;
    			this.shiftObj.eventId = event.id;
    			this.shiftObj.title = event.title;
    			this.shiftObj.date = moment(event.start, ["YYYY-MM-DD"]).format("YYYY-MM-DD");
    			this.shiftObj.timeStart = moment(event.start, ["h:mma"]).format("h:mma");
    			this.shiftObj.timeEnd = moment(event.end, ["h:mma"]).format("h:mma");
    			this.shiftObj.teamName = event.title;

                //TODO pass this information back to the event dialog

    			//TODO close event Dialog
    		},


            //re-renders the calendar everytime the filter function changes
            reRenderCalendar: function() {

            },

            createShift: function() {
                var newEventId = this.getNewEventId();
                this.shiftObj.eventId = newEventId;

                //TODO set the start, end, title to the passed in shiftObj from EventDialog
                var event = {
    	            id: newEventId,
    	            start: null,
    	            end: null,
    	            title: null,
                    color: this.getCampusColor(this.shiftObj.teamName),
                    textColor: "white",
    	        }

                //$('#calendar').fullCalendar('renderEvent', event, true);
            },


    		shiftEdit: function(eventId) {
    			$('#calendar').fullCalendar('updateEvent', eventId);

    			//TODO close event Dialog
    		},


    		shiftDelete: function(eventId) {
    			$('#calendar').fullCalendar('removeEvents', eventId);
    			//TODO close event Dialog
    		},
        },
    }
</script>

<style scoped lang='scss'>
@import '../../../node_modules/fullcalendar/dist/fullcalendar.css';

#calendar {
    height: 80vh;
}
</style>
