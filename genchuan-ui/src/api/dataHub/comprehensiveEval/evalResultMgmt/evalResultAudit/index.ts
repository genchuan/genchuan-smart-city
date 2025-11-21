import request from '@/config/axios'

// 结果审核 VO
export interface EvalResultAuditVO {
  id: number // 主键ID
  resultAuditId: string // 审核ID
  evalTaskId: string // 关联任务ID
  evalTaskName: string // 关联任务名称
  evalObjectId: string // 评价对象ID
  evalObjectName: string // 评价对象名称
  autoCalcResult: string // 自动计算结果
  autoCalcScore: number // 自动计算得分
  auditStatus: string // 审核状态
  auditorId: string // 审核人ID
  auditorName: string // 审核人姓名
  auditOpinion: string // 审核意见
  auditTime: Date // 审核时间
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 结果审核 API
export const EvalResultAuditApi = {
  // 查询结果审核分页
  getEvalResultAuditPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-result-audit/page`, params })
  },

  // 查询结果审核详情
  getEvalResultAudit: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-result-audit/get?id=` + id })
  },

  // 新增结果审核
  createEvalResultAudit: async (data: EvalResultAuditVO) => {
    return await request.post({ url: `/datacenter/eval-result-audit/create`, data })
  },

  // 修改结果审核
  updateEvalResultAudit: async (data: EvalResultAuditVO) => {
    return await request.put({ url: `/datacenter/eval-result-audit/update`, data })
  },

  // 删除结果审核
  deleteEvalResultAudit: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-result-audit/delete?id=` + id })
  },

  // 导出结果审核 Excel
  exportEvalResultAudit: async (params) => {
    return await request.download({ url: `/datacenter/eval-result-audit/export-excel`, params })
  }
}
