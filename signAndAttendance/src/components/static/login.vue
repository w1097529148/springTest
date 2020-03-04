<template>
<div class="mainDiv">
<div style="width:100%;height:100%;margin:0 auto;padding:10% 0;" >
<Row type="flex" justify="center" align="middle">
<Col :xs="24" :sm="21" :md="14" :lg="11" :xl="8" :xxl="7">
<Card :bordered="false">
        <h2 style="text-align:center" v-text="title"></h2>
        <Form ref="formInline" :model="formInline" :rules="this.ruleInline">
                <FormItem prop="studentId">
                    <Input type="text" prefix="ios-person-outline" v-model="formInline.studentId" placeholder="用户名" >
                    </Input>
                </FormItem>
                <FormItem prop="pwd">

                    <Input type="password" v-model="formInline.pwd" placeholder="密码" password >
<Icon type="ios-lock-outline" slot="prefix"></Icon>
                    </Input>
                </FormItem>
                <FormItem v-show="title==='注册'" prop="password1">
                                    <Input type="password" v-model="formInline.password1" placeholder="确认密码" password >
                <Icon type="ios-lock-outline" slot="prefix"></Icon>
                                    </Input>
                                </FormItem>
                <FormItem>
                <RadioGroup v-model="formInline.radio">
                        <Radio label="我是老师"></Radio>
                        <Radio label="我是学生"></Radio>
                    </RadioGroup>
                 </FormItem>
                 <FormItem prop="code">
                 <Input type="text" v-model="formInline.code" placeholder="验证码">
                 <Icon type="ios-lock-outline" slot="prepend"></Icon>
                 </Input>
                  </FormItem>
<FormItem>
            <Button type="success" @click="title==='登录'?handleSubmit('formInline'):handleSubmit1('formInline')"  v-text="title"></Button>
            <Button @click="handleReset('formInline')" style="margin-left: 8px">重置</Button>
        </FormItem>
                </Form>
                <div v-show="title==='登录'">
                <Row>
                <Col span="8">
                <router-link :to="'register'">忘记密码?</router-link>
                </Col>
                <Col span="6" offset="10">
                <router-link :to="'register'">点我注册</router-link>
                                </Col>
                </Row>
                </div>
        </Card>
</Col>
</Row>
</div>
</div>
</template>

<script>
export default {
props:{
title:{
default:'登录',
type:String
}
},
data(){
return {
formInline:{
studentId: '',
pwd: '',
password1: '',
code:'',
radio:'我是学生'
},
register:false,
ruleInline: {
studentId: [
                        { required: true, message: '用户名不能为空', trigger: 'blur' },
                        { type: 'string', min: 6, message: '不能低于6个字符', trigger: 'blur' }
                    ],
                    pwd: [
                        { required: true, message: '密码不能为空', trigger: 'blur' },
                        { type: 'string', min: 6, message: '不能低于6个字符', trigger: 'blur' }
                    ],
                     password1: [
                                            {message: '确认密码不能为空', trigger: 'blur' },
                                            { type: 'string', min: 6, message: '不能低于6个字符', trigger: 'blur' }
                                        ],
                       code: [
                                              { required: true, message: '验证码不能为空', trigger: 'blur' }
                                          ]
                },
                passMessages:{}
            }
        },
         methods: {
         handleSubmit (name) {
         this.$refs[name].validate((valid) => { //valid 是validate方法的回调参数 代表是否通过验证 如果为true就是通过
         if(valid){
this.$http.get('http://localhost:8100/login/login', {
	params: {
    	studentId:this.formInline.studentId,
                            pwd:this.formInline.pwd,
                            code:this.formInline.code,
                            value:this.formInline.radio
	}
})
.then((res)=> {
if(res.status === 200){
                const that = this;
alert("请求通过")
for(let key in res.data){
that.passMessages=res.data[key]
}
// 将登录信息使用vuex传递到mainPage页面
this.$store.commit('handleUserName',this.passMessages);
 that.$router.push({name:'mainPage'})
}



})
.catch((error)=> {
 console.log(error);
});
}else {
 this.$Message.error('请填写空字段');
 }
})
},
handleSubmit1 (name) {
this.$refs[name].validate((valid) => { //valid 是validate方法的回调参数 代表是否通过验证 如果为true就是通过
if (valid) {
this.$http.get('http://localhost:8100/register/selectRole', {
	params: {
    	user:this.formInline.studentId,
      password:this.formInline.pwd,
      password1:this.formInline.password1,
      code:this.formInline.code,
      value:this.formInline.radio
	}
})
.then((response)=> {
this.register=response.data;
this.$emit("reg",this.register);
this.$emit("reg1",this.formInline);
})
.catch((error)=> {
 console.log(error);
});
} else {
this.$Message.error('请填写空字段');
}
})
},
                    handleReset (name) {
                        this.$refs[name].resetFields();
                    }
                }
}
</script>

<style scoped>
.mainDiv{
width:100%;
height:100%;



}
</style>
