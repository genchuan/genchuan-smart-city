import request from '@/config/axios'

// 按监测部件分析研判统计 VO
export interface AnalysisMonCompVO {
  id: number // 主键ID
  statAnalysisId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  compMajorId: string // 部件大类ID
  compMajorName: string // 部件大类名称
  compMinorId: string // 部件小类ID
  compMinorName: string // 部件小类名称
  totalCompCount: number // 部件总数量
  onlineCompCount: number // 在线部件数
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 按监测部件分析研判统计 API
export const AnalysisMonCompApi = {
  // 查询按监测部件分析研判统计分页
  getAnalysisMonCompPage: async (params: any) => {
    return await request.get({ url: `/datacenter/analysis-mon-comp/page`, params })
  },

  // 查询按监测部件分析研判统计详情
  getAnalysisMonComp: async (id: number) => {
    return await request.get({ url: `/datacenter/analysis-mon-comp/get?id=` + id })
  },

  // 新增按监测部件分析研判统计
  createAnalysisMonComp: async (data: AnalysisMonCompVO) => {
    return await request.post({ url: `/datacenter/analysis-mon-comp/create`, data })
  },

  // 修改按监测部件分析研判统计
  updateAnalysisMonComp: async (data: AnalysisMonCompVO) => {
    return await request.put({ url: `/datacenter/analysis-mon-comp/update`, data })
  },

  // 删除按监测部件分析研判统计
  deleteAnalysisMonComp: async (id: number) => {
    return await request.delete({ url: `/datacenter/analysis-mon-comp/delete?id=` + id })
  },

  // 导出按监测部件分析研判统计 Excel
  exportAnalysisMonComp: async (params) => {
    return await request.download({ url: `/datacenter/analysis-mon-comp/export-excel`, params })
  },
}