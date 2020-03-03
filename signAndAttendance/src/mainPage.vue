<template>
<div>
   <BackTop :height="420" :bottom="200">
        <div class="top">返回顶端</div>
    </BackTop>
    <Layout>
    <!-- 头部-->
        <Header style="background-color:white;margin:0;padding:0;height:100px;"  >
<headt :userInfo="Message" v-if="this.Message"></headt>
</Header>
<Content>
       <div>
    <router-view :Message="this.Message" v-if="this.Message"/>
       </div>
</Content>

<Footer><foot></foot></Footer>
</Layout>
    </Layout>

</div>
</template>
<script>
import headt from './components/head/head';
import foot from './components/footer/footer';
    export default {
    data(){
        return {
Message:{}

      }

    },
    components:{
    headt,
    foot
    },
    methods: {
                      scrollHandle(e){
                      let scrollTop =  window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
                      const _this=this;
                      console.log(scrollTop);
                      if(scrollTop>=420){
                      console.log(_this.fix1)
                      _this.fix1=true;
                      }else{
                      _this.fix1=false;
                      }
                        },
getParams(){
if(this.$route.query.message){
        // 取到路由带过来的参数
this.Message= JSON.parse(this.$route.query.message);

}
},
            },
           mounted(){
                      window.addEventListener('scroll',this.scrollHandle);//绑定页面滚动事件
                      this.getParams();
                  },
    }
</script>
<style scoped>

    .top{
        padding: 10px;
        background: rgba(0, 153, 229, 0.7);
        color: #fff;
        text-align: center;
        border-radius: 2px;
    }



</style>
