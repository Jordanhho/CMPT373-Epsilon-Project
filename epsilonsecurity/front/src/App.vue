<template>
	<v-app>

		<v-navigation-drawer v-if="userIsAuthenticated" persistent enable-resize-watcher v-model="drawer" :mini-variant.sync="mini" :mobile-break-point="700" app>

			<v-toolbar flat class="transparent">
				<v-list class="pa-0">
					<v-list-tile avatar>
						<v-list-tile-avatar>
							<img :src="userPhoto" />
						</v-list-tile-avatar>
						<v-list-tile-content>
							<v-list-tile-title v-html="userName"></v-list-tile-title>
							<v-list-tile-sub-title v-html="userRole"></v-list-tile-sub-title>
						</v-list-tile-content>
						<v-list-tile-action>
							<v-btn icon @click.native.stop="mini = !mini">
								<v-icon>chevron_left</v-icon>
							</v-btn>
						</v-list-tile-action>
					</v-list-tile>
				</v-list>
			</v-toolbar>

			<v-list class="pt-0">
				<v-divider></v-divider>
				<template v-for="(item, i) in menuItems">
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

		<v-toolbar fixed app dark class="primary">
			<v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
			<v-toolbar-title v-text="title"></v-toolbar-title>
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

	export default {
		name: 'app',
		data() {
			return {
        drawer: true,
				title: 'Epsilon Security',
				mini: true,
			}
		},
		computed: {
			menuItems () {
				let items = []

				if (this.userIsAuthenticated) {
					switch (this.userRole) {
						case 'lead':
						case 'supervisor':
						case 'admin':
							items = [
								{ title: 'Schedules', url: '/manage-schedules', icon: 'today' },
								{ title: 'Users', url: '/manage-users', icon: 'person' },
								{ title: 'Teams', url: '/todo', icon: 'group' },
								{ title: 'Availabilities', url: '/todo', icon: 'hourglass_full' },
							]
							break
//						case 'supervisor':
//							items = [
//								{ title: 'Schedules', url: '/manage-schedules', icon: 'today' },
//								{ title: 'Users', url: '/manage-users', icon: 'person' },
//								{ title: 'Teams', url: '/todo', icon: 'group' },
//								{ title: 'Availabilities', url: '/todo', icon: 'hourglass_full' },
//							]
//							break
//						case 'lead':
//							items = [
//								{ title: 'My Feed', url: '/', icon: 'notifications_none' },
//								{ title: 'My Schedule', url: '/my-schedule', icon: 'schedule' },
//								{ title: 'My Availability', url: '/my-availability',icon: 'hourglass_empty' },
//								{ title: 'My Profile', url: '/my-profile', icon: 'person_outline' },
//							]
//							break
						default: // volunteer
							items = [
								{ title: 'My Feed', url: '/', icon: 'notifications_none' },
								{ title: 'My Schedule', url: '/my-schedule', icon: 'schedule' },
								{ title: 'My Availability', url: '/my-availability',icon: 'hourglass_empty' },
								{ title: 'My Profile', url: '/my-profile', icon: 'person_outline' },
							]
					}
				}

				items = items.concat([
					{ title: 'Sign out', url: '/signout', icon: 'exit_to_app' }
				])

				return items
			},
			userIsAuthenticated () {
				return this.$store.getters.isLoggedIn
			},
			userRole () {
				return this.$store.getters.userRole
			},
			userName () {
				return this.$store.getters.userName
			},
			userPhoto () {
				return this.$store.getters.userPhoto
			}
		},
		methods: { }
	}
</script>

<style lang="stylus">
	@import 'stylus/main.styl'

	body
		overflow: hidden
</style>
