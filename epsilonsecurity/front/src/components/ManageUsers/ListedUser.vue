<template>
    <router-link exact :to="'/manage-users/' + user.id" class="listed-user" tag="li">
        <span>{{user.firstName}} {{user.lastName}}</span>
    </router-link>
</template>

<script>

    export default {
        name: 'listed-user',
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

    .listed-user {
        cursor: pointer;
        list-style: none;
        padding-top: 0.2em;
        padding-left: 0.5em;
        padding-bottom: 0.2em;
    }

    .listed-user span {
        text-align: left;
        text-overflow: ellipsis;
        white-space: nowrap;
        max-width: 100%;
        overflow: hidden;
        overflow-x: hidden;
    }

    .router-link-active {
        background: #ccc;
    }


</style>
