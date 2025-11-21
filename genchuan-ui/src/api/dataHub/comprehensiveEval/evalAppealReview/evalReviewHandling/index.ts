import request from '@/config/axios'

// 复核办理 VO
export interface EvalReviewVO {
  id: number // 主键ID
  reviewId: string // 复核ID
  appealId: string // 关联申诉ID
  appealCode: string // 申诉编号
  evalTaskId: string // 关联任务ID
  evalObjectId: string // 评价对象ID
  evalObjectName: string // 评价对象名称
  reviewUserId: string // 复核人员ID
  reviewUserName: string // 复核人员姓名
  reviewOpinion: string // 复核意见
  reviewMats: string // 核查材料
  reviewStatus: string // 复核状态
  assignTime: Date // 分配时间
  reviewCompleteTime: Date // 复核完成时间
  assignUserId: string // 分配人ID
  assignUserName: string // 分配人姓名
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 复核办理 API
export const EvalReviewApi = {
  // 查询复核办理分页
  getEvalReviewPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-review/page`, params })
  },

  // 查询复核办理详情
  getEvalReview: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-review/get?id=` + id })
  },

  // 新增复核办理
  createEvalReview: async (data: EvalReviewVO) => {
    return await request.post({ url: `/datacenter/eval-review/create`, data })
  },

  // 修改复核办理
  updateEvalReview: async (data: EvalReviewVO) => {
    return await request.put({ url: `/datacenter/eval-review/update`, data })
  },

  // 删除复核办理
  deleteEvalReview: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-review/delete?id=` + id })
  },

  // 导出复核办理 Excel
  exportEvalReview: async (params) => {
    return await request.download({ url: `/datacenter/eval-review/export-excel`, params })
  }
}
