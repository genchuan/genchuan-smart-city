import request from '@/config/axios'

// 监测部件扩展配置 VO
export interface MonCompExtVO {
  id: number // 主键ID
  extId: string // 扩展配置ID
  compId: string // 部件ID
  extKey: string // 扩展键
  extValue: string // 扩展值
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 监测部件扩展配置 API
export const MonCompExtApi = {
  // 查询监测部件扩展配置分页
  getMonCompExtPage: async (params: any) => {
    return await request.get({ url: `/datacenter/mon-comp-ext/page`, params })
  },

  // 查询监测部件扩展配置详情
  getMonCompExt: async (id: number) => {
    return await request.get({ url: `/datacenter/mon-comp-ext/get?id=` + id })
  },

  // 新增监测部件扩展配置
  createMonCompExt: async (data: MonCompExtVO) => {
    return await request.post({ url: `/datacenter/mon-comp-ext/create`, data })
  },

  // 修改监测部件扩展配置
  updateMonCompExt: async (data: MonCompExtVO) => {
    return await request.put({ url: `/datacenter/mon-comp-ext/update`, data })
  },

  // 删除监测部件扩展配置
  deleteMonCompExt: async (id: number) => {
    return await request.delete({ url: `/datacenter/mon-comp-ext/delete?id=` + id })
  },

  // 导出监测部件扩展配置 Excel
  exportMonCompExt: async (params) => {
    return await request.download({ url: `/datacenter/mon-comp-ext/export-excel`, params })
  },
}