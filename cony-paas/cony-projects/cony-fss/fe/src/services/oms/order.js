import xhr from '../xhr/'

class OrderServer {
  getOrder () {
    return xhr({
      sysName: 'oms',
      url: '/profile'
    })
  }
  postOrder (data) {
    return xhr({
      sysName: 'oms',
      method: 'post',
      url: '/profile',
      body: data
    })
  }
  getTable () {
    return xhr({
      sysName: 'oms',
      url: '/table'
    })
  }
}

export default new OrderServer()
