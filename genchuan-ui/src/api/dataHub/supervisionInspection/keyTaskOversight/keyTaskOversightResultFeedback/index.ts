import request from '@/config/axios'

// 巡查巡检督办结果反馈 VO
export interface InspectSupvFeedbackVO {
  id: number // 主键
  feedbackId: string // 反馈ID
  feedbackCode: string // 反馈编码
  supvTaskId: string // 关联督办任务ID
  supvTaskCode: string // 关联督办任务编码
  problemName: string // 关联问题名称
  supvResult: string // 督办结果
  supvResultDesc: string // 督办结果说明
  supvFeedbackOpinion: string // 督办反馈意见
  rectifyAcceptResult: string // 关联整改验收结果
  feedbackUser: string // 反馈人
  feedbackTime: Date // 反馈时间
  supvStatusChange: string // 督办任务状态变更
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检督办结果反馈 API
export const InspectSupvFeedbackApi = {
  // 查询巡查巡检督办结果反馈分页
  getInspectSupvFeedbackPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-supv-feedback/page`, params })
  },

  // 查询巡查巡检督办结果反馈详情
  getInspectSupvFeedback: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-supv-feedback/get?id=` + id })
  },

  // 新增巡查巡检督办结果反馈
  createInspectSupvFeedback: async (data: InspectSupvFeedbackVO) => {
    return await request.post({ url: `/datacenter/inspect-supv-feedback/create`, data })
  },

  // 修改巡查巡检督办结果反馈
  updateInspectSupvFeedback: async (data: InspectSupvFeedbackVO) => {
    return await request.put({ url: `/datacenter/inspect-supv-feedback/update`, data })
  },

  // 删除巡查巡检督办结果反馈
  deleteInspectSupvFeedback: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-supv-feedback/delete?id=` + id })
  },

  // 导出巡查巡检督办结果反馈 Excel
  exportInspectSupvFeedback: async (params) => {
    return await request.download({ url: `/datacenter/inspect-supv-feedback/export-excel`, params })
  }
}
