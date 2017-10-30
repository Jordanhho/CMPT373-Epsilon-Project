
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
        <div id = "qualificationList">
                <h1 id= "q-header">
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
                {text: "Campus Navigation Training"}
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
        padding-top: 7%;
    }

    #profile-header{
        flex-flow: row nowrap;
        display: flex;
        margin-left: 7%; 
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
        flex-flow: row nowrap;
        display: flex;
        margin-top: 3%;
        margin-left: 7%;
        background: #DCDCDC;
    }
    #tab{        
        list-style-type: none;
        text-align: right;
        font-size: 2em;
    }
    #content{
        background: #DCDCDC;
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
    #q-header{
        font-size: 2em;  
        text-align: left;
        margin-top: 2%;  
        margin-left: 1%;    
    }
    #q-body{
        display: flex;
        margin-top: 2.5%;
    }
    .certificate-list{
        flex-flow: row nowrap;         
    }

</style>

