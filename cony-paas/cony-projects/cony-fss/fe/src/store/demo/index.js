import Vue from 'vue'
import Vuex from 'vuex'
import { content1 } from './content1/store'
Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    content1: content1
  }
})
