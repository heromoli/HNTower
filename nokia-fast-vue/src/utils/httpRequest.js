import Vue from 'vue'
import axios from 'axios'
import router from '@/router'
//qs 在请求FORMDATA 传输的时候需要 一种JSON转化格式
import qs from 'qs'
import merge from 'lodash/merge'
import { clearLoginInfo } from '@/utils'

const http = axios.create({
  timeout: 1000 * 30,
  withCredentials: true,
  headers: {
    'Content-Type': 'application/json; charset=utf-8'
  }
})

/**
 * 请求拦截
 */
http.interceptors.request.use(config => {
  config.headers['token'] = Vue.cookie.get('token') // 请求头带上token
  return config
}, error => {
  return Promise.reject(error)
})

/**
 * 响应拦截
 * 必须符合RDATE结构 否则无法返回
 */
http.interceptors.response.use(response => {

    if(response.data){
        var resCode = response.data.code
        if (0 === resCode) {  //code为0表示正常返回，无异常
            return response

        } else if (resCode === 401) { // 401, token失效, 重新登录
            clearLoginInfo()
            router.push({ name: 'login' })

        } else { //其余为500, 404...等异常
            var resMsg = response.data.msg
            if(resMsg){
              Vue.prototype.$message.error(resMsg)
            }
        }
    }

    return response
}, error => {
  return Promise.reject(error)
})

/**
 * 请求地址处理
 * @param {*} actionName action方法名称
 */
http.adornUrl = (actionName) => {
  // 设置统一前缀代理服务器地址
  return process.env.VUE_APP_PROXY_TARGET + actionName
}

/**
 * get请求参数处理
 * @param {*} params 参数对象
 * @param {*} openDefultParams 是否开启默认参数?
 */
http.adornParams = (params = {}, openDefultParams = true) => {
  //默认添加t 确保每次请求都是新的
  var defaults = {
    't': new Date().getTime()
  }
  //merge 合并 如果后面覆盖前面相同的key
  return openDefultParams ? merge(defaults, params) : params
}

/**
 * post请求数据处理
 * @param {*} data 数据对象
 * @param {*} openDefultdata 是否开启默认数据?
 * @param {*} contentType 数据格式
 *  json: 'application/json; charset=utf-8'
 *  form: 'application/x-www-form-urlencoded; charset=utf-8'
 */
http.adornData = (data = {}, openDefultdata = true, contentType = 'json') => {
  //默认添加t 确保每次请求都是新的
  var defaults = {
    't': new Date().getTime()
  }
  data = openDefultdata ? merge(defaults, data) : data
  return contentType === 'json' ? JSON.stringify(data) : qs.stringify(data)
}

export default http
