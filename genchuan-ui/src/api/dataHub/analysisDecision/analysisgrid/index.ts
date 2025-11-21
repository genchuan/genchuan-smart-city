import request from '@/config/axios'

// 按网格分域分析研判统计 VO
export interface AnalysisGridVO {
  id: number // 主键ID
  statAnalysisId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  gridId: string // 网格ID
  gridName: string // 网格名称
  gridType: string // 网格类型
  streetCode: string // 所在街道代码
  streetName: string // 所在街道名称
  monCompOnlineRate: number // 监测部件在线率
  evtTimelyHandleRate: number // 事件处置及时率
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 按网格分域分析研判统计 API
export const AnalysisGridApi = {
  // 查询按网格分域分析研判统计分页
  getAnalysisGridPage: async (params: any) => {
    return await request.get({ url: `/datacenter/analysis-grid/page`, params })
  },

  // 查询按网格分域分析研判统计详情
  getAnalysisGrid: async (id: number) => {
    return await request.get({ url: `/datacenter/analysis-grid/get?id=` + id })
  },

  // 新增按网格分域分析研判统计
  createAnalysisGrid: async (data: AnalysisGridVO) => {
    return await request.post({ url: `/datacenter/analysis-grid/create`, data })
  },

  // 修改按网格分域分析研判统计
  updateAnalysisGrid: async (data: AnalysisGridVO) => {
    return await request.put({ url: `/datacenter/analysis-grid/update`, data })
  },

  // 删除按网格分域分析研判统计
  deleteAnalysisGrid: async (id: number) => {
    return await request.delete({ url: `/datacenter/analysis-grid/delete?id=` + id })
  },

  // 导出按网格分域分析研判统计 Excel
  exportAnalysisGrid: async (params) => {
    return await request.download({ url: `/datacenter/analysis-grid/export-excel`, params })
  },
}