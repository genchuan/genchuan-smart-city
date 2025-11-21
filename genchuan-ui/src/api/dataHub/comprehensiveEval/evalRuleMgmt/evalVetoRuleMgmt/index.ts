import request from '@/config/axios'

// 否决项规则管理 VO
export interface EvalVetoRuleVO {
  id: number // 主键ID
  vetoRuleId: string // 否决项ID
  vetoRuleName: string // 否决项名称
  vetoRuleCode: string // 否决项编码
  applyObjectType: string // 适用对象类型
  vetoCondition: string // 否决条件
  idxItemId: string // 关联指标项ID
  idxItemName: string // 关联指标项名称
  idxThreshold: string // 指标阈值
  validCycle: string // 生效周期
  vetoResult: string // 否决结果
  vetoDesc: string // 否决描述
  enableStatus: string // 启用状态
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  updateUserBiz: string // 更新人(业务)
  updateTimeBiz: Date // 更新时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 否决项规则管理 API
export const EvalVetoRuleApi = {
  // 查询否决项规则管理分页
  getEvalVetoRulePage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-veto-rule/page`, params })
  },

  // 查询否决项规则管理详情
  getEvalVetoRule: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-veto-rule/get?id=` + id })
  },

  // 新增否决项规则管理
  createEvalVetoRule: async (data: EvalVetoRuleVO) => {
    return await request.post({ url: `/datacenter/eval-veto-rule/create`, data })
  },

  // 修改否决项规则管理
  updateEvalVetoRule: async (data: EvalVetoRuleVO) => {
    return await request.put({ url: `/datacenter/eval-veto-rule/update`, data })
  },

  // 删除否决项规则管理
  deleteEvalVetoRule: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-veto-rule/delete?id=` + id })
  },

  // 导出否决项规则管理 Excel
  exportEvalVetoRule: async (params) => {
    return await request.download({ url: `/datacenter/eval-veto-rule/export-excel`, params })
  }
}
