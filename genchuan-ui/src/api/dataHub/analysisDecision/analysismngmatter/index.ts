import request from '@/config/axios'

// 按管理事项分析研判统计 VO
export interface AnalysisMngMatterVO {
  id: number // 主键ID
  statAnalysisId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  matterMajorId: string // 事项大类ID
  matterMajorName: string // 事项大类名称
  matterMinorId: string // 事项小类ID
  matterMinorName: string // 事项小类名称
  deptCode: string // 处置部门代码
  deptName: string // 处置部门名称
  totalRptCount: number // 事项上报总量
  matterCompleteRate: number // 事项办结率
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 按管理事项分析研判统计 API
export const AnalysisMngMatterApi = {
  // 查询按管理事项分析研判统计分页
  getAnalysisMngMatterPage: async (params: any) => {
    return await request.get({ url: `/datacenter/analysis-mng-matter/page`, params })
  },

  // 查询按管理事项分析研判统计详情
  getAnalysisMngMatter: async (id: number) => {
    return await request.get({ url: `/datacenter/analysis-mng-matter/get?id=` + id })
  },

  // 新增按管理事项分析研判统计
  createAnalysisMngMatter: async (data: AnalysisMngMatterVO) => {
    return await request.post({ url: `/datacenter/analysis-mng-matter/create`, data })
  },

  // 修改按管理事项分析研判统计
  updateAnalysisMngMatter: async (data: AnalysisMngMatterVO) => {
    return await request.put({ url: `/datacenter/analysis-mng-matter/update`, data })
  },

  // 删除按管理事项分析研判统计
  deleteAnalysisMngMatter: async (id: number) => {
    return await request.delete({ url: `/datacenter/analysis-mng-matter/delete?id=` + id })
  },

  // 导出按管理事项分析研判统计 Excel
  exportAnalysisMngMatter: async (params) => {
    return await request.download({ url: `/datacenter/analysis-mng-matter/export-excel`, params })
  },
}