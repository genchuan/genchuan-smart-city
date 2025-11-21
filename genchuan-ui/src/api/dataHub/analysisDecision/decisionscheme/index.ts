import request from '@/config/axios'

// 决策方案生成 VO
export interface DecisionSchemeVO {
  id: number // 主键ID
  decisionSchemeId: string // 方案ID
  schemeName: string // 方案名称
  relAnalysisId: string // 关联分析ID
  relAnalysisType: string // 关联分析类型
  schemeGoal: string // 方案目标
  implementationMeasures: string // 实施措施
  resDemand: string // 资源需求
  expectedEffect: string // 预期效果
  schemeStatus: string // 方案状态
  applyUserId: string // 申请人ID
  applyUserName: string // 申请人姓名，与申请人ID同步，用户信息表（sys_user）
  applyTime: Date // 申请时间
  auditUserId: string // 审核人ID
  auditUserName: string // 审核人姓名
  auditTime: Date // 审核时间
  auditOpinion: string // 审核意见
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 决策方案生成 API
export const DecisionSchemeApi = {
  // 查询决策方案生成分页
  getDecisionSchemePage: async (params: any) => {
    return await request.get({ url: `/datacenter/decision-scheme/page`, params })
  },

  // 查询决策方案生成详情
  getDecisionScheme: async (id: number) => {
    return await request.get({ url: `/datacenter/decision-scheme/get?id=` + id })
  },

  // 新增决策方案生成
  createDecisionScheme: async (data: DecisionSchemeVO) => {
    return await request.post({ url: `/datacenter/decision-scheme/create`, data })
  },

  // 修改决策方案生成
  updateDecisionScheme: async (data: DecisionSchemeVO) => {
    return await request.put({ url: `/datacenter/decision-scheme/update`, data })
  },

  // 删除决策方案生成
  deleteDecisionScheme: async (id: number) => {
    return await request.delete({ url: `/datacenter/decision-scheme/delete?id=` + id })
  },

  // 导出决策方案生成 Excel
  exportDecisionScheme: async (params) => {
    return await request.download({ url: `/datacenter/decision-scheme/export-excel`, params })
  },
}