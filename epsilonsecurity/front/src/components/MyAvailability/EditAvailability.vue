<template>

    <div id = "edit-availability-popup">
    <transition name="modal">
        <div class="modal-mask">
            <div class="modal-wrapper">
                <div class="modal-container">



                    <div class="Time Start: ">
                        <p>First Name: </p>
                        <input  v-model="userData.firstName"
                                placeholder="edit me">
                    </div>
                    <div class="Time End: ">
                        <p>Last Name: </p>
                        <input  v-model="userData.lastName"
                                placeholder="edit me">
                    </div>
                    <div class="Campus: ">
                        <p>Teams: </p>
                        <div v-for="team in teams">
                            <input  :id="team.name"
                                    type="checkbox"
                                    :value="team.id"
                                    v-model="listOfTeamIDsForUser"
                                    @click="displayAllTeams">
                            <label :for="team.name">
                                {{ team.name }}
                            </label>
                        </div>
                    </div>



                    <div class="modal-footer">
                        <button class="editButton"
                                @click="$emit('edit')">
                            Edit
                        </button>
                        <button class="closeButton"
                                @click="$emit('close')">
                            Cancel
                        </button>
                    </div>



                </div>
            </div>
        </div>
    </transition>
</template>

<script>

    export default {
        data () {
            return {
                listOfTeamIDsForUser: [],
            }
        },
        components: {

        },
        props: {
            teams: {
                type: Array,
                required: true
            },
            userTeams: {
                type: Array,
                required: true
            },
            userData: {
                type: Object,
                required: true
            }
        },
        methods: {
            displayAllTeams() {
                alert(this.listOfTeamIDsForUser);
            },
        },
        created: function() {
            this.listOfTeamIDsForUser = this.userTeams.map(x => x.id);
            //console.log(JSON.stringify(this.userTeams, null, 2));
        }
    }
</script>

<style scoped>
    #edit-availability-popup {
        background: #ddd;
        list-style: none;
        padding: 16px 0;
        display: flex;
        flex-flow: column nowrap;
    }

    .modal-mask {
        position: fixed;
        z-index: 10000;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, .5);
        display: table;
        transition: opacity .3s ease;
    }

    .modal-wrapper {
        display: table-cell;
        vertical-align: middle;
    }

    .modal-container {
        width: 300px;
        margin: 0 auto;
        padding: 20px 30px;
        background-color: #fff;
        border-radius: 2px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
        transition: all .3s ease;
        font-family: Helvetica, Arial, sans-serif;
    }

    .modal-header h3 {
        margin-top: 0;
        color: #42b983;
    }

    .modal-body {
        margin: 20px 0;
    }

    .modal-default-button {
        float: right;
    }

    /*
     * The following styles are auto-applied to elements with
     * transition="modal" when their visibility is toggled
     * by Vue.js.
     *
     * You can easily play with the modal transition by editing
     * these styles.
     */

    .modal-enter {
        opacity: 0;
    }

    .modal-leave-active {
        opacity: 0;
    }

    .modal-enter .modal-container,
    .modal-leave-active .modal-container {
        -webkit-transform: scale(1.1);
        transform: scale(1.1);
    }

</style>
