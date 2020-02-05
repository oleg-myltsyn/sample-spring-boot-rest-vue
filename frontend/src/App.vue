<template>
    <div id="app">
        <!--    <img alt="Vue logo" src="./assets/logo.png">-->
        <!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
        <TableServer msg="Server Table" url="http://localhost:8081/api/user/all"/>

        <router-link
                :to="{path:'/table/client'}">
            Перейти к клиентской таблице
        </router-link>

        <router-link
                :to="{path:'/table/server'}">
            Перейти к серверной таблице
        </router-link>

        <router-view></router-view>
    </div>
</template>

<script>
    // import HelloWorld from './components/HelloWorld.vue'
    // import TableClient from "./components/TableClient";
    import TableServer from "./components/TableServer";

    export default {
        name: 'app',
        components: {
            TableServer
            // TableClient
            // HelloWorld
        }
    }
</script>

<style>
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>
