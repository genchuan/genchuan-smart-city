import request from '@/config/axios'

// 复核结果反馈 VO
export interface EvalReviewFeedbackVO {
  id: number // 主键ID
  reviewFeedbackId: string // 反馈ID
  reviewId: string // 关联复核ID
  appealId: string // 关联申诉ID
  appealCode: string // 申诉编号
  evalObjectId: string // 评价对象ID
  evalObjectName: string // 评价对象名称
  originalEvalLevel: string // 原评价等级
  originalEvalScore: number // 原评价得分
  finalReviewResult: string // 最终复核结果
  revisedLevel: string // 修正后等级
  revisedScore: number // 修正后得分
  feedbackOpinion: string // 反馈意见
  feedbackTime: Date // 反馈时间
  feedbackMethod: string // 反馈方式
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 复核结果反馈 API
export const EvalReviewFeedbackApi = {
  // 查询复核结果反馈分页
  getEvalReviewFeedbackPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-review-feedback/page`, params })
  },

  // 查询复核结果反馈详情
  getEvalReviewFeedback: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-review-feedback/get?id=` + id })
  },

  // 新增复核结果反馈
  createEvalReviewFeedback: async (data: EvalReviewFeedbackVO) => {
    return await request.post({ url: `/datacenter/eval-review-feedback/create`, data })
  },

  // 修改复核结果反馈
  updateEvalReviewFeedback: async (data: EvalReviewFeedbackVO) => {
    return await request.put({ url: `/datacenter/eval-review-feedback/update`, data })
  },

  // 删除复核结果反馈
  deleteEvalReviewFeedback: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-review-feedback/delete?id=` + id })
  },

  // 导出复核结果反馈 Excel
  exportEvalReviewFeedback: async (params) => {
    return await request.download({ url: `/datacenter/eval-review-feedback/export-excel`, params })
  }
}
