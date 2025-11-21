import request from '@/config/axios'

// 评价网格统计 VO
export interface EvalGridRptVO {
  id: number // 主键ID
  evalStatId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  gridType: string // 评价网格类型(A/B/C)
  idxId: string // 关联评价指标ID
  idxName: string // 关联评价指标名称
  totalCount: number // 网格总数(个)
  totalArea: number // 总面积(m²)
  avgMgCount: number // 平均所含管理数(个)
  totalMgCount: number // 总所含管理网格数(个)
  typeRatio: number // 类型占比(%)
  newCount: number // 新增网格数(个)
  statUserId: string // 统计人
  statTime: Date // 统计时间
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 评价网格统计 API
export const EvalGridRptApi = {
  // 查询评价网格统计分页
  getEvalGridRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-grid-rpt/page`, params })
  },

  // 查询评价网格统计详情
  getEvalGridRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-grid-rpt/get?id=` + id })
  },

  // 新增评价网格统计
  createEvalGridRpt: async (data: EvalGridRptVO) => {
    return await request.post({ url: `/datacenter/eval-grid-rpt/create`, data })
  },

  // 修改评价网格统计
  updateEvalGridRpt: async (data: EvalGridRptVO) => {
    return await request.put({ url: `/datacenter/eval-grid-rpt/update`, data })
  },

  // 删除评价网格统计
  deleteEvalGridRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-grid-rpt/delete?id=` + id })
  },

  // 导出评价网格统计 Excel
  exportEvalGridRpt: async (params) => {
    return await request.download({ url: `/datacenter/eval-grid-rpt/export-excel`, params })
  },
}
