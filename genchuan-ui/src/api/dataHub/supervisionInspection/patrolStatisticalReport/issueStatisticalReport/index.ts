import request from '@/config/axios'

// 巡查巡检问题统计 VO
export interface InspectProblemRptVO {
  id: number // 主键
  problemStatId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  areaCode: string // 所属区域代码
  areaName: string // 所属区域名称
  problemTypeId: string // 问题类型ID
  problemTypeName: string // 问题类型名称
  rptProblemCount: number // 上报问题数
  level1ProblemCount: number // 一级问题数
  rectifyCompleteCount: number // 整改完成数
  rectifyRate: number // 整改率
  unrectifiedReason: string // 未整改原因
  concentratedArea: string // 问题集中区域
  statUser: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检问题统计 API
export const InspectProblemRptApi = {
  // 查询巡查巡检问题统计分页
  getInspectProblemRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-problem-rpt/page`, params })
  },

  // 查询巡查巡检问题统计详情
  getInspectProblemRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-problem-rpt/get?id=` + id })
  },

  // 新增巡查巡检问题统计
  createInspectProblemRpt: async (data: InspectProblemRptVO) => {
    return await request.post({ url: `/datacenter/inspect-problem-rpt/create`, data })
  },

  // 修改巡查巡检问题统计
  updateInspectProblemRpt: async (data: InspectProblemRptVO) => {
    return await request.put({ url: `/datacenter/inspect-problem-rpt/update`, data })
  },

  // 删除巡查巡检问题统计
  deleteInspectProblemRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-problem-rpt/delete?id=` + id })
  },

  // 导出巡查巡检问题统计 Excel
  exportInspectProblemRpt: async (params) => {
    return await request.download({ url: `/datacenter/inspect-problem-rpt/export-excel`, params })
  }
}
