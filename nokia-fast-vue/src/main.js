import Vue from 'vue'
import App from '@/App'
import router from '@/router'                 // api: https://github.com/vuejs/vue-router
import store from '@/store'                   // api: https://github.com/vuejs/vuex
import VueCookie from 'vue-cookie'            // api: https://github.com/alfhen/vue-cookie
import '@/element-ui'                         // api: https://github.com/ElemeFE/element
import '@/icons'                              // api: http://www.iconfont.cn/
import '@/element-ui-theme'
import '@/assets/scss/index.scss'
import {isAuth} from '@/utils'
import common from '@/utils/common'
import httpRequest from '@/utils/httpRequest' // api: https://github.com/axios/axios
Vue.use(VueCookie);
Vue.use(common);


// import cloneDeep from 'lodash/cloneDeep'
// import vueResource from 'vue-resource'
// Vue.use(vueResource)
Vue.config.productionTip = false;

// import BaiduMap from 'vue-baidu-map'
//
// Vue.use(BaiduMap, {
//     ak: 't8kjxQX0YzyotRuL4uzt84Nusvb1rlN5'
//
// });

import VueAMap from 'vue-amap';
import { lazyAMapApiLoaderInstance } from 'vue-amap';
Vue.use(VueAMap);
VueAMap.initAMapApiLoader({
    key: '2800cd2c93696eaab6f192523cc5271e',
    plugin: ['AMap.Autocomplete', 'AMap.PlaceSearch', 'AMap.Scale', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType', 'AMap.PolyEditor', 'AMap.CircleEditor', 'AMap.MarkerClusterer'],
    v: '1.4.4',
    uiVersion: '1.0.11'
});
// lazyAMapApiLoaderInstance.load().then(() => {
//      var map = new AMap.Map('container', {
//         center: new AMap.LngLat(110.317312, 20.022712)
//     });
// });

//echarts的引入
import echarts from 'echarts'
Vue.prototype.$echarts = echarts;
//highcharts的引入
import VueHighcharts from 'vue-highcharts';
Vue.use(VueHighcharts);

// 挂载全局
Vue.prototype.$http = httpRequest; // ajax请求方法
Vue.prototype.isAuth = isAuth;     // 权限方法

// 保存整站vuex本地储存初始状态
// window.SITE_CONFIG['storeState'] = cloneDeep(store.state)

/* eslint-disable no-new */
new Vue({
    router,
    store,
    ...App,
}).$mount('#app');

