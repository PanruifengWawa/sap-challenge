<template>
    <div class="login-wrap">
        <div class="ms-title">后台管理系统</div>
        <div class="ms-login">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
                <el-form-item prop="userName">
                    <el-input v-model="ruleForm.userName" placeholder="userName"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="password" v-model="ruleForm.password" @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                </div>
                <p style="font-size:12px;line-height:30px;color:#ff4949;">{{tips}}</p>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                ruleForm: {
                    userName: '',
                    password: ''
                },
                tips: "",
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                const self = this;
                self.$refs[formName].validate((valid) => {
                    if (valid) {
                    	    self.login();
//                      localStorage.setItem('ms_username',self.ruleForm.username);
//                      self.tips = "aaa";
//                      console.log(self.tips);
//                      self.$router.push('/readme');
                    } else {
                        tips = "请输入用户名密码";
                        return false;
                    }
                });
            },
            login() {
              	const self = this;
            	    let formData = new FormData();
            	    formData.append("userName",this.ruleForm.userName);
            	    formData.append("password",this.ruleForm.password);
            	    this.$axios.post(self.baseUrl + "/api/company/login",formData,{
            	    	    headers: {
            	    	    	    'Content-Type': 'multipart/form-data'
            	    	    }
            	    })
            	    .then(function (response){
            	    	    let status = response.data.status;
            	    	    if (status != 0) {
            	    	    	    self.tips = response.data.data;
            	    	    } else {
            	    	        localStorage.setItem('token',response.data.data);
            	    	        localStorage.setItem('ms_username',self.ruleForm.userName);
            	    	    	    self.$router.push('/home');
            	    	    }
            	    })
            	    .catch(function (err){
            	    	    self.tips = "服务器错误";
            	    });
            }
        }
    }
</script>

<style scoped>
    .login-wrap{
        position: relative;
        width:100%;
        height:100%;
    }
    .ms-title{
        position: absolute;
        top:50%;
        width:100%;
        margin-top: -230px;
        text-align: center;
        font-size:30px;
        color: #fff;

    }
    .ms-login{
        position: absolute;
        left:50%;
        top:50%;
        width:300px;
        height:160px;
        margin:-150px 0 0 -190px;
        padding:40px;
        border-radius: 5px;
        background: #fff;
    }
    .login-btn{
        text-align: center;
    }
    .login-btn button{
        width:100%;
        height:36px;
    }
</style>