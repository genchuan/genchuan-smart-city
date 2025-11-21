import request from '@/config/axios'

// 结果存档管理 VO
export interface EvalResultArchVO {
  id: number // 主键ID
  resultArchId: string // 存档ID
  archCode: string // 存档编号
  evalTaskId: string // 关联任务ID
  evalTaskName: string // 关联任务名称
  evalObjectId: string // 评价对象ID
  evalObjectName: string // 评价对象名称
  finalEvalLevel: string // 最终评价等级
  finalEvalScore: number // 最终评价得分
  archReason: string // 存档原因
  archTime: Date // 存档时间
  archAttachs: string // 存档附件
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 结果存档管理 API
export const EvalResultArchApi = {
  // 查询结果存档管理分页
  getEvalResultArchPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-result-arch/page`, params })
  },

  // 查询结果存档管理详情
  getEvalResultArch: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-result-arch/get?id=` + id })
  },

  // 新增结果存档管理
  createEvalResultArch: async (data: EvalResultArchVO) => {
    return await request.post({ url: `/datacenter/eval-result-arch/create`, data })
  },

  // 修改结果存档管理
  updateEvalResultArch: async (data: EvalResultArchVO) => {
    return await request.put({ url: `/datacenter/eval-result-arch/update`, data })
  },

  // 删除结果存档管理
  deleteEvalResultArch: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-result-arch/delete?id=` + id })
  },

  // 导出结果存档管理 Excel
  exportEvalResultArch: async (params) => {
    return await request.download({ url: `/datacenter/eval-result-arch/export-excel`, params })
  }
}
