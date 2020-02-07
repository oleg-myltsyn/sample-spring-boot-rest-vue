<template>
    <div>
        <Header msg="Welcome to Your App"/>

        <router-link
                :to="{path:'/table/client'}">
            Перейти к клиентской таблице
        </router-link>

        <router-link
                :to="{path:'/table/server'}">
            Перейти к серверной таблице
        </router-link>

        <router-view/>
    </div>
</template>

<script>
    import Header from '../components/Header'
    export default {
        name: 'HomePage',
        components: {
            Header
        }
    }
</script>

