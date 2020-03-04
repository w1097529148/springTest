<template>
<div class="layout">
    <Layout>
<Content>
<Card class="mainCard">

<Row>
<Col span="16" offset="4">
<div style="width:100%;">
<Row :gutter="16" justify="space-between" v-if="curses">
<Col span="3" v-for="(curse,index) in curses" :key="index">
<a href="#" v-text="curse.name" @click="getCursesByName(curse.name)"></a>
</Col>
</Row>

</div>
 <Divider />
 <div style="width:100%;">
 <Row :gutter="16" justify="space-between" v-if="curses">
 <Col span="3" v-for="(curse,index) in curses" :key="index">
 <a href="#" v-text="curse.name" @click="getCursesByName(curse.name)"></a>
 </Col>
 </Row>
 </div>
  <Divider />
</Col>
</Row>
</Card>
<div style="width:100%;">
<Row>
<Col span="16" offset="4">
<div>
<Layout>
<Content>
<Row style="margin:0 auto" v-if="this.routerParams" >
<!--与我相关-->
<doing :text="'查询结果'" :message="this.test"  :teacher="this.routerParams.我的教师" v-if="test[0]">
</doing>
<!--精品推荐-->
<doing :text="'精品推荐'" :message="this.routerParams.全部课程"  :teacher="this.routerParams.全部教师">
</doing>
<!--更多-->
<doing  :text="'全部课程'" :message="this.routerParams.全部课程" :teacher="this.routerParams.全部教师">
</doing>
</Row>
</Content>
</Layout>
</div>
</Col>
</Row>

</div>
</Content>
</Layout>
</div>
</template>

<script>
import doing from '../doing/doing';
import HelloWorld from '../hot/hot';
export default {
data(){
return{
curses:[
{name:'java'},
{name:'java'},
{name:'java'},
{name:'java'},
{name:'java'},
{name:'java'},
],
routerParams:[],
test:[]
}
},
methods: {
    getMessages(){
    this.$http.get('http://localhost:8100/login/getStudentMessages',{
    params:{
Id:this.Message.studentId
    }
    }).then((resp)=>{
    this.routerParams=resp.data
    }).catch((error)=>{
    console.log(error)})

    },
            getCursesByName(name){
            this.$http.get('http://localhost:8300/curse/getCurseByName',{
            params:{
        CurseName:name
            }
            }).then((resp)=>{
            this.test=resp.data
            }).catch((error)=>{
            console.log(error)})

            },
        },
        computed:{
        Message(){
        return this.$store.getters.user
        }
        },
                  mounted(){
                  this.getMessages();
                  },
components:{
doing,
HelloWorld
 }
}
</script>

<style scoped>
.mainCard{
width:100%;
height:100%;
}
</style>
