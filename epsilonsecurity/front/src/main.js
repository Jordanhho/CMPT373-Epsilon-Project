import Vue from 'vue'
import router from './router'
import Vuetify from 'vuetify'
import { store } from './store'
import App from './App'

Vue.use(Vuetify)

new Vue({
	el: "#app-container",
	router,
	store,
  render(h) {
    return h(App);
  },
	created () {
		/*
		if user is authenticated:
			- get user info from backend
			- store user info in store
		else:
			- go to the homepage => redirect to CAS
		 */
	}
});