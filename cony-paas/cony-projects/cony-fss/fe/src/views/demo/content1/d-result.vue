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
      <span>查询结果</span>
    </div>
    <div class="box-card-content">
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
          prop="date"
          label="日期"
          width="180">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="address"
          label="地址">
        </el-table-column>
      </el-table>
    </div>
  </el-card>
</template>

<script>
  import Vue from 'vue'
  import {Card, Table, TableColumn} from 'element-ui'
  import { mapState } from 'vuex'
  Vue.use(Card)
  Vue.use(TableColumn)
  Vue.use(Table)

  export default {

    computed: mapState({
      // 箭头函数可使代码更简练
      tableData: state => state.content1.tableData
    }),
    methods: {
    }
  }
</script>

<style>
</style>
