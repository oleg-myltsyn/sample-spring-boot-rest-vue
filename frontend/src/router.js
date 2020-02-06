
// routing
import TableServer from "./components/TableServer";
import TableClient from "./components/TableClient";

import VueRouter from 'vue-router';
import Vue from "vue";


Vue.use(VueRouter);

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

export default router;

