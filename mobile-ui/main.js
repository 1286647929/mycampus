import App from './App'


// #ifndef VUE3
import Vue from 'vue'


// 引入公用的组件 uni-nav-bar
import uniNavBar from "@/components/uni-nav-bar/index.vue"
Vue.component("uni-nav-bar", uniNavBar);

// 引入uView
import uView from 'uview-ui'
Vue.use(uView)
// 如此配置即可
uni.$u.config.unit = 'rpx'

// 引入vuex
import store from '@/store';

Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
	store,
	...App
})

// 引入请求封装，将app参数传递到配置中
require('@/config/http.interceptor.js')(app)


app.$mount()
// #endif

// #ifdef VUE3
import {
	createSSRApp
} from 'vue'
export function createApp() {
	const app = createSSRApp(App)
	return {
		app
	}
}
// #endif
