<template>

    <v-container>

        <ul v-if="scheduleReminders.length > 0" class="my-feed">
            <li class="my-feed-element" v-for="scheduleReminder in scheduleReminders">
                <ScheduleReminder
                        v-bind:shiftName="scheduleReminder.shiftName"
                        v-bind:shiftStartTime="scheduleReminder.shiftStartTime"
                        v-bind:shiftEndTime="scheduleReminder.shiftEndTime"
                        v-bind:shiftTeamName="scheduleReminder.shiftTeamName"
                        v-bind:shiftDate="scheduleReminder.shiftDate"
                        v-bind:daysTilShift="scheduleReminder.daysTilShift">
                </ScheduleReminder>
            </li>
        </ul>

        <h2 v-if="scheduleReminders.length <= 0" class="empty-message" display-3>No Reminders</h2>
        <h6 v-if="scheduleReminders.length <= 0" class="empty-message" title>Sign up for some shifts!</h6>

    </v-container>

</template>

<script>

    import ScheduleReminder from './ScheduleReminder.vue'
    import axios from 'axios';

    export default {
        name: 'my-feed',
        data() {
            return {
//                daysTilShift: '',
//                shiftStartTime: '',
//                shiftEndTime: '',
//                shiftDate: '',
//                shiftName: '',
                scheduleReminders: []
            }
        },
        components: {
            ScheduleReminder
        },
        methods: {
            populateScheduleReminder(scheduleReminder) {
//                alert(JSON.stringify(scheduleReminder.data, null, 2))
                var arrLength = scheduleReminder.data.length;

                for (var i = 0; i < arrLength; i++) {
                    this.scheduleReminders.splice(i, 0, scheduleReminder.data[i])
                }
            }
        },
        created: function () {
            axios.get('/api/users/' + 2 + '/reminders')
                .then(this.populateScheduleReminder)
                .catch(function (error) {
                    console.log(error)
                });
        }
    }

</script>

<style scoped>

    html, body {
        height: 100%;
    }

    .empty-message {
        text-align: center;
    }

    .container {
        padding: 0;
    }

    .my-feed {
        height: 90vh;
        overflow-y: scroll;
        padding: 0;
    }

    .my-feed-element {
        list-style: none;
        padding: 1em;
    }

</style>
