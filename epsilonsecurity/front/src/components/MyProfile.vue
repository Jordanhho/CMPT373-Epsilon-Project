
<template>
     <div id = "profile-main">
        <div id = "profile-header">
            <img :src="userPhoto" alt="" id= "user-photo">
            <div id = "basic-info">
                <span id= "user-name">{{userName}}</span>
                <div id = "contact-info">
                    <li v-for= "contact in contactInfo">
                        {{contact.text}}    
                    </li>
                </div> 
            </div>
        </div>
        <div id = "personal-info">
            <h1 class = "header">
                Information
            </h1>
            <div id= "info-body">
                <div id = "tab">
                    <li v-for= "tab in tabs">
                            {{tab.text}}    
                    </li>
                </div>
                <div id = "content">
                    <li v-for= "content in tabContent">
                            {{content.text}}    
                    </li>
                </div>
                <div id = "hours">
                    <span id= "number-hours">{{hoursNumber}}</span>
                    <span id= "hours-text">HOURS</span>    
                </div>
            </div>
        </div>
        <div id = "qualificationList">
                <h1 class = "header">
                    Qualification
                </h1>
                <div id = "q-body">        
                    <certificate class="certificate-list" 
                    v-for= "certificate in certificateNames" 
                    v-bind:certificateName="certificate.text">
                    </certificate>
                </div>
        </div>
    </div>
</template>

<script>
import Icon from 'vue-awesome/components/Icon.vue'
import Certificate from './Certificate.vue'

export default {
    name: 'user-profile',
    data(){
        return {
            userName: 'Ada Lovelace',
            userPhoto: 'http://lorempixel.com/100/100/people',
            contactInfo: [
                {text: 'alovelace@gmail.com'},
                {text: '604-657-9124'}
            ],
            tabs: [
                {text: 'Sfu Email: '},
                {text: 'Team: '},
                {text: 'Role: '}
            ],
            tabContent: [
                {text: 'adall@sfu.ca'},
                {text: 'Burnaby, Surrey'},
                {text: 'Volunteer'}
            ],
            hoursNumber: "14",
            certificateNames: [
                {text: "Customer Support Training"},
                {text: "Security Training"},
                {text: "Hazard Training"},
                {text: "Lost and Found Training"},
                {text: "Campus Navigation Training"},
                {text: "Whatever here"}
            ]
        }
    },
    components: {
        Certificate
    }
}
</script>

<style scoped>
    #profile-main {
        background: white;
        flex-flow: column nowrap;
        width: 100%;
        border-style: groove;
        padding-top: 5%;
    }

    #profile-header{
        flex-flow: row nowrap;
        display: flex;
        padding: 1% 0% 1% 7%;
        background: lightgrey; 
    }
    #user-photo{
        width: 12em;
        height: 12em;
        border-radius: 50%;
        border-style: none;
    }
    #basic-info{
        margin-left: 5%;
        margin-top: 1.5%;
        flex-flow: column nowrap;
    }
    #user-name{
        font-size: 4em;
        text-align: left;        
    }
    #contact-info{
        list-style-type: none;
        text-align: left;
        font-size: 2em;
    }
    #personal-info{
        margin-top: 1.5%;
    }
    #info-body{
        flex-flow: row nowrap;
        display: flex;
        margin-left: 7%;        
    }
    #tab{        
        list-style-type: none;
        text-align: right;
        font-size: 2em;
    }
    #content{
        background: white;
        list-style-type: none;
        display: block;
        text-align: left;
        font-size: 2em;
        margin-left: .5%;
    }
    #hours{
        display: flex;
        flex-flow: column nowrap;
        margin-right: 15%;
    }
    #number-hours{
        font-size: 3em;
    }
    #qualificationList{
        margin-top: 3%;
    }
    .header{
        font-size: 2em;  
        text-align: left;
        margin: 1% 0% 1.5% 1%;      
    }
    #q-body{
        display: flex;
        
    }
    .certificate-list{
        flex-flow: row nowrap;         
    }

</style>

