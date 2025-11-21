import request from '@/config/axios'

// 监测事件数据配置 VO
export interface MonEvtDataCfgVO {
  id: number // 主键ID
  monEvtDataId: string // 配置ID
  evtCatId: string // 事件分类ID
  evtCatName: string // 事件分类名称
  fieldName: string // 字段名称
  fieldCode: string // 字段代码
  fieldType: string // 字段类型
  fieldLength: string // 字段长度
  constraintType: string // 约束条件
  valueRange: string // 值域范围
  fieldDesc: string // 字段说明
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 监测事件数据配置 API
export const MonEvtDataCfgApi = {
  // 查询监测事件数据配置分页
  getMonEvtDataCfgPage: async (params: any) => {
    return await request.get({ url: `/datacenter/mon-evt-data-cfg/page`, params })
  },

  // 查询监测事件数据配置详情
  getMonEvtDataCfg: async (id: number) => {
    return await request.get({ url: `/datacenter/mon-evt-data-cfg/get?id=` + id })
  },

  // 新增监测事件数据配置
  createMonEvtDataCfg: async (data: MonEvtDataCfgVO) => {
    return await request.post({ url: `/datacenter/mon-evt-data-cfg/create`, data })
  },

  // 修改监测事件数据配置
  updateMonEvtDataCfg: async (data: MonEvtDataCfgVO) => {
    return await request.put({ url: `/datacenter/mon-evt-data-cfg/update`, data })
  },

  // 删除监测事件数据配置
  deleteMonEvtDataCfg: async (id: number) => {
    return await request.delete({ url: `/datacenter/mon-evt-data-cfg/delete?id=` + id })
  },

  // 导出监测事件数据配置 Excel
  exportMonEvtDataCfg: async (params) => {
    return await request.download({ url: `/datacenter/mon-evt-data-cfg/export-excel`, params })
  },
}