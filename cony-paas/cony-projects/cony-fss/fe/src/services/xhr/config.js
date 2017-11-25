// 此处配置后端 API 根路径 以及 全局错误处理
// 更多配置请根据业务逻辑自行实现
// import $ from '@jquery'

const bmdsHost = window.location.host
const bmdsEnv = bmdsHost.indexOf('test2') !== -1 ? 'test2' : (bmdsHost.indexOf('test') !== -1 ? 'test' : (bmdsHost.indexOf('pre') !== -1 ? 'pre' : ''))

// 后端 API 根路径
export const rootPath = (sysName) => {
  let path = '/api'

  if (process.env.NODE_ENV === 'production') {
    path = 'http://' + sysName + bmdsEnv + '.maimaiche.com/api'
  }
  return path
}

// XHR 错误处理
export const errHandler = (e) => {
  // $.toast({
  //   heading: '请求 API 失败',
  //   icon: 'error',
  //   stack: false
  // })
  // console.warn(e)
}
