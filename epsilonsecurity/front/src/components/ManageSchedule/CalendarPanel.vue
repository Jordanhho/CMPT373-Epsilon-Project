<template>
    <h2> rendering {{ teamId }} </h2>
</template>
<script>
    import FullCalendar from 'vue-full-calendar';

    export default {
        data: function() {
            return {

            }
        },
        components: {
            'full-calendar': FullCalendar,
        },
        props: {
            teamId: {
                type: String,
                required: true,
            }
        }
    }
</script>
<style>



</style>
