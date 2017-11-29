import Vue from 'vue'
import router from './router'
import Vuetify from 'vuetify'
import { store } from './store'
import App from './App'
import axios from 'axios'

Vue.use(Vuetify)

new Vue({
	el: "#app-container",
	router,
	store,
  render(h) {
    return h(App);
  },
	created () {
		console.log('main> app created!')

		// note: user is presumed authenticated before the SPA loads.
		// so, fetch user info from backend.
		// todo: fetch user info from backend
		console.log('main> making request for auth user...')
		axios.get('/api/auth-user')
			.then(response => {
				console.log(JSON.stringify(response, null, 2))
			})
		/*
		if user is authenticated:
			- get user info from backend
			- store user info in store
		else:
			- go to the homepage => redirect to CAS
		 */
	}
});