import request from '@/config/axios'

// 按行政区划分析研判统计 VO
export interface AnalysisRegionVO {
  id: number // 主键ID
  statAnalysisId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  regionLevel: string // 行政区划级别
  normalMonCompCount: number // 正常监测部件数
  abnMonCompCount: number // 异常监测部件数
  monEvtCompleteRate: number // 监测事件办结率
  statUser: string // 统计人
  statTime: Date // 统计时间
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 按行政区划分析研判统计 API
export const AnalysisRegionApi = {
  // 查询按行政区划分析研判统计分页
  getAnalysisRegionPage: async (params: any) => {
    return await request.get({ url: `/datacenter/analysis-region/page`, params })
  },

  // 查询按行政区划分析研判统计详情
  getAnalysisRegion: async (id: number) => {
    return await request.get({ url: `/datacenter/analysis-region/get?id=` + id })
  },

  // 新增按行政区划分析研判统计
  createAnalysisRegion: async (data: AnalysisRegionVO) => {
    return await request.post({ url: `/datacenter/analysis-region/create`, data })
  },

  // 修改按行政区划分析研判统计
  updateAnalysisRegion: async (data: AnalysisRegionVO) => {
    return await request.put({ url: `/datacenter/analysis-region/update`, data })
  },

  // 删除按行政区划分析研判统计
  deleteAnalysisRegion: async (id: number) => {
    return await request.delete({ url: `/datacenter/analysis-region/delete?id=` + id })
  },

  // 导出按行政区划分析研判统计 Excel
  exportAnalysisRegion: async (params) => {
    return await request.download({ url: `/datacenter/analysis-region/export-excel`, params })
  },
}