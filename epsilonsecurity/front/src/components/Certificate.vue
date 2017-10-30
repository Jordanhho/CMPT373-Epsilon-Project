<template>
    <div id = "certificate">
        <icon name="certificate" id="certificate-symbol" ></icon>
        <span id= "certificate-name">{{certificateName}}</span>
    </div>
</template>

<script>
import Icon from 'vue-awesome/components/Icon.vue'
export default{
    name: 'certificate',
    data(){
        return{}
    },
    components: {
        Icon
    },
    props: {
        certificateName: {
            type: String, 
            required: true 
        }
    }
}
</script>

<style scoped> 
    #certificate{
        border-style: solid;
        margin-left: 1%;
        margin-right: 1%;
        padding-bottom: 1%;
    }
    #certificate-symbol{
        display: block;
        width: 2.5em;
        height: 2.5em;
        color: gold; 
        margin-top: 5%;
        margin-left: auto;
        margin-right: auto;
        margin-bottom: 2.5%;        
    }
    #certificate-name{
        font-size: 2em;
        padding-left: 0;
        padding-right: 0;
    }
</style>    

