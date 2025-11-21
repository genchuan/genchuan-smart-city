import http from '../genchuanHttp2';


//资产 添加
export function assetsEdit(data) {
    return http({
        url: "/api/asset",
        method: "post",
        data: data
    })
}

//资产 添加字段
export function assetsEditField(id,data) {
    return http({
        url: `/api/plugins/telemetry/ASSET/${id}/SERVER_SCOPE`,
        method: "post",
        data: data
    })
}

//资产 列表分页
export function assetsList(params) {
    return http({
        url: "/api/tenant/assetInfos",
        method: "get",
        params:params
    })
}

// 资产 列表里面的属性
export function assetsListField(params) {
    return http({
        url: `/api/plugins/telemetry/ASSET/${params.id}/values/attributes/SERVER_SCOPE`,
        method: "get",
    })
}



// 资产 删除
export function assetsDel(params) {
    return http({
        url: `/api/asset/${params}`,
        method: "delete",
    })
}
