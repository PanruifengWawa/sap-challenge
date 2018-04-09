<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-date"></i> 公司信息</el-breadcrumb-item>
                <el-breadcrumb-item>公司信息维护</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="form-box">
            <el-form ref="form" :model="company" label-width="80px">
                <el-form-item label="公司名称">
                    <el-input v-model="company.name"></el-input>
                </el-form-item>
                <el-form-item label="公司地址">
                    <el-input v-model="company.address"></el-input>
                </el-form-item>
                <el-form-item label="公司电话">
                    <el-input v-model="company.phone"></el-input>
                </el-form-item>
                <el-form-item label="公司简介">
                    <el-input type="textarea" v-model="company.description"></el-input>
                </el-form-item>
                <!--<el-form-item label="选择器">
                    <el-select v-model="form.region" placeholder="请选择">
                        <el-option key="bbk" label="步步高" value="bbk"></el-option>
                        <el-option key="xtc" label="小天才" value="xtc"></el-option>
                        <el-option key="imoo" label="imoo" value="imoo"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="日期时间">
                    <el-col :span="11">
                        <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
                    </el-col>
                    <el-col class="line" :span="2">-</el-col>
                    <el-col :span="11">
                        <el-time-picker type="fixed-time" placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
                    </el-col>
                </el-form-item>
                <el-form-item label="选择开关">
                    <el-switch on-text="" off-text="" v-model="form.delivery"></el-switch>
                </el-form-item>
                <el-form-item label="多选框">
                    <el-checkbox-group v-model="form.type">
                        <el-checkbox label="步步高" name="type"></el-checkbox>
                        <el-checkbox label="小天才" name="type"></el-checkbox>
                        <el-checkbox label="imoo" name="type"></el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item label="单选框">
                    <el-radio-group v-model="form.resource">
                        <el-radio label="步步高"></el-radio>
                        <el-radio label="小天才"></el-radio>
                        <el-radio label="imoo"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="文本框">
                    <el-input type="textarea" v-model="form.desc"></el-input>
                </el-form-item>-->
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
                company: {
                    name: '',
                    phone: '',
                    address: '',
                    description: ''
                }
            }
        },
        methods: {
            onSubmit() {
            	    const self = this;
            	    let data = '';
            	    for(let key in self.company) {
            	    	    if(self.company[key] != null && self.company[key] != '') {
            	    	    	    data += encodeURIComponent(key) + '=' + encodeURIComponent(self.company[key]) + '&'
            	    	    }
            	    }
            		self.updateCompanyInfo(data);
            },
            updateCompanyInfo(companyInfo) {
              	const self = this;
            	    self.$axios.put(self.baseUrl + "/api/company",companyInfo,{
            	    	    headers: {
            	    	    	    'Content-Type': 'application/x-www-form-urlencoded',
            	    	    	    "token": localStorage.getItem('token')
            	    	    }
            	    })
            	    .then(function (response){
            	    	    let status = response.data.status;
            	    	    if (status == 0) {
            	    	    	    self.$message.success('提交成功！');
            	    	    } else {
            	    	        self.$message.info('提交失败！');
            	    	    }
            	    })
            	    .catch(function (err){
            	    	    self.$message.info('服务器错误！');
            	    });
            },
            getCompanyInfo() {
            	    const self = this;
                self.$axios.get(self.baseUrl + "/api/company",{
            	        headers: {
            	    	        "token": localStorage.getItem('token')
            	        }
                })
                .then(function (response){
                	    let status = response.data.status;
            	    	    if (status == 0) {
            	    	    	    self.company = response.data.data;
            	    	    } else {
            	    	    	    localStorage.removeItem('ms_username');
                        localStorage.removeItem('token');
                        self.$message.info('用户未登录！');
                        self.$router.push('/login');
            	    	    }
                })
                .catch(function (err){
                    self.$message.info('服务器错误！');
                })
            }
        },
        mounted: function () {
        	    this.getCompanyInfo();
        },
    }
</script>