import request from '@/config/axios'

// 结果公示 VO
export interface EvalResultPublicVO {
  id: number // 主键ID
  resultPublicId: string // 公示ID
  resultAuditId: string // 关联审核ID
  evalTaskId: string // 关联任务ID
  evalTaskName: string // 关联任务名称
  evalObjectId: string // 评价对象ID
  evalObjectName: string // 评价对象名称
  evalLevel: string // 评价等级
  evalScore: number // 评价得分
  publicScope: string // 公示范围
  publicStartTime: Date // 公示开始时间
  publicEndTime: Date // 公示结束时间
  publicStatus: string // 公示状态
  publicUrl: string // 公示链接
  objectCount: number // 异议数量
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 结果公示 API
export const EvalResultPublicApi = {
  // 查询结果公示分页
  getEvalResultPublicPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-result-public/page`, params })
  },

  // 查询结果公示详情
  getEvalResultPublic: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-result-public/get?id=` + id })
  },

  // 新增结果公示
  createEvalResultPublic: async (data: EvalResultPublicVO) => {
    return await request.post({ url: `/datacenter/eval-result-public/create`, data })
  },

  // 修改结果公示
  updateEvalResultPublic: async (data: EvalResultPublicVO) => {
    return await request.put({ url: `/datacenter/eval-result-public/update`, data })
  },

  // 删除结果公示
  deleteEvalResultPublic: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-result-public/delete?id=` + id })
  },

  // 导出结果公示 Excel
  exportEvalResultPublic: async (params) => {
    return await request.download({ url: `/datacenter/eval-result-public/export-excel`, params })
  }
}
