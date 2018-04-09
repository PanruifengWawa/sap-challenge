<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-date"></i> 职位信息</el-breadcrumb-item>
                <el-breadcrumb-item>添加职位</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="form-box">
            <el-form ref="form" :model="job" label-width="80px">
                <el-form-item label="职位名称">
                    <el-input v-model="job.position"></el-input>
                </el-form-item>
   
                <el-form-item label="职位介绍">
                    <el-input type="textarea" v-model="job.description"></el-input>
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
                job: {
                    position: '',
                    description: ''
                }
            }
        },
        methods: {
            onSubmit() {
            	    const self = this;
            	    let formData = new FormData();
            	    for(let key in self.job) {
            	    	    if(self.job[key] != null && self.job[key] != '') {
            	    	    	    formData.append(key, self.job[key]);
            	    	    }
            	    }
            		self.addJob(formData);
            },
            addJob(jobInfo) {
              	const self = this;
            	    self.$axios.post(self.baseUrl + "/api/job",jobInfo,{
            	    	    headers: {
            	    	    	    'Content-Type': 'multipart/form-data',
            	    	    	    "token": localStorage.getItem('token')
            	    	    }
            	    })
            	    .then(function (response){
            	    	    let status = response.data.status;
            	    	    if (status == 0) {
            	    	    	    self.$message.success('提交成功！');
            	    	    	    self.$router.push('/joblist');
            	    	    } else {
            	    	        self.$message.info(response.data.data);
            	    	    }
            	    })
            	    .catch(function (err){
            	    	    self.$message.info('服务器错误！');
            	    });
            }
        }
    }
</script>