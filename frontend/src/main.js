import Vue from 'vue';
import App from './App.vue';
import {ClientTable} from "vue-tables-2";

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import {BootstrapVue} from 'bootstrap-vue';

Vue.config.productionTip = false;

Vue.use(BootstrapVue);
Vue.use(ClientTable, {options:{}, useVuex:false, theme:'bootstrap4', template:'default'});

new Vue({
  render: h => h(App),
}).$mount('#app');
