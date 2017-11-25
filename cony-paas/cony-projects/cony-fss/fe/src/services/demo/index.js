import xhr from '../xhr/'

class DemoServer {
  getTable (date) {
    var body = {
      minDate: date[0],
      maxDate: date[1]
    }
    console.log('getTable====', body)
    return xhr({
      sysName: 'demo',
      url: '/table',
      body: body
    })
  }
}

export default new DemoServer()
