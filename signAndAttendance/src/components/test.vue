<template>

<!--课程表-->
    <div>
    <div style="text-align:center;font-size:28px;">我的课程表</div>
<Table :columns="columns15" :data="test.个人课表" size="small" ref="table" stripe border show-header></Table>
    <br>
    <Button type="primary" size="large" @click="exportData()"><Icon type="ios-download-outline"></Icon>导出课程表到Excel</Button>
<br/>
<p>支持IE9~IE11、Edge、Chrome、Safari、Firefox 全系列浏览器。</p>
  <p> IE9、Safari 需要手动修改后缀名为 .csv。</p>
   <p>IE9暂时只支持英文，中文会显示为乱码。</p>
    </div>
</template>
<script>
    export default {
    props:{
    Message:{}
    },
        data () {
            return {
                columns15: [
                {
                title:"时间",
                align:"center",
                type:"index",
                indexMethod:(row)=>{
                // row._index打印出了0到9，通过数学计算可以算出每一个对应的序号，再返回出对应的序号
                let i=row._index;
if(i==0)
return "第"+(i+1)+"节\n06:40~07:25"
if(i==1)
return "第"+(i+1)+"节\n08:25~09:00"
if(i==2)
return "第"+(i+1)+"节\n09:05~09:50"
if(i==3)
return "第"+(i+1)+"节\n10:25~11:10"
if(i==4)
return "第"+(i+1)+"节\n11:15~12:00"
if(i==5)
return "第"+(i+1)+"节\n14:25~15:10"
if(i==6)
return "第"+(i+1)+"节\n15:15~16:00"
if(i==7)
return "第"+(i+1)+"节\n16:25~17:10"
if(i==8)
return "第"+(i+1)+"节\n17:15~18:00"
if(i==9)
return "第"+(i+1)+"节\n19:00~19:45"
if(i==10)
return "第"+(i+1)+"节\n19:50~20:35"
if(i==11)
return "第"+(i+1)+"节\n20:40~21:25"
                }
                },
                {
                        title: '周一',
                        align:"center",
                        type:"text",
                        key:"monday",

                    },
                    {
                        title: '周二',
                        type:"text",
                        align:"center",
                    key:'tuesday'
                    },
                    {
                        title: '周三',
                        align:"center",
                        type:"text",
                    key:'wednesday'
                    },
                    {
                        title: '周四',
                        type:"text",
                        align:"center",
                        key:'thursday'
                    },
                    {
                        title: '周五',
                        type:"text",
                        align:"center",
                         key:'friday'
                    },
                    {
                    title: '周六',
                    align:"center",
                    type:"text",
                        key:'saturday'
                    },
                    { title: '周日',
                    align:"center",
                    type:"text",
                    key:'sunday'
                     }
                     ],
                     test:[],
                     loading:true
            }
        },
        methods: {
            getData(){
if(this.Message){
let url=""
let id=""
if(this.Message.studentId){
url="http://localhost:8300/curse/getStudentCurseTable"
 id=this.Message.studentId
}else if(this.Message.teacherId){
url="http://localhost:8300/curse/getTeacherCurse"
 id=this.Message.teacherId
}
 this.$http.get(url,{
            params:{
            Id:id,
            }}).then(
              resp=>{
              this.test=resp.data;
            }).catch((error)=> {
                      console.log(error);
                     })
            }
}, exportData () {
                                  this.$refs.table.exportCsv({
                                      filename: '我的课表'
                                  });
                          }


            },
        mounted(){
        this.loading=true;
        this.getData();
        this.loading=false;
        },
        watch:{
        'Message':'getData'
        }
    }
</script>
