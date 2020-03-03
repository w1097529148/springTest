<template>
<div style="width:100%;height:100%" v-if="(AllCurse||getCurseTimes)">
<Badge style="width:100%;height:100%" :text="(this.AllCurse?'已完成':'正在进行')">
<Card >
<p v-if="(getCurseTimes&&state)" ><span>距离开始:</span><span v-text="this.hr+'小时:'+this.min+'分钟:'+this.sec+'秒'"></span></p>
<span>正在进行</span>
<b v-text="(AllCurse?AllCurse.curseName:getCurseMessages.curseName)">程序语言设计</b>
<Tag type="border"  color="error" v-text="this.AllCurse?'已完成':'正在进行'"></Tag>
<h4 style="color:#84A4FF">
<span>正在学习</span>
<span>使用对象</span>
</h4>
<h4 style="color:#9999B3">
<span>截止日期：</span>
<span v-text="(AllCurse?(this.AllCurse.startTime+'~'+this.AllCurse.endTime):this.getCurseTimes?this.getCurseTimes.substring(0,17):'')">2017.06.05-2018.06.05</span>
</h4>
<h4>
<span>进度：</span>
<span>    <Steps :current="0" size="small">
           <Step title="0%"></Step>
           <Step title="25%"></Step>
           <Step title="50%"></Step>
           <Step title="75%"></Step>
           <Step title="100%"></Step>
       </Steps>
       </span>
       </h4>
        <Divider dashed  size="small"/>
        <div style="width:100%;text-align:center" v-if="typeof this.AllCurse=='undefined'">
        <Button type="info" ghost :disabled="(this.Message.studentId?this.disable:!this.state)" @click="test1" v-text="this.Message.studentId?'点击签到':'发布签到'">点击签到</Button>
        <Button type="warning" ghost v-if="this.Message.studentId">申请请假</Button>
        </div>
</Card>
 </Badge>
     <Modal
              title="以我为中心签到"
              v-model="test"
               @on-ok="onOk()"
               >
                <div class="baidumap" id="allmap"  v-if="this.test">
                </div>
          </Modal>
          <div id="allmap1">
                          </div>
