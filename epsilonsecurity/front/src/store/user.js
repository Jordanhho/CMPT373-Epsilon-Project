/* eslint-disable */

export default {
  state: {
    // user: null
		user: {
			name: "Uzziah Eyee",
			email: "ueyee@sfu.ca",
			photo: "https://randomuser.me/api/portraits/men/85.jpg",
			// role: "admin",
			// role: "supervisor",
			role: "teamlead",
			// role: "volunteer"
		}
  },
  mutations: {
    setUser (state, payload) {
      state.user = payload
    }
  },
  actions: {
    signupUser ({commit}, payload) {},
    signUserIn ({commit}, payload) {},
    signUserOut ({commit}) {},
    autoSignIn ({commit}, payload) {},
    fetchUserData ({commit, getters}) {}
  },
  getters: {
    user (state) {
      return state.user
    },
    isLoggedIn (state) {
      return state.user !== null
    },
    userRole (state) { // todo: clean this up
      if (state.user !== null) {
        return state.user.role || null
      } else {
        return null
      }
    },
    userName (state) {
      return (state.user !== null) ? state.user.name : null
    },
		userPhoto (state) {
			return (state.user !== null) ? state.user.photo : null
		}
  }
}
