<template>
<div style="margin-left:13%;margin-right:13%;">
 <Layout>
        <Content class="content">
 <Row :gutter="16" justify="center" align="middle" type="flex">
<Col span="3" align="middle">
<Tooltip content="热点资讯" placement="right-start">
<Icon type="md-megaphone"/>
                <b>
                热点资讯:
                </b>
                 </Tooltip>
</Col>

<Col span="21" align="middle">
<myCarouse
 :height="height"
 :numbers="numbers"
 :number="number"
 :content-arr="message"
 :teacherMessage="teacher"
></myCarouse>
</Col>
    </Row>
</Content>
     </Layout>
</div>
</template>
<script>
import myCarouse from '../myTopCarouselItem/myTopCarouselItem';
    export default {
    props:{
    message:{
    type:Array
    },
    teacher:{
    type:Array
    }
    },
    data(){
    return{
    value2: 0,
    height:50,
    numbers:1,
    number:0

   }
   },
   components:{
   myCarouse
   }
    }

</script>
<style scope>
.ivu-btn-ghost.ivu-btn-dashed, .ivu-btn-ghost.ivu-btn-default {
color:black;
border-color:rgba(0,0,0,0.2);
}
.content{
background-color:white;border-bottom:1px solid rgba(0,0,0,0.2)
}
.ivu-icon-md-flame,.ivu-icon-md-megaphone{
color:red;
line-height:50px;
font-size:25px;
}
.demo-carousel{

font-size:20px;
}
.ivu-tooltip{

height:50px;
}
</style>
