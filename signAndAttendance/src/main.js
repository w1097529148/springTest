// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import BaiduMap from 'vue-baidu-map'
import App from './App'
import router from './router'
import ViewUI from 'view-design'
import 'view-design/dist/styles/iview.css';
import './config'
import VueI18n from 'vue-i18n'
import animated from 'animate.css' // npm install animate.css --save安装，在引入
import  qs from 'qs'
import moment from 'moment'
import store from './store/Vuex'
Vue.use(BaiduMap, {
  // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
  ak: 'DyWu5yeHP1g2w34C0xoIvNQxnwrsFnDj&s=1'
});
        Vue.prototype.$moment = moment;//格式转换
Vue.prototype.$qs = qs;
Vue.use(animated)
Vue.use(VueI18n)
Vue.config.productionTip = false
Vue.config.devtools = true;
/* eslint-disable no-new */
Vue.use(ViewUI)
router.beforeEach((to, from, next) => {
  ViewUI.LoadingBar.start();
  next();
});
router.afterEach(route => {
  ViewUI.LoadingBar.finish();
});
new Vue({
  el: '#app',
  router,
  VueI18n,
  store,
  components: { App },
  template: '<App/>'
})

