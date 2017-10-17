import Vue from 'vue'
import VueRouter from 'vue-router'
import MyFeed from './components/MyFeed.vue';
import MySchedule from './components/MySchedule.vue';
import MyProfile from './components/MyProfile.vue';
// todo: import other components here
import NotFound from './components/NotFound.vue';

Vue.use(VueRouter)

export default new VueRouter({
	mode: 'history', // caveats. see https://router.vuejs.org/en/essentials/history-mode.html
  routes: [
		{ path: '/', component: MyFeed },
		{ path: '/my-schedule', component: MySchedule },
		{ path: '/my-profile', component: MyProfile }, 
		// todo: other routes here
		{ path: '*', component: NotFound },
  ]
})
