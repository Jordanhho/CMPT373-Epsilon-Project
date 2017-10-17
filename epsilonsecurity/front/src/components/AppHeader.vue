<template>
	<div id="app-header">
		<button @click="$emit('toggle')">
			<icon name="bars" id="hamburger" ></icon>
		</button>
		<img :src="appIcon" alt="">
		<h1>{{appName}}</h1>
		<span class="spacer"></span>
		<span id="user-name">{{userName}}</span>
		<img :src="userPhoto" alt="" id="user-photo">
	</div>
</template>

<script>
import Icon from 'vue-awesome/components/Icon.vue'

export default {
	name: 'app-header',
	data() {
		return {
			appIcon: '',
			appName: 'Epsilon Security',
			userName: 'Ada Lovelace',
			userPhoto: 'http://lorempixel.com/100/100/people'
		}
	},
	methods: { },
	components: {
		Icon
	}
}
</script>

<style scoped>
	#app-header {
		background: #f1f1f1;
		color: #333;
		display: flex;
		flex-flow: row nowrap;
		align-items: center;
		padding: 8px 16px;
	}
	#app-header button {
		margin-right: 16px;
		outline: none;
		border: none;
		background: none;
	}
	#hamburger {
		width: auto;
		height: 2.5em; 
		
		/* needed by safari */
		max-width: 100%;
		max-height: 100%;
	}
	#app-header h1 {
		font-size: 20px;
		font-weight: normal;
		text-align: left;
		font-size: 16px;
		text-overflow: ellipsis;
		white-space: nowrap;
		overflow: hidden;
	}
	#app-header .spacer {
		flex-grow: 1;
	}
	#user-name {
		text-overflow: ellipsis;
		white-space: nowrap;
		overflow: hidden;
	}
	#user-photo {
		width: 40px;
		height: 40px;
		background: #ccc;
		border-radius: 50%;
		margin-left: 8px;
	}
</style>
