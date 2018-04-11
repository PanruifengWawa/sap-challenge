<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 考试管理</el-breadcrumb-item>
                <el-breadcrumb-item>考卷列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="handle-box"> 
            <el-input v-model="select_word" placeholder="筛选考卷" class="handle-input mr10"></el-input>
        </div>
        <el-table :data="data" border style="width: 100%" ref="multipleTable">
         
            <el-table-column prop="id" label="id" sortable width="100">
            </el-table-column>
            <el-table-column prop="name" label="考卷名称" width="325">
            </el-table-column>
            <el-table-column prop="score" label="满分" width="100">
            </el-table-column>
            <el-table-column prop="jobPostion" label="适合职位" width="325">
            </el-table-column>
            <el-table-column label="考卷内容" >
            		<template scope="scope">
					<el-button size="small"
                            @click="handleEdit(scope.$index, scope.row)">查看</el-button>
                </template>
            </el-table-column>
            <el-table-column label="操作" >
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
                    :current-page="cur_page"
                    :page-size="page_size"
                    :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
	import ExamDetails from './ExamDetails.vue';
    export default {
        data() {
            return {
                tableData: [],
                cur_page: 1,
                total: 1,
                select_word: '',
                page_size: 10,
                current_row_content: ''
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
                    	    (d.name.indexOf(self.select_word) > -1 )
                    ){
                    		let questions = [];
                   	 	let score = 0;
        					try{
        						questions = JSON.parse(d.content);
        						for(let question of questions ) {	
        							score += parseInt(question.score);
        						}
        						d["score"] = score;
        					}catch(e){
        						console.log(e);
        					}
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
                self.$axios.get(self.baseUrl + "/api/exam?pageSize=" + self.page_size + 
                "&pageIndex=" + self.cur_page,{
                	    headers: {
            	    	    	    "token": localStorage.getItem('token')
            	    	    }
                }).then((res) => {
                    let status = res.data.status;
            	    	    if (status == 0) {
            	    	    	    self.tableData = res.data.data;
            	    	    	    self.total = res.data.totalNumber;
            	    	    } else {
            	    	        self.$message.info(res.data.data);
            	    	    }
                })
            },
            deleteRow(id) {
            		let self = this;
                self.$axios.delete(self.baseUrl + "/api/exam/" + id ,{
                	    headers: {
            	    	    	    "token": localStorage.getItem('token')
            	    	    }
                }).then((res) => {
                		let status = res.data.status;
            	    	    if (status == 0) {
            	    	    	    self.$message.info("成功删除");
            	    	    	    self.getData();
            	    	    } else {
            	    	        self.$message.info(res.data.data);
            	    	    }
                })
            },
            handleDelete(index, row) {
            		this.deleteRow(row["id"]);

            },
            handleEdit(index, row) {
            		this.current_row_content = row['content'];
            		this.$layer.iframe({
  					content: {
    						content: ExamDetails, //传递的组件对象
    						parent: this,//当前的vue对象
    						data: []//props
  					},
  					title: "考卷详情",
  					area:['800px','600px']
				});

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