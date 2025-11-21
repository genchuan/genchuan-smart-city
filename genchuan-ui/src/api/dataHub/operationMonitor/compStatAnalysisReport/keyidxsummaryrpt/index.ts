import request from '@/config/axios'

// 关键指标汇总报表 VO
export interface KeyIdxSummaryRptVO {
  id: number // 主键
  rptId: string // 报表ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  adminCode: string // 行政区划代码
  adminName: string // 行政区划名称
  idxId: string // 指标ID
  idxName: string // 指标名称
  idxUnit: string // 指标单位
  idxLevel: string // 指标重要等级
  idxAvg: number // 指标平均值
  idxMax: string // 指标最大值
  exceedCount: number // 超标次数
  momImproveRate: number // 环比改善率
  rptCreateTime: Date // 报表生成时间
  rptCreateUser: number // 报表生成人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 关键指标汇总报表 API
export const KeyIdxSummaryRptApi = {
  // 查询关键指标汇总报表分页
  getKeyIdxSummaryRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/key-idx-summary-rpt/page`, params })
  },

  // 查询关键指标汇总报表详情
  getKeyIdxSummaryRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/key-idx-summary-rpt/get?id=` + id })
  },

  // 新增关键指标汇总报表
  createKeyIdxSummaryRpt: async (data: KeyIdxSummaryRptVO) => {
    return await request.post({ url: `/datacenter/key-idx-summary-rpt/create`, data })
  },

  // 修改关键指标汇总报表
  updateKeyIdxSummaryRpt: async (data: KeyIdxSummaryRptVO) => {
    return await request.put({ url: `/datacenter/key-idx-summary-rpt/update`, data })
  },

  // 删除关键指标汇总报表
  deleteKeyIdxSummaryRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/key-idx-summary-rpt/delete?id=` + id })
  },

  // 导出关键指标汇总报表 Excel
  exportKeyIdxSummaryRpt: async (params) => {
    return await request.download({ url: `/datacenter/key-idx-summary-rpt/export-excel`, params })
  }
}
