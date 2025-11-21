import http from '../genchuanHttp5';

import qs from 'qs'; // 引入qs库处理URL编码

export function shizhenLogin(loginData) {
  // 准备需要发送的参数（合并必要字段）
  const params = {
    username: loginData.username,
    password: loginData.password,
    // grant_type: 'password' // 包含grant_type参数
  };

  return http({
    url: "/auth/oauth/token?randomStr=&code=&grant_type=password", // 移除URL中的grant_type，避免重复
    method: "post",
    data: qs.stringify(params), // 将对象转为key=value&key2=value2格式
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded', // 显式指定Content-Type
      // "istoken":"false",
      "tenant-id":325,
      "authorization":"Basic cGlnOnBpZw=="
    }
  })
}


//  登录
// export function shizhenLogin(data) {
//   return http({
//     url: "/auth/oauth/token?randomStr=&code=&grant_type=password",
//     method: "post",
//     data: data
//   })
// }

/*export function shizhenLogin(loginData) {
  // 创建FormData对象
  const formData = new FormData();

  // 添加登录所需字段（根据实际需求调整字段名）
  formData.append('username', loginData.username); // 用户名
  formData.append('password', loginData.password); // 密码
  formData.append('grant_type', 'password');
  // 如果有其他参数也需要添加到FormData中
  // formData.append('otherParam', loginData.otherParam);

  return http({
    url: "/auth/oauth/token?randomStr=&code=&grant_type=password",
    method: "post",
    data: formData, // 使用FormData对象作为请求体
  })
}*/

// // thingsBoard 刷新token
// export function thingsBoardRefreshToken(data) {
//   return http({
//     url: "/api/auth/token",
//     method: "post",
//     data: data
//   })
// }


