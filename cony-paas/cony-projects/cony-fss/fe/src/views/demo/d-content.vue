<docs>
  * https://github.com/auth0-blog/vue-jwt-authentication
  * https://auth0.com/blog/build-an-app-with-vuejs/
</docs>
<style>
  .d-content{
    min-height: 500px;
    padding: 0 20px;
  }
</style>
<template>
    <el-col :span="19">
      <d-breadcrumb></d-breadcrumb>
      <router-view></router-view>
    </el-col>
</template>

<script>
  import Vue from 'vue'
  import {Col, Breadcrumb, BreadcrumbItem} from 'element-ui'
  import dBreadcrumb from './d-breadcrumb'

  Vue.use(Col)
  Vue.use(Breadcrumb)
  Vue.use(BreadcrumbItem)
  export default {
    name: 'd-content',
    title: 'd-content',
    components: {
      dBreadcrumb
    },
    data () {
      return {}
    },

    methods: {
    }
  }
</script>

<style>
</style>
