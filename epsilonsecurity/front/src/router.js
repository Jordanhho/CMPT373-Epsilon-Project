import Vue from 'vue'
import VueRouter from 'vue-router'
import MyFeed from './components/MyFeed.vue';
import MySchedule from './components/MySchedule.vue';
import MyAvailability from './components/MyAvailability.vue';
import ManageUsers from './components/ManageUsers/ManageUsers.vue';
import ProfileView from './components/ManageUsers/ProfileView.vue';
import MyProfile from './components/MyProfile.vue';
// todo: import other components here
import NotFound from './components/NotFound.vue';
import { store } from './store'

Vue.use(VueRouter)

const router = new VueRouter({
	mode: 'history', // caveats. see https://router.vuejs.org/en/essentials/history-mode.html
  routes: [
		{
			path: '/',
			component: MyFeed,
			meta: {
				requiresAuth: true,
				adminOnly: false
			}
		},

		{
			path: '/my-schedule',
			component: MySchedule,
			meta: {
				requiresAuth: true,
				adminOnly: false
			}
		},
		// this is a red flag -- everything about /manage-users should be in 1 subtree.
		{
			path: '/manage-users',
			name: 'userManagementList',
			component: ManageUsers,
			meta: {
				requiresAuth: true,
				adminOnly: true
			},
			children: [
				{
					path: ':id',
					name: 'userManagementProfile',
					component: ProfileView,
					props: true,
					meta: {
						requiresAuth: true,
						adminOnly: true
					}
				}
			]
		},
		{
			path: '/manage-teams',
			component: MySchedule, // todo: create component
			meta: {
				requiresAuth: true,
				adminOnly: true
			}
		},
		  {
			  path: '/my-availability',
			  component: MyAvailability,
			  meta: {
				  requiresAuth: true,
				  adminOnly: false
		  }
		},


		{
			path: '/my-profile',
			component: MyProfile,
			meta: {
				requiresAuth: true,
				adminOnly: false
			}
		},
		{
			path: '/signout',
			component: NotFound, //todo: is it needed?
			beforeEnter: (to, from, next) => {
				store.dispatch('signUserOut')
				next(false)
			}
		},
		// todo: other routes here
		{
			path: '*',
			component: NotFound
		},
	]
})

router.beforeEach( (to, from, next) => {
	// if this particular route requires an authenticated user
	const pageRequiresAuth = to.matched.some(record => record.meta.requiresAuth)
	const isLoggedIn = store.getters.isLoggedIn

	if (pageRequiresAuth && !isLoggedIn) {
			next("/login") //todo: signout to cas
	} else {
		next()
	}
})

export default router
