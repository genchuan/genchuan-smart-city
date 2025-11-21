import http from '../genchuanHttp2';


//资产配置 添加 修改
export function assetProfileAddEdit(data) {
    return http({
        url: "/api/assetProfile",
        method: "post",
        data: data
    })
}

//资产配置 列表分页
export function assetProfilesQueryList(params) {
    return http({
        url: "/api/assetProfiles",
        method: "get",
        params:params
    })
}

// 资产配置 删除
export function assetProfilesDel(params) {
    return http({
        url: `/api/assetProfile/${params}`,
        method: "delete",
    })
}


//设备配置 添加 修改
export function deviceProfileAddEdit(data) {
    return http({
        url: "/api/deviceProfile",
        method: "post",
        data: data
    })
}

//设备配置 列表分页
export function deviceProfileQueryList(params) {
    return http({
        url: "/api/deviceProfiles",
        method: "get",
        params:params
    })
}

// 设备配置 删除
export function deviceProfileDel(params) {
    return http({
        url: `/api/deviceProfile/${params}`,
        method: "delete",
    })
}


