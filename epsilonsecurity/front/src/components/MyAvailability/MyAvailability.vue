<template>
	<div id="my-availability">

		<div id = "ConfirmationSubmissionPopUp" v-if="showSubmission" >
			<confirmation-submission-popup @submitAvail = "submitAvail" @cancelAvail = "cancelAvail">

			</confirmation-submission-popup>

		</div>


		<div id = "ApprovalStatusBar">
			<approvalStatus-bar v-bind:status="status">

			</approvalStatus-bar>
		</div>



		<div id = "center-container" >


			<div id = "my-calendar">
				<full-calendar id="calendar"
							   :event-sources="eventSources"
							   :config="config">
				</full-calendar>
			</div>


			<div id = "MenuBar">
				<menu-bar>

				</menu-bar>
			</div>

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

	//menu bar
	import MenuBar from './MenuBar.vue';



    export default {

        data() {
            return {
				//data
                status: "Open",
                submitted: false,
                showSubmission: false,


                config: {

                  	timezone: 'local',

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


                eventSources: [
                    // 1st event source
                    {
                        events: function(start, end, timezone, callback) {


//                            // todo: use userId when api is ready.
//                            const sfuEmail = store.getters.currentUserEmail
//
//
//                            axios.get(`/api/users/${sfuEmail}/shifts`)
//                                .then(response => {
//                                    // console.log(JSON.stringify(response.data,null,2))
//                                    callback(response.data)
//                                })
//                                .catch(error => {
//                                    console.log(error)
//                                })
                        },


                        color: "black",
                        textColor: "white"
                    }
                ],





            }
        },




        methods: {



            showSubmitConfirmation: function () {
                this.showSubmission = true;
            },

            hideSubmitConfirmation: function () {
                this.showSubmission = false;
            },


            submitAvail: function () {
                this.submitted = true;
                this.status = 'Pending';
                this.hideSubmitConfirmation();
            },

            cancelAvail: function () {
                this.submitted = false;
            },



        },


        components: {
            "approvalStatus-bar": ApprovalStatusBar,
			"submission-bar": SubmissionBar,
			"confirmation-submission-popup": ConfirmationSubmissionPopUp,
            "menu-bar": MenuBar,
		}
    }

</script>




<style scoped lang='scss'>
	@import '../../../node_modules/fullcalendar/dist/fullcalendar.css';


	$menu-edit-width: 20%;



	#my-availability {
		background: white;
		width: 100%;
		position: relative;
	}


	#ApprovalStatusBar {
		background: lightblue;
		width: 100%;
		height: 2em;
	}



	#center-container {
		background: white;
		width: 100%;
		height: 20em;
		flex-flow: row nowrap;
		//height: 80%;
		position: relative;

	}



	#my-calendar {
		background: white;
		position: relative;
		height: 10em;
		left: 0;
		//height: 100%;
		width: 100% - $menu-edit-width;

	}


	#menu-bar {

		//width: $menu-edit-width;
		background: white;
		height: 10em;
		right: 0;
		//height: 100%;
		width: $menu-edit-width;

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
