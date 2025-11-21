import request from '@/config/axios'
//数据更新周期配置
// 列表分页
export const listPage = (params) => {
    return request.get({ url: '/datacenter/geocoding-updatecycle/page', params })
}
// 列表不分页
export const listAll = (params) => {
    return request.get({ url: '/datacenter/geocoding-updatecycle/list', params })
}
// 详情
export const getOne = (id) => {
    return request.get({ url: '/datacenter/geocoding-updatecycle/get?id=' + id })
}
// 新增
export const createData = (data) => {
    return request.post({ url: '/datacenter/geocoding-updatecycle/create', data })
}
// 修改
export const updateData = (data) => {
    return request.put({ url: '/datacenter/geocoding-updatecycle/update', data })
}
// 删除
export const delData = (id) => {
    return request.delete({ url: '/datacenter/geocoding-updatecycle/delete?id=' + id })
}
      