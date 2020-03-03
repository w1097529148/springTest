<template>
<div @mouseleave="leave()" style="width:100%;height:100%">
<Row :gutter="16">
<Col span="12" class="leftDiv">

 <div style="text-align:center;padding-bottom:1%;" v-for="(item,index) in this.items" :key="index" @mouseenter="enter(index)" >

    <Menu :theme="theme3" active-name="-1" style="background-color:rgba(0,0,0,0.2);margin:0 auto;color:white;">

            <MenuItem name="index" >
              <span v-html="item.id+'&bull;'+item.name"></span>
                                   <Icon type="ios-arrow-forward"></Icon>
            </MenuItem>


    </Menu>

</div>
</Col>
<Col span="12">
<div class="rightDiv" v-if="this.current!==0">
               <Card :title="(this.items[this.current-1].name)" style="height:357px;background-color:rgba(255,255,255,0.5);" icon="ios-options" :padding="0">
                           <CellGroup style="text-align:center;">
                               <Cell style="color:white;margin-bottom:1%;background-color:rgba(0,0,0,0.5);" :title="(item1+'')" v-for="(item1,index1) in this.items[this.current-1].other" :key="index1+7" />
                           </CellGroup>
                       </Card>
            </div>
</Col>
</Row>
</div>

</template>

<script>
export default {
data(){
return{
items:[
{id:1,name:'zs1',age:18,other:{name:'zs1',age:11}},
{id:2,name:'zs2',age:18,other:{name:'zs2',age:12}},
{id:3,name:'zs3',age:18,other:{name:'zs3',age:12}},
{id:4,name:'zs4',age:18,other:{name:'zs4',age:12}},
{id:5,name:'zs5',age:18,other:{name:'zs5',age:12}},
{id:6,name:'zs6',age:18,other:{name:'zs6',age:13}},
{id:7,name:'zs6',age:18,other:{name:'zs6',age:13}}
],
current:0,
theme3: 'light'
}
},
methods:{
enter(index){

this.current=index+1;

},
leave(){
this.current=0;
}
},
}
</script>

<style scoped>


</style>