</div>
</template>
<script>
import { MP } from '../../map.js';
export default {
    props:{
    AllCurse:{},    //没课的集合
   getCurseTimes:"",    //上课时间
   getCurseMessages:{},    //课程信息
   Message:{}
    },
    data() {
         return {
            hr: 0,
            min: 0,
             sec: 0,
           timer:null,
           disable:true,
           test:false,
           ak:'DyWu5yeHP1g2w34C0xoIvNQxnwsFnDjr', //百度地图的ak
          point:{},  //经纬度对象
          loading:true,  //Modal异步加载
          result:false,
          t:'10:15:00~24:00:003',
          state:true, //倒计时显示状态，false不显示
          signTeacher:{},
          distance:0
         }
       },
       mounted: function () {
       if(this.t)
           this.countdown(this.t.substring(0,8))
       },
       methods: {
          countdown(times){
                   const date=new Date();
                     let hours=date.getHours();
                     let minutes=date.getMinutes();
                     let seconds=date.getSeconds();
                     const end = parseInt(times.substring(0,2))*60*60+parseInt(times.substring(3,5))*60+parseInt(times.substring(6,8))
                     const now = parseInt(hours)*60*60+parseInt(minutes)*60+parseInt(seconds);
                     const msec = end - now
                     let hr = parseInt(msec / 60 / 60 % 24)
                     let min = parseInt(msec / 60 % 60)
                     let sec = parseInt(msec % 60)
                     this.hr = hr > 9 ? hr :hr
                     this.min = min > 9 ? min :min
                     this.sec = sec > 9 ? sec :sec
                     const that = this
                     this.timer=setTimeout(function () {
                       that.countdown(times)
                     }, 1000)
                   },
         test1(){
        this.test=true
this.$nextTick(() => {
      const _this = this
      MP(_this.ak).then(BMap => {
       _this.getSelfPoint()
      })
    })
         },
         onOk(){
              this.loading=true;
              if(this.Message.studentId){
              this.$http.post('http://localhost:8400/sign/sign',this.$qs.stringify({
                  studentId:this.Message.studentId,
                  curseId:this.getCurseMessages.id
              })).then(resp=>{
              alert("signTeacher"+resp.data)
              this.signTeacher=resp.data
               if(this.signTeacher){
                this.$nextTick(() => {
                                               const _this = this
                                               MP(_this.ak).then(BMap => {
                                               alert("进来了")
                                               alert(_this.signTeacher.signCoordinatesX)
                                               alert(_this.signTeacher.signCoordinatesY)
                                                _this.baiduMap(_this.signTeacher.signCoordinatesX,_this.signTeacher.signCoordinatesY)
                                               })
                                             })
                                             if(this.distance>=0&&this.distance<=500){
               this.$http.post('http://localhost:8400/sign/signInsert',this.$qs.stringify({
                                 studentId:this.Message.studentId,
                                 curseId:this.signTeacher.curseId,
                                 teacherSignId:this.signTeacher.id,
                                 pointX:this.signTeacher.signCoordinatesX,
                                 pointY:this.signTeacher.signCoordinatesY
                             })).then(resp=>{
                             alert("距离"+this.distance+"米")
              }).catch(error=>{
              console.log(error)
              })
               this.loading=false;
         }else{
         alert("逃课是不对的，你已经超出签到范围，请在签到范围500米内签到")
         }
         }
         }).catch(error=>{
                           console.log(error)
                           })
                           }
         else if(this.Message.teacherId){
         this.$http.post('http://localhost:8400/sign/release',this.$qs.stringify({
                          teacherId:this.Message.teacherId,
                          curseId:this.getCurseMessages.id,
                          pointX:this.point.lng,
                          pointY:this.point.lat
                      })).then(resp=>{
                      this.result=resp.data
                      if(result)
                     {
                      alert("签到成功")
                     }
                      else{
                       alert("签到失败")
                      }

                      }).catch(error=>{
                      console.log(error)
                      })
                        this.loading=false;
         }

         },
         getSelfPoint(){//获取自身坐标
         const that=this
  // 百度地图API功能
  let map = new BMap.Map("allmap");
  map.centerAndZoom("四川大学锦江学院",22);  //初始化地图,设置城市和地图级别。
   let geolocation = new BMap.Geolocation();
   geolocation.getCurrentPosition(function(r){
     if(this.getStatus() == BMAP_STATUS_SUCCESS){
       let mk = new BMap.Marker(r.point);
        alert('您的位置：'+r.point.lng+','+r.point.lat);
               that.point=r.point
             }
             else {
               alert('failed'+this.getStatus());
             }
           },{enableHighAccuracy: true})
         },
          baiduMap (pointX,pointY) {
          const that=this
          //获取两点的距离
           // 百度地图API功能
           let map = new BMap.Map("allmap1");
           let geolocation = new BMap.Geolocation();
           geolocation.getCurrentPosition(function(r){//此处无法进行，不能同时给一个div赋值
             if(this.getStatus() == BMAP_STATUS_SUCCESS){
               let pointA = new BMap.Point(r.point.lng,r.point.lat);  // 创建点坐标当前位置
               let pointB = new BMap.Point(pointX,pointY);  // 创建点坐标教师位置
               that.distance=(map.getDistance(pointA,pointB)).toFixed(2);//获取两点距离,保留小数点后两位
             }
             else {
               alert('failed'+that.getStatus());
             }
           },{enableHighAccuracy: true})
           }
         },
          watch:{
          'sec':function(newval,oldval){
if(newval<0){
this.disable=!this.disable
window.clearInterval(this.timer)
    if(!this.disable)  {
      this.countdown(this.t.substring(9,17))
    }
else{
this.disable=true
this.state=false
window.clearInterval(this.timer)
}
}
if(newval<0&&oldval<0)  {
this.disable=true
this.state=false
window.clearInterval(this.timer)
}
          }
          }
          }
</script>

<style>
.baidumap{
  width: 500px;
  height: 300px;
}
</style>
