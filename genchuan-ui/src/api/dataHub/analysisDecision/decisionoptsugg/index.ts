import request from '@/config/axios'

// 决策优化建议 VO
export interface DecisionOptSuggVO {
  id: number // 主键ID
  optSuggId: string // 建议ID
  relEvalId: string // 关联评估ID
  relSchemeId: string // 关联方案ID
  relSchemeName: string // 关联方案名称
  suggType: string // 建议类型
  suggContent: string // 建议内容
  refCaseId: string // 参考案例ID
  refCaseName: string // 参考案例名称
  suggPriority: string // 建议优先级
  suggStatus: string // 建议状态
  proposer: string // 提出人
  proposerName: string // 提出人姓名
  proposeTime: Date // 提出时间
  handler: string // 处理人
  handleTime: Date // 处理时间
  handleOpinion: string // 处理意见
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 决策优化建议 API
export const DecisionOptSuggApi = {
  // 查询决策优化建议分页
  getDecisionOptSuggPage: async (params: any) => {
    return await request.get({ url: `/datacenter/decision-opt-sugg/page`, params })
  },

  // 查询决策优化建议详情
  getDecisionOptSugg: async (id: number) => {
    return await request.get({ url: `/datacenter/decision-opt-sugg/get?id=` + id })
  },

  // 新增决策优化建议
  createDecisionOptSugg: async (data: DecisionOptSuggVO) => {
    return await request.post({ url: `/datacenter/decision-opt-sugg/create`, data })
  },

  // 修改决策优化建议
  updateDecisionOptSugg: async (data: DecisionOptSuggVO) => {
    return await request.put({ url: `/datacenter/decision-opt-sugg/update`, data })
  },

  // 删除决策优化建议
  deleteDecisionOptSugg: async (id: number) => {
    return await request.delete({ url: `/datacenter/decision-opt-sugg/delete?id=` + id })
  },

  // 导出决策优化建议 Excel
  exportDecisionOptSugg: async (params) => {
    return await request.download({ url: `/datacenter/decision-opt-sugg/export-excel`, params })
  },
}