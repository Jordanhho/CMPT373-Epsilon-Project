import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from './components/Home.vue';
import Foo from './components/Foo.vue';
import Bar from './components/Bar.vue';
import NotFound from './components/NotFound.vue';

Vue.use(VueRouter)

export default new VueRouter({
	mode: 'history', // caveats. see https://router.vuejs.org/en/essentials/history-mode.html
  routes: [
		{ path: '/', name: 'home', component: Home },
		{ path: '/foo', name: 'foo', component: Foo },
		{ path: '/bar', name: 'bar', component: Bar },
		{ path: '*', component: NotFound },
  ]
})
