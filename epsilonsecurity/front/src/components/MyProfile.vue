
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
        <certificate id = "test">
            "Hello?"
        </certificate>
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
                {text: 'Sfu Email:'},
                {text: 'Team: '}
            ],
            tabContent: [
                {text: 'adall@sfu.ca'},
                {text: 'Burnaby, Surrey'}
            ],
            hoursNumber: "14"
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
    }

    #profile-header{
        flex-flow: row nowrap;
        display: flex;
        margin-top: 7%;
    }
    #user-photo{
        width: 12em;
        height: 12em;
        border-radius: 50%;
        border-style: none;
        margin-left: 10%;
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
        margin-top: 5%;
    }
    #tab{        
        list-style-type: none;
        text-align: right;
        margin-left: 10%;
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
    #test{
        background: beige;
        width: 30%;
    }

</style>

