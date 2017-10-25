<template>
    <div id = "profile">
        <img :src="userPhoto" alt="" id= "user-photo">
        <ul id = "user-info">
            <li v-for= "info in infoList">
                {{info.text}}
            </li>
        </ul>
    </div>
</template>

<script>
    export default {
        name: 'user-profile',
        data(){
            return {
                userPhoto: 'http://lorempixel.com/100/100/people',
                infoList: [
                    {text: 'first name, last name'},
                    {text: 'team'},
                    {text: 'role'},
                    {text: 'email'},
                    {text: 'phone'}

                ]
            }
        },
    }
</script>

<style scoped lang='scss'>
    #profile {
        display: flex;
    }

    #user-info, #user-photo {
        flex-flow: nowrap row;
    }

    #user-photo {
        width: 150px;
        height: 150px;
        border-radius: 50%;
    }

    #user-info {
        list-style: none;
        text-align: left;
    }




</style>