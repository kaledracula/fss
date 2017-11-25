import { rootPath, errHandler } from './config'
import $ from '@jquery'

const xhr = ({ sysName = '', method = 'get', url, body = null }) => {
  let request = $.ajax({
    type: method,
    dataType: 'json',
    url: rootPath(sysName) + url,
    data: body
    // crossDomain: true, // 跨域
    // xhrFields: { withCredentials: true } // 跨域允许带上 cookie
  })
  .fail(errHandler)

  return request
}

export default xhr
