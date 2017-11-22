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
                                <p id="notification-title">{{notificationTitle}}</p>
                            </v-flex>

                            <v-flex d-flex xs6>
                                <p class="text-xs-right" id="hours-ago">{{hoursAgo}}</p>
                            </v-flex>

                            <v-flex d-flex xs12>
                                <p>{{notificationBody}}</p>
                            </v-flex>
                        </v-layout>
                    </v-flex>

                </v-layout>
            </v-flex>

        </v-layout>

    </v-container>

</template>

<script>

    import VAvatar from "vuetify/src/components/VAvatar/VAvatar";
    import Icon from "../../node_modules/vue-awesome/components/Icon.vue";
    import VIcon from "vuetify/src/components/VIcon/VIcon";

    export default {
        name: 'scheduleReminder',
        data() {
            return {}
        },
        components: {
            Icon,
            VIcon,
            VAvatar
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
        max-width: 30%;
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

