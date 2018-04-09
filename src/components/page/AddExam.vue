<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-date"></i> 考试管理</el-breadcrumb-item>
                <el-breadcrumb-item>添加考卷</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="form-box">
            <el-form ref="form" :model="examination" label-width="80px">
           	 	<el-form-item label="职位选择">
                    <el-select v-model="examination.jobPosition" placeholder="请选择">
                        <el-option v-for="job in jobs" :key="job.position" :value="job.position">{{job.position}}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="考卷名称">
                    <!--<el-input v-model="exam.name"></el-input>
                    <el-button type="primary" class="add-button"  @click="onSubmit">搜索</el-button>-->
                    <div class="handle-box">
           
            				<el-input v-model="examination.name"  class="handle-input mr10"></el-input>
           				<el-button type="primary" @click="addQuestions">添加考题</el-button>
       		 		</div>
                </el-form-item>
   				
                <div v-for="(exam) in exams">
					<el-form-item label="题目">
						 <el-input v-model="exam.name"></el-input>
                		</el-form-item>
					<el-form-item label="选项:">
                    		<el-radio-group v-model="exam.right_choice">
                        		<el-radio class="handle-box" label="A">A. <el-input v-model="exam.choice.A"></el-input></el-radio><br>
                        		<el-radio class="handle-box" label="B">B. <el-input v-model="exam.choice.B"></el-input></el-radio><br>
                        		<el-radio class="handle-box" label="C">C. <el-input v-model="exam.choice.C"></el-input></el-radio><br>
                        		<el-radio class="handle-box" label="D">D. <el-input v-model="exam.choice.D"></el-input></el-radio><br>
                    		</el-radio-group>
                		</el-form-item>
				</div> 
              
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
                examination: {
                    name: '',
                    content: '',
                    jobPosition: '',
                    jobId: -1
                    
                },
                jobs: [],
                exams:[]
            }
        },
        methods: {
            onSubmit() {
            		const self = this;
            		if(self.examination.name.trim() == '') {
            			self.$message.info("请填写考卷名称");
            			return;
            		} else {
            			self.examination.name = self.examination.name.trim();
            		}
            		if(self.examination.jobPosition.trim() == '') {
            			self.$message.info("请选择职位");
            			return;
            		} else {
            			self.examination.jobPosition = self.examination.jobPosition.trim();
            		}
            		for(let job of self.jobs) {
            			if(self.examination.jobPosition == job.position) {
            				self.examination.jobId = job.id;
            				break;
            			}
            		}
            		if(self.examination.jobId == -1) {
            			self.$message.info("请选择职位");
            			return;
            		}
            		if(self.exams.length == 0) {
            			self.$message.info("请添加考题");
            			return;
            		}
            		for(let exam of self.exams) {
            			if(exam.name.trim() == "") {
            				self.$message.info("请填写考题内容");
            				return;
            			} else {
            				exam.name = exam.name.trim();
            			}
            			let all_choice = ["A", "B", "C", "D"];
            			for(let one of all_choice) {
            				if(exam.choice[one].trim() == "") {
            					self.$message.info("请填写考题选项");
            					return;
            				} else {
            					exam.choice[one] = exam.choice[one].trim();
            				}
            			}
            		}
            		self.examination.content = JSON.stringify(self.exams);
            		
            		let formData = new FormData();
            	    for(let key in self.examination) {
            	    	    if(self.examination[key] != null && self.examination[key] != '') {
            	    	    	    formData.append(key, self.examination[key]);
            	    	    }
            	    }
            		self.addExam(formData);
            		
            },
            addExam(data) {
            		const self = this;
            	    self.$axios.post(self.baseUrl + "/api/exam",data,{
            	    	    headers: {
            	    	    	    'Content-Type': 'multipart/form-data',
            	    	    	    "token": localStorage.getItem('token')
            	    	    }
            	    })
            	    .then(function (response){
            	    	    let status = response.data.status;
            	    	    if (status == 0) {
            	    	    	    self.$message.success('提交成功！');
            	    	    	    self.$router.push('/examlist');
            	    	    } else {
            	    	        self.$message.info(response.data.data);
            	    	    }
            	    })
            	    .catch(function (err){
            	    	    self.$message.info('服务器错误！');
            	    });
            },
            addQuestions() {
            		let exam = {
            			"name": "",
            			"choice": {
            				"A": "",
            				"B": "",
            				"C": "",
            				"D": ""
            			},
            			"right_choice": "A"
            		};
            		this.exams.push(exam);
            },
            getJobList() {
              	const self = this;
            	    self.$axios.get(self.baseUrl + "/api/job",{
            	    	    headers: {
            	    	    	    "token": localStorage.getItem('token')
            	    	    }
            	    })
            	    .then(function (response){
            	    	    let status = response.data.status;
            	    	    if (status == 0) {
            	    	    	    self.jobs = response.data.data;
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
            this.getJobList();
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
.handle-input{
    width: 300px;
    display: inline-block;
}
</style>