<template>
	<div id="header">
		<button id="sidebar-btn">
			<icon name="bars" id="navicon" scale=2.3></icon>
		</button>
		<span id="app-name">{{appName}}</span>
		<span id="spacer"></span>
		<span id="user-name">{{userName}}</span>
		<img :src="userPhoto" :alt="userName" id="user-photo">
	</div>
</template>

<script>

var Icon = require('vue-awesome')

export default {
	data () {
		return {
			appName: 'Epsilon Security',
			userName: 'Mark Twain',
			userPhoto: 'http://lorempixel.com/100/100/people'
		}
	},
	components: {
    Icon
  }
}
</script>

<style>
	#header {
		display: flex;
		flex-flow: row nowrap;
		align-items: center;
		padding: 4px 16px;
	}
	#app-name {
		font-size: 20px;
	}
	#spacer {
		flex-grow: 1;
	}
	#user-photo {
		width: 45px;
		height: 45px;
		border-radius: 50%;
		margin: 0 0 0 8px;
	}
	#sidebar-btn {
		background: none;
		margin: 0 8px 0 0;
		outline: none;
		border: none;
		display: flex; 
		flex-flow: row nowrap; 
		align-items: center;
	}
	#navicon {
		font-size: 20px;
		padding: 0; margin: 0;
		height: 100%;
	}
</style>
