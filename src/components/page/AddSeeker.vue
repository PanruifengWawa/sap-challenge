<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-date"></i> 考试管理</el-breadcrumb-item>
                <el-breadcrumb-item>添加考卷</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="form-box">
            <el-form ref="form" label-width="80px">
           	 	<el-form-item label="考卷选择">
                    <el-select v-model="exam_id" placeholder="请选择">
                        <el-option v-for="exam in exams" :key="exam.name" :value="exam.id" :label="exam.name"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="考生账号">
                   <el-input type="textarea" :autosize="{minRows:5,maxRows:5}" v-model="labels" class="handle-input2"></el-input>
                </el-form-item>
   				
              	<el-form-item label="考试链接">
                   <el-input type="textarea" :autosize="{minRows:5,maxRows:5}" v-model="label_access_id" class="handle-input1"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">提交</el-button>
                    <el-button>取消</el-button>
                </el-form-item>
            </el-form>
        </div>

    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                labels: '',
                exam_id: '',
                label_access_id: '',
                exams:[]
            }
        },
        methods: {
            onSubmit() {
            		const self = this;
            		
            		if(self.exam_id == '') {
            			self.$message.info("请选择考卷");
            			return;
            		}
            		if(self.labels == '') {
            			self.$message.info("请填写账号");
            			return;
            		}
            		
            		let formData = new FormData();
            	    let labelArr = self.labels.split("\n");
            	    formData.append("examId",self.exam_id);
            	    for(let label of labelArr) {
            	    		formData.append("labels",label);
            	    }
            		self.addSeeker(formData);
            		
            },
            addSeeker(data) {
            		const self = this;
            	    self.$axios.post(self.baseUrl + "/api/seeker",data,{
            	    	    headers: {
            	    	    	    'Content-Type': 'multipart/form-data',
            	    	    	    "token": localStorage.getItem('token')
            	    	    }
            	    })
            	    .then(function (response){
            	    	    let status = response.data.status;
            	    	    if (status == 0) {
            	    	    	    self.$message.success('提交成功！');
            	    	    	    let accessIds = response.data.data;
            	    	    	    for(let accessId of accessIds) {
            	    	    	    		self.label_access_id += accessId;
            	    	    	    }
            	    	    } else {
            	    	        self.$message.info(response.data.data);
            	    	    }
            	    })
            	    .catch(function (err){
            	    	    self.$message.info('服务器错误！');
            	    });
            },
            getExamList() {
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
            }
        },
        created(){
            this.getExamList();
        },
    }
</script>
<style scoped>
.handle-box{
    margin-bottom: 20px;
}
.handle-select{
    width: 120px;
}
.handle-input2{
    width: 200px;
    display: inline-block;
}
.handle-input1{
    width: 500px;
    display: inline-block;
}

</style>