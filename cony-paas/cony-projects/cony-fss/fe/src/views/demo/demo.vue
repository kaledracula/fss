<docs>
  * https://github.com/auth0-blog/vue-jwt-authentication
  * https://auth0.com/blog/build-an-app-with-vuejs/
</docs>
<style>
  #demo{
    font-size: 14px;
  }
</style>
<template>
  <div id = "demo">
    <d-head></d-head>
    <el-row>

      <d-nav></d-nav>
      <d-content></d-content>
    </el-row>
  </div>
</template>

<script>
  import Vue from 'vue'
  import {Row} from 'element-ui'
  import dNav from './d-nav'
  import dContent from './d-content'
  import dHead from './d-head'

  import store from '../../store/demo/index'

  Vue.use(Row)

  export default {
    name: 'demo',
    title: 'demo',
    components: {
      dNav,
      dContent,
      dHead
    },
    store,
    methods: {
    }
  }
</script>

<style>
</style>
