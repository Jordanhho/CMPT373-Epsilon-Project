import axios from 'axios'

export default {
  state: {
    // user: null
		user: {
			id: 16,
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

    signUserOut ({commit}) {

    	// 1. Clear this user's session on the backend
			axios.get("/logout")
			.then(response => {
				console.log('store> backend logout complete')

				// 2. Clear this user's info on the frontend
				commit('setUser', null)

				// Ideally, the backend should redirect to the CAS logout page after clearing-out the user's session.
				// However the browser blocks the redirect due to CORS Policy violation (still don't know how to prevent this).
				// The work-around is to intentionally configure the backend NOT to redirect to CLP, AND manually
				// visit the CLP as we are doing below.
				// document.location.href = "https://cas.sfu.ca/cas/logout"
			})
			.catch(error => {
				console.error(`store> ${error.message}`)
			})
		}
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
		},
		uid (state) {
    	return (state.user !== null) ? state.user.id : null
		}
  }
}
