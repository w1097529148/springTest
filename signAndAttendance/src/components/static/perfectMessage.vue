<template>
<Row  type="flex" justify="center">
<Col span="10">
    <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
        <FormItem  prop="name">
            <Input v-model="formValidate.name" placeholder="姓名"></Input>
        </FormItem>
        <FormItem  prop="address">
        <Input v-model="formValidate.address" placeholder="住址"></Input>
        </FormItem>
        <FormItem>
            <Row>
                <Col span="11">
                    <FormItem prop="birthday">
                        <DatePicker type="date" placeholder="出生日期" v-model="formValidate.birthday"></DatePicker>
                    </FormItem>
                </Col>
            </Row>
        </FormItem>
        <FormItem  prop="gender">
            <RadioGroup v-model="formValidate.gender">
                <Radio label="男">男</Radio>
                <Radio label="女">女</Radio>
            </RadioGroup>
        </FormItem>
                <FormItem  prop="tel">
                <Input v-model="formValidate.tel" placeholder="联系方式"></Input>
                </FormItem>
        <FormItem prop="grade">
             <Input v-model="formValidate.grade" :placeholder="role==='我是学生'?'年级':'工作时长'" ></Input>
        </FormItem>
 <FormItem prop="cla">
             <Input v-model="formValidate.cla" :placeholder="role==='我是学生'?'班级':'毕业院校'"></Input>
        </FormItem>
         <FormItem prop="professional">
                     <Input v-model="formValidate.professional" :placeholder="role==='我是学生'?'专业':'学历'"></Input>
                </FormItem>
     <FormItem prop="major">
                         <Input v-model="formValidate.major" placeholder="主修方面"></Input>
                    </FormItem>
        <FormItem>
        <Row type="flex" justify="start" style="width:100%">
        <Col span="2">
        <Button type="primary" @click="handleSubmit('formValidate')">提交</Button>
        </Col>
        <Col span="2" offset="20">
         <Button @click="handleReset('formValidate')">重置</Button>
        </Col>
        </Row>
        </FormItem>
    </Form>
    </Col>
    </Row>
</template>
<script>
    export default {
   props: {
   role:{
   default:'我是学生',
   type:String
   },
   user:{}
   },
        data () {
            return {
                formValidate: {
                  name: '',
                  address: '',
                  gender: '',
                  birthday: '',
                  tel:'',
                  cla:'',//毕业院校 班级
                  major:'',
                  professional:'',//学历 专业
                  grade:'', //工作时长 年级
                },
                ruleValidate: {
                    name: [
                        { required: true, message: '姓名不能为空', trigger: 'blur' }
                    ],
                    city: [
                        { required: true, message: '请填写你的城市', trigger: 'blur' }
                    ],
                    gender: [
                        { required: true, message: '请选择你的性别', trigger: 'change' }
                    ],
                    date: [
                        { required: true, type: 'date', message: '请选择日期', trigger: 'change' }
                    ],
                     address: [
                     { required: true, message: '地址不能为空', trigger: 'blur' }
                     ],
                      tel: [
                      { required: true, message: '联系方式不能为空', trigger: 'blur' }
                      ],
                       major: [
                       { required: true, message: '主修方面不能为空', trigger: 'blur' }
                       ],
                      professional: [
                      { required: true, message: '专业不能为空', trigger: 'blur' }
                      ],
                     cla: [
                                            { required: true, message: '班级不能为空', trigger: 'blur' }
                                        ],
                     grade: [
                                            { required: true, message: '年级不能为空', trigger: 'blur' }
                                        ]
                },
                                Role:this.role,
            }
        },
        methods: {
            handleSubmit (name) {
                this.$refs[name].validate((valid) => {
                   const {password1,radio,...user1}=this.user;
                    alert("进来了")
                    alert(this.Role)
let url=this.Role==="我是学生"?'http://localhost:8100/register/studentRole':'http://localhost:8100/register/teacherRole';
alert("进来了")
this.$http.post(url,{
formValid:this.formValidate,
user:user1,
code:"hello"
},{headers:{
          'Content-Type':"application/json;charset=utf-8"  //必须设置传输方式
       }}
).then((response)=> {
this.$emit("reg",response.data);
})
.catch((error)=> {
 console.log(error);
});
 })
            },
            handleReset (name) {
                this.$refs[name].resetFields();
            }
        }
    }
</script>
