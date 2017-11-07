<template>
	<router-link :to="section.url" tag="li" exact class="sidenav-item">
		<icon v-bind:name="section.icon" class="icon"></icon>	
		<span>{{ section.title }}</span>
	</router-link>
</template>

<script>
import Icon from 'vue-awesome/components/Icon.vue'

export default {
	props: {
		section: {
			type: Object,
			required: true
		}
	},
	components: {
		Icon
	}
}
</script>

<style scoped>

	.sidenav-item {
		display: flex;
		flex-flow: row nowrap;
		align-items: center;
		font-size: 18px;
		padding: 8px 16px;
		cursor: pointer;
	}
	.sidenav-item .icon {
		margin-right: 8px;
		width: 16px;
	}
	.sidenav-item span {
		text-align: left;
		text-overflow: ellipsis;
		white-space: nowrap;
		overflow: hidden;
	}
	/* Default class for styling the active router link */
	.router-link-active {
		background: #ccc;
	}

</style>

<doc>
props:
	section = {
		title: String,
		icon: String,
		url: String
	}

emmits:
	tbd
</doc>

