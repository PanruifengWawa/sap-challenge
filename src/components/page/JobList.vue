<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 职位信息</el-breadcrumb-item>
                <el-breadcrumb-item>职位列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="handle-box"> 
            <el-input v-model="select_word" placeholder="筛选职位" class="handle-input mr10"></el-input>
        </div>
        <el-table :data="data" border style="width: 100%" ref="multipleTable">
         
            <el-table-column prop="id" label="id" sortable width="150">
            </el-table-column>
            <el-table-column prop="position" label="职位" width="120">
            </el-table-column>
            <el-table-column prop="description" label="职位描述" >
            </el-table-column>
            <el-table-column label="操作" width="180">
                <template scope="scope">

                    <el-button size="small" type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination">
            <el-pagination
                    @current-change ="handleCurrentChange"
                    layout="prev, pager, next"
                    :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                tableData: [],
                cur_page: 1,
                total: 100,
                select_word: ''
            }
        },
        created(){
            this.getData();
        },
        computed: {
            data(){
                const self = this;
                return self.tableData.filter(function(d){
                    if(
                    	    (d.position.indexOf(self.select_word) > -1 )
                    ){
                       return d;
                    }
                })
            }
        },
        methods: {
            handleCurrentChange(val){
                this.cur_page = val;
                this.getData();
            },
            getData(){
                let self = this;
                self.$axios.get('./static/vuetable.json', {page:self.cur_page}).then((res) => {
                    self.tableData = res.data.list;
                })
            },
            handleDelete(index, row) {
                this.$message.error('删除第'+(index+1)+'行');
            }
        }
    }
</script>

<style scoped>
.handle-box{
    margin-bottom: 20px;
}
.handle-select{
    width: 120px;
}
.handle-input{
    width: 300px;
    display: inline-block;
}
</style>