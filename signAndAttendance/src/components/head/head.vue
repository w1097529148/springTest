<template>
<div style="height:100%">
<!-- 头部 -->
  <Row type="flex" justify="space-around" class="code-row-bg" align="middle">
                        <Col :xs="8" :sm="12" :md="8" :lg="3" order="1"
                        style="line-height:1"
                        offset="2"
                        >
                        <img src="static/images/asset-logoIco.png"/>
                        </Col>
                        <Col span="5" order="2" offset="1" >

                         <Menu mode="horizontal"  active-name="0" width="auto">
                                                        <MenuItem name="1" :to="{path:'mainPage'}">
                                                            <Icon type="ios-paper" />
                                                            <b>首页</b>
                                                        </MenuItem>
                                                        <MenuItem name="2" :to="{path:'mainCurse'}">
                                                            <Icon type="ios-people" />
                                                           <b>课程</b>
                                                        </MenuItem>
                                                        <MenuItem name="3">
                                                            <Icon type="ios-construct" />
                                                            <b>职业规划</b>
                                                        </MenuItem>
                                                    </Menu>
                                                    </Sider>
                                                    </Col>
                        <Col span="6" order="3"
                       offset="2"
                        >
                        <Input search enter-button placeholder="搜索" />
                        </Col>
                            <Col span="4" order="4" offset="1"  align="middle">
    <Menu mode="horizontal"  active-name="1" class="menu"
    >
    <MenuItem name="2" >
           <Badge :count="100" :offset="[20,0]">
             <Icon type="ios-notifications-outline" size="26" style="margin-top:80%">
             </Icon>
          </Badge>
        </MenuItem>
        <MenuItem name="4">
<div class="demo-avatar" @click="getPersonal()">
  <Avatar :src="this.userInfo.title"/>
<span v-text="(this.userInfo.studentName||this.userInfo.teacherName)"></span>
          </div>
        </MenuItem>
    </Menu>
    </Sider>
    </Col>
     </Row>

</div>
</template>

<script>
export default {
data(){
return{
user:{}
}
},
computed:{
userInfo(){
return this.$store.getters.user
}
},
methods:{
getPersonal(){
this.$router.push({
   name:'personal'})
}
},
}
</script>

<style scoped>
.ivu-menu::after{
 display:none;
}
b{
font-size:20px;
}
.code-row-bg{
height:100%;
}
</style>
