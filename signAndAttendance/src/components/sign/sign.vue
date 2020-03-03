<template>
<div>

<Modal
         title="以我为中心签到"
         v-model="test"
          @on-ok="onOk()">
           <div class="baidumap" id="allmap">
           </div>
     </Modal>
</div>

</template>
<script>
import { MP } from '../../map.js';
export default{
data(){
return{
ak:'DyWu5yeHP1g2w34C0xoIvNQxnwrsFnDjr',
test:true
}
},
mounted () {
this.$nextTick(() => {
      const _this = this
      MP(_this.ak).then(BMap => {
        _this.baiduMap()
      })
    }),
    this.$nextTick(() => {
          const _this = this
          MP(_this.ak).then(BMap => {
            _this.baiduMap1()
          })
        })
},
methods: {

  baiduMap () {
     // 百度地图API功能
    var map = new BMap.Map("allmap");
     var geolocation = new BMap.Geolocation();
     geolocation.getCurrentPosition(function(r){
       if(this.getStatus() == BMAP_STATUS_SUCCESS){
         var pointA = new BMap.Point(r.point.lng,r.point.lat);  // 创建点坐标A--大渡口区
         var pointB = new BMap.Point(106.581515,29.615467);  // 创建点坐标B--江北区
         alert('从大渡口区到江北区的距离是：'+(map.getDistance(pointA,pointB)).toFixed(2)+' 米。');  //获取两点距离,保留小数点后两位
         var polyline = new BMap.Polyline([pointA,pointB], {strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5});  //定义折线
       }
       else {
         alert('failed'+this.getStatus());
       }
     },{enableHighAccuracy: true})
     }

},
 baiduMap1 () {
     // 百度地图API功能
    var map = new BMap.Map("allmap");
     var geolocation = new BMap.Geolocation();
     geolocation.getCurrentPosition(function(r){
       if(this.getStatus() == BMAP_STATUS_SUCCESS){
         var pointA = new BMap.Point(r.point.lng,r.point.lat);  // 创建点坐标A--大渡口区
         var pointB = new BMap.Point(106.581515,29.615467);  // 创建点坐标B--江北区
         alert('从大渡口区到江北区的距离是：'+(map.getDistance(pointA,pointB)).toFixed(2)+' 米。');  //获取两点距离,保留小数点后两位
         var polyline = new BMap.Polyline([pointA,pointB], {strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5});  //定义折线
       }
       else {
         alert('failed'+this.getStatus());
       }
     },{enableHighAccuracy: true})
     }
}
</script>
<style>
.baidumap{
  width: 500px;
  height: 300px;
}
</style>
