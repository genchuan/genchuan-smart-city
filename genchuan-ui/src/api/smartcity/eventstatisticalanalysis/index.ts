import request from '@/config/axios'

// 事件统计分析 VO
export interface EventStatisticalAnalysisVO {
  id: number // 主键
  statisticalCycle: string // 统计周期
  eventType: string // 事件类型
  numberOfEvents: string // 事件数量
  place: string // 发生地点
  highIncidencePeriod: string // 高发时段
  aht: string // 平均处理时长
  processingSuccessRate: string // 处理成功率
  severityDistribution: string // 严重程度分布
  involvedDepartments: string // 涉及部门
  responsibleParty: string // 责任主体
  proportionOfProcessing: string // 处理方式占比
  resourceConsumption: string // 资源消耗统计
  proportionOfReporting: string // 不同渠道上报占比
  numberOfRepeatedEvents: string // 重复事件数量
  timeSeriesTrend: string // 时间序列趋势
  numberOfRelated: string // 关联部件故障次数
  publicFeedbackSatisfaction: string // 公众反馈满意度评分
}

// 事件统计分析 API
export const EventStatisticalAnalysisApi = {
  // 查询事件统计分析分页
  getEventStatisticalAnalysisPage: async (params: any) => {
    return await request.get({ url: `/smartcity/event-statistical-analysis/page`, params })
  },

  // 查询事件统计分析详情
  getEventStatisticalAnalysis: async (id: number) => {
    return await request.get({ url: `/smartcity/event-statistical-analysis/get?id=` + id })
  },

  // 新增事件统计分析
  createEventStatisticalAnalysis: async (data: EventStatisticalAnalysisVO) => {
    return await request.post({ url: `/smartcity/event-statistical-analysis/create`, data })
  },

  // 修改事件统计分析
  updateEventStatisticalAnalysis: async (data: EventStatisticalAnalysisVO) => {
    return await request.put({ url: `/smartcity/event-statistical-analysis/update`, data })
  },

  // 删除事件统计分析
  deleteEventStatisticalAnalysis: async (id: number) => {
    return await request.delete({ url: `/smartcity/event-statistical-analysis/delete?id=` + id })
  },

  // 导出事件统计分析 Excel
  exportEventStatisticalAnalysis: async (params) => {
    return await request.download({ url: `/smartcity/event-statistical-analysis/export-excel`, params })
  }
}