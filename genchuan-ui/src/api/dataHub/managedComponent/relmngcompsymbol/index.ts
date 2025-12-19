import request from '@/config/axios'

// 管理部件图示关联 VO
export interface RelMngCompSymbolVO {
  id: number // 主键ID
  mngCompSymbolId: string // 关联ID
  minorId: string // 所属小类ID
  minorName: string // 所属小类名称
  symbolId: string // 图示ID
  symbolName: string // 图示名称
  symbolPath: string // 图示路径
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 管理部件图示关联 API
export const RelMngCompSymbolApi = {
  // 查询管理部件图示关联分页
  getRelMngCompSymbolPage: async (params: any) => {
    return await request.get({ url: `/datacenter/rel-mng-comp-symbol/page`, params })
  },

  // 查询管理部件图示关联详情
  getRelMngCompSymbol: async (id: number) => {
    return await request.get({ url: `/datacenter/rel-mng-comp-symbol/get?id=` + id })
  },

  // 新增管理部件图示关联
  createRelMngCompSymbol: async (data: RelMngCompSymbolVO) => {
    return await request.post({ url: `/datacenter/rel-mng-comp-symbol/create`, data })
  },

  // 修改管理部件图示关联
  updateRelMngCompSymbol: async (data: RelMngCompSymbolVO) => {
    return await request.put({ url: `/datacenter/rel-mng-comp-symbol/update`, data })
  },

  // 删除管理部件图示关联
  deleteRelMngCompSymbol: async (id: number) => {
    return await request.delete({ url: `/datacenter/rel-mng-comp-symbol/delete?id=` + id })
  },

  // 导出管理部件图示关联 Excel
  exportRelMngCompSymbol: async (params) => {
    return await request.download({ url: `/datacenter/rel-mng-comp-symbol/export-excel`, params })
  }
}