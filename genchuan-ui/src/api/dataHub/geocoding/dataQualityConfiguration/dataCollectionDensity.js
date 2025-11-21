import request from '@/config/axios'
//数据采集密度配置
// 列表分页
export const listPage = (params) => {
    return request.get({ url: '/datacenter/geocoding-density/page', params })
}
// 列表不分页
export const listAll = (params) => {
    return request.get({ url: '/datacenter/geocoding-density/list', params })
}
// 详情
export const getOne = (id) => {
    return request.get({ url: '/datacenter/geocoding-density/get?id=' + id })
}
// 新增
export const createData = (data) => {
    return request.post({ url: '/datacenter/geocoding-density/create', data })
}
// 修改
export const updateData = (data) => {
    return request.put({ url: '/datacenter/geocoding-density/update', data })
}
// 删除
export const delData = (id) => {
    return request.delete({ url: '/datacenter/geocoding-density/delete?id=' + id })
}
      