<template>
	<v-app>

		<v-navigation-drawer clipped enable-resize-watcher v-model="drawer" app>
			<v-list>
				<template v-for="(item, i) in items">
					<v-list-tile v-if="item.icon"
						:key="i"
						router
						:to="item.url"
						exact>
						<v-list-tile-action>
							<v-icon light v-html="item.icon"></v-icon>
						</v-list-tile-action>
						<v-list-tile-content>
							<v-list-tile-title v-text="item.title"></v-list-tile-title>
						</v-list-tile-content>
					</v-list-tile>
					<v-divider v-else-if="item.divider" :key="i"></v-divider>
					<v-subheader v-else-if="item.header" v-text="item.header" :key="i"></v-subheader>
				</template>
			</v-list>
		</v-navigation-drawer>
		
		<v-toolbar clipped-left fixed app dark class="primary">
			<v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
			<v-toolbar-title v-text="title"></v-toolbar-title>
			<v-spacer></v-spacer>
			<v-chip  outline color="primary" text-color="white">
				<v-avatar size="40px">
					<img src="http://lorempixel.com/100/100/people" alt="">
				</v-avatar>
				Isacc Asimov <!--todo: dynamic name-->
			</v-chip>
		</v-toolbar>

		<main>
			<v-content>
				<v-container fluid fill-height>
					<router-view></router-view>
				</v-container>
			</v-content>
		</main>
		
	</v-app>
</template>

<script>
	import AppHeader from './AppHeader.vue'
	import SideNav from './SideNav.vue'

	export default {
		name: 'app',
		data() {
			return {
        drawer: true,
				title: 'Epsilon Security',
				items: [
					{ title: 'My Feed', url: '/', icon: 'notifications_none' },
					{ title: 'My Schedule', url: '/my-schedule', icon: 'schedule' },
					{ title: 'My Availability', url: '/my-availability',icon: 'apps' },
					{ title: 'My Profile', url: '/my-profile', icon: 'person_outline' },
					{ divider: true },
					{ header: 'ADMIN' },
					{ title: 'Manage Schedules', url: '/manage-schedules', icon: 'today' },
					{ title: 'Manage Users', url: '/manage-users', icon: 'people_outline' },
					{ title: 'Manage Teams', url: '/manage-teams', icon: 'people_outline' }
				]
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

<style lang="stylus">
	@import '../stylus/main.styl'
</style>


<!--
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
-->