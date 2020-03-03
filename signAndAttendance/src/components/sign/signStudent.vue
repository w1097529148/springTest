<template>
<div>
<span>距离开始:</span><span>{{hr}}:{{min}}:{{sec}}</span>
</div>
</template>
<script>
export default{
data(){
return{
hr:'00',
min:'00',
sec:'00',
timer:null
}
},
methods:{
         countdown(times){
         const date=new Date();
           var hours=date.getHours();
           var minutes=date.getMinutes();
           var seconds=date.getSeconds();
           const end = parseInt(times.substring(0,2))*60*60+parseInt(times.substring(3,5))*60+parseInt(times.substring(6,8))
           const now = parseInt(hours)*60*60+parseInt(minutes)*60+parseInt(seconds);

           const msec = end - now

           let hr = parseInt(msec / 60 / 60 % 24)
           let min = parseInt(msec / 60 % 60)

           let sec = parseInt(msec % 60)

           this.hr = hr > 9 ? hr :('0'+hr)
           this.min = min > 9 ? min :('0'+min)
           this.sec = sec > 9 ? sec :('0'+sec)
           const that = this
           this.timer=setTimeout(function () {
             that.countdown(times)
           }, 1000)
         }
},
mounted(){
this.countdown('24:00:00');
}
}
</script>

<style scoped>

</style>
