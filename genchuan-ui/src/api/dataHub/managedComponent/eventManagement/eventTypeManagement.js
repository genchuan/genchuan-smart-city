import request from '@/config/axios'

// 事件类型管理
// 分页列表
export const listPage = (params) => {
  return request.get({ url: '/datacenter/event-type-management/page', params })
}

// 所有列表
export const listAll = () => {
  return request.get({ url: '/datacenter/event-type-management/list-all' })
}

// 详情
export const getOne = (id) => {
  return request.get({ url: '/datacenter/event-type-management/get', params: { id } })
}

// 新增
export const createData = (data) => {
  return request.post({ url: '/datacenter/event-type-management/create', data })
}

// 修改
export const updateData = (data) => {
  return request.put({ url: '/datacenter/event-type-management/update', data })
}

// 删除
export const delData = (id) => {
  return request.delete({ url: '/datacenter/event-type-management/delete', params: { id } })
}

// 更新启用状态
export const updateEnableStatus = (data) => {
  return request.put({ url: '/datacenter/event-type-management/update-enable-status', data })
}

// 获取所有启用的事件大类列表
export const listBigCategories = () => {
  return request.get({ url: '/datacenter/event-type-management/list-big-categories' })
}

// 根据大类代码获取启用的中类列表
export const listMidCategories = (eventBigCode) => {
  return request.get({
    url: '/datacenter/event-type-management/list-mid-categories',
    params: { eventBigCode }
  })
}

// 根据中类代码获取启用的小类列表
export const listSmallCategories = (eventMidCode) => {
  return request.get({
    url: '/datacenter/event-type-management/list-small-categories',
    params: { eventMidCode }
  })
}


