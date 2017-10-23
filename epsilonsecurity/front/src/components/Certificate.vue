props: ['certificateName']
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
    }
}
</script>

<style scoped> 
    #certificate{
        flex-flow: column nowrap;
        width: 100%;
    }
    #certificate-symbol{
        width: auto;
        height: 2.5em;
        display: block;
        color: gold;
        margin-left: auto;
        margin-right: auto;
        margin-bottom: 5%;        
    }
    #certificate-name{
        font-size: 2em;
        display: block;        
    }
</style>    

