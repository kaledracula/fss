<docs>
  * https://github.com/auth0-blog/vue-jwt-authentication
  * https://auth0.com/blog/build-an-app-with-vuejs/
</docs>
<style scoped>
  .box-card{
    border: none;
  }
  .box-card-header{
    padding: 10px 15px;
    background: #20a0ff;
    color:#fff;
    text-align: left;
    font-size: 14px;
    font-weight: bold;
  }
  .box-card-content{
    padding: 15px;
  }
  .date-picker{
    margin-right: 10px;
  }
</style>
<template>
  <el-card class="box-card" :body-style = "{ padding: '0px' }">
    <div class="box-card-header clearfix">
      <span>查询条件</span>
    </div>
    <div class="box-card-content">
      <span>查询时间：</span>
      <el-date-picker class="date-picker"
        :picker-options="pickerOptions"
        v-model="date"
        type="daterange"
        align="right"
        placeholder="选择日期范围">
      </el-date-picker>
      <el-button @click="search">查询</el-button>
    </div>
  </el-card>
</template>

<script>
  import Vue from 'vue'
  //  import { mapState } from 'vuex'
  import {Card, Button, DatePicker} from 'element-ui'
  Vue.use(Card)
  Vue.use(Button)
  Vue.use(DatePicker)

  export default {
    data () {
      return {
        date: [],
        pickerOptions: {
          onPick (date) {
          }
        }
      }
    },
    methods: {
      search () {
        this.$store.dispatch('content1:search1', this.date)
        console.log('content1:search1', this.date)
      }
    }
  }
</script>

<style>
</style>
