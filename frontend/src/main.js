import Vue from 'vue';
import App from './App.vue';
import VueRouter from 'vue-router';

import {ClientTable, ServerTable} from "vue-tables-2";

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import {BootstrapVue} from 'bootstrap-vue';

Vue.config.productionTip = false;

Vue.use(BootstrapVue);

Vue.use(ClientTable, {options:{}, useVuex:false, theme:'bootstrap4', template:'default'});
Vue.use(ServerTable, {options:{}, useVuex:false, theme:'bootstrap4', template:'default'});

Vue.use(VueRouter);

window.axios = require('axios');


// routing
import TableServer from "./components/TableServer";
import TableClient from "./components/TableClient";


const routes = [
  {
    path: '/table/client',
    component: TableClient,
    props: {msg: 'Client Table'}
  },
  {
    path: '/table/server',
    component: TableServer,
    props: {msg: 'Server Table', url: 'http://localhost:8081/api/user/all'}
  }
];


const router = new VueRouter({
  routes
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
