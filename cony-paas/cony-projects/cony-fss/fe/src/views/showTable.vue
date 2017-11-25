<template>
  <el-table
    :data="tableData3"
    border
    tooltip-effect="dark"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      label="日期"
      width="120">
      <template scope="scope">{{ scope.row.date }}</template>
    </el-table-column>
    <el-table-column
      prop="name"
      label="姓名"
      width="120">
    </el-table-column>
    <el-table-column
      prop="address"
      label="地址"
      show-overflow-tooltip>
    </el-table-column>
  </el-table>
</template>

<script>
  import Vue from 'vue'
  import {Table, TableColumn} from 'element-ui'
//  import orderServer from '../services/oms/order'

  Vue.use(Table)
  Vue.use(TableColumn)

  export default {
    data () {
      return {
        tableData3: [],
        multipleSelection: []
      }
    },
    mounted () {
//      orderServer.getTable().done(function (m) {
//        console.log(m)
//        this.tableData3 = m
//      })
    },
    methods: {
      handleSelectionChange (val) {
        this.multipleSelection = val
      }
    }
  }
</script>
