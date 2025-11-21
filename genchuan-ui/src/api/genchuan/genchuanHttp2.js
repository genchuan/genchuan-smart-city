import axios from 'axios'


// process.env.NODE_ENV === "development" ? '/api' : `${window.location.origin}/api`
const baseURL = import.meta.env.VITE_USER_NODE_ENV === "development" ? '/thingsBoard-api' : `${import.meta.env.VITE_THINGS_BOARD_URL}`
const http = axios.create({
  // 设置超时时间
  timeout: 30000,
  // baseURL: '/thingsBoard-api'
  baseURL: baseURL
})
// 添加请求拦截器  解决刷新token 和芋道配合没有token的时候，拦截器中自动登录
http.interceptors.request.use(
  async config => {
    if (window.localStorage.getItem('thingsBoardJwt_token')) {
      const timestamp = new Date().getTime()
      const jwt_time = window.localStorage.getItem('thingsBoardJwt_time')
      if ((timestamp - jwt_time) > 72000) {
        await refreshToken();
        config.headers['Authorization'] = `Bearer ${window.localStorage.getItem('thingsBoardJwt_token')}`
      } else {
        config.headers['Authorization'] = `Bearer ${window.localStorage.getItem('thingsBoardJwt_token')}`
      }
    } else {
      await loginToken();
      config.headers['Authorization'] = `Bearer ${window.localStorage.getItem('thingsBoardJwt_token')}`
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// thingBoard 刷新token
const refreshToken = async () => {
  await axios.post(`${baseURL}/api/auth/token`, {
    refreshToken: window.localStorage.getItem('thingsBoardRefresh_token')
  })
    .then(thingsBoardRes => {
      window.localStorage.setItem('thingsBoardJwt_token', thingsBoardRes.data.token)
      window.localStorage.setItem('thingsBoardRefresh_token', thingsBoardRes.data.refreshToken)
      window.localStorage.setItem('thingsBoardJwt_time', new Date().getTime().toString())
    })
    .catch(async error => {
      await loginToken();
      console.error('thingBoard刷新token失败:', error)
    })
}

// thingBoard 登录
const loginToken = async () => {
  await axios.post(`${baseURL}/api/auth/login`, {
    username: import.meta.env.VITE_THINGS_BOARD_NAME,
    password: import.meta.env.VITE_THINGS_BOARD_PASSWORD
  })
    .then(thingsBoardRes => {
      window.localStorage.setItem('thingsBoardJwt_token', thingsBoardRes.data.token)
      window.localStorage.setItem('thingsBoardRefresh_token', thingsBoardRes.data.refreshToken)
      window.localStorage.setItem('thingsBoardJwt_time', new Date().getTime().toString())
    })
    .catch(error => {
      console.log('thingBoard登录失败')
      console.error('Error:', error)
    })
}


http.interceptors.response.use(
  response => {
    const res = response.data
    return res
  },
  error => {
    return Promise.reject(error)
  }
)

export default http
