import request from '@/config/axios'

// 综合趋势分析报表 VO
export interface TrendAnalysisRptVO {
  id: number // 主键
  rptId: string // 报表ID
  trendDimType: string // 分析维度类型
  trendDimId: string // 分析维度ID
  trendDimName: string // 分析维度名称
  timeGranularity: string // 时间粒度
  startTime: Date // 起始时间
  endTime: Date // 结束时间
  cycle1Label: string // 周期1标识
  cycle1Value: number // 周期1数值
  cycle2Label: string // 周期2标识
  cycle2Value: number // 周期2数值
  overallChangeRate: number // 整体变化率
  trendType: string // 趋势类型
  rptCreateTime: Date // 报表生成时间
  rptCreateUser: number // 报表生成人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 综合趋势分析报表 API
export const TrendAnalysisRptApi = {
  // 查询综合趋势分析报表分页
  getTrendAnalysisRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/trend-analysis-rpt/page`, params })
  },

  // 查询综合趋势分析报表详情
  getTrendAnalysisRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/trend-analysis-rpt/get?id=` + id })
  },

  // 新增综合趋势分析报表
  createTrendAnalysisRpt: async (data: TrendAnalysisRptVO) => {
    return await request.post({ url: `/datacenter/trend-analysis-rpt/create`, data })
  },

  // 修改综合趋势分析报表
  updateTrendAnalysisRpt: async (data: TrendAnalysisRptVO) => {
    return await request.put({ url: `/datacenter/trend-analysis-rpt/update`, data })
  },

  // 删除综合趋势分析报表
  deleteTrendAnalysisRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/trend-analysis-rpt/delete?id=` + id })
  },

  // 导出综合趋势分析报表 Excel
  exportTrendAnalysisRpt: async (params) => {
    return await request.download({ url: `/datacenter/trend-analysis-rpt/export-excel`, params })
  }
}
