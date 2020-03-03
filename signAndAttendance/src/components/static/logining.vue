<template>
<div class="login">


<login style="width:60%;margin:0 auto 0 auto"></login>


</div>
</template>

<script>
import headt from '../head/head';
    import login from './login';
export default {
    components:{
    login

    }
}
</script>

<style scoped>
.login{
background-image: url(../../../static/images/loginbackground.jpeg);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  position:absolute;
  width:100%;
  height:100%;
}
</style>
