import request from '@/config/axios'

// 事件类型配置 VO
export interface EvtTypeCfgVO {
  id: number // 自增主键ID
  evtTypeId: string // 类型ID
  parentTypeId: string // 父类型ID
  typeLevel: string // 类型层级
  typeCode: string // 类型编码
  typeName: string // 类型名称
  typeDesc: string // 类型说明
  regionCode: string // 所属区域代码
  regionName: string // 所属区域名称
  enableStatus: string // 启用状态
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 事件类型配置 API
export const EvtTypeCfgApi = {
  // 查询事件类型配置分页
  getEvtTypeCfgPage: async (params: any) => {
    return await request.get({ url: `/datacenter/evt-type-cfg/page`, params })
  },

  // 查询事件类型配置详情
  getEvtTypeCfg: async (id: number) => {
    return await request.get({ url: `/datacenter/evt-type-cfg/get?id=` + id })
  },

  // 新增事件类型配置
  createEvtTypeCfg: async (data: EvtTypeCfgVO) => {
    return await request.post({ url: `/datacenter/evt-type-cfg/create`, data })
  },

  // 修改事件类型配置
  updateEvtTypeCfg: async (data: EvtTypeCfgVO) => {
    return await request.put({ url: `/datacenter/evt-type-cfg/update`, data })
  },

  // 删除事件类型配置
  deleteEvtTypeCfg: async (id: number) => {
    return await request.delete({ url: `/datacenter/evt-type-cfg/delete?id=` + id })
  },

  // 导出事件类型配置 Excel
  exportEvtTypeCfg: async (params) => {
    return await request.download({ url: `/datacenter/evt-type-cfg/export-excel`, params })
  },
}
