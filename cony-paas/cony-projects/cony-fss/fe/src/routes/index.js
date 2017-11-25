import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '../views/login'
import ShowTable from '../views/showTable'
import demo from '../views/demo/demo'
import content1 from '../views/demo/content1/d-content1'
import content2 from '../views/demo/content2/d-content2'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/showTable',
      name: 'ShowTable',
      component: ShowTable
    },
    {
      path: '/demo',
      name: 'demo',

      component: demo,
      children: [
        {
          // 当 /demo/content1 匹配成功，
          // UserProfile 会被渲染在 User 的 <router-view> 中
          path: 'content1',
          name: 'content1',
          component: content1
        },
        {
          // 当 /demo/content1 匹配成功，
          // UserProfile 会被渲染在 User 的 <router-view> 中
          path: 'content2',
          name: 'content2',
          component: content2
        }
      ]
    }
  ]
})
