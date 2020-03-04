import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const actions = {}
const mutations = {
  handleUserName(state, user){
    state.user = user
    // 把登录的用户的名保存到localStorage中，防止页面刷新，导致vuex重新启动，用户名就成为初始值（初始值为空）的情况
    window.localStorage.setItem('user', JSON.stringify(user))
  }
}
const state = {
  user:JSON.parse( window.localStorage.getItem('user'))||null
}
// getters 只会依赖 state 中的成员去更新
const getters = {
  user: (state) => state.user
}

export default new Vuex.Store({
  actions,
  mutations,
  state,
  getters
})
