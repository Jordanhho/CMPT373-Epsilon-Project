<template>

    <ul class="my-feed">
        <li class="my-feed-list" v-for="scheduleReminder in scheduleReminders">
            <ScheduleReminder></ScheduleReminder>
        </li>
    </ul>

</template>

<script>

    import ScheduleReminder from './ScheduleReminder.vue'

    export default {
        name: 'my-feed',
        data() {
            return {
                scheduleReminders: [1, 2, 3]
            }
        },
        components: {
            ScheduleReminder
        }
    }

</script>

<style scoped>

    .my-feed {
        height: 100%;
        width: 100%;
    }

    .my-feed-list {
        list-style: none;
        padding-bottom: 1em;
    }

</style>
