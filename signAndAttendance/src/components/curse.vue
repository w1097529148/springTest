<template>
<div class="content">

 <Row :gutter="16" justify="center" align="center">
<Col span="3">
<Button type="default" ghost long>java</Button>
</Col>
<Col span="3">
<Button type="default" ghost long>javaScript</Button>
</Col>
<Col span="3">
<Button type="default" ghost long>Spring</Button>
</Col>
<Col span="3">
<Button type="default" ghost long>html</Button>
</Col>
<Col span="3">
<Button type="default" ghost long>c语言</Button>
</Col>
<Col span="3">
<Button type="default" ghost long>c++</Button>
</Col>
<Col span="3">
      <Button type="default" ghost long>Go</Button>
      </Col>
      <Col span="3">
      <Button type="default" ghost long>Python</Button>
      </Col>
    </Row>


</div>
</template>
script>
    export default {

    data(){
    return{
   }
   }
    }

</script>
<style scope>
.ivu-btn-ghost.ivu-btn-dashed, .ivu-btn-ghost.ivu-btn-default {
color:black;
border-color:rgba(0,0,0,0.2);
}
.content{
background-color:white;
margin-left:13%;
margin-right:13%;
}
</style>
