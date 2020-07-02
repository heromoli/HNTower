import Vue from 'vue'
import App from '@/App'
import ElementUI from 'element-ui'            // api: https://github.com/ElemeFE/element
import '@/element-ui-theme'
import store from '@/store'                   // api: https://github.com/vuejs/vuex
import VueCookie from 'vue-cookie'            // api: https://github.com/alfhen/vue-cookie
import '@/icons'                              // api: http://www.iconfont.cn/
import '@/assets/scss/index.scss'
import {isAuth} from '@/utils'
import common from '@/utils/common'
import httpRequest from '@/utils/httpRequest' // api: https://github.com/axios/axios
import router from '@/router'                 // api: https://github.com/vuejs/vue-router
Vue.use(VueCookie);
Vue.use(common);
Vue.use(ElementUI);


// import cloneDeep from 'lodash/cloneDeep'
// import vueResource from 'vue-resource'
// Vue.use(vueResource)
Vue.config.productionTip = false;

import VueAMap from 'vue-amap';
import {lazyAMapApiLoaderInstance} from 'vue-amap';

Vue.use(VueAMap);
VueAMap.initAMapApiLoader({
    key: '2800cd2c93696eaab6f192523cc5271e',
    plugin: ['Autocomplete', 'PlaceSearch', 'Scale', 'OverView', 'ToolBar', 'MapType', 'PolyEditor', 'CircleEditor', 'MarkerCluster','CloudDataLayer', 'CloudDataSearch','Weather','RangingTool'],
    v: '1.4.13',
    uiVersion: '1.0.11'
});

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

