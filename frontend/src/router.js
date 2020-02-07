// routing
import VueRouter from 'vue-router';
import Vue from "vue";

import TableServer from "./components/TableServer";
import TableClient from "./components/TableClient";
import LoginPage from "./pages/LoginPage";
import HomePage from "./pages/HomePage";

import {config} from './config';


Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        component: HomePage,
        children: [
            {
                path: '/table/client',
                component: TableClient,
                props: {msg: 'Client Table'}
            },
            {
                path: '/table/server',
                component: TableServer,
                props: {msg: 'Server Table', url: `${config.baseUrl}/auth/user/all`}
            }
        ]
    },
    {
        path: '/login',
        component: LoginPage
    }
];


const router = new VueRouter({
    routes
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/login'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    if (authRequired && !loggedIn) {
        return next({
            path: '/login',
            query: {returnUrl: to.path}
        });
    }

    next();
});


export default router;

