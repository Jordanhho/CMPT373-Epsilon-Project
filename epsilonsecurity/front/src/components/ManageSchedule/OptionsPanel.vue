<template>
    <v-container>
        <v-layout>
            <v-select x12
                        v-bind:items='teams'
                        v-model='selectedTeam'
                        @input='$emit("selected", selectedTeam)'>
            </v-select>
        </v-layout>
    </v-container>
</template>
<script>
import axios from 'axios';

export default {
    data: function () {
        return {
            teams: [],
            selectedTeam: 1,
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
            });
        }
    },
    created: function() {
        axios.get('/api/teams')
        .then(this.populateTeams)
        .catch(function (error) {
            console.log(error);
        });
    }
}
</script>
<style>
</style>
