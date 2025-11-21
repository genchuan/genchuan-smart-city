import request from '@/config/axios'

// 决策执行跟踪 VO
export interface DecisionExecTrackVO {
  id: number // 主键ID
  execTrackId: string // 跟踪ID
  relSchemeId: string // 关联方案ID
  relSchemeName: string // 关联方案名称
  plannedExecCycle: string // 计划实施周期
  executedDays: number // 已实施天数
  execProgress: number // 实施进度
  expectedEffectIdx: string // 预期效果指标
  actualEffectIdx: string // 实际效果指标
  effectAchievementRate: number // 效果达成率
  unmetItems: string // 未达标项
  trackStatus: string // 跟踪状态
  trackUser: string // 跟踪人
  trackTime: Date // 跟踪时间
  lastTrackTime: Date // 上次跟踪时间
  rectificationSugg: string // 整改建议
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 决策执行跟踪 API
export const DecisionExecTrackApi = {
  // 查询决策执行跟踪分页
  getDecisionExecTrackPage: async (params: any) => {
    return await request.get({ url: `/datacenter/decision-exec-track/page`, params })
  },

  // 查询决策执行跟踪详情
  getDecisionExecTrack: async (id: number) => {
    return await request.get({ url: `/datacenter/decision-exec-track/get?id=` + id })
  },

  // 新增决策执行跟踪
  createDecisionExecTrack: async (data: DecisionExecTrackVO) => {
    return await request.post({ url: `/datacenter/decision-exec-track/create`, data })
  },

  // 修改决策执行跟踪
  updateDecisionExecTrack: async (data: DecisionExecTrackVO) => {
    return await request.put({ url: `/datacenter/decision-exec-track/update`, data })
  },

  // 删除决策执行跟踪
  deleteDecisionExecTrack: async (id: number) => {
    return await request.delete({ url: `/datacenter/decision-exec-track/delete?id=` + id })
  },

  // 导出决策执行跟踪 Excel
  exportDecisionExecTrack: async (params) => {
    return await request.download({ url: `/datacenter/decision-exec-track/export-excel`, params })
  },
}