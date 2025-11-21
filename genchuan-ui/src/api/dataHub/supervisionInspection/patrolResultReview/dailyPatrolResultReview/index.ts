import request from '@/config/axios'

// 巡查巡检日常结果复核 VO
export interface InspectDailyReviewVO {
  id: number // 主键
  dailyReviewId: string // 复核ID
  dailyReviewCode: string // 复核编码
  dailyExecId: string // 关联日常记录ID
  dailyExecCode: string // 关联日常记录编码
  execUserId: string // 巡查人员ID
  execUserName: string // 巡查人员姓名
  execAreaName: string // 巡查区域名称
  reviewContent: string // 复核内容
  reviewResult: string // 复核结果
  reviewOpinion: string // 复核意见
  reviewBasis: string // 复核依据
  reviewUser: string // 复核人
  reviewTime: Date // 复核时间
  rectifyRequirement: string // 整改要求
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检日常结果复核 API
export const InspectDailyReviewApi = {
  // 查询巡查巡检日常结果复核分页
  getInspectDailyReviewPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-daily-review/page`, params })
  },

  // 查询巡查巡检日常结果复核详情
  getInspectDailyReview: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-daily-review/get?id=` + id })
  },

  // 新增巡查巡检日常结果复核
  createInspectDailyReview: async (data: InspectDailyReviewVO) => {
    return await request.post({ url: `/datacenter/inspect-daily-review/create`, data })
  },

  // 修改巡查巡检日常结果复核
  updateInspectDailyReview: async (data: InspectDailyReviewVO) => {
    return await request.put({ url: `/datacenter/inspect-daily-review/update`, data })
  },

  // 删除巡查巡检日常结果复核
  deleteInspectDailyReview: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-daily-review/delete?id=` + id })
  },

  // 导出巡查巡检日常结果复核 Excel
  exportInspectDailyReview: async (params) => {
    return await request.download({ url: `/datacenter/inspect-daily-review/export-excel`, params })
  }
}
