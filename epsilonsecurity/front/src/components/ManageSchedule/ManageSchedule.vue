<template>
    <v-container fluid>
        <v-layout row wrap>
            <v-flex xs12 md3>
                <!-- OptionsPanel !-->
                <options @selected='newTeamSelected'>
                </options>
            </v-flex>
            <v-flex xs12 md9 fill-height>
                <!-- CalendarPanel !-->
                <router-view>
                </router-view>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import OptionsPanel from './OptionsPanel.vue';
    import Icon from 'vue-awesome/components/Icon.vue';
    import axios from 'axios';

    //window.jQuery = window.$ = require('jquery');

    export default {
        name: 'manage-schedule',
        data() {
            return {
            }
        },
        methods: {
            newTeamSelected(selected) {
                this.$router.push({path: '/manage-schedules/' + selected})
            }
        },
        mounted: function() {
            if(this.$route.name == 'manage-schedules') {
                this.$router.push({path: '/manage-schedules/' + 1})
            }
        },
        components: {
            "options": OptionsPanel,
            Icon
        }
    }
</script>


<style scoped lang='scss'>

</style>
