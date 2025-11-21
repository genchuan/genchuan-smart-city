import request from '@/config/axios'

// 方案模拟对比统计 VO
export interface SchemeSimCompareVO {
  id: number // 主键ID
  simCompareId: string // 对比ID
  compareName: string // 对比名称
  participateSchemeIds: string // 参与方案IDs
  participateSchemeNames: string // 参与方案名称
  resInputWeight: number // 资源投入权重
  expectedEffectWeight: number // 预期效果权重
  implementationCycleWeight: number // 实施周期权重
  scheme1Id: string // 方案1_ID
  scheme1ResScore: number // 方案1_资源投入得分
  scheme1EffectScore: number // 方案1_预期效果得分
  scheme1CycleScore: number // 方案1_周期得分
  scheme1CompositeScore: number // 方案1_综合得分
  scheme2Id: string // 方案2_ID
  scheme2ResScore: number // 方案2_资源投入得分
  scheme2EffectScore: number // 方案2_预期效果得分
  scheme2CycleScore: number // 方案2_周期得分
  scheme2CompositeScore: number // 方案2_综合得分
  compareConclusion: string // 对比结论
  compareUser: string // 对比人
  compareTime: Date // 对比时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 方案模拟对比统计 API
export const SchemeSimCompareApi = {
  // 查询方案模拟对比统计分页
  getSchemeSimComparePage: async (params: any) => {
    return await request.get({ url: `/datacenter/scheme-sim-compare/page`, params })
  },

  // 查询方案模拟对比统计详情
  getSchemeSimCompare: async (id: number) => {
    return await request.get({ url: `/datacenter/scheme-sim-compare/get?id=` + id })
  },

  // 新增方案模拟对比统计
  createSchemeSimCompare: async (data: SchemeSimCompareVO) => {
    return await request.post({ url: `/datacenter/scheme-sim-compare/create`, data })
  },

  // 修改方案模拟对比统计
  updateSchemeSimCompare: async (data: SchemeSimCompareVO) => {
    return await request.put({ url: `/datacenter/scheme-sim-compare/update`, data })
  },

  // 删除方案模拟对比统计
  deleteSchemeSimCompare: async (id: number) => {
    return await request.delete({ url: `/datacenter/scheme-sim-compare/delete?id=` + id })
  },

  // 导出方案模拟对比统计 Excel
  exportSchemeSimCompare: async (params) => {
    return await request.download({ url: `/datacenter/scheme-sim-compare/export-excel`, params })
  },
}