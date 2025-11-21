import request from '@/config/axios'

// 按资产分域分析研判统计 VO
export interface AnalysisAssetVO {
  id: number // 主键ID
  statAnalysisId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  assetMajorId: string // 资产大类ID
  assetMajorName: string // 资产大类名称
  assetMinorId: string // 资产小类ID
  assetMinorName: string // 资产小类名称
  totalAssetCount: number // 资产总存量
  assetIntactRate: number // 资产完好率
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 按资产分域分析研判统计 API
export const AnalysisAssetApi = {
  // 查询按资产分域分析研判统计分页
  getAnalysisAssetPage: async (params: any) => {
    return await request.get({ url: `/datacenter/analysis-asset/page`, params })
  },

  // 查询按资产分域分析研判统计详情
  getAnalysisAsset: async (id: number) => {
    return await request.get({ url: `/datacenter/analysis-asset/get?id=` + id })
  },

  // 新增按资产分域分析研判统计
  createAnalysisAsset: async (data: AnalysisAssetVO) => {
    return await request.post({ url: `/datacenter/analysis-asset/create`, data })
  },

  // 修改按资产分域分析研判统计
  updateAnalysisAsset: async (data: AnalysisAssetVO) => {
    return await request.put({ url: `/datacenter/analysis-asset/update`, data })
  },

  // 删除按资产分域分析研判统计
  deleteAnalysisAsset: async (id: number) => {
    return await request.delete({ url: `/datacenter/analysis-asset/delete?id=` + id })
  },

  // 导出按资产分域分析研判统计 Excel
  exportAnalysisAsset: async (params) => {
    return await request.download({ url: `/datacenter/analysis-asset/export-excel`, params })
  },
}