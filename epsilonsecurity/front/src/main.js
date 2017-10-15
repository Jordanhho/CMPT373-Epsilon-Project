import Vue from 'vue';
import router from './router'
import App from './components/App.vue';

new Vue({
	el: "#app-container",
	router,
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