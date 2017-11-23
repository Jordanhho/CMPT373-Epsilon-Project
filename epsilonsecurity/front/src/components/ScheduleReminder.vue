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
                                <p id="notification-title">Trash Picker</p>
                            </v-flex>

                            <v-flex d-flex xs6>
                                <p class="text-xs-right" id="hours-ago">23h</p>
                            </v-flex>

                            <v-flex d-flex xs12>
                                <p>Just pick up the trash.</p>
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
            return {}
        },
        props: {
            notificationTitle: {
                type: String,
                required: false
            },
            notificationBody: {
                type: String,
                required: false
            },
            hoursAgo: {
                type: String,
                required: false
            }
        }
    }
</script>

<style scoped>

    .container {
        max-width: 40%;
        width: 100%;
    }

    #notification-title {
        font-weight: bold;
    }

    #hours-ago {
        font-weight: bold;
        color: dimgrey;
    }


</style>

