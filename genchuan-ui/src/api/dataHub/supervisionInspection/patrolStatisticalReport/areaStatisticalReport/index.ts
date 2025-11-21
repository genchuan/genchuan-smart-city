import request from '@/config/axios'

// 巡查巡检区域统计 VO
export interface InspectAreaRptVO {
  id: number // 主键ID
  areaStatId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  areaCode: string // 行政区划代码
  areaName: string // 行政区划名称
  parentAreaName: string // 上级区域名称
  dailyInspectCount: number // 日常巡查次数
  specInspectCount: number // 专项巡查次数
  emerInspectCount: number // 应急巡查次数
  totalProblemCount: number // 上报问题总数
  rectifyCompleteCount: number // 整改完成数
  rectifyRate: number // 整改率
  concentratedProblemType: string // 问题集中类型
  statUser: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检区域统计 API
export const InspectAreaRptApi = {
  // 查询巡查巡检区域统计分页
  getInspectAreaRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-area-rpt/page`, params })
  },

  // 查询巡查巡检区域统计详情
  getInspectAreaRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-area-rpt/get?id=` + id })
  },

  // 新增巡查巡检区域统计
  createInspectAreaRpt: async (data: InspectAreaRptVO) => {
    return await request.post({ url: `/datacenter/inspect-area-rpt/create`, data })
  },

  // 修改巡查巡检区域统计
  updateInspectAreaRpt: async (data: InspectAreaRptVO) => {
    return await request.put({ url: `/datacenter/inspect-area-rpt/update`, data })
  },

  // 删除巡查巡检区域统计
  deleteInspectAreaRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-area-rpt/delete?id=` + id })
  },

  // 导出巡查巡检区域统计 Excel
  exportInspectAreaRpt: async (params) => {
    return await request.download({ url: `/datacenter/inspect-area-rpt/export-excel`, params })
  }
}
