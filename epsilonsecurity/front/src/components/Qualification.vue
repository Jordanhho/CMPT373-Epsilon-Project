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
        text-align: center;
        margin-left: 5%;
        margin-right: 5%;
    }
    #qualification-symbol{
        display: block;
        width: 2.5em;
        height: 2.5em;
        color: gold; 
        margin-top: 2%;
        margin-left: auto;
        margin-right: auto;   
    }
    #qualification-name{
        font-size: 2rem;
        padding: 15% 0 5% 0;
    }
</style>    

