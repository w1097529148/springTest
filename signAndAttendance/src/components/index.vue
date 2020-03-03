<template>
<div>
  <Layout>
        <Content>
        <div :class="[this.fix1?'fixed':'dropdown1']">
       <droptest></droptest>
       </div>
        <div  class="curse">
                        <Card title="我的课程表" icon="ios-options" :padding="10" shadow class="card">
                                                   <CellGroup>
                                                       <Cell title="继续学习 程序语言设计" label="正在学习-使用对象" />
                                                     <Divider style="margin:5px"/>
                                                       <Cell title="继续学习 程序语言设计" label="正在学习-使用对象" />
                                                     <Divider style="margin:5px"/>
                                                       <Cell title="继续学习 程序语言设计" label="正在学习-使用对象" />
                                                     <Divider style="margin:5px" />
                                                     <Button type="primary" ghost style="margin:3% 25%">全部课程</Button>
                                                   </CellGroup>
                                               </Card>
                                           </div>


           <Carousel autoplay loop radius-dot  >
                         <CarouselItem v-for="(image,index) in images" :key="index" >
                             <div class="demo-carousel"
                              :style="{
                              width:'100%',
                             height:'357px',
                            backgroundImage: 'url('+image+')',
                            backgroundSize: '100% 100%'
                            }"></div>
                         </CarouselItem>

                     </Carousel>
                     </Content>
<Footer style="width:100%;margin:0;padding:0">
<!--热点资讯-->
<HelloWorld  :number="this.number" :teacher="this.routerParams.全部教师" :message="this.routerParams.全部课程"></HelloWorld>
</Footer>
<Layout>
<Content>
<Row style="width:75%;margin:0 auto" v-if="this.routerParams" >
<!--与我相关-->
<doing :message="this.routerParams.与我相关" :user="this.Message" :teacher="this.routerParams.我的教师?this.routerParams.我的教师:null">
</doing>
<!--精品推荐-->
<doing :text="this.text" :message="this.routerParams.全部课程" :user="this.Message" :teacher="this.routerParams.全部教师">
</doing>
<!--更多-->
<doing  :text="this.text1" :message="this.routerParams.全部课程" :user="this.Message" :teacher="this.routerParams.全部教师">
</doing>
</Row>
</Content>
</Layout>
  </Layout>


</div>
</template>
<script>
import HelloWorld from './hot/hot';
import doing from './doing/doing';
import droptest from './dropdown/droptest';

    export default {
    name:"index",
    props:{
    Message:{}
    },
    data(){
        return {
        hello:'hello',
        text:'精品推荐',
        text1:"更多",
        number:0,
 fix1:false,
 routerParams:{},
images:['static/images/banner01.gif','static/images/banner02.jpg','static/images/banner03.jpg','static/images/banner04.jpg','static/images/banner05.jpg']
 }
    },
    components:{
    HelloWorld,
    doing,
    droptest,
    },
    methods: {
    getMessages(){
    this.$http.get('http://localhost:8100/login/getStudentMessages',{
    params:{
studentId:this.Message.studentId,
teacherId:this.Message.teacherId
    }
    }).then(resp=>{
    this.routerParams=resp.data
    }).catch(error=>{
    console.log(error)})
    }
        },
                  watch:{
                  Message:'getMessages'
                  },
                  created(){
               this.getMessages()
                  }
    }
</script>
<style scoped>

.dropdown1{
z-index:99;
position: absolute;
margin-left:13%;
padding:0 0 1% 2%;
height:357px;
color:white;
font-size:30px;
width:30%;
}

.curse{
z-index:99;
position: absolute;
margin-top:1.5%;
margin-left:75.6%;

}
.ivu-dropdown-rel .ivu-dropdown-item{
color:white;
font-size:20px;
}

.fixed{
height:357px;
color:white;
font-size:30px;
width:30%;
z-index:99;
position: fixed;
margin-left:3%;
margin-top:5%;
}


</style>
