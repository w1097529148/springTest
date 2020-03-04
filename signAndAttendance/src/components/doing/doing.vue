<template>
<div>
 <Layout>
 <Header style="margin:0;padding:0;background-color:#F5F7F9">
 <Row align="middle" type="flex" justify="start">
 <Col span="2" class-name="title">
 <b v-text="text">
 </b>
 </Col>
 <Col span="2" offset="20" >
<router-link :to="'javaScript:void(0)'" >查看全部</router-link>
 </Col>
 </Row>
 </Header>
        <Content>
 <Row align="middle" type="flex" justify="start" :gutter="16">
<Col span="4" align="middle" class-name="test" v-for="(item,index) in message" :key="index" v-if="teacher!=null|user!=null">
<router-link :to="{path:'/index/curse',query:{curseData:JSON.stringify(item),message:JSON.stringify(user),teacher:JSON.stringify(teacher!=null?teacher[index]:user)}}" >
<Badge text="正在进行" v-if="index<12">
      <Card class="card"  :bordered="false">
                          <div style="text-align:center" slot="title">
                            <img style="width:100%;" :src="item.title">
                        </div>

                <b v-text="item.startTime+'~'+item.endTime"></b>
                <p>
                <b style="text-align:left"><span v-text="item.curseName"></span>
                <span v-html="' &bull; '+item.whethe"></span></b>
                </p>
                <p><b v-text="'学分 : '+item.score+'分'"></b></p>
                 <p><b v-text="'教师 : '+(teacher!=null?teacher[index].teacherName:user.teacherName)"></b></p>
            </Card>
            </Badge>
            </router-link>
</Col>
    </Row>
</Content>
     </Layout>
</div>
</template>

<script>
export default {
props:{
text:{
default:'与我相关',
type:String
},
message:{
type:Array
},
teacher:{
type:Array,
}
},
computed:{
user(){
return this.$store.getters.user
}
}

}
</script>

<style scoped>
.title{
font-size:20px;
}
.test{
margin-bottom:2%;
}
</style>
