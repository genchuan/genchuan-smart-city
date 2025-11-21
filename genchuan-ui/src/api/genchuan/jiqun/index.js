import http from '../genchuanHttp';

// thingsBoard 登录
export function thingsBoardLogin(data) {
  return http({
    url: "/login",
    method: "post",
    data: data
  })
}

// // thingsBoard 刷新token
// export function thingsBoardRefreshToken(data) {
//   return http({
//     url: "/api/auth/token",
//     method: "post",
//     data: data
//   })
// }


