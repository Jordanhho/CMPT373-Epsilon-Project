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
                            v-bind:shiftTypes='shiftTypes'
                            v-bind:editMode='editMode'
                            @shiftsAdded=''
                            @addClick='editMode == false'>
                            <!-- line 16: TODO, maybe when you want to rerender? -->
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

                editMode: false,

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

                //TODO pass this information back to the event dialog emit shiftObj back to event dialog

    			//TODO close event Dialog
    		},


            //re-renders the calendar everytime the filter function changes
            renderCalendar: function() {

            },



            renderAvailabilities: function() {
                //get all availabitiies from database and render it into the calendar
                axios.get('//api/onetimeavailabilites')
                .then(this.populateShiftObjs)
                .catch(function (error) {
                    console.log(error);
                });
            },


            //initalize this user's availability list that exists for this availability week
            // populateAvailabilityObjs(response) {
            //     var localUserTeamIdList = response.data.map(userTeamId => userTeamId.userTeamId);
            //     var localTimeStartList = response.data.map(timeStartItem => timeStartItem.timeStart);
            //     var localTimeEndList = response.data.map(timeEndItem => timeEndItem.timeEnd);
            //     var localTeamIdList = [];
            //     var localTeamNameList = [];
            //
            //     //sets local list of team id
            //     for(var i = 0; i < localUserTeamIdList.length; i++) {
            //         localTeamIdList.push(this.getTeamIdFromUserTeamId(localUserTeamIdList[i]));
            //     }
            //
            //     //adds retrieved data to local availability list
            //     for(var i = 0; i < response.data.length; i++) {
            //         var newTeamName = this.getTeamNameFromTeamId(localTeamIdList[i]);
            //         var newEventId = this.getNewEventId();
            //
            //         //format moment objects from epoch
            //         var startMomentObj = moment(localTimeStartList[i], ["X"]);
            //         var endMomentObj = moment(localTimeEndList[i], ["X"]);
            //
            //         var event = {
            //            id: newEventId,
            //            start: startMomentObj,
            //            end: endMomentObj,
            //            title: newTeamName,
            //            backgroundColor:  this.getCampusColor(newTeamName),
            //            textColor:  "white",
            //            overlap: false,
            //            borderColor: "black",
            //         }
            //
            //         var localAvailability = {
            //             eventObj: event,
            //             eventId: newEventId,
            //             date: moment(startMomentObj).format("YYYY-MM-DD"),
            //             timeStart: moment(startMomentObj).format("h:mma"),
            //             timeEnd: moment(endMomentObj).format("h:mma"),
            //             teamName: newTeamName,
            //             teamId: localUserTeamIdList[i],
            //         }
            //         //push local list
            //         this.availabilityList.push(localAvailability);
            //
            //         //render the event
            //          $('#calendar').fullCalendar('renderEvent', event, true);
            //     }
            // },

            renderShifts: function() {
                //remove all events before rendering
                $('#calendar').fullCalendar('removeEvents');

                //get all shifts from database and render it into the calendar
                axios.get('/api/shifts')
                .then(this.populateShiftObjs)
                .catch(function (error) {
                    console.log(error);
                });
            },


            populateShiftObjs(response) {
                //console.log(JSON.stringify(response.data, null, 6));
                var titleList = response.data.map(shiftObj => shiftObj.title);
                var timeStartList = response.data.map(shiftObj => shiftObj.timeStart);
                var timeEndList = response.data.map(shiftObj => shiftObj.timeEnd);
                var descriptionList = response.data.map(shiftObj => shiftObj.description);
                var wasPresentList = response.data.map(shiftObj => shiftObj.wasPresent);

                //render all objects
                for(var i = 0; i < response.data.length; i++) {
                    //format timeStart, timeEnd
                    var startMomentObj = moment(timeStartList[i], ["X"]);
                    var endMomentObj  = moment(timeEndList[i], ["X"]);

                    //new event obj
                    var event = {
                       id: this.getNewEventId(),
                       start: startMomentObj,
                       end: endMomentObj,
                        title: titleList[i],
                        description: descriptionList[i],
                        backgroundColor:  this.getCampusColor(campusList[i]),
                        textColor:  "white",
                        borderColor: "black",
                    }

                    //render events
                    $('#calendar').fullCalendar('renderEvent', event, true);
                }
            },
        },
        created: function() {
    		//renders shifts at startup
    		this.renderShifts();
    	}
    }
</script>

<style scoped lang='scss'>
@import '../../../node_modules/fullcalendar/dist/fullcalendar.css';

#calendar {
    height: 80vh;
}
</style>
