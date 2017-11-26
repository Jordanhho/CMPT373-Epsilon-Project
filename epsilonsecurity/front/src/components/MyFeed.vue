<template>

    <v-container>

        <ul class="my-feed">
            <li class="my-feed-element" v-for="scheduleReminder in scheduleReminders">
                <ScheduleReminder></ScheduleReminder>
            </li>
        </ul>

    </v-container>

</template>

<script>

    import ScheduleReminder from './ScheduleReminder.vue'
    import axios from 'axios';

    export default {
        name: 'my-feed',
        data() {
            return {
                hoursAgo: '',
                shiftStartTime: '',
                shiftEndTime: '',
                shiftDate: '',
                shiftName: '',
                scheduleReminders: []
            }
        },
        components: {
            ScheduleReminder
        },
        methods: {
            populateScheduleReminder(scheduleReminder) {
                var arrLength = scheduleReminder.data.length;

                for (var i = 0; i < arrLength; i++) {
                    this.scheduleReminders.splice(i, 0, scheduleReminder.data[i])
                }
//                this.shiftStartTime = scheduleReminder.data.firstName;
//                this.shiftEndTime = scheduleReminder.data.firstName;
//                this.shiftDate = scheduleReminder.data.firstName;
//                this.shiftName = scheduleReminder.data.firstName;
            }
        },
        created: function () {
            axios.get('/api/users')
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
