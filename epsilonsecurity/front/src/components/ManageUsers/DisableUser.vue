<template>
    <v-layout row justify-center>
        <v-dialog v-model="dialog" persistent max-width="500px">
            <v-btn color="primary" dark slot="activator" >{{ display }}</v-btn>
            <v-card>
                <v-card-title>
                    <span class="headline">Are you sure you want to {{ display }} this user?</span>
                </v-card-title>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" flat @click.native="confirm"> {{ display}} </v-btn>
                    <v-btn color="blue darken-1" flat @click.native="closeDialog">Close</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-layout>
</template>

<script>

    export default {
        data () {
            return {
                mainSections: [
                ],
                dialog: false,
            }
        },
        components: {

        },
        methods: {
            closeDialog() {
                this.dialog = false;
            },
            confirm() {
                this.$emit('disable');
                this.closeDialog();
            },
        },
        computed: {
            display: function() {
                return this.enabled ? 'disable':'enable';
            }
        },
        props: {
            enabled: {
                type: Boolean,
                required: true
            }
        },
    }
</script>

<style scoped>
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