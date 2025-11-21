import request from '@/config/axios'

// 评价结果分析 VO
export interface EvalResultAnalysisVO {
  id: number // 主键ID
  resultAnalysisId: string // 分析ID
  resultAnalysisName: string // 分析名称
  resultAnalysisCode: string // 分析编码
  evalTaskId: string // 关联任务ID
  evalTaskName: string // 关联任务名称
  analysisDimension: string // 分析维度
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  idxItemId: string // 指标项ID
  idxItemName: string // 指标项名称
  timeRange: string // 时间范围
  analysisResult: string // 分析结果
  chartType: string // 图表类型
  generateTime: Date // 生成时间
  generateUser: string // 生成人
  createTimeBiz: Date // 创建时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 评价结果分析 API
export const EvalResultAnalysisApi = {
  // 查询评价结果分析分页
  getEvalResultAnalysisPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-result-analysis/page`, params })
  },

  // 查询评价结果分析详情
  getEvalResultAnalysis: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-result-analysis/get?id=` + id })
  },

  // 新增评价结果分析
  createEvalResultAnalysis: async (data: EvalResultAnalysisVO) => {
    return await request.post({ url: `/datacenter/eval-result-analysis/create`, data })
  },

  // 修改评价结果分析
  updateEvalResultAnalysis: async (data: EvalResultAnalysisVO) => {
    return await request.put({ url: `/datacenter/eval-result-analysis/update`, data })
  },

  // 删除评价结果分析
  deleteEvalResultAnalysis: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-result-analysis/delete?id=` + id })
  },

  // 导出评价结果分析 Excel
  exportEvalResultAnalysis: async (params) => {
    return await request.download({ url: `/datacenter/eval-result-analysis/export-excel`, params })
  }
}
