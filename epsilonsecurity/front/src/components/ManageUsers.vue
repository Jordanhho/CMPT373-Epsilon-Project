<template>
    <div id="manage-users">
        <div id = "user-list">
            <div id = "userlist-view">
                <h1>list of users</h1>
            </div>
        </div>
        <div id = "profile-view">
            <img :src="userPhoto" alt="" id= "user-photo">
            <div id = "user-info">
                <li v-for= "info in infoList">
                    {{info.text}}
                </li>
            </div>
        </div>
        <div id = "qualification-view">
            <div id = "qualification-table">
                <h1>list of qualifications</h1>
            </div>
        </div>
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

<style scoped>
    #manage-users {
        background: white;
        width: 100%;
        position: relative;
    }
    #manage-users h1 {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
    }

    #profile-view {
        height: 50%;
        width: 70%;
        background: white;
        flex-flow: column nowrap;
        position: absolute;
        margin-top: 5%;
        margin-left: 25%;
        margin-right: 20%;
        border-style: inset;
    }

    #user-photo{
        width: 150px;
        height: 150px;
        border-radius: 50%;
        margin-top: 2%;
        margin-bottom: 2%;
        border-style: outset;
    }

    #user-info{
        border-style: outset;
        font-size: 1.0em;
        margin-top: 2%;
        margin-left: 1%;
        text-align: left;
        list-style: none;
        padding-left: 50%;
        padding-top: 0.5%;
        padding-bottom: 0.5%;
        border-left-width: inherit;
    }

    #qualification-view {
        height: 50%;
        width: 70%;
        background: white;
        flex-flow: column nowrap;
        position: absolute;
        margin-top: 35%;
        margin-left: 25%;
        margin-right: 20%;
        border-style: inset;
    }

    #userlist-view {
        height: 100%;
        width: 20%;
        background: white;
        flex-flow: column nowrap;
        position: absolute;
        margin-top: 2%;
        margin-left: 1%;
        margin-right: 20%;
        border-style: inset;
    }

</style>
