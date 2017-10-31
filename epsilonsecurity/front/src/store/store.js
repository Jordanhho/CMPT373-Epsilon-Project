import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
	state: {
		user: {
			firstName: "Uzziah",
			lastName: "Eyee",
			email: "uzziah@sfu.ca",
			role: "admin",
			// token: null
			token: "mock_jwt"
		}
	},
	getters: {
		currentUserEmail: function(state) {
			return state.user.email
		},
		isLoggedIn: function(state) {
			return state.user.token !== null
		}
	},
	mutations: {
    setToken: function(state, payload) {
      state.token = payload
      localStorage.setItem("token", payload)
    },
    clearToken: function(state) {
      state.token = null
      localStorage.removeItem("token")
    }
	},
	actions: {

    login: function({commit}, credentials) {
			// todo: use the passed in credentials
			// mock: send user credentials to backend - async
			return new Promise(function(resolve, reject) {
				setTimeout(function() {
          commit('setToken', "SOME_JWT")
          resolve()
				}, 1000)
			})
		},
		logout: function({commit}) {
      commit('clearToken')
		}
  }
})