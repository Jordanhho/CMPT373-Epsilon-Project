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

    export default {
        name: 'my-feed',
        data() {
            return {
                scheduleReminders: [1, 2, 3, 1, 1, 1, 1]
            }
        },
        components: {
            ScheduleReminder
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
