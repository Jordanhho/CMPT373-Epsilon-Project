import axios from 'axios'
import router from '../router'

export default {
  state: {
    user: null
		// user: {
			// id: 16,
			// firstName: "ada",
			// lastName: "lovelace",
			// sfuEmail: "alove@sfu.ca",
			// photoURL: "https://randomuser.me/api/portraits/women/2.jpg",
		  // roleId: 1 // 1 => admin, 2 => ?
		// }
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
				document.location.href = "https://cas.sfu.ca/cas/appLogout" // to logout from only this app
				//document.location.href = "https://cas.sfu.ca/cas/logout" // to logout from all CAS apps
			})
			.catch(error => {
				console.error(`store> ${error.message}`)
			})
		},
		fetchUserData ({commit, dispatch}) {
			commit('setLoading', true)

			axios.get('/api/authuser')
			.then(response => {
				commit('setLoading', false)

				const userInfo = response.data
				console.log(JSON.stringify(userInfo, null, 2))
				commit('setUser', userInfo)

				router.push('/')
			})
			.catch(error => {
				commit('setLoading', false)
				if (error.response && error.response.status == 404) {
					// The user has an SFU account but is not registered for this app.
					// Ideally, the backend should automatically logout the user and redirect to the CAS Logout Page,
					// but this fails due to CORs policy.
					// Hence, we manually log the user out here.
					alert("Error: Authenticated CAS user not registered for this app.")
					dispatch('signUserOut')
				} else if (error.response && error.response.status == 500) {
					console.log(JSON.stringify(error.response))
				} else {
					console.error(error)
				}
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
      	switch (state.user.roleId) {
					case 1:
						return "admin"
						break
					case 2:
						return "supervisor"
						break
					case 3:
						return "lead"
						break
					case 4:
						return "employee"
						break
					default:
						return "volunteer"
				}
        return state.user.role || null
      } else {
        return null
      }
    },
    userName (state) {
      return (state.user !== null) ? `${state.user.firstName} ${state.user.lastName}` : null
    },
		userPhoto (state) {
			return (state.user !== null) ? state.user.photoURL : null
		},
		uid (state) {
    	return (state.user !== null) ? state.user.id : null
		},
		username (state) {
    	// todo: split user.sfuEmail and return first part
			return (state.user !== null) ? state.user.username : null
		}
  }
}
