<template>
<div>
hello
<div>{{day}}天:{{hr}}小时:{{min}}分:{{sec}}秒</div>
</div>
</template>

<script>
export default {
data () {
     return {
       day: 0, hr: 0, min: 0, sec: 0,
       getCurseTimes:"20:40:00~21:25:00"

     }
   },
   mounted: function () {
     this.countdown()
   },

   methods: {
     countdown: function () {
     const date=new Date();
    var year=date.getFullYear();
       /* 在日期格式中，月份是从0开始的，因此要加0
        * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
        * */
       var month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
       var days=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
       var hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
       var minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
       var seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
       // 拼接
      const time= year+"-"+month+"-"+days+" "+this.getCurseTimes.substring(0,8);
       const end = Date.parse(new Date(time))
       const now = Date.parse(new Date())
       const msec = end - now
       let day = parseInt(msec / 1000 / 60 / 60 / 24)
       let hr = parseInt(msec / 1000 / 60 / 60 % 24)
       let min = parseInt(msec / 1000 / 60 % 60)
       let sec = parseInt(msec / 1000 % 60)
       this.day = day
       this.hr = hr > 9 ? hr : '0' + hr
       this.min = min > 9 ? min : '0' + min
       this.sec = sec > 9 ? sec : '0' + sec
       const that = this
       setTimeout(function () {
         that.countdown()
       }, 1000)
     }
   }
   }
</script>

<style scoped>

</style>
