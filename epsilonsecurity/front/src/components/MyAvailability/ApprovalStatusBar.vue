<template>

    <div id = "approvalStatus-bar">
        <p> Status:

            <!--TODO GRAB status of availability open-->
            <span v-if="status == 'Open'">
				<span style="color: blue;">

					{{ status }}
				</span>
			</span>


            <!--TODO GRAB status of availability Pending-->
            <span v-else-if="status == 'Pending'">
				<span style="color: red;">
					{{ status }}
				</span>
			</span>

            <!--TODO GRAB status of availability APproved-->
            <span v-else="status == 'Approved'">
				<span style="color: green;">
					{{ status }}
				</span>

            </span>

        </p>

    </div>


</template>


<script>

    export default {
        data() {
            return {

            }
        },
        props:{
            status: {
                type: String,
                required: true,
            }
        },

    }

</script>

<style scoped>


    #approvalStatus-bar {
        background: lightblue;

        width: 100%;
        height: 100%;

    }


</style>
