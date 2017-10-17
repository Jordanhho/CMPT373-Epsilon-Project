<template>
  <div id="app">
		<app-header id="app-header" v-on:toggle="toggleSidenav"/>
		<div id="content">
			<side-nav id="side-nav" v-show="showSidenav"/>
    	<router-view/>
		</div>
  </div>
</template>

<script>
	import AppHeader from './AppHeader.vue'
	import SideNav from './SideNav.vue'

	export default {
		name: 'app',
		data() {
			return {
				showSidenav: true
			}
		},
		methods: {
			toggleSidenav() {
				this.showSidenav = !(this.showSidenav)
			}
		},
		components: {
			'side-nav': SideNav,
			'app-header': AppHeader
		}
	}
</script>

<style>
	* {
		margin: 0;
		padding: 0;
		box-sizing: border-box;
		outline: none;
	}
	html {
		height: 100%;
		width: 100%;
	}
	body {
		height: 100%;
	}
	#app {
		font-family: 'Avenir', Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		text-align: center;
		color: #2c3e50;
		display: flex;
		flex-flow: column nowrap;
		height: 100%;
	}
	#content {
		background: black;
		flex-grow: 1;
		display: flex;
		flex-flow: row nowrap;
	}
	#side-nav {
		/* width: 200px; */
		background: white;
		flex-grow: 0;
		flex-shrink: 0;
	}

	/* for tablets */
	@media only screen and (min-width: 600px) {
	}

	/* for desktop */
	@media only screen and (min-width: 768px) {

	}
</style>
