<template>
<div>
<Layout>
<Content>
<div class="backDiv"></div>
<Row type="flex" justify="start" class="code-row-bg">
<Col span="4" offset="4">
<Affix :offset-top="10">
           <div style="padding: 10px;background: #f8f8f9">
               <Card icon="ios-options" :padding="0" shadow style="width: 100%;">
               <div style="text-align:center">
               <h3 style="color:#84A4FF;padding:10%">个人中心</h3>
               <Avatar size="150" :src="this.Message.title" />
               <p style="margin:10%" v-text="(this.Message.studentName||this.Message.teacherName)">admin</p>
               <Divider dashed  size="small"/>
               </div>
                   <CellGroup style="padding-bottom:10%" >
                     <Cell selected :to="{name:'signCurse',query:{message:JSON.stringify(Message)}}">
                      <Icon type="ios-home" />
                       <span>我的课程</span>
                     </Cell>
                      <Cell :to="item.url" v-for="(item,index) in icons" :key="index" v-if="(icons&&Message)">
                      <Icon :type="item.icon" />
                       <span v-text="item.name"></span>
                     </Cell>
                   </CellGroup>

               </Card>
           </div>
    </Affix>
    </Col>

    <Col span="12">
    <div>
        <router-view :Message="Message"/>
    </div>
</Col>
</Row>
</Content>
</Layout>
</div>
</template>
<script>

    export default {
    data(){
        return {
        icons:[
        {name:'我的签到',url:'/index/sign',icon: 'ios-list-box'},
        {name:'我的消息',url:'/index/message',icon: 'ios-mail'},
        {name:'我的课表',url:'/index/myCurse',icon: 'ios-heart'},
        {name:'个人设置',url:'/components/button',icon: 'ios-settings'},
        {name:'退出',url:'/components/button',icon: 'ios-log-out'},
        ],
        }
        },
        computed:{
        Message(){
  return this.$store.getters.user
        }
        }
    }
</script>

<style scoped>
.backDiv{
width:100%;
height:220px;
background-image: url(../../static/images/back.jpg);
  background-repeat: no-repeat;
  background-size: 100% 100%;

}
</style>
