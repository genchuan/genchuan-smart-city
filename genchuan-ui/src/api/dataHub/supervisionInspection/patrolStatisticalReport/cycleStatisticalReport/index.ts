import request from '@/config/axios'

// 巡查巡检周期统计 VO
export interface InspectCycleRptVO {
  id: number // 主键ID
  cycleStatId: string // 统计ID
  statDimension: string // 统计维度
  areaCode: string // 所属区域代码
  areaName: string // 所属区域名称
  cycleList: string // 周期列表
  inspectCountList: string // 巡查次数列表
  problemCountList: string // 问题数列表
  rectifyRateList: string // 整改率列表
  avgFoundRateList: string // 平均问题发现率列表
  statUser: string // 统计人
  statTimeBiz: Date // 统计时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检周期统计 API
export const InspectCycleRptApi = {
  // 查询巡查巡检周期统计分页
  getInspectCycleRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-cycle-rpt/page`, params })
  },

  // 查询巡查巡检周期统计详情
  getInspectCycleRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-cycle-rpt/get?id=` + id })
  },

  // 新增巡查巡检周期统计
  createInspectCycleRpt: async (data: InspectCycleRptVO) => {
    return await request.post({ url: `/datacenter/inspect-cycle-rpt/create`, data })
  },

  // 修改巡查巡检周期统计
  updateInspectCycleRpt: async (data: InspectCycleRptVO) => {
    return await request.put({ url: `/datacenter/inspect-cycle-rpt/update`, data })
  },

  // 删除巡查巡检周期统计
  deleteInspectCycleRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-cycle-rpt/delete?id=` + id })
  },

  // 导出巡查巡检周期统计 Excel
  exportInspectCycleRpt: async (params) => {
    return await request.download({ url: `/datacenter/inspect-cycle-rpt/export-excel`, params })
  }
}
