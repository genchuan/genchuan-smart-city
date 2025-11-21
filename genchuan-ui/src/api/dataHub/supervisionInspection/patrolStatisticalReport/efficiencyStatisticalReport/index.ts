import request from '@/config/axios'

// 巡查巡检效率统计 VO
export interface InspectEfficacyRptVO {
  id: number // 主键
  efficacyStatId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  areaCode: string // 所属区域代码
  areaName: string // 所属区域名称
  participantCount: number // 参与巡查人员数
  totalInspectCount: number // 总巡查次数
  perPersonInspectCount: number // 人均巡查次数
  avgSingleEndure: number // 平均单次巡查时长
  totalProblemCount: number // 上报问题总数
  avgRectifyEndure: number // 问题平均整改时长
  efficacyEval: string // 效率评估
  statUser: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检效率统计 API
export const InspectEfficacyRptApi = {
  // 查询巡查巡检效率统计分页
  getInspectEfficacyRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-efficacy-rpt/page`, params })
  },

  // 查询巡查巡检效率统计详情
  getInspectEfficacyRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-efficacy-rpt/get?id=` + id })
  },

  // 新增巡查巡检效率统计
  createInspectEfficacyRpt: async (data: InspectEfficacyRptVO) => {
    return await request.post({ url: `/datacenter/inspect-efficacy-rpt/create`, data })
  },

  // 修改巡查巡检效率统计
  updateInspectEfficacyRpt: async (data: InspectEfficacyRptVO) => {
    return await request.put({ url: `/datacenter/inspect-efficacy-rpt/update`, data })
  },

  // 删除巡查巡检效率统计
  deleteInspectEfficacyRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-efficacy-rpt/delete?id=` + id })
  },

  // 导出巡查巡检效率统计 Excel
  exportInspectEfficacyRpt: async (params) => {
    return await request.download({ url: `/datacenter/inspect-efficacy-rpt/export-excel`, params })
  }
}
