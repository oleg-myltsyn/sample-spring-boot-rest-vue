<template>
    <div id="app">
        <Header msg="Welcome to Your App"/>

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
    import Header from './components/Header'

    export default {
        name: 'app',
        components: {
            Header
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
