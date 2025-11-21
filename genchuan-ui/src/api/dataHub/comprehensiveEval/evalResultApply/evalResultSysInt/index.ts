import request from '@/config/axios'

// 结果系统对接 VO
export interface EvalResultSystemDockVO {
  id: number // 主键ID
  resultDockId: string // 对接ID
  resultDockCode: string // 对接编号
  resultArchId: string // 关联存档ID
  externalSystemId: string // 外部系统ID
  externalSystemName: string // 外部系统名称
  dockMethod: string // 对接方式
  dockFrequency: string // 对接频率
  mapRuleId: string // 数据映射规则ID
  dockStatus: string // 对接状态
  dockTime: Date // 对接时间
  dockData: string // 对接数据
  failReason: string // 失败原因
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 结果系统对接 API
export const EvalResultSystemDockApi = {
  // 查询结果系统对接分页
  getEvalResultSystemDockPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-result-system-dock/page`, params })
  },

  // 查询结果系统对接详情
  getEvalResultSystemDock: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-result-system-dock/get?id=` + id })
  },

  // 新增结果系统对接
  createEvalResultSystemDock: async (data: EvalResultSystemDockVO) => {
    return await request.post({ url: `/datacenter/eval-result-system-dock/create`, data })
  },

  // 修改结果系统对接
  updateEvalResultSystemDock: async (data: EvalResultSystemDockVO) => {
    return await request.put({ url: `/datacenter/eval-result-system-dock/update`, data })
  },

  // 删除结果系统对接
  deleteEvalResultSystemDock: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-result-system-dock/delete?id=` + id })
  },

  // 导出结果系统对接 Excel
  exportEvalResultSystemDock: async (params) => {
    return await request.download({
      url: `/datacenter/eval-result-system-dock/export-excel`,
      params
    })
  }
}
