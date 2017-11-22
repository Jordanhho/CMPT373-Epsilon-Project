<template>

    <div class="my-feed">
        <ScheduleReminder></ScheduleReminder>
        <ScheduleReminder></ScheduleReminder>
        <ScheduleReminder></ScheduleReminder>
    </div>

</template>

<script>

    import ScheduleReminder from './ScheduleReminder.vue'

    export default {
        name: 'my-feed',
        data() {
            return {
                scheduleReminders: []
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
        margin: auto;
    }


</style>
