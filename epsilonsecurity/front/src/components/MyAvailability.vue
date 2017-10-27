<template>
	<div id="my-availability">

		<div id="my-modal-container" v-if="showSubmission">
			<div id="confirmation-modal">
				<p>Do you really want to submit this availability?</p>
				<p>
					<button @click="submitAvail">Yes</button>
					<button @click="cancelAvail">No</button>
				</p>

			</div>
		</div>


		<!--
		<div id = "my-availability-modal-container">
			<div id ="edit-availability-modal">
				<p> Please enter a start and end time</p>

			</div>
		</div>
		-->


		<div id = "approval-status">
			<p> Status:

				<span v-if="status == 'Open'">
				<span style="color: blue;">
					{{ status }}
				</span>
			</span>

				<span v-else-if="status == 'Pending'">
				<span style="color: red;">
					{{ status }}
				</span>
			</span>

				<span v-else="status == 'Pending'">
				<span style="color: green;">
					{{ status }}
				</span>
			</span>

			</p>
		</div>



		<div id = "my-calendar">
			<full-calendar id="calendar"
						   :events="events"
						   :config="config">
			</full-calendar>
		</div>


		<div id="submission-bar" v-if="!submitted">
			<p>
				Submit Availability
				<button @click="showSubmitConfirmation">Submit</button>
			</p>
		</div>


	</div>
</template>

<script>

    //calendar
    import Vue from 'vue'
    import FullCalendar from 'vue-full-calendar'

    import moment from 'moment';



    Vue.use(FullCalendar) // add the vue-full-calendar plugin to Vue
    window.jQuery = window.$ = require('jquery') // we need jquery too

    export default {
        data() {
            return {

                //data
                status: "Open",
                submitted: false,
                showSubmission: false,
				showEditAvail: false,


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

                    eventColor: '#f00',

                    navLinks: true, // can click day/week names to navigate views

                    eventLimit: true, // allow "more" link when too many events
                },


                events: [

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
                ],

            }
        },


        methods: {

            showEditAvailability: function () {
                this.showEditAvail = true;
                return;
            },

            hideEditAvailability: function () {
                this.showEditAvail = false;
                return;
            },

            showSubmitConfirmation: function () {
                this.showSubmission = true;
                return;
            },

            hideSubmitConfirmation: function () {
                this.showSubmission = false;
                return;
            },

            submitAvail: function () {
                this.submitted = true;
                this.status = 'Pending';
                this.hideSubmitConfirmation();
                return;
            },

            cancelAvail: function () {
                this.submitted = false;
                this.hideSubmitConfirmation();
                return;
            },

        }

        //before routes updates
    }

</script>




<style scoped>
	@import '~fullcalendar/dist/fullcalendar.css';

	#my-availability {
		background: white;
		width: 100%;


		position: relative;
		display: flex;
		flex-flow: column nowrap;
		justify-content: flex-start;

	}

	#approval-status {
		background: lightblue;
		width: 100%;
		height: 20px;
	}

	#my-calendar {
		background: white;
		width: 100%;
		position: relative;
		flex-grow: 1;
	}

	#submission-bar {
		background: lightblue;
		width: 100%;
		/*height: 20px;*/
	}

	#my-modal-container {
		position: absolute;
		left:0px; top: 0px;
		z-index: 1000;
		height: 100%;
		width: 100%;
		background: rgba(0,0,0,0.5);
	}

	#confirmation-modal {
		background: white;
		display: inline-block;
		position: absolute;
		left: 50%; top: 50%;
		width: 300px;
		padding: 16px;
		border-radius: 8px;
		transform: translate(-50%, -50%);
		opacity: 1;
	}

	#my-availability-modal-container {
		position: absolute;
		left:0px; top: 0px;
		z-index: 1000;
		height: 100%;
		width: 100%;
		background: rgba(0,0,0,0.5);
	}

	#edit-availability-modal {
		background: white;
		display: inline-block;
		position: absolute;
		left: 50%; top: 50%;
		width: 300px;
		padding: 16px;
		border-radius: 8px;
		transform: translate(-50%, -50%);
		opacity: 1;
	}



</style>
