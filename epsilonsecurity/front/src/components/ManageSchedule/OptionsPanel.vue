<template>
    <v-container>
        <v-layout row wrap>
            <v-select x12
                        v-bind:items='teams'
                        v-model='selectedTeam'
                        @input='$emit("selected", selectedTeam)'>
            </v-select>
        </v-layout>
        <v-layout row wrap class='cap-height scroll-y'>
            <v-flex xs12>
                <v-checkbox v-for='shift in shifts'
                            :label='shift.name'
                            :value='shift'
                            v-model='selectedShifts' >
                </v-checkbox>
            </v-flex>
        </v-layout>
    </v-container>
</template>
<script>
import axios from 'axios';

export default {
    data: function () {
        return {
            teams: [],
            shifts: [],
            selectedShifts: [],
            selectedTeam: 1,
        }
    },
    watch: {
        selectedShifts: function(val) {
            this.$emit('changed', this.selectedShifts);
        }
    },
    methods: {
        populateTeams(response){
            this.teams = response.data.map(
                function(team) {
                    return {
                        value: team.id,
                        text: team.name
                    }
                }
            );
        },
        populateShifts(response) {
            this.shifts = response.data;
        },
        setTeam(team) {
            this.selectedTeam = team;
        }
    },
    mounted: function() {
        axios.get('/api/teams')
        .then(this.populateTeams)
        .catch(function (error) {
            console.log(error);
        });

        axios.get('/api/shifttypes')
        .then(this.populateShifts)
        .catch(function (error) {
            console.log(error);
        });
    }
}
</script>
<style scoped lang='scss'>
.cap-height {
    max-height: calc(100vh - 32px - 32px - 32px - 74px - 32px);
}
</style>
