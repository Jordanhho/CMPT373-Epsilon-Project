<template>
    <transition name="modal">
        <div class="modal-mask">
            <div class="modal-wrapper">
                <div class="modal-container">
                    <div class="first-name">
                        <p>First Name: </p>
                        <input v-model="userData.firstName" placeholder="edit me">
                    </div>
                    <div class="last-name">
                        <p>Last Name: </p>
                        <input v-model="userData.lastName" placeholder="edit me">
                    </div>
                    <div class="team-name">
                        <p>Teams: </p>
                        <div v-for="team in teams">
                            <input :id="team.name" type="checkbox" :value="team.id" v-model="userData.teams" @click="displayAllTeams()">
                            <label :for="team.name">{{ team.name }}</label>
                        </div>
                    </div>
                    <div class="role">
                        <p>Role: </p>
                        <input v-model="userData.role" placeholder="edit me">
                    </div>
                    <div class="contact-email">
                        <p>Contact Email: </p>
                        <input v-model="userData.contactEmail" placeholder="edit me">
                    </div>
                    <div class="sfu-email">
                        <p>SFU Email: </p>
                        <input v-model="userData.sfuEmail" placeholder="edit me">
                    </div>
                    <div class="modal-footer">
                        <button class="editButton" @click="$emit('edit')">
                            Edit
                        </button>
                        <button class="closeButton" @click="$emit('close')">
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
                mainSections: [
                ]
            }
        },
        components: {

        }
    }
</script>

<style scoped>
    #add-user {
        background: #ddd;
        list-style: none;
        padding: 16px 0;
        display: flex;
        flex-flow: column nowrap;
    }

    .modal-mask {
        position: fixed;
        z-index: 9998;
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
        margin: 0px auto;
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