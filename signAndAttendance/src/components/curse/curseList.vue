<template>
<div>
<h3 style="color:#84A4FF;margin:1%" v-text="(Message.teacherName?'预进行的课程':'待签到的课程')">待签到的课程</h3>
    <div  v-for="(item,index) in getCurseMessages" :key="index" v-if="(getCurseMessages&&getCurseTimes&&Message)">
<curse :getCurseMessages="item"
:getCurseTimes="getCurseTimes[index]"
:Message="Message"
style="margin-top:2%"></curse>
</div>
    <h3 style="color:#84A4FF;margin:1%">全部课程</h3>
    <div style="width:100%">
    <Row type="flex" justify="start" class="code-row-bg" :gutter="16">
<!--遍历今天没课的集合-->
        <Col span="12" style="margin-bottom:2%;" v-for="(item,index) in MineCurse" :key="index"  v-if="(MineCurse&&Message)">
    <curse :AllCurse="item" :Message="Message"></curse>
            </Col>
            </Row>
    </div>
</div>
</template>

<script>
import curse from './curse';
export default {
props:{
Message:{}
},
data(){
return{
  MineCurse:[],
  getCurseTimes:[],//上课时间
  getCurseMessages:[]//课程信息
}
},
    components:{
    curse
    },
        methods:{
              getCurse(){
              this.$http.get('http://localhost:8300/curse/getCurse',{
              params:{
              studentId:this.Message.studentId,
              teacherId:this.Message.teacherId
              }
              }).then(resp=>{
             this.MineCurse= resp.data.今天没课//获取有课属性
            const DoingCurse=resp.data
             this.$delete(DoingCurse,"今天没课")//删除今天没课属性
                     for(let key in DoingCurse){
             this.getCurseTimes.push(key)
             this.getCurseMessages.push(DoingCurse[key])
                     }
              }).catch(error=>{
              console.log(error)
              })
              },
        },
        mounted(){
        this.getCurse();
        },
        watch:{
        'Message':'getCurse'
        }
}
</script>

<style scoped>

</style>
