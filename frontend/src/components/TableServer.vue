<template>
    <div id="table" v-cloak>
        <h1>{{ msg }}</h1>
        <button class="btn btn-dark mb-2 mt-2" v-on:click="goBack">Close table</button>

        <v-server-table :url="url" :columns="columns" :options="options">
        </v-server-table>
    </div>
</template>

<style>

</style>


<script>
    import moment from 'moment';
    import {authHeader} from '../services/auth-header';
    import {userService} from '../services/user-service';

    export default {
        name: 'TableServer',
        props: {
            msg: String,
            url: String
        },
        components: {},
        methods: {
            formatDate: function(date) {
                return moment(date).format('DD-MM-YYYY HH:mm:ss');
            },
            goBack() {
                this.$router.push('/');
            }
        },
        data() {
            return {
                columns: ['id', 'username', 'email', 'created_at'],
                tableData: [],
                options: {
                    perPage: 25,
                    perPageValues: [25],
                    requestFunction(data) {
                        return axios.get(this.url, {
                            headers: authHeader(),
                            params: data
                        }).catch((error) => {userService.logout(); location.reload(true)});
                    },
                    requestAdapter(data) {
                        return {
                            sort: data.orderBy ? data.orderBy : 'name',
                            direction: data.ascending ? 'asc' : 'desc'
                        }
                    },
                    responseAdapter(response) {
                        let data = this.getResponseData(response);
                        return {
                            data: data.response,
                            count: data.response.length
                        }
                    },
                    filterable: false,
                    templates: {
                        created_at: (h, row) => {
                            return this.formatDate(row.created_at);
                        }
                    }
                }
            };
        }
    }

</script>
