import request from '@/config/axios'

// 巡查巡检结果反馈 VO
export interface InspectResultFeedbackVO {
  id: number // 主键ID
  feedbackId: string // 反馈ID
  feedbackCode: string // 反馈编码
  publicId: string // 关联公示ID
  publicTitle: string // 关联公示标题
  feedbackUserName: string // 反馈人姓名
  feedbackUserPhone: string // 反馈人手机号
  feedbackContent: string // 反馈内容
  feedbackPhotoUrls: string // 反馈照片URLs
  feedbackTime: Date // 反馈时间
  feedbackStatus: string // 反馈状态
  verifyUserId: string // 核实人员ID
  verifyUserName: string // 核实人员姓名
  verifyResult: string // 核实结果
  verifyOpinion: string // 核实意见
  problemId: string // 关联问题ID
  handleResult: string // 处理结果
  handleUser: string // 处理人
  handleTime: Date // 处理时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检结果反馈 API
export const InspectResultFeedbackApi = {
  // 查询巡查巡检结果反馈分页
  getInspectResultFeedbackPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-result-feedback/page`, params })
  },

  // 查询巡查巡检结果反馈详情
  getInspectResultFeedback: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-result-feedback/get?id=` + id })
  },

  // 新增巡查巡检结果反馈
  createInspectResultFeedback: async (data: InspectResultFeedbackVO) => {
    return await request.post({ url: `/datacenter/inspect-result-feedback/create`, data })
  },

  // 修改巡查巡检结果反馈
  updateInspectResultFeedback: async (data: InspectResultFeedbackVO) => {
    return await request.put({ url: `/datacenter/inspect-result-feedback/update`, data })
  },

  // 删除巡查巡检结果反馈
  deleteInspectResultFeedback: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-result-feedback/delete?id=` + id })
  },

  // 导出巡查巡检结果反馈 Excel
  exportInspectResultFeedback: async (params) => {
    return await request.download({
      url: `/datacenter/inspect-result-feedback/export-excel`,
      params
    })
  }
}
