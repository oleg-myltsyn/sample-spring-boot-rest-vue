import Vue from 'vue';
import App from './App.vue';

import {ClientTable, ServerTable} from "vue-tables-2";

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import {BootstrapVue} from 'bootstrap-vue';

import router from './router'

Vue.config.productionTip = false;

Vue.use(BootstrapVue);

Vue.use(ClientTable, {options:{}, useVuex:false, theme:'bootstrap4', template:'default'});
Vue.use(ServerTable, {options:{}, useVuex:false, theme:'bootstrap4', template:'default'});

window.axios = require('axios');


new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
