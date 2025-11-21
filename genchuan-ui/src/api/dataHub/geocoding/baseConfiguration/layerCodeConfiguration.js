import request from '@/config/axios'
//图层代码配置
// 列表分页
export const listPage = (params) => {
    return request.get({ url: '/datacenter/geocoding-layer/page', params })
}
// 列表不分页
export const listAll = (params) => {
    return request.get({ url: '/datacenter/geocoding-layer/list', params })
}
// 详情
export const getOne = (id) => {
    return request.get({ url: '/datacenter/geocoding-layer/get?id=' + id })
}
// 新增
export const createData = (data) => {
    return request.post({ url: '/datacenter/geocoding-layer/create', data })
}
// 修改
export const updateData = (data) => {
    return request.put({ url: '/datacenter/geocoding-layer/update', data })
}
// 删除
export const delData = (id) => {
    return request.delete({ url: '/datacenter/geocoding-layer/delete?id=' + id })
}
      