
<template>
     <div id = "profile-main" class = "scroll-y">
        <div id = "profile-header">
            <img :src="userPhoto" alt="" id= "user-photo">
            <div id = "basic-info">
                <span id= "user-name">{{userName}}</span>
                <div id = "contact-info">
                    <li v-for= "contact in contactInfo">
                        {{contact}}
                    </li>
                </div>
            </div>
        </div>
        <div id = "personal-info">
            <h1 class = "header">
                Information
            </h1>
            <v-container id= "info-body" grid-list-sm test-xs-center>
                <v-layout v-bind="binding">
                    <v-flex>
                        <v-layout>
                            <div id = "tab">
                                <li v-for= "tab in tabs">
                                    {{tab.text}}
                                </li>
                            </div>
                            <div id = "content">
                                <li v-for= "content in tabContent">
                                    {{content}}
                                </li>
                            </div>
                        </v-layout>
                    </v-flex>
                    <v-flex>
                        <v-layout>
                            <span id= "spacer"></span>
                            <div id= "hours">
                                <div id = "hours-info">
                                    <span id= "hours-number">{{hoursNumber}}</span>
                                    <span id= "hours-text">HOURS</span>
                                </div>
                                <v-btn id = "hours-btn"  color = "primary" dark @click.stop= "graphDspl = true">
                                    Detailed Hours
                                </v-btn>
                            </div>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-container>

        </div>
        <div id = "qualificationList"  class = "scroll-y">
                <h1 class = "header">
                    Qualification
                </h1>
                <div id = "q-body">
                    <v-container grid-list-sm test-xs-center>
                        <v-layout v-bind="binding">
                            <v-flex v-for ="qualification in qualificationNames" :key="qualification">
                                <div>
                                    <qualification v-bind:qualificationName= "qualification" class = "card-style"></qualification>
                                </div>
                            </v-flex>
                        </v-layout>
                    </v-container>
                </div>
        </div>

        <v-dialog v-model="graphDspl" max-width="500px">
            <v-card dark color = "primary">
                <v-card-title>Working Hours By Shift Type</v-card-title>
                <pie-chart :data="chartData" id= "pie-chart"></pie-chart>
            </v-card>
        </v-dialog>
    </div>

</template>

<script>
import Icon from 'vue-awesome/components/Icon.vue'
import Qualification from './Qualification.vue'
import Chartkick from 'chartkick'
import VueChartkick from 'vue-chartkick'
import Chart from 'chart.js'


import axios from 'axios'

