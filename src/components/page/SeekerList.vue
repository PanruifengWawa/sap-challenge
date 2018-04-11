
<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 考试管理</el-breadcrumb-item>
                <el-breadcrumb-item>考卷列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="handle-box"> 
            <el-select v-model="exam_id" placeholder="请选择" class="handle-select mr10">
                <el-option v-for="exam in exams" :key="exam.id" :label="exam.name" :value="exam.id"></el-option>
            </el-select>
            <el-button type="primary" icon="search" @click="search">搜索</el-button>
        </div>
        <el-table :data="data" border style="width: 100%" ref="multipleTable">
         
            <el-table-column prop="id" label="id" sortable width="100">
            </el-table-column>
            <el-table-column prop="label" label="考号" width="150">
            </el-table-column>
            <el-table-column prop="score" label="成绩" width="100">
            </el-table-column>
            <el-table-column prop="finishedDate" label="完成日期" width="250" :formatter="dateFormat"  >
            </el-table-column>
            <el-table-column prop="accessId" label="考试链接">
            </el-table-column>
            <el-table-column label="操作" width="100" >
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
	import moment from "moment"
    export default {
        data() {
            return {
                tableData: [],
                cur_page: 1,
                total: 1,
                page_size: 10,
                current_row_content: '',
                exams: [],
                exam_id: ''
            }
        },
        created(){
        		this.getExamist();
        		this.getData();
        },
        computed: {
            data(){
                const self = this;
                return self.tableData.filter(function(d){
    
                    return d;
                })
            }
        },
        methods: {
        		search() {
        			this.getData();
        		},
            handleCurrentChange(val){
                this.cur_page = val;
                this.getData();
            },
            getData(){
                let self = this;
                self.$axios.get(self.baseUrl + "/api/seeker?pageSize=" + self.page_size + 
                "&pageIndex=" + self.cur_page + "&examId=" + self.exam_id,{
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
                self.$axios.delete(self.baseUrl + "/api/seeker/" + id ,{
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
            dateFormat:function(row) {  
            		var date = row.finishedDate;  
          		if (date == null) {  
             		return "未完成";  
          		}  
          		return moment(date).format('YYYY-MM-DD');  
            },
            handleDelete(index, row) {
            		this.deleteRow(row["id"]);

            },
            getExamist() {
              	const self = this;
            	    self.$axios.get(self.baseUrl + "/api/exam",{
            	    	    headers: {
            	    	    	    "token": localStorage.getItem('token')
            	    	    }
            	    })
            	    .then(function (response){
            	    	    let status = response.data.status;
            	    	    if (status == 0) {
            	    	    	    self.exams = response.data.data;
            	    	    } else {
            	    	        self.$message.info(response.data.data);
            	    	    }
            	    })
            	    .catch(function (err){
            	    	    self.$message.info('服务器错误！');
            	    });
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