import request from '@/config/axios'

// 意见反馈 VO
export interface PublicFeedbackVO {
  id: number // 主键ID
  feedbackId: string // 反馈ID 
  feedbackNo: string // 反馈单号 
  feedbackTypeCode: string // 反馈类型编码 
  feedbackTypeName: string // 反馈类型名称 
  feedbackContent: string // 反馈内容 
  imgPath: string // 上传图片路径 
  feedbackUserId: number // 反馈人ID 
  feedbackUserName: string // 反馈人姓名
  feedbackUserPhone: string // 反馈人电话 
  evalStatus: number // 评估状态 
  evalOpinion: string // 评估意见 
  evalUserId: number // 评估人ID 
  evalTime: Date // 评估时间
  feedbackTime: Date // 反馈时间 
  remark: string // 备注，补充说明 
  extCat1: string // 分类扩展字段1 
  extCat2: string // 分类扩展字段2 
  extCommon1: string // 通用扩展字段1 
  extCommon2: string // 通用扩展字段2 
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 意见反馈 API
export const PublicFeedbackApi = {
  // 查询意见反馈分页
  getPublicFeedbackPage: async (params: any) => {
    return await request.get({ url: `/datacenter/public-feedback/page`, params })
  },

  // 查询意见反馈详情
  getPublicFeedback: async (id: number) => {
    return await request.get({ url: `/datacenter/public-feedback/get?id=` + id })
  },

  // 新增意见反馈
  createPublicFeedback: async (data: PublicFeedbackVO) => {
    return await request.post({ url: `/datacenter/public-feedback/create`, data })
  },

  // 修改意见反馈
  updatePublicFeedback: async (data: PublicFeedbackVO) => {
    return await request.put({ url: `/datacenter/public-feedback/update`, data })
  },

  // 删除意见反馈
  deletePublicFeedback: async (id: number) => {
    return await request.delete({ url: `/datacenter/public-feedback/delete?id=` + id })
  },

  // 导出意见反馈 Excel
  exportPublicFeedback: async (params) => {
    return await request.download({ url: `/datacenter/public-feedback/export-excel`, params })
  },
}