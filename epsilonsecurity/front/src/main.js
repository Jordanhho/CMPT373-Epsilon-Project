import Vue from 'vue'
import router from './router'
import Vuetify from 'vuetify'

import { store } from './store'
import App from './App'

import Chartkick from 'chartkick'
import VueChartkick from 'vue-chartkick'

Vue.use(Vuetify)
Vue.use(VueChartkick, { Chartkick })


new Vue({
	el: "#app-container",
	router,
	store,
  render(h) {
    return h(App);
  },
	created () {
		console.log('main> app created!')
		// alert('main> app created!')

		// note: user is presumed authenticated by the time the SPA loads.
		// so, fetch user info from backend.
		this.$store.dispatch('fetchUserData')
	}
});