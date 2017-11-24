<template>
	<ul id="side-nav">
		<side-nav-item
			v-for="section in mainSections"
			v-bind:key="section.id"
			v-bind:section="section"
			class="nav-item"
			/>
	</ul>
</template>

<script>
import SideNavItem from './SideNavItem.vue'

let sectionId = 1

export default {
	data () {
		return {
			mainSections: [
				{
					id: sectionId++,
					title: 'My Feed',
					url: '/',
					icon: 'bell-o'
				},
				{
					id: sectionId++,
					title: 'My Schedule',
					url: '/my-schedule',
					icon: 'calendar-o'
				},
				{
					id: sectionId++,
					title: 'My Availability',
					url: '/my-availability',
					icon: 'clock-o'
				},
				{
					id: sectionId++,
					title: 'My Profile',
					url: '/my-profile',
					icon: 'user-o'
				},
				{
					id: sectionId++,
					title: 'Manage Schedules',
					url: '/manage-schedules',
					icon: 'calendar'
				},
				{
					id: sectionId++,
					title: 'Manage Users',
					url: '/manage-users',
					icon: 'users'
				},
				{
					id: sectionId++,
					title: 'Manage Availabilities',
					url: '/manage-availabilities',
					icon: 'clock-o'
				}
			]
		}
	},
	components: {
		'side-nav-item': SideNavItem
	}
}
</script>

<style scoped>
	#side-nav {
		background: #ddd;
		list-style: none;
		padding: 16px 0;
		display: flex;
		flex-flow: column nowrap;
		align-items: left;
	}
	.nav-item {
		width: 100%;
		margin-bottom: 8px;
	}
</style>
