import request from '@/config/axios'

// 关联风险识别 VO
export interface RelatedRiskIdentVO {
  id: number // 主键
  identId: string // 识别ID
  triggerWarnId: string // 触发预警ID
  triggerWarnName: string // 触发预警名称
  triggerWarnType: string // 触发预警类型
  relatedRiskName: string // 关联风险名称
  relatedRiskType: string // 关联风险类型
  relatedBasis: string // 关联依据
  riskLevel: string // 风险等级
  identTime: Date // 识别时间
  identMethod: string // 识别方式
  identifyUserId: string // 识别用户ID
  identifyUserName: string // 识别用户姓名
  isHandled: string // 是否已处置
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 关联风险识别 API
export const RelatedRiskIdentApi = {
  // 查询关联风险识别分页
  getRelatedRiskIdentPage: async (params: any) => {
    return await request.get({ url: `/datacenter/related-risk-ident/page`, params })
  },

  // 查询关联风险识别详情
  getRelatedRiskIdent: async (id: number) => {
    return await request.get({ url: `/datacenter/related-risk-ident/get?id=` + id })
  },

  // 新增关联风险识别
  createRelatedRiskIdent: async (data: RelatedRiskIdentVO) => {
    return await request.post({ url: `/datacenter/related-risk-ident/create`, data })
  },

  // 修改关联风险识别
  updateRelatedRiskIdent: async (data: RelatedRiskIdentVO) => {
    return await request.put({ url: `/datacenter/related-risk-ident/update`, data })
  },

  // 删除关联风险识别
  deleteRelatedRiskIdent: async (id: number) => {
    return await request.delete({ url: `/datacenter/related-risk-ident/delete?id=` + id })
  },

  // 导出关联风险识别 Excel
  exportRelatedRiskIdent: async (params) => {
    return await request.download({ url: `/datacenter/related-risk-ident/export-excel`, params })
  }
}
