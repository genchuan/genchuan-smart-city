import request from '@/config/axios'

// 规则项管理 VO
export interface EvalRuleItemVO {
  id: number // 主键ID
  ruleItemId: string // 规则项ID
  ruleItemName: string // 规则项名称
  ruleItemCode: string // 规则项编码
  ruleCatId: string // 所属规则分类ID
  ruleCatName: string // 所属规则分类名称
  idxItemId: string // 关联指标项ID
  idxItemName: string // 关联指标项名称
  scoreLogic: string // 评分逻辑
  fullScore: number // 满分值
  ruleType: string // 规则类型
  ruleDesc: string // 规则描述
  enableStatus: string // 启用状态
  createUser: string // 创建人
  createTimeSys: Date // 创建时间
  updateUser: string // 更新人
  updateTimeSys: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 规则项管理 API
export const EvalRuleItemApi = {
  // 查询规则项管理分页
  getEvalRuleItemPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-rule-item/page`, params })
  },

  // 查询规则项管理详情
  getEvalRuleItem: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-rule-item/get?id=` + id })
  },

  // 新增规则项管理
  createEvalRuleItem: async (data: EvalRuleItemVO) => {
    return await request.post({ url: `/datacenter/eval-rule-item/create`, data })
  },

  // 修改规则项管理
  updateEvalRuleItem: async (data: EvalRuleItemVO) => {
    return await request.put({ url: `/datacenter/eval-rule-item/update`, data })
  },

  // 删除规则项管理
  deleteEvalRuleItem: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-rule-item/delete?id=` + id })
  },

  // 导出规则项管理 Excel
  exportEvalRuleItem: async (params) => {
    return await request.download({ url: `/datacenter/eval-rule-item/export-excel`, params })
  }
}
