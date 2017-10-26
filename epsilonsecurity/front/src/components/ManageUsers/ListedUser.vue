<template>
    <div class="listed-user" @click="onClickButton">
        {{user.first}}, {{user.last}}
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
                this.$emit('clicked', this.user.userid);
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