<template>
<div :style="{height:hei*nums + 'px'}" class="rollScreen_container" id ="rollScreen_container" v-if="(contentArr&&teacherMessage)">
<div :style = {transform:transform} class="rollScreen_list"  :class="{rollScreen_list_un:num===0}" >
<div :style="{height:hei+'px',lineHeight:hei+'px'}" v-for="(item,index) in contentArr" :key="index" class="rollScreen_once">
<router-link :to="{path:'/index/curse',query:{curseData:JSON.stringify(item),teacher:JSON.stringify(teacherMessage[index])}}" ><span v-text="teacherMessage[index].teacherName+'发布了一门新课程《'+item.curseName+'》'"></span></router-link>
</div>
<div :style="{height:hei+'px',lineHeight:hei+'px'}" v-for="(item,index) in contentArr" :key="index+contentArr.length" class="rollScreen_once" >
<router-link :to="{path:'/index/curse',query:{curseData:JSON.stringify(item),teacher:JSON.stringify(teacherMessage[index])}}" ><span v-text="teacherMessage[index].teacherName+'发布了一门新课程《'+item.curseName+'》'"></span></router-link>
</div>
</div>
</div>
</template>

<script>
export default {
props:{
height:{
default:40,
type:Number
},
numbers:{
default:1,
type:Number
},
number:{
default:0,
type:Number
},
contentArr: {
default:()=>['内容1', '内容2', '内容3', '内容4', '内容5', '内容6', '内容7'],
type:Array
},
teacherMessage:{
type:Array
}
},
data(){
return{
hei:this.height,
num:this.number,
nums:this.numbers,
}
},
created: function () {
    let _this = this;
    setInterval(function () {
      if (_this.num !== _this.contentArr.length) {
        _this.num++;
      } else {
        _this.num = 0
      }
    }, 3000)
  },
    computed: {
      transform: function () {

        return 'translateY(-' + this.num * this.hei + 'px)'
      }
    },
}
</script>
<style>
  .rollScreen_container{
  font-weight: bold;
  width:100%;
    display: inline-block;
    position:relative;
overflow:hidden;
    text-align:center;
  }
  .rollScreen_list{
    transition: 1s linear;
  }

  .rollScreen_list_un{
    transition: none
  }
  .rollScreen_once a{
  color:red
  }
</style>
