import request from '@/config/axios'

// 综合对比分析报表 VO
export interface CompareAnalysisRptVO {
  id: number // 主键
  rptId: string // 报表ID
  compareDimType: string // 对比维度类型
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  statIdx: string // 统计指标
  compareObj1Id: string // 对比对象1ID
  compareObj1Name: string // 对比对象1名称
  compareObj1Value: number // 对比对象1数值
  compareObj2Id: string // 对比对象2ID
  compareObj2Name: string // 对比对象2名称
  compareObj2Value: number // 对比对象2数值
  avgValue: number // 平均值
  maxObjId: string // 最大值对象ID
  maxObjName: string // 最大值对象名称
  maxValue: number // 最大值
  minObjId: string // 最小值对象ID
  minObjName: string // 最小值对象名称
  minValue: number // 最小值
  maxDiffValue: number // 最大差异值
  maxDiffRate: number // 最大差异率
  rptCreateTime: Date // 报表生成时间
  rptCreateUser: number // 报表生成人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 综合对比分析报表 API
export const CompareAnalysisRptApi = {
  // 查询综合对比分析报表分页
  getCompareAnalysisRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/compare-analysis-rpt/page`, params })
  },

  // 查询综合对比分析报表详情
  getCompareAnalysisRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/compare-analysis-rpt/get?id=` + id })
  },

  // 新增综合对比分析报表
  createCompareAnalysisRpt: async (data: CompareAnalysisRptVO) => {
    return await request.post({ url: `/datacenter/compare-analysis-rpt/create`, data })
  },

  // 修改综合对比分析报表
  updateCompareAnalysisRpt: async (data: CompareAnalysisRptVO) => {
    return await request.put({ url: `/datacenter/compare-analysis-rpt/update`, data })
  },

  // 删除综合对比分析报表
  deleteCompareAnalysisRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/compare-analysis-rpt/delete?id=` + id })
  },

  // 导出综合对比分析报表 Excel
  exportCompareAnalysisRpt: async (params) => {
    return await request.download({ url: `/datacenter/compare-analysis-rpt/export-excel`, params })
  }
}
