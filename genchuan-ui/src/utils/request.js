import axios from 'axios';
import { ElMessage } from 'element-plus'; // 假设你使用 Element Plus 的消息提示

// 创建 axios 实例
const request = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_API || '', // 基础 API 地址（可在 .env 文件中配置）
  timeout: 5000, // 请求超时时间（毫秒）
  headers: {
    'Content-Type': 'application/json;charset=utf-8'
  }
});

// 请求拦截器：发送请求前处理
request.interceptors.request.use(
  (config) => {
    // 可在此处添加请求头信息（如 Token）
    // 示例：const token = localStorage.getItem('token');
    // if (token) config.headers.Authorization = `Bearer ${token}`;
    return config;
  },
  (error) => {
    // 请求错误处理
    ElMessage.error('请求发送失败，请稍后重试');
    return Promise.reject(error);
  }
);

// 响应拦截器：接收响应后处理
request.interceptors.response.use(
  (response) => {
    const res = response.data;
    // 假设后端接口统一返回格式为 { code: 200, data: ..., msg: '' }
    if (res.code !== 200) {
      // 非成功状态：显示错误信息
      ElMessage.error(res.msg || '接口请求失败');
      return Promise.reject(res);
    }
    // 成功状态：返回数据部分
    return res;
  },
  (error) => {
    // 响应错误处理（如网络错误、超时等）
    let message = '网络异常，请检查网络连接';
    if (error.code === 'ECONNABORTED') {
      message = '请求超时，请稍后重试';
    } else if (error.response) {
      // 根据 HTTP 状态码提示
      switch (error.response.status) {
        case 401:
          message = '未授权，请重新登录';
          // 可在此处添加跳转到登录页的逻辑
          break;
        case 403:
          message = '权限不足，无法访问';
          break;
        case 404:
          message = '接口不存在';
          break;
        case 500:
          message = '服务器内部错误';
          break;
        default:
          message = `请求错误（${error.response.status}）`;
      }
    }
    ElMessage.error(message);
    return Promise.reject(error);
  }
);

export default request;
