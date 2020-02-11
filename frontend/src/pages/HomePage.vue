<template>
    <div>
        <Header msg="Welcome to Your App"/>

        <div class="row justify-content-center">
            <div class="col-sm-4">
                <router-link tag="button" class="btn btn-primary"
                        :to="{path:'/table/client'}">
                    Перейти к клиентской таблице
                </router-link>
            </div>

            <div class="col-sm-4">
                <router-link tag="button" class="btn btn-primary"
                        :to="{path:'/table/server'}">
                    Перейти к серверной таблице
                </router-link>
            </div>
        </div>

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

