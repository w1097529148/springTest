<template>
<div>
    <Table :data="tableData1" :columns="tableColumns1" stripe></Table>
    <div style="margin: 10px;overflow: hidden">
        <div style="float: right;">
            <Page :total="this.tableData1.length" :current="1" @on-change="changePage" show-total></Page>
        </div>
    </div>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                tableData1: [],
                tableColumns1: [
                    {
                        title: 'curseId',
                        key: 'curseId'
                    },
                    {
                        title: '坐标X',
                        key: 'signCoordinatesX'
                    },
                    {
                        title: '坐标Y',
                        key: 'signCoordinatesY'
                    },
                    {
                        title: '签到状态',
                        key: 'state'
                    },
                    {
                        title: '签到时间',
                        key: 'time'
                    }
                ]
            }
        },
        computed:{
        Message(){
        return this.$store.getters.user
        }
        },
        methods: {
            mockTableData1 () {
               this.$http.get('http://localhost:8400/sign/getStudentSignMessage',{
               params:{
               studentId:this.Message.studentId
               }
               }).then(resp=>{
               this.tableData1 = resp.data
               }).catch(error=>{
               console.log(error)})
            },
            changePage () {
                // The simulated data is changed directly here, and the actual usage scenario should fetch the data from the server
                let data=[];
                data=this.tableData1.slice(this.currentPage*this.currentPageSize,(this.currentPage+1)*this.currentPageSize)
            }
        },
        mounted(){
        this.mockTableData1 ()
        }
    }
</script>
