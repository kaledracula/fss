<docs>
  * https://github.com/auth0-blog/vue-jwt-authentication
  * https://auth0.com/blog/build-an-app-with-vuejs/
</docs>

<template>
    <el-col :span="5">
      <el-menu default-active="2" class="el-menu-vertical-demo" @open="" @close="">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>导航一</template>
          <el-menu-item-group title="group-1">
            <el-menu-item index="1-1" >
              <router-link :to="{ path: '/demo/content1' }">content1</router-link>
            </el-menu-item>
            <el-menu-item index="1-2" >
              <router-link :to="{ path: '/demo/content2' }">content2</router-link>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-menu-item index="2"><i class="el-icon-menu"></i>导航二</el-menu-item>
        <el-menu-item index="3"><i class="el-icon-setting"></i>导航三</el-menu-item>
      </el-menu>
    </el-col>
</template>

<script>
  import Vue from 'vue'
  import {Row, Col, Menu, Submenu, MenuItem, MenuItemGroup} from 'element-ui'
  Vue.use(Row)
  Vue.use(Col)
  Vue.use(Menu)
  Vue.use(Submenu)
  Vue.use(MenuItem)
  Vue.use(MenuItemGroup)
  export default {
    name: 'd-nav',
    title: 'd-nav',
    data () {
      return {}
    },

    methods: {
    }
  }
</script>

<style>
</style>
