import request from '@/config/axios'

// 规则分类管理 VO
export interface EvalRuleCatVO {
  id: number // 主键ID
  ruleCatId: string // 规则分类ID
  ruleCatName: string // 规则分类名称
  ruleCatCode: string // 分类编码
  idxSystemId: string // 适用指标体系ID
  idxSystemName: string // 适用指标体系名称
  catDesc: string // 分类描述
  enableStatus: string // 启用状态
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 创建时间(系统)
  updateTimeSys: Date // 更新时间(系统)
}

// 规则分类管理 API
export const EvalRuleCatApi = {
  // 查询规则分类管理分页
  getEvalRuleCatPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-rule-cat/page`, params })
  },

  // 查询规则分类管理详情
  getEvalRuleCat: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-rule-cat/get?id=` + id })
  },

  // 新增规则分类管理
  createEvalRuleCat: async (data: EvalRuleCatVO) => {
    return await request.post({ url: `/datacenter/eval-rule-cat/create`, data })
  },

  // 修改规则分类管理
  updateEvalRuleCat: async (data: EvalRuleCatVO) => {
    return await request.put({ url: `/datacenter/eval-rule-cat/update`, data })
  },

  // 删除规则分类管理
  deleteEvalRuleCat: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-rule-cat/delete?id=` + id })
  },

  // 导出规则分类管理 Excel
  exportEvalRuleCat: async (params) => {
    return await request.download({ url: `/datacenter/eval-rule-cat/export-excel`, params })
  }
}
