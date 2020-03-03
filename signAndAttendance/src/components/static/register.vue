<template>
<div style="width:100%;height:100%">
    <p>当前正在进行第 {{ current + 1 }} 步</p>
<div style="width:60%;margin: 0 auto">
<Steps :current="current" >
        <Step title="注册" icon="ios-person" >
        </Step>
        <Step title="完善资料" icon="ios-camera" >
        </Step>
        <Step title="完成" icon="md-checkmark-circle" :status="this.current==2?'process':'wait'" ></Step>
    </Steps>
<div class="register" v-show="current===0">
    <login :title="'注册'" @reg='getMessage' @reg1='getForm' class="register" ></login>
</div>
<div v-show="current===1">
        <perfect :user="this.formData" @reg='getMessage'></perfect>
</div>
<div  class="complete" v-show="current===2">
        <complete></complete>
</div>
<Row type="flex" style="width:100%">
<Col span="2" >
<Button type="primary" @click="back">返回</Button>
</Col>
<Col span="2" offset="20">
<Button type="primary" @click="next" :disabled="!this.message" v-show="this.current<2">下一步</Button>
</Col>
</Row>
</div>


    </div>
</template>
<script>
    import login from './login';
    import perfect from './perfectMessage';
    import complete from './complete';
    export default {

        data () {
            return {
                current: 0,
                message:false,
                formData:{}
            }
        },
        methods: {
            next () {
                if (this.current == 3) {
                    this.current = 0;
                } else {
                    this.current += 1;
                    this.message=false;
                }
            },
            getMessage:function(val){
            this.message=val;
            },
            back:function(){
           this.current>0?this.current-=1:0;
            },
            getForm:function(val){
            this.formData=val;
            }
        },
        components:{
        login,
        perfect,
        complete
        }
    }
</script>
<style>
.register{
background-image: url(../../../static/images/loginbackground.jpeg);
  background-repeat: no-repeat;
  background-size: 100% 100%;
}
.complete{
width:100%;
height:100%;
}
</style>
