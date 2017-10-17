<template>
  <div id = "profile-main">
      <div id = "top-bar">
            <span id= "user-name">{{userName}} </span>
            <span class= "top-bar-spacer">&nbsp</span>
            <button>
            <icon name= "toggle-down" id="expand"></icon>
            </button>
      </div>
      
      <span id="spacer">{{text}}</span>
      <img :src="userPhoto" alt="" id= "user-photo">
      <!--<span> class = "dots"</span>
      <span> class = "user-info" </span> 
      <div id = "user-stat">
          <span>class = "placeholder"<span>
      </div>  -->
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
            text: 'Space #2'
        }
    },
    components: {
        Icon
    }
}
</script>

<style scoped>
    #profile-main {
        width: 80%;
        background: grey;
        flex-flow: column nowrap;
        position: relative;
        margin-left: 10%;
        margin-right: 10%;
    }
    #top-bar{
        background: red;
        height: 20%;
        width: 100%;
        margin-top: 10%;
        flex-flow: row nowrap;
        position: relative;
    }
    #user-name{
        height: 100%;
        width: 20%;
        background: green;
        position: absolute;
        left: 5%;
    }
    .top-bar-spacer{
        background: pink;
        width: 40%;
        height: 100%;
        position: absolute;
        left: 25%;
        display: block;
    }

    #top-bar button{
        background: blue;
        /* border: none; */
        font-size: 50%;
        position: absolute;
        height: 100%;
        left: 66%;
    }

    #spacer{
        background: white;
        display: flex;
        height: 50px;
    }

    #user-photo{
        background: yellow;
        width: 180px;
        height: 180px;
        border-radius: 50%;
    }
    
    /*
    #profile-main .user-info{
        background: green;
    }
    #user-stat .placeholder{
        background: pink;
    } */
</style>

