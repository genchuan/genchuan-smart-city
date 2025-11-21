import request from '@/config/axios'

// 按应用场景分析研判统计 VO
export interface AnalysisAppSceneVO {
  id: number // 主键ID
  statAnalysisId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 按应用场景分析研判统计 API
export const AnalysisAppSceneApi = {
  // 查询按应用场景分析研判统计分页
  getAnalysisAppScenePage: async (params: any) => {
    return await request.get({ url: `/datacenter/analysis-app-scene/page`, params })
  },

  // 查询按应用场景分析研判统计详情
  getAnalysisAppScene: async (id: number) => {
    return await request.get({ url: `/datacenter/analysis-app-scene/get?id=` + id })
  },

  // 新增按应用场景分析研判统计
  createAnalysisAppScene: async (data: AnalysisAppSceneVO) => {
    return await request.post({ url: `/datacenter/analysis-app-scene/create`, data })
  },

  // 修改按应用场景分析研判统计
  updateAnalysisAppScene: async (data: AnalysisAppSceneVO) => {
    return await request.put({ url: `/datacenter/analysis-app-scene/update`, data })
  },

  // 删除按应用场景分析研判统计
  deleteAnalysisAppScene: async (id: number) => {
    return await request.delete({ url: `/datacenter/analysis-app-scene/delete?id=` + id })
  },

  // 导出按应用场景分析研判统计 Excel
  exportAnalysisAppScene: async (params) => {
    return await request.download({ url: `/datacenter/analysis-app-scene/export-excel`, params })
  },
}