import request from '@/config/axios'

// 事件关联分析 VO
export interface EventAnalysisVO {
  id: number // 主键
  analysisNumber: string // 关联分析编号
  mainEventNumber: string // 主事件编号
  mainEventName: string // 主事件名称
  relatedEventNumber: string // 关联事件编号
  relatedEventName: string // 关联事件名称
  associationType: string // 关联类型
  associationStrength: string // 关联强度
  analysisTime: Date // 分析时间
  analysts: string // 分析人员
  conclusion: string // 分析结论
  relatedEvidence: string // 关联证据
  recommendedMeasure: string // 建议措施
}

// 事件关联分析 API
export const EventAnalysisApi = {
  // 查询事件关联分析分页
  getEventAnalysisPage: async (params: any) => {
    return await request.get({ url: `/smartcity/event-analysis/page`, params })
  },

  // 查询事件关联分析详情
  getEventAnalysis: async (id: number) => {
    return await request.get({ url: `/smartcity/event-analysis/get?id=` + id })
  },

  // 新增事件关联分析
  createEventAnalysis: async (data: EventAnalysisVO) => {
    return await request.post({ url: `/smartcity/event-analysis/create`, data })
  },

  // 修改事件关联分析
  updateEventAnalysis: async (data: EventAnalysisVO) => {
    return await request.put({ url: `/smartcity/event-analysis/update`, data })
  },

  // 删除事件关联分析
  deleteEventAnalysis: async (id: number) => {
    return await request.delete({ url: `/smartcity/event-analysis/delete?id=` + id })
  },

  // 导出事件关联分析 Excel
  exportEventAnalysis: async (params) => {
    return await request.download({ url: `/smartcity/event-analysis/export-excel`, params })
  }
}