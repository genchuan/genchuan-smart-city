import request from '@/config/axios'

// 预警告警风险趋势预测 VO
export interface EarlyWarnRiskPredVO {
  id: number // 主键
  trendForecastId: string // 预测ID
  forecastObject: string // 预测对象
  forecastObjectValue: string // 预测对象值
  historyDataCycle: string // 历史数据周期
  historyDataRange: string // 历史数据范围
  forecastCycle: string // 预测周期
  forecastTimeRange: string // 预测时间范围
  forecastIndicators: string // 预测指标
  forecastModel: string // 预测模型
  forecastAccuracy: number // 预测准确率
  forecastUserId: string // 预测人ID
  forecastUserName: string // 预测人姓名
  forecastTime: Date // 预测时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 预警告警风险趋势预测 API
export const EarlyWarnRiskPredApi = {
  // 查询预警告警风险趋势预测分页
  getEarlyWarnRiskPredPage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-risk-pred/page`, params })
  },

  // 查询预警告警风险趋势预测详情
  getEarlyWarnRiskPred: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-risk-pred/get?id=` + id })
  },

  // 新增预警告警风险趋势预测
  createEarlyWarnRiskPred: async (data: EarlyWarnRiskPredVO) => {
    return await request.post({ url: `/datacenter/early-warn-risk-pred/create`, data })
  },

  // 修改预警告警风险趋势预测
  updateEarlyWarnRiskPred: async (data: EarlyWarnRiskPredVO) => {
    return await request.put({ url: `/datacenter/early-warn-risk-pred/update`, data })
  },

  // 删除预警告警风险趋势预测
  deleteEarlyWarnRiskPred: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-risk-pred/delete?id=` + id })
  },

  // 导出预警告警风险趋势预测 Excel
  exportEarlyWarnRiskPred: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-risk-pred/export-excel`, params })
  }
}
