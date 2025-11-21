import request from '@/config/axios'

// 决策效果评估 VO
export interface DecisionEffectEvalVO {
  id: number // 主键ID
  effectEvalId: string // 评估ID
  relSchemeId: string // 关联方案ID
  relSchemeName: string // 关联方案名称
  implementationCycle: string // 实施周期
  expectedEffect: string // 预期效果
  actualEffect: string // 实际效果
  effectAchievementRate: number // 效果达成率
  resourceTotalInput: number // 资源总投入
  inputReturnRate: number // 投入回报率
  userSatisfy: number // 用户满意度
  evalGrade: string // 评估等级
  successExp: string // 成功经验
  improvePts: string // 改进点
  evalUser: string // 评估人
  evalTime: Date // 评估时间
  evalRptAttach: string // 评估报告附件
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 决策效果评估 API
export const DecisionEffectEvalApi = {
  // 查询决策效果评估分页
  getDecisionEffectEvalPage: async (params: any) => {
    return await request.get({ url: `/datacenter/decision-effect-eval/page`, params })
  },

  // 查询决策效果评估详情
  getDecisionEffectEval: async (id: number) => {
    return await request.get({ url: `/datacenter/decision-effect-eval/get?id=` + id })
  },

  // 新增决策效果评估
  createDecisionEffectEval: async (data: DecisionEffectEvalVO) => {
    return await request.post({ url: `/datacenter/decision-effect-eval/create`, data })
  },

  // 修改决策效果评估
  updateDecisionEffectEval: async (data: DecisionEffectEvalVO) => {
    return await request.put({ url: `/datacenter/decision-effect-eval/update`, data })
  },

  // 删除决策效果评估
  deleteDecisionEffectEval: async (id: number) => {
    return await request.delete({ url: `/datacenter/decision-effect-eval/delete?id=` + id })
  },

  // 导出决策效果评估 Excel
  exportDecisionEffectEval: async (params) => {
    return await request.download({ url: `/datacenter/decision-effect-eval/export-excel`, params })
  },
}