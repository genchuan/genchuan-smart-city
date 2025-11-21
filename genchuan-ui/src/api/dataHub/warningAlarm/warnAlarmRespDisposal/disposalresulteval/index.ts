import request from '@/config/axios'

// 预警告警处置评估 VO
export interface EarlyWarnHandleEvalVO {
  id: number // 主键
  evalId: string // 评估ID
  earlyWarnId: string // 预警ID
  dispatchId: string // 派单ID
  evalUserId: string // 评估人ID
  evalUserName: string // 评估人姓名
  problemSolveScore: number // 问题解决度得分
  timelinessScore: number // 响应及时性得分
  stdizationScore: number // 现场规范性得分
  comprehensiveScore: number // 综合得分
  evalLevel: string // 评估等级
  evalTime: Date // 评估时间
  evalRemark: string // 评估备注
  isSecondHandle: string // 是否二次处置
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 预警告警处置评估 API
export const EarlyWarnHandleEvalApi = {
  // 查询预警告警处置评估分页
  getEarlyWarnHandleEvalPage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-handle-eval/page`, params })
  },

  // 查询预警告警处置评估详情
  getEarlyWarnHandleEval: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-handle-eval/get?id=` + id })
  },

  // 新增预警告警处置评估
  createEarlyWarnHandleEval: async (data: EarlyWarnHandleEvalVO) => {
    return await request.post({ url: `/datacenter/early-warn-handle-eval/create`, data })
  },

  // 修改预警告警处置评估
  updateEarlyWarnHandleEval: async (data: EarlyWarnHandleEvalVO) => {
    return await request.put({ url: `/datacenter/early-warn-handle-eval/update`, data })
  },

  // 删除预警告警处置评估
  deleteEarlyWarnHandleEval: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-handle-eval/delete?id=` + id })
  },

  // 导出预警告警处置评估 Excel
  exportEarlyWarnHandleEval: async (params) => {
    return await request.download({
      url: `/datacenter/early-warn-handle-eval/export-excel`,
      params
    })
  }
}
