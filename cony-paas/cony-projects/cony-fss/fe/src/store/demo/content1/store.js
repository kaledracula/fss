import demoServer from '../../../services/demo/index'

export const content1 = {
  state: {
    tableData: [{
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    }, {
      date: '2016-05-04',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1517 弄'
    }, {
      date: '2016-05-01',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1519 弄'
    }, {
      date: '2016-05-03',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1516 弄'
    }]
  },
  mutations: {
    'content1:search1' (state, params) {
      console.log('mutations====', state, params)
      state.tableData = params
    }
  },
  actions: {
    async 'content1:search1' ({ commit, state }, params) {
      console.log('actions======', state, params)

      commit('content1:search1', await demoServer.getTable(params))
    }
  },
  getters: {

  }
}
