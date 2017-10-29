import Vue from 'vue';
import router from './router'
import Vuetify from 'vuetify'
import store from './store/store'
// import './stylus/main.styl'
import App from './components/App.vue';
import Icon from 'vue-awesome/icons' //all the icons of font-awesome

Vue.use(Vuetify)

new Vue({
	el: "#app-container",
	router,
	store,
  render(h) {
    return h(App);
  }
});

/* 
If using this, ensure you're using the full variant
of the core vue library with compiler+runtime.
see: https://vuejs.org/v2/guide/installation.html#Runtime-Compiler-vs-Runtime-only
*/
// new Vue({
// 	el: '#app-container',
// 	router,
// 	template: '<App/>',
// 	components: { App }
// })

// TODO: make this work instead of including cdn link in index.scala.html
// require('/path/to/node_modules/vuetify/dist/vuetify.min.css')
// Keep in mind, you will need to ensure your webpack config contains a css-loader.