import axios from "axios";
import Vue from 'vue'

const BaseUrl="www.mysign.com";
axios.defaults.withCredentials=true;//允许session跨域
const config ={
  locale:'zh-CN',
  url: BaseUrl,
  debug:{
    http:false
  },
  api:'${baseUrl}/api',

};


axios.defaults.baseURL=config.api;
axios.defaults.timeout=1000;
Vue.prototype.$http = axios;



