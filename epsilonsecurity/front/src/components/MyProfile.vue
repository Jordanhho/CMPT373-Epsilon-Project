<template>
    <div id= "my-profile">
        <div id = "profile-main">
            <img :src="userPhoto" alt="" id= "user-photo">
            <div id = "top-bar">
                    <span id= "user-name">{{userName}} </span>

                    <button>
                        <icon name= "toggle-down" scale="2" id="expand"></icon>
                    </button>
            </div>
            <div id = "user-info">
                <li v-for= "info in infoList">
                    {{info.text}}
                </li>
            </div> 
        </div>
    </div>
</template>

<script>
import Icon from 'vue-awesome/components/Icon.vue'

export default {
    name: 'user-profile',
    data(){
        return {
            userName: 'Ada Lovelace',
            userPhoto: 'http://lorempixel.com/100/100/people',
            infoList: [
                {text: 'Role: Volunteer'},
                {text: 'Team: Burnaby, Surrey'},
                {text: 'Sfu Id: 301211345'},
                {text: 'Sfu Email: adall@sfu.ca'},
                {text: 'Contact Email: alovelace@gmail.com'},
                {text: 'Phone Number: 604-445-5544'}
            ]
        }
    },
    components: {
        Icon
    }
}
</script>

<style scoped>
    #my-profile{
        background: #a6192e;
        width: 100%;
        position: relative;
    }
    #profile-main {
        height: 90%;
        width: 80%;
        background: white;
        flex-flow: column nowrap;
        position: relative;
        margin-top: 5%;
        margin-left: 10%;
        margin-right: 10%;
        border-style: inset;
    }

    /* #user-photo{
        width: 180px;
        height: 180px;
        border-radius: 50%;
    } */

    #user-photo{
        width: 180px;
        height: 180px;
        border-radius: 50%;
        margin-top: 2%;
        margin-bottom: 2%;
        border-style: outset;
    }

    #top-bar{
        background: #a6192e;
        height: 5%;
        width: 100%;
        /* margin-top: 10%; */
        flex-flow: row nowrap;
        position: relative;
        margin-left: 0.1%;
        margin-bottom: 4%;
    }

    #user-name{
        height: 100%;
        width: 50%;
        position: absolute;
        font-size: 2em;
        color: white;
        display: flex;
        padding-left: 10%;
        align-items: center;
    }

    #top-bar button{
        background: transparent;
        border: none;
        height: 100%;
        position: absolute;
        right: 0%;
        
    }

    #user-info{
        border-style: outset;
        font-size: 1.5em;
        margin-top: 2%;
        margin-left: 0.1%;
        text-align: left;
        padding-left: 10%;
        padding-top: 0.5%;
        padding-bottom: 0.5%;
    }
    /*#user-stat .placeholder{
        background: pink;
    } */
</style>

