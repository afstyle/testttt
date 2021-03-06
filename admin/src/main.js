import Vue from 'vue';
import ViewUI from 'view-design';
import iViewPro from './libs/iview-pro/iview-pro.min.js';
import VueRouter from 'vue-router';
import Routers from './router';
import Util from './libs/util';
import App from './app.vue';
import 'view-design/dist/styles/iview.css';
import './libs/iview-pro/iview-pro.css';
import Http from "./axios/axios";
import filter from "./utils/filter";

Vue.use(VueRouter);
Vue.use(ViewUI, {
    transfer: true,
    size: 'default',
    capture: false,
    select: {
        arrow: 'md-arrow-dropdown',
        arrowSize: 20
    }
});
Vue.use(iViewPro);
Vue.prototype.Http = Http;

// 路由配置
const RouterConfig = {
    mode: 'history',
    routes: Routers
};
const router = new VueRouter(RouterConfig);

router.beforeEach((to, from, next) => {
    ViewUI.LoadingBar.start();
    Util.title(to.meta.title);
    next();
});

router.afterEach((to, from, next) => {
    ViewUI.LoadingBar.finish();
    window.scrollTo(0, 0);
});


/**
 * axios拦截器
 */
/*axios.interceptors.request.use(function (config) {
    console.log("请求：", config);
    return config;
}, error => {})
axios.interceptors.response.use(function (response) {
    console.log("返回结果：", response);
    return response;
}, error => {})*/

// 全局过滤器
Object.keys(filter).forEach(value => {
    Vue.filter(value, filter[value])
})


new Vue({
    el: '#app',
    router: router,
    render: h => h(App)
});
