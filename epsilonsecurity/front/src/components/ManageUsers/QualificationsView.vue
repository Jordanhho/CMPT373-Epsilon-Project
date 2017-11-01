<template>
    <div id='qualifications'>
        <h4>
            Qualifications
        </h4>
        <v-container grid-list-md class='scroll-y qualifications-list'>
            <v-layout row wrap >
                <v-flex xs4 sm4 md4 v-for='i in 13' class='placeholder'>
                    <div class='box'> </div>
                </v-flex>
            </v-layout>
        </v-container>
    </div>
</template>

<script></script>
   <!-- teams burnaby
         surrey
         vancouver
    role admin
sfuemail blah@derp.com -->
<style scoped>
    #qualifications {
        margin-top: 5em;
        min-height: 10em;
    }

    .box {
        background: lightgrey;
        height: 100%
    }

    .placeholder {
        padding: 1em;
        height: 7em;
    }

    .qualifications-list {
        max-height: 30em;
    }
</style>
