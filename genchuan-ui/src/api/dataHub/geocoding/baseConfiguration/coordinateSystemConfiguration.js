import request from '@/config/axios'
//坐标系配置
// 列表分页
export const listPage = (params) => {
    return request.get({ url: '/datacenter/geocoding-coordinate/page', params })
}
// 列表不分页
export const listAll = (params) => {
    return request.get({ url: '/datacenter/geocoding-coordinate/list', params })
}
// 详情
export const getOne = (id) => {
    return request.get({ url: '/datacenter/geocoding-coordinate/get?id=' + id })
}
// 新增
export const createData = (data) => {
    return request.post({ url: '/datacenter/geocoding-coordinate/create', data })
}
// 修改
export const updateData = (data) => {
    return request.put({ url: '/datacenter/geocoding-coordinate/update', data })
}
// 删除
export const delData = (id) => {
    return request.delete({ url: '/datacenter/geocoding-coordinate/delete?id=' + id })
}
      