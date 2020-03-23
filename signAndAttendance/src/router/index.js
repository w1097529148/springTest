import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
function route(path,name,file,children){
  return {
    exact:true,
    path,
    name,
    children,
    component: ()=>import("../components"+file)
  }
}

export default new Router({

  routes: [
    route('/login','login1','/static/logining'),
    route('/index/getTime','getTimes','/getTime/getTime'),
    route('/index/signAttendance','signStudent','/sign/signStudent'),
    {
      path: '/',
      name:'login',
      redirect:"/login",

    },
    {
      path:'/index/mainPage',
      name:'mainPage',
      component:()=>import('../mainPage'),
      redirect:"/index/index",
      children:[
        route('/index/index','index','/index'),
        route('/register','register','/static/register'),
        route('/index/curse','curse','/curse/curseInclude'),
        route('/index/mainCurse','mainCurse','/mainList/mainList'),
        {
          path: '/index/Personal',
          name: 'personal',
          component: () => import('../components/personal'),
          redirect:"/index/signCurse",
          children: [
            route('/index/signCurse','signCurse','/curse/curseList'),
            route('/index/myCurse','MyCurse','/test'),
            route('/index/sign','sign','/sign/sign'),
            route('/index/message','message','/message/MyMessage')
          ]
        }
      ]
    },
  ]
})

