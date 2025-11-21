import request from '@/config/axios'

// 按监测事件分析研判统计 VO
export interface AnalysisMonEvtVO {
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

// 按监测事件分析研判统计 API
export const AnalysisMonEvtApi = {
  // 查询按监测事件分析研判统计分页
  getAnalysisMonEvtPage: async (params: any) => {
    return await request.get({ url: `/datacenter/analysis-mon-evt/page`, params })
  },

  // 查询按监测事件分析研判统计详情
  getAnalysisMonEvt: async (id: number) => {
    return await request.get({ url: `/datacenter/analysis-mon-evt/get?id=` + id })
  },

  // 新增按监测事件分析研判统计
  createAnalysisMonEvt: async (data: AnalysisMonEvtVO) => {
    return await request.post({ url: `/datacenter/analysis-mon-evt/create`, data })
  },

  // 修改按监测事件分析研判统计
  updateAnalysisMonEvt: async (data: AnalysisMonEvtVO) => {
    return await request.put({ url: `/datacenter/analysis-mon-evt/update`, data })
  },

  // 删除按监测事件分析研判统计
  deleteAnalysisMonEvt: async (id: number) => {
    return await request.delete({ url: `/datacenter/analysis-mon-evt/delete?id=` + id })
  },

  // 导出按监测事件分析研判统计 Excel
  exportAnalysisMonEvt: async (params) => {
    return await request.download({ url: `/datacenter/analysis-mon-evt/export-excel`, params })
  },
}