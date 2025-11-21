import request from '@/config/axios'
//地理编码分段组合规则配置
// 列表分页
export const listPage = (params) => {
    return request.get({ url: '/datacenter/geocoding-segmentrule/page', params })
}
// 列表不分页
export const listAll = (params) => {
    return request.get({ url: '/datacenter/geocoding-segmentrule/list', params })
}
// 详情
export const getOne = (id) => {
    return request.get({ url: '/datacenter/geocoding-segmentrule/get?id=' + id })
}
// 新增
export const createData = (data) => {
    return request.post({ url: '/datacenter/geocoding-segmentrule/create', data })
}
// 修改
export const updateData = (data) => {
    return request.put({ url: '/datacenter/geocoding-segmentrule/update', data })
}
// 删除
export const delData = (id) => {
    return request.delete({ url: '/datacenter/geocoding-segmentrule/delete?id=' + id })
}
      