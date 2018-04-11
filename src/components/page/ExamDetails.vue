<template>
    <div>
        <div class="form-box">
            <el-form ref="form" label-width="80px">
            		<!--<el-ul v-for="(exam,index) in exams">
            			<el-li>{{index + 1}}.</el-li>
            			<el-li>{{exam.name}}</el-li><br>
            			<el-li>{{exam.name}}</el-li>
            			
            			<el-form-item label="单选框">
                   		<el-radio-group v-model="exam.name">
                        		<el-radio label="步步高"></el-radio>
                        		<el-radio label="小天才"></el-radio>
                        		<el-radio label="imoo"></el-radio>
                    		</el-radio-group>
               	    </el-form-item>
            			
            		</el-ul>-->
				<div v-for="(exam,index) in exams">
					<el-form-item label="题目:">
						 {{exam.name}}({{exam.score}}分)
                		</el-form-item>
					<el-form-item label="选项:">
                    		<el-radio-group v-model="exam.right_choice" disabled>
                        		<el-radio label="A">A.{{exam.choice.A}}</el-radio>
                        		<el-radio label="B">B.{{exam.choice.B}}</el-radio>
                        		<el-radio label="C">C.{{exam.choice.C}}</el-radio>
                        		<el-radio label="D">D.{{exam.choice.D}}</el-radio>
                    		</el-radio-group>
                		</el-form-item>
				</div>             	
                
            </el-form>
        </div>

    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                content: ''
            }
        },
        methods: {
        },
        created: function () {
        		this.content = this.$parent.current_row_content;
        },
        computed: {
        		exams: function() {
        			let result = [];
        			try{
        				result = JSON.parse(this.content);
        			}catch(e){
        				console.log(e);
        			}
        			return result;
        		}
        }
    }
</script>