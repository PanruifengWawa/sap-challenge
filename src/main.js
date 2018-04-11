import Vue from 'vue';
import App from './App';
import router from './router';
import axios from 'axios';
import ElementUI from 'element-ui';
import layer from 'vue-layer'
import 'element-ui/lib/theme-default/index.css';    // 默认主题
// import '../static/css/theme-green/index.css';       // 浅绿色主题
import "babel-polyfill";


Vue.use(ElementUI);
Vue.use(require('vue-moment')); 
Vue.prototype.$axios = axios;
Vue.prototype.$layer = layer(Vue);
Vue.prototype.baseUrl = "http://localhost:8081";
new Vue({
    router,
    render: h => h(App)
}).$mount('#app');