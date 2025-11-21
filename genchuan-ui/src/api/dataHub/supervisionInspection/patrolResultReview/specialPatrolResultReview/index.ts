import request from '@/config/axios'

// 巡查巡检专项结果复核 VO
export interface InspectSpecReviewVO {
  id: number // 主键
  specReviewId: string // 复核ID
  specReviewCode: string // 复核编码
  specExecId: string // 关联专项记录ID
  specExecCode: string // 关联专项记录编码
  specPlanId: string // 关联专项计划ID
  specPlanName: string // 关联专项计划名称
  execUserId: string // 巡查人员ID
  execUserName: string // 巡查人员姓名
  specTopic: string // 专项主题
  reviewFocus: string // 复核重点
  reviewResult: string // 复核结果
  reviewOpinion: string // 复核意见
  reviewUser: string // 复核人
  reviewTime: Date // 复核时间
  reckRequirement: string // 重查要求
  createUser: string // 创建人
  createTimeBiz: Date // 创建时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检专项结果复核 API
export const InspectSpecReviewApi = {
  // 查询巡查巡检专项结果复核分页
  getInspectSpecReviewPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-spec-review/page`, params })
  },

  // 查询巡查巡检专项结果复核详情
  getInspectSpecReview: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-spec-review/get?id=` + id })
  },

  // 新增巡查巡检专项结果复核
  createInspectSpecReview: async (data: InspectSpecReviewVO) => {
    return await request.post({ url: `/datacenter/inspect-spec-review/create`, data })
  },

  // 修改巡查巡检专项结果复核
  updateInspectSpecReview: async (data: InspectSpecReviewVO) => {
    return await request.put({ url: `/datacenter/inspect-spec-review/update`, data })
  },

  // 删除巡查巡检专项结果复核
  deleteInspectSpecReview: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-spec-review/delete?id=` + id })
  },

  // 导出巡查巡检专项结果复核 Excel
  exportInspectSpecReview: async (params) => {
    return await request.download({ url: `/datacenter/inspect-spec-review/export-excel`, params })
  }
}
