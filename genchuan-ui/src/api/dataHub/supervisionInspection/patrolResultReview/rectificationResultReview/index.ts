import request from '@/config/axios'

// 巡查巡检整改结果复核 VO
export interface InspectRectifyReviewVO {
  id: number // 主键ID
  rectifyReviewId: string // 复核ID
  rectifyReviewCode: string // 复核编码
  acceptId: string // 关联验收ID
  acceptCode: string // 关联验收编码
  rectifyTaskId: string // 关联整改任务ID
  problemName: string // 关联问题名称
  deptName: string // 责任部门名称
  leaderUserName: string // 整改负责人姓名
  reviewMethod: string // 复核方式
  reviewPhotoUrls: string // 复核照片URLs
  reviewResult: string // 复核结果
  reviewOpinion: string // 复核意见
  reviewUser: string // 复核人
  reviewTime: Date // 复核时间
  createUser: string // 创建人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检整改结果复核 API
export const InspectRectifyReviewApi = {
  // 查询巡查巡检整改结果复核分页
  getInspectRectifyReviewPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-rectify-review/page`, params })
  },

  // 查询巡查巡检整改结果复核详情
  getInspectRectifyReview: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-rectify-review/get?id=` + id })
  },

  // 新增巡查巡检整改结果复核
  createInspectRectifyReview: async (data: InspectRectifyReviewVO) => {
    return await request.post({ url: `/datacenter/inspect-rectify-review/create`, data })
  },

  // 修改巡查巡检整改结果复核
  updateInspectRectifyReview: async (data: InspectRectifyReviewVO) => {
    return await request.put({ url: `/datacenter/inspect-rectify-review/update`, data })
  },

  // 删除巡查巡检整改结果复核
  deleteInspectRectifyReview: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-rectify-review/delete?id=` + id })
  },

  // 导出巡查巡检整改结果复核 Excel
  exportInspectRectifyReview: async (params) => {
    return await request.download({
      url: `/datacenter/inspect-rectify-review/export-excel`,
      params
    })
  }
}
