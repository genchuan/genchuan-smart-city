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


//警告 列表分页
export function alarmsQueryList(params) {
    return http({
        url: "/api/v2/alarms",
        method: "get",
        params:params
    })
}

// 获取告警列表评论数据
export function alarmQueryListRemarks(params) {
    return http({
        url: `/api/alarm//${params.id}/comment`,
        method: "get",
        params:params
    })
}

// 添加告警列表评论数据
export function remarksAddEdit(id,data) {
    return http({
        url: `/api/alarm/${id}/comment`,
        method: "post",
        data: data
    })
}

// 告警 应答
export function remarksAck(id,data) {
    return http({
        url: `/api/alarm/${id}/ack`,
        method: "post",
        data: data
    })
}

// 获取 告警弹条信息
export function remarksInfo(id) {
    return http({
        url: `/api/alarm/info/${id}`,
        method: "get",
    })
}


// 告警 清除
export function remarksClear(id,data) {
    return http({
        url: `/api/alarm/${id}/clear`,
        method: "post",
        data: data
    })
}












