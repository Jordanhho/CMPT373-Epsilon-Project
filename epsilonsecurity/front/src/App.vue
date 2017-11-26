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

	export default {
		name: 'app',
		data() {
			return {
        drawer: true,
				title: 'Epsilon Security',
				items: [
					{ title: 'My Feed', url: '/', icon: 'notifications_none' },
					{ title: 'My Schedule', url: '/my-schedule', icon: 'schedule' },
					{ title: 'My Availability', url: '/my-availability',icon: 'hourglass_empty' },
					{ title: 'My Profile', url: '/my-profile', icon: 'person_outline' },
					{ title: 'Statistics', url: '/todo', icon: 'trending_up' },
					{ divider: true },
					{ header: 'Manage' },
					{ title: 'Schedules', url: '/manage-schedules', icon: 'today' },
					{ title: 'Users', url: '/manage-users', icon: 'person' },
					{ title: 'Teams', url: '/todo', icon: 'group' },
					{ title: 'Availabilities', url: '/todo', icon: 'hourglass_full' },
					{ title: 'Statistics', url: '/todo', icon: 'multiline_chart' }
				]
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
