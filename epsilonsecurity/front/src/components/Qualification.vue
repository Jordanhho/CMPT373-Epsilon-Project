<template>
    <div id = "qualification">
        <icon name="certificate" id="qualification-symbol" ></icon>
        <span id= "qualification-name">{{qualificationName}}</span>
    </div>
</template>

<script>
import Icon from 'vue-awesome/components/Icon.vue'
export default{
    name: 'qualification',
    data(){
        return{}
    },
    components: {
        Icon
    },
    props: {
        qualificationName: {
            type: String, 
            required: true 
        }
    }
}
</script>

<style scoped> 
    #qualification{
        border-style: solid;
        margin-left: 1%;
        margin-right: 1%;
        padding-bottom: 1%;
    }
    #qualification-symbol{
        display: block;
        width: 2.5em;
        height: 2.5em;
        color: gold; 
        margin-top: 5%;
        margin-left: auto;
        margin-right: auto;
        margin-bottom: 2.5%;        
    }
    #qualification-name{
        font-size: 2em;
        padding-left: 0;
        padding-right: 0;
    }
</style>    

