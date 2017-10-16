<template>
  <div id = "profile-main">

      <div id = "top-bar">
        <h1>{{userName}} </h1>
            <span id= "space"></span>
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
            text: 'Space'
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
        height: auto;
        width: 100%;
        margin-top: 10%;
        flex-flow: row nowrap;
    }
    #top-bar h1{
        height: 100%;
        width: 40%;
        background: green;
        position: relative;
        left: 0;
        top: 0;
    }
    #span{
        position: relative;
        width: 50%;
        top: 0;
        left: 40;    
    }

    #top-bar button{
        background: blue;
        position: relative;
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

