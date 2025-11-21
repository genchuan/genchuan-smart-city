import request from '@/config/axios'

// 监测点位汇总报表 VO
export interface MonPtSummaryRptVO {
  id: number // 主键
  rptId: string // 报表ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  adminCode: string // 行政区划代码
  adminName: string // 行政区划名称
  totalPtCount: number // 点位总数量
  airPtCount: number // 空气质量监测点位数
  trafPtCount: number // 交通流量监测点位数
  manholePtCount: number // 井盖状态监测点位数
  otherPtCount: number // 其他类型点位数
  newPtCount: number // 新增点位数量
  disablePtCount: number // 停用点位数量
  ptCoverageRate: number // 点位覆盖率
  rptCreateTime: Date // 报表生成时间
  rptCreateUser: number // 报表生成人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 监测点位汇总报表 API
export const MonPtSummaryRptApi = {
  // 查询监测点位汇总报表分页
  getMonPtSummaryRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/mon-pt-summary-rpt/page`, params })
  },

  // 查询监测点位汇总报表详情
  getMonPtSummaryRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/mon-pt-summary-rpt/get?id=` + id })
  },

  // 新增监测点位汇总报表
  createMonPtSummaryRpt: async (data: MonPtSummaryRptVO) => {
    return await request.post({ url: `/datacenter/mon-pt-summary-rpt/create`, data })
  },

  // 修改监测点位汇总报表
  updateMonPtSummaryRpt: async (data: MonPtSummaryRptVO) => {
    return await request.put({ url: `/datacenter/mon-pt-summary-rpt/update`, data })
  },

  // 删除监测点位汇总报表
  deleteMonPtSummaryRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/mon-pt-summary-rpt/delete?id=` + id })
  },

  // 导出监测点位汇总报表 Excel
  exportMonPtSummaryRpt: async (params) => {
    return await request.download({ url: `/datacenter/mon-pt-summary-rpt/export-excel`, params })
  }
}
