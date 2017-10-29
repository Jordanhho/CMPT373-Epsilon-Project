<template>
	<div id="my-availability">

		<p> Showsubmission: {{showSubmission}} </p>
		<div id = "ConfirmationSubmissionPopUp" v-if="showSubmission" >
			<confirmation-submission-popup @submitAvail = "submitAvail" @cancelAvail = "cancelAvail">

			</confirmation-submission-popup>

		</div>


		<div id = "ApprovalStatusBar">
			<approvalStatus-bar v-bind:status="status">

			</approvalStatus-bar>
		</div>

		<div id = "my-calendar">
			<full-calendar id="calendar"
						   :events="events"
						   :config="config">
			</full-calendar>
		</div>

		<div id = "SubmissionBar" v-if="!submitted">
			<submission-bar @showSubmitPopUp = "showSubmitConfirmation">
		</submission-bar>

		</div>

	</div>




</template>

<script>

    //calendar
    import Vue from 'vue';
    import FullCalendar from 'vue-full-calendar';
    import moment from 'moment';

    // add the vue-full-calendar plugin to Vue
    Vue.use(FullCalendar);

    // we need jquery too
    window.jQuery = window.$ = require('jquery');

	//submissionBar
	import SubmissionBar from './SubmissionBar.vue';

	//approvalStatusBar
	import ApprovalStatusBar from './ApprovalStatusBar.vue';

	//confirmation submission
	import ConfirmationSubmissionPopUp from './ConfirmationSubmissionPopUp.vue';


    export default {

        data() {
            return {
				//data
                status: "Open",
                submitted: false,
                showSubmission: false,


                config: {



                  	defaultView: 'agendaWeek',

                    height: 'parent',
                    allDaySlot: false,

					editable: false,
                    selectable: false,
                    selectHelper: false,

                    // smallest increment of a shift 15 minutes
                    slotDuration: '00:15:00',

                    // scroll calendar to the specified time
                    scrollTime: '00:00:00', // (12 AM)

					//shows today marker
                    nowIndicator: true,
                },



                events: [


                    /*
                    {
                        id: 1,
                        title: 'My First Overlapping Shift',
                        start: '2017-10-16 01:00:00',
                        end: '2017-10-16 02:30:00'
                    },
                    {
                        id: 2,
                        title: 'My Second Overlapping Shift',
                        start: '2017-10-16 02:00:00',
                        end: '2017-10-16 03:30:00'
                    },
                    {
                        id: 3,
                        title: 'My Weekday Shift 1',
                        start: '2017-10-19 03:00:00',
                        end: '2017-10-19 04:00:00'
                    },
                    {
                        id: 4,
                        title: 'My Weekdend Shift',
                        start: '2017-10-21 01:00:00',
                        end: '2017-10-21 01:30:00'
                    }
                    */
                ],

            }
        },


        methods: {


//            addEvent: function (timeStart, timeEnd) {
//                var title = prompt('Event Title:');
//                if (title) {
//                    calendar.fullCalendar('renderEvent',
//                        {
//                            id: id,
//                            title: title,
//                            start: start,
//                            end: end,
//                            allDay: allDay
//                        },
//                        true // make the event "stick"
//                    );
//
//
//                    /**
//                     * ajax call to store event in DB
//                     */
//
//                    /*
//                    jQuery.post(
//                        "event/new" // your url
//                        , { // re-use event's data
//                            title: title,
//                            start: start,
//                            end: end,
//                            allDay: allDay
//                        }
//                    );
//                    */
//                }
//                //calendar.fullCalendar('unselect');
//            },


            showSubmitConfirmation: function () {
                this.showSubmission = true;
                alert("Clicked Submit!");
            },

            hideSubmitConfirmation: function () {
                this.showSubmission = false;
               // alert("HideSubmit");
            },


            submitAvail: function () {
                this.submitted = true;
                this.status = 'Pending';
                this.hideSubmitConfirmation();
                alert("submit avail");
            },

            cancelAvail: function () {
                this.submitted = false;
                this.hideSubmitConfirmation();
                alert("cancel avail");
            },



        },


        components: {
            "approvalStatus-bar": ApprovalStatusBar,
			"submission-bar": SubmissionBar,
			"confirmation-submission-popup": ConfirmationSubmissionPopUp,
		}
    }

</script>




<style scoped>
	@import '../../../node_modules/fullcalendar/dist/fullcalendar.css';







	#my-availability {
		background: white;
		width: 100%;
		position: relative;
		display: flex;
		flex-flow: column nowrap;
		justify-content: flex-start;
	}


	#ApprovalStatusBar {
		background: lightblue;
		width: 100%;
		height: 2em;
	}

	#my-calendar {
		background: white;
		position: relative;
		flex-grow: 1;
	}


	#SubmissionBar {
		background: lightblue;
		width: 100%;
		height: 2em;
	}

	#ConfirmationSubmissionPopUp {
		background: blue;
		width: 100%;
		height: 2em;
		z-index: 9000;
		position: absolute;
		opacity: 1;
		left: 0;
		top: 0;

	}


</style>
