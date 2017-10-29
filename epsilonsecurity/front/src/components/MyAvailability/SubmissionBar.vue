<template>

    <div id ="submission-bar">
        <p>
            Submit Availability
            <button id= "confirmation-submission-popup" @click="$emit('showSubmitPopUp')">Submit</button>
        </p>
    </div>

</template>


<script>

    //confirmation submission
    import ConfirmationSubmissionPopUp from './ConfirmationSubmissionPopUp.vue';

    export default {
        data() {
            return {
                //data

            }
        },


        methods: {


        },


        components: {

            "confirmation-submission-popup": ConfirmationSubmissionPopUp,
        }
    }

</script>

<style scoped>


    #submission-bar {
        background: lightblue;
        width: 100%;
        height: 100%;

    }



</style>
