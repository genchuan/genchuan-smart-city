import axios from 'axios'

const http = axios.create({
  // 设置超时时间
  timeout: 30000,
  baseURL: '/jiqun-api'
})
// 添加请求拦截器
http.interceptors.request.use(
  config => {

   /* if (window.localStorage.getItem('jwt_token')) {
      const timestamp = new Date().getTime()
      const jwt_time = window.localStorage.getItem('jwt_time')
      if ((timestamp - jwt_time) > 7200000) {
        axios.post(`${window._CONFIG['thingsBoard']}/api/auth/token`, {
          refreshToken: window.localStorage.getItem('refresh_token')
        })
          .then(result => {
            window.localStorage.setItem('jwt_token', result.token)
            window.localStorage.setItem('refresh_token', result.refreshToken)
            window.localStorage.setItem('jwt_time', new Date().getTime().toString())
            config.headers['Authorization'] = `Bearer ${window.localStorage.getItem('jwt_token')}`
          })
          .catch(error => {
            console.error('Error:', error)
          })
      } else {
        config.headers['Authorization'] = `Bearer ${window.localStorage.getItem('jwt_token')}`
      }
    }*/


    return config
  },
  error => {
    return Promise.reject(error)
  }
)


http.interceptors.response.use(
  response => {
    const res = response.data
    console.log(11111)
    return res
  },
  error => {
    console.log(2222)
    return Promise.reject(error)
  }
)

export default http
