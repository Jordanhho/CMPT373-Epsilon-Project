<template>
    <v-container fluid fill-height>
        <v-layout row wrap>
            <v-flex xs11 lg3 v-if='left || $vuetify.breakpoint.lgAndUp'>
                <!-- OptionsPanel !-->
                <options @selected='newTeamSelected'
                         @changed='newShiftSelected'>
                </options>
            </v-flex>
            <v-flex xs1 hidden-lg-and-up fill-height>
                <button @click='toggleView' class='fill-all'>
                        <icon   :name='arrowDirection()'
                                class='back colored center'></icon>
                </button>
            </v-flex>
            <v-flex xs11 lg9 fill-height v-if='!left || $vuetify.breakpoint.lgAndUp'>
                <!-- CalendarPanel !-->
                <router-view v-bind:shiftTypes='selectedShifts'>
                </router-view>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import OptionsPanel from './OptionsPanel.vue';
    import Icon from 'vue-awesome/components/Icon.vue';
    import axios from 'axios';

    export default {
        name: 'manage-schedule',
        data() {
            return {
                selectedTeam: 1,
                left: true,
                selectedShifts: []
            }
        },
        methods: {
            newTeamSelected(selected) {
                this.selectedTeam = selected;
                this.$router.push({path: '/manage-schedules/' + selected});
            },
            newShiftSelected(selected) {
                this.selectedShifts = selected;
            },
            toggleView() {
                this.left = !this.left;
            },
            arrowDirection() {
                if(this.left) {
                    return 'arrow-right';
                } else {
                    return 'arrow-left';
                }
            }
        },
        mounted: function() {
            this.$router.push({path: '/manage-schedules/' + this.selectedTeam});
        },
        components: {
            "options": OptionsPanel,
            Icon
        }
    }
</script>


<style scoped lang='scss'>

.center {
    position: relative;
    top: 50%;
    transform: translateY(-50%);
}

.fill-all {
    height: 100%;
    width: 100%;
}

.colored {
    color: purple;
    height: 3em;
    width: auto;
}

</style>