export default {
    name: 'user-profile',
    data(){
        return {
            userName: '',
            userPhoto: this.$store.getters.userPhoto,
            contactInfo: [],
            tabs: [
                {text: 'Sfu Email: '},
                {text: 'Team: '},
                {text: 'Role: '}
            ],
            tabContent: [],
            hoursNumber: '',
            graphDspl: false,
            chartData: [],
            qualificationNames: [],
            loggedInUserId: this.$store.getters.id,
            roleId: '',
        }
    },
    methods: {
        populateUserData(userData){
            this.userName = userData.data.firstName + " " + userData.data.lastName;
            this.contactInfo = [userData.data.contactEmail, userData.data.phoneNumber];
            this.tabContent.splice(0, 0, userData.data.sfuEmail);
            this.roleId = userData.data.roleId;
            axios.get('/api/roles/' + this.roleId)
            .then(this.populateRoleName)
            .catch(function(error){
                console.log(error)
            })
        },
        populateTeamData(teamData){
            var teamList = "";
            var listLength = teamData.data.length;
            for(var i = 0; i < listLength; i++){
                teamList += teamData.data[i].name;
                if(i != listLength - 1){
                    teamList += ", ";
                }
            }
            this.tabContent.splice(1, 0, teamList);
        },
        populateQualificationData(qualificationData){
            var listLength = qualificationData.data.length;
            for(var i = 0; i < listLength; i++){
                this.qualificationNames.splice(i, 0, qualificationData.data[i].qualificationName);
            }
        },
        populateRoleName(roleData){
            this.tabContent.splice(3, 0 , roleData.data.name);
        },
        populateHourData(hourData){
            this.hoursNumber = hourData.data;
        },
        populateChartData(hourByShiftTypeData){
            var listLength = hourByShiftTypeData.data.length;
            for(var i = 0; i < listLength; i++){
                this.chartData.splice(i, 0, [hourByShiftTypeData.data[i].shiftTypeName, hourByShiftTypeData.data[i].hour]);
            }
        }
    },
    components: {
        Qualification,
        Chart
    },
    created: function () {
        this.loggedInUserId = this.$store.getters.uid;
        axios.get('/api/users/' + this.loggedInUserId)
        .then(this.populateUserData)
        .catch(function(error){
            console.log(error)
        });
        axios.get('/api/users/' + this.loggedInUserId + '/teams')
        .then(this.populateTeamData)
        .catch(function(error){
            console.log(error)
        });
        axios.get('/api/qualification/' + this.loggedInUserId)
        .then(this.populateQualificationData)
        .catch(function(error){
            console.log(error)
        });
        axios.get('/api/shifts/hours/' + this.loggedInUserId)
        .then(this.populateHourData)
        .catch(function(error){
            console.log(error)
        });
        axios.get('/api/shifts/hours/byType/' + this.loggedInUserId)
        .then(this.populateChartData)
        .catch(function(error){
            console.log(error)
        });
    },
    computed: {
        binding() {
            const binding = {}

            if(this.$vuetify.breakpoint.mdAndDown)
                binding.column = true

            return binding
        }
    }

}
</script>

<style scoped>
    #profile-main {
        background: white;
        flex-flow: column nowrap;
        width: 100%;
        height: 100%;
        overflow: auto;
        border-style: groove;
        padding-top: 5%;
        height: 80em;
    }

    #profile-header{
        flex-flow: row nowrap;
        display: flex;
        padding: 1% 0% 1% 7%;
        background: lightgrey;
    }
    #user-photo{
        width: 15em;
        height: 15em;
        border-radius: 50%;
        border-style: none;
    }
    #basic-info{
        margin-left: 5%;
        flex-flow: column nowrap;
    }
    #user-name{
        font-size: 4.5em;
        text-align: left;
    }
    #contact-info{
        list-style-type: none;
        text-align: left;
        font-size: 2.5em;
    }

    #personal-info{
        margin-top: 1.5%;
    }
    #tab{
        list-style-type: none;
        text-align: right;
        font-size: 2.3em;
        margin-left: 15%;
        margin-top: 3%;
    }
    #content{
        background: white;
        list-style-type: none;
        display: block;
        text-align: left;
        font-size: 2.3em;
        margin-left: .5%;
        margin-top: 3%;
    }
    #spacer{
        flex-grow: .55;
    }
    #hours{
        display: flex;
        flex-flow: column nowrap;
        margin-right: 10%;
        align-items: left;
    }
    #hours-info{
        display: flex;
        flex-flow: row nowrap;
        font-size: 2em;
        text-align: right;
        margin-left: 10%;
    }
    #hours-number{
        font-size: 3.5em;
        border-top: 0%;
    }
    #hours-text{
        align-self: flex-end;
        padding-bottom: 1.7em;
        padding-left: .5em;
    }
    #hours-btn{
        font-size: 1.5em;
    }

    #qualificationList{
        margin-top: 3%;
        height: 40rem;
    }
    #q-body{
        display: flex;
    }
    .header{
        font-size: 2.5em;
        text-align: left;
        margin: 1% 0% 1.5% 3%;
        border-bottom-style: ridge;
    }
    #pie-chart{
        background: white;
        height: 20%;
        padding-bottom: 1.5em;
    }
</style>
