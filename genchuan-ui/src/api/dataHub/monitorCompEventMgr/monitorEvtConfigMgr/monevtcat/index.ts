import request from '@/config/axios'

// 监测事件分类配置 VO
export interface MonEvtCatVO {
  id: number // 主键ID
  monEvtCatId: string // 分类ID
  parentEvtId: string // 父类ID
  evtCatLevel: string // 分类层级
  evtCatCode: string // 分类代码
  evtCatName: string // 分类名称
  evtCatDesc: string // 分类说明
  enableStatus: string // 启用状态
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 监测事件分类配置 API
export const MonEvtCatApi = {
  // 查询监测事件分类配置分页
  getMonEvtCatPage: async (params: any) => {
    return await request.get({ url: `/datacenter/mon-evt-cat/page`, params })
  },

  // 查询监测事件分类配置详情
  getMonEvtCat: async (id: number) => {
    return await request.get({ url: `/datacenter/mon-evt-cat/get?id=` + id })
  },

  // 新增监测事件分类配置
  createMonEvtCat: async (data: MonEvtCatVO) => {
    return await request.post({ url: `/datacenter/mon-evt-cat/create`, data })
  },

  // 修改监测事件分类配置
  updateMonEvtCat: async (data: MonEvtCatVO) => {
    return await request.put({ url: `/datacenter/mon-evt-cat/update`, data })
  },

  // 删除监测事件分类配置
  deleteMonEvtCat: async (id: number) => {
    return await request.delete({ url: `/datacenter/mon-evt-cat/delete?id=` + id })
  },

  // 导出监测事件分类配置 Excel
  exportMonEvtCat: async (params) => {
    return await request.download({ url: `/datacenter/mon-evt-cat/export-excel`, params })
  },
}