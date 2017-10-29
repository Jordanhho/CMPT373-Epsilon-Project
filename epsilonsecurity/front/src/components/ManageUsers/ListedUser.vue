<template>
    <div class="listed-user" @click="onClickButton">
        {{user.firstName}}, {{user.lastName}}
    </div>
</template>

<script>

    export default {
        name: 'listed-user',
        data() {
            return {

            }
        },
        methods: {
            onClickButton (event) {
                this.$emit('clicked', this.user.userID);
            },
        },
        components: {

        },
        props: {
            user: {
                type: Object,
                required: true
            },
        },
        onclick: function() {
            // get database id here
        },
    }
</script>

<style scoped lang='scss'>

</style>
