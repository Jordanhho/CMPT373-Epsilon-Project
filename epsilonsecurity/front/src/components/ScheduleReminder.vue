<template>

    <v-container elevation-2 class="pa-3">

        <v-layout row wrap>

            <v-flex xs3>
                <v-avatar class="red">
                    <v-icon dark>schedule</v-icon>
                </v-avatar>
            </v-flex>

            <v-flex d-flex xs9>
                <v-layout row wrap>

                    <v-flex d-flex xs12>
                        <v-layout row wrap>
                            <v-flex d-flex xs6>
                                <p id="notification-title">Shift Reminder</p>
                            </v-flex>

                            <v-flex d-flex xs6>
                                <p class="text-xs-right" id="hours-ago">in {{daysTilShift}} day(s)</p>
                            </v-flex>

                            <v-flex d-flex xs12>
                                <p>You have a {{shiftName}} shift from {{shiftStartTime}} to {{shiftEndTime}}
                                    on {{shiftDate}} in {{shiftTeamName}}.</p>
                            </v-flex>
                        </v-layout>
                    </v-flex>

                </v-layout>
            </v-flex>

        </v-layout>

    </v-container>

</template>

<script>

    export default {
        name: 'scheduleReminder',
        data() {
            return {

            }
        },
        props: {
            daysTilShift: {
                type: String,
                required: false
            },
            shiftStartTime: {
                type: String,
                required: false
            },
            shiftEndTime: {
                type: String,
                required: false
            },
            shiftName: {
                type: String,
                required: false
            },
            shiftDate: {
                type: String,
                required: false
            },
            shiftTeamName: {
                type: String,
                required: false
            }
        }
    }

</script>

<style scoped>

    #notification-title {
        font-weight: bold;
        font-size: 14pt;
    }

    #hours-ago {
        font-weight: bold;
        color: dimgrey;
    }


</style>

