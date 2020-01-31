<template>
    <div id="table" v-cloak>
        <h1>{{ msg }}</h1>
        <v-server-table url="https://api.github.com/users/matfish2/repos" :columns="columns" :options="options">
        </v-server-table>
    </div>
</template>

<style>
    #table {
        width: 95%;
        margin: 0 auto;
    }
</style>


<script>
    import moment from 'moment';

    export default {
        name: 'TableServer',
        props: {
            msg: String
        },
        components: {},
        methods: {
            formatDate: function(date) {
                return moment(date).format('DD-MM-YYYY HH:mm:ss');
            }
        },
        data() {
            return {
                columns: ['name', 'created_at', 'updated_at', 'pushed_at'],
                tableData: [],
                options: {
                    perPage: 25,
                    perPageValues: [25],
                    requestFunction(data) {
                        return axios.get(this.url, {
                            params: data
                        });
                    },
                    requestAdapter(data) {
                        return {
                            sort: data.orderBy ? data.orderBy : 'name',
                            direction: data.ascending ? 'asc' : 'desc'
                        }
                    },
                    responseAdapter({data}) {
                        return {
                            data,
                            count: data.length
                        }
                    },
                    filterable: false,
                    templates: {
                        created_at: (h, row) => {
                            return this.formatDate(row.created_at);
                        },
                        updated_at: (h, row) => {
                            return this.formatDate(row.updated_at);
                        },
                        pushed_at: (h, row) => {
                            return this.formatDate(row.pushed_at);
                        }
                    }
                }
            };
        }
    }

</script>
