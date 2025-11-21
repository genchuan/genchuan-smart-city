import request from '@/config/axios'

// 巡查巡检类型统计 VO
export interface InspectTypeRptVO {
  id: number // 主键ID
  typeStatId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  areaCode: string // 所属区域代码
  areaName: string // 所属区域名称
  inspectType: string // 巡查类型
  inspectCount: number // 巡查次数
  participantCount: number // 参与人员数
  foundProblemCount: number // 发现问题数
  problemFoundRate: number // 问题发现率
  rectifyCompleteCount: number // 整改完成数
  rectifyRate: number // 整改率
  avgInspectEndure: number // 平均巡查时长
  statUser: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检类型统计 API
export const InspectTypeRptApi = {
  // 查询巡查巡检类型统计分页
  getInspectTypeRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-type-rpt/page`, params })
  },

  // 查询巡查巡检类型统计详情
  getInspectTypeRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-type-rpt/get?id=` + id })
  },

  // 新增巡查巡检类型统计
  createInspectTypeRpt: async (data: InspectTypeRptVO) => {
    return await request.post({ url: `/datacenter/inspect-type-rpt/create`, data })
  },

  // 修改巡查巡检类型统计
  updateInspectTypeRpt: async (data: InspectTypeRptVO) => {
    return await request.put({ url: `/datacenter/inspect-type-rpt/update`, data })
  },

  // 删除巡查巡检类型统计
  deleteInspectTypeRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-type-rpt/delete?id=` + id })
  },

  // 导出巡查巡检类型统计 Excel
  exportInspectTypeRpt: async (params) => {
    return await request.download({ url: `/datacenter/inspect-type-rpt/export-excel`, params })
  }
}
