import request from '@/config/axios'

// 调度效果反馈 VO
export interface DispatchEffectFeedbackVO {
  id: number // 自增主键ID
  feedbackId: string // 反馈ID
  commandId: string // 关联指令ID
  commandNo: string // 指令编号
  execUnitId: string // 执行单位ID
  execUnitName: string // 执行单位名称
  effectDesc: string // 执行效果描述
  feedbackAttachPath: string // 反馈附件路径
  feedbackUserId: string // 反馈人ID
  feedbackUserName: string // 反馈人姓名
  feedbackTime: Date // 反馈时间
  evalResult: string // 评估结果
  evalDesc: string // 评估说明
  evalUserId: string // 评估人ID
  evalTime: Date // 评估时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 调度效果反馈 API
export const DispatchEffectFeedbackApi = {
  // 查询调度效果反馈分页
  getDispatchEffectFeedbackPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dispatch-effect-feedback/page`, params })
  },

  // 查询调度效果反馈详情
  getDispatchEffectFeedback: async (id: number) => {
    return await request.get({ url: `/datacenter/dispatch-effect-feedback/get?id=` + id })
  },

  // 新增调度效果反馈
  createDispatchEffectFeedback: async (data: DispatchEffectFeedbackVO) => {
    return await request.post({ url: `/datacenter/dispatch-effect-feedback/create`, data })
  },

  // 修改调度效果反馈
  updateDispatchEffectFeedback: async (data: DispatchEffectFeedbackVO) => {
    return await request.put({ url: `/datacenter/dispatch-effect-feedback/update`, data })
  },

  // 删除调度效果反馈
  deleteDispatchEffectFeedback: async (id: number) => {
    return await request.delete({ url: `/datacenter/dispatch-effect-feedback/delete?id=` + id })
  },

  // 导出调度效果反馈 Excel
  exportDispatchEffectFeedback: async (params) => {
    return await request.download({ url: `/datacenter/dispatch-effect-feedback/export-excel`, params })
  },
}