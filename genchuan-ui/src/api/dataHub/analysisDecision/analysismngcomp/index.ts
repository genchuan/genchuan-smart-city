import request from '@/config/axios'

// 按管理部件分析研判统计 VO
export interface AnalysisMngCompVO {
  id: number // 主键ID
  statAnalysisId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  compMajorId: string // 部件大类ID
  compMajorName: string // 部件大类名称
  compMinorId: string // 部件小类ID
  compMinorName: string // 部件小类名称
  totalCompStock: number // 部件总存量
  damagedCompCount: number // 损坏部件数
  compDamageRate: number // 部件损坏率
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 按管理部件分析研判统计 API
export const AnalysisMngCompApi = {
  // 查询按管理部件分析研判统计分页
  getAnalysisMngCompPage: async (params: any) => {
    return await request.get({ url: `/datacenter/analysis-mng-comp/page`, params })
  },

  // 查询按管理部件分析研判统计详情
  getAnalysisMngComp: async (id: number) => {
    return await request.get({ url: `/datacenter/analysis-mng-comp/get?id=` + id })
  },

  // 新增按管理部件分析研判统计
  createAnalysisMngComp: async (data: AnalysisMngCompVO) => {
    return await request.post({ url: `/datacenter/analysis-mng-comp/create`, data })
  },

  // 修改按管理部件分析研判统计
  updateAnalysisMngComp: async (data: AnalysisMngCompVO) => {
    return await request.put({ url: `/datacenter/analysis-mng-comp/update`, data })
  },

  // 删除按管理部件分析研判统计
  deleteAnalysisMngComp: async (id: number) => {
    return await request.delete({ url: `/datacenter/analysis-mng-comp/delete?id=` + id })
  },

  // 导出按管理部件分析研判统计 Excel
  exportAnalysisMngComp: async (params) => {
    return await request.download({ url: `/datacenter/analysis-mng-comp/export-excel`, params })
  },
}