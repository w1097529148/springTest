<template>
<div class="layout">
    <Layout>
<Content>
<div class="back">
<Row>
<Col span="8" offset="4">
<Card style="background-color:rgba(0,0,0,0);height:100%;color:white;border:none" v-if="this.times" :loading="loading">
                <h1>课程/<span v-text="Params.curseName"></span></h1>
                <h3 v-text="'上课地点:'+Params.place"></h3>
                <h3 v-text="'学分:'+Params.score+'分'"></h3>
                <h3 v-text="'类型:'+Params.whethe"></h3>
                <h3 v-text="'上课时间:'+this.times"></h3>
                <h3 v-text="'单/双周:'+Params.week"></h3>
                <div class="curseDiv" >
                <div>
                <h4>持续时间</h4> <h4 v-text="Params.startTime+'~'+Params.endTime"></h4>
                </div>
                 <div>
                 <h4>限制人数</h4>
                 <h4 v-text="56"></h4>
                  </div>
                      <div>
                       <h4>课程时长</h4>
                       <h4 v-text="Params.duration"></h4>
                        </div>
                </div>
                 <Button type="primary" style="margin-top:1%" @click="modal=true" :disabled="this.disable" v-if="userMessage.studentId">选择本课程</Button>
                 <Modal
                         v-model="modal"
                         title="课程选择"
                         :mask-closable="false"
                         @on-ok="chooseThisCurse">
                         <p>选择本课程?</p>
                     </Modal>
            </Card>
</Col>
<Col span="5" offset="4">
    <Card style="background-color:rgba(0,0,0,0);height:100%;color:white;border:none">
        <div style="text-align:center">
            <img :src="Params.title" style="width:100%">
            <h3 style="margin:1%;"><Icon type="md-heart-outline" /><span v-text="">28人已选择</span></h3>
        </div>
    </Card>
</Col>
</Row>
</div>
<div style="width:100%;">
<Row justify="center">
<Col span="17" offset="4">
 <Tabs value="name1">
        <TabPane label="课程介绍" name="name1">
        <Card class="card">
        <Row justify="center">
        <Col span="3" class="includeDiv">
        <div>
        <h3>课程介绍</h3>
        </div>
        </Col>
        <Col span="21" class="includeDiv">
                <div>
                <p>本课程介绍编程语言的基本概念，重点介绍函数式编程。本课程使用的语言ML，球拍，红宝石作为教学概念的车辆，但真正的意图是教足够的关于如何任何语言“合在一起”，使您更有效的编程在任何语言-和学习新的...</p>
                </div>
                </Col>
                </Row>
        </Card>
                 <Card class="card">
                        <Row justify="center">
                        <Col span="3" class="includeDiv">
                        <div>
                        <h3>课程介绍</h3>
                        </div>
                        </Col>
                        <Col span="21" class="includeDiv">
                                <div>
                                <p>本课程介绍编程语言的基本概念，重点介绍函数式编程。本课程使用的语言ML，球拍，红宝石作为教学概念的车辆，但真正的意图是教足够的关于如何任何语言“合在一起”，使您更有效的编程在任何语言-和学习新的...</p>
                                </div>
                                </Col>
                                </Row>
                        </Card>
                         <Card class="card">
                                        <Row justify="center">
                                        <Col span="3" class="includeDiv">
                                        <div>
                                        <h3>课程介绍</h3>
                                        </div>
                                        </Col>
                                         <Col span="3" class="includeDiv">
                                                                                <div>
                                                                               <Avatar size="150" :src="teacherMessage.title" />
                                                                                </div>
                                                                                </Col>
                                        <Col span="18" class="includeDiv">
                                                <div v-for="(item,value,index) in teacherMessage" :key="index" >
                                                <Row style="margin-top:2%">
                                                <div v-if="(index!=0&&index!=1&index!=3&index!=5)"><Col span="3" offset="2"><b v-text="curse[index]">教学方</b></Col>
                                                <Col span="19"><span v-text="item" ></span></Col></div>
                                                </Row>
                                                </div>
                                                </Col>
                                                </Row>
                                        </Card>
        </TabPane>
        <TabPane label="目录" name="name2">
         <Card class="card">
                <Row justify="center">
                <Col span="3" class="includeDiv">
                <div>
                <h3>课程介绍</h3>
                </div>
                </Col>
                <Col span="21" class="includeDiv">
                        <div>
                        <p>本课程介绍编程语言的基本概念，重点介绍函数式编程。本课程使用的语言ML，球拍，红宝石作为教学概念的车辆，但真正的意图是教足够的关于如何任何语言“合在一起”，使您更有效的编程在任何语言-和学习新的...</p>
                        </div>
                        </Col>
                        </Row>
                </Card>

        </TabPane>
    </Tabs>
</Col>
</Row>
</div>
</Content>
</Layout>
</div>
</template>

<script>
export default {
data(){
return{
curse:['0','1','教学方','3','年龄','5','联系方式','学历','主修方面','性别','教育经验','毕业院校'],
Params:{},
teacherMessage:{},
modal:false,
userMessage:{},
times:'',
disable:false,
loading:true
}
},
methods:{
      getParams(){
        // 取到路由带过来的参数
        this.Params = JSON.parse(this.$route.query.curseData);
        if(this.$route.query.message)
        this.userMessage = JSON.parse(this.$route.query.message);
        if(this.$route.query.teacher)
        this.teacherMessage = JSON.parse(this.$route.query.teacher);
      },
      chooseThisCurse:function(){
      this.$http.post('http://localhost:8300/curse/studentAddCurse',this.$qs.stringify(
            {
            CurseId:this.Params.id,
            studentId:this.userMessage.studentId
            })
).then(resp=>{
      alert("选择成功")
      }).catch(error=>{
      console.log(error)
      })
      },
getCurseTimeAnd(){
this.$http.get('http://localhost:8300/curse/getCurseTimes',{
params:{
curseTimes:this.Params.times,
CurseId:this.Params.id,//判断是否选择了本课程
}
}).then(resp=>{
for (let key in resp.data){
this.times=key
this.disable=resp.data[key]
}
}).catch(error=>{
console.log(error)
})
}
      },
      mounted:function(){
    this.getParams();
    this.getCurseTimeAnd();
    this.loading=false;
    },
}
</script>

<style scoped>
.back{
width:100%;
background-image: url(../../../static/images/back.jpg);
  background-repeat: no-repeat;
  background-size: 100% 100%;
}
.curseDiv div{
display: inline-block;
padding:1% 1% 1% 2%;
border-right:1px solid #cccccc;
}
.curseDiv div{
margin:1%;
text-align:center
}

.includeDiv{
display: inline-block;
}
.includeDiv p{
margin:1% 2%;
}
.includeDiv p>b{
margin-right:2%;
}

.card{
margin-bottom:2%;
}

</style>
