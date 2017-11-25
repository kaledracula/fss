<docs>
  * https://github.com/auth0-blog/vue-jwt-authentication
  * https://auth0.com/blog/build-an-app-with-vuejs/
</docs>
<style scoped>
  .el-breadcrumb{
    padding: 15px;
  }
</style>
<template>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item >{{crumb1}}</el-breadcrumb-item>
        <el-breadcrumb-item >{{crumb2}}</el-breadcrumb-item>
      </el-breadcrumb>
</template>

<script>
  import Vue from 'vue'
  import {Breadcrumb, BreadcrumbItem} from 'element-ui'
  Vue.use(Breadcrumb)
  Vue.use(BreadcrumbItem)
  export default {
    name: 'd-breadcrumb',
    title: 'd-breadcrumb',
    data () {
      return {
        crumb1: '',
        crumb2: ''
      }
    },
    created () {
      this.crumb1 = this.$route.matched[0].name
      this.crumb2 = this.$route.matched[1] ? this.$route.matched[1].name : ''
    },
    watch: {
      '$route' (to, from) {
        // 对路由变化作出响应...
        console.log(to)
        this.crumb1 = to.matched[0].name
        this.crumb2 = to.matched[1].name
      }
    },
    methods: {
    }
  }
</script>

<style>
</style>
