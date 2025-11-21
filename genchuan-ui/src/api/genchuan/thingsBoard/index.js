import http from '../genchuanHttp2';

// thingsBoard 登录
export function thingsBoardLogin(data) {
  return http({
    url: "/api/auth/login",
    method: "post",
    data: data
  })
}


// thingsBoard api文档
export function thingsboardApiDoc() {
  return http({
    url: "/v3/api-docs/thingsboard",
    method: "get",
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


