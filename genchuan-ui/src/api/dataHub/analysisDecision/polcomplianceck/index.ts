import request from '@/config/axios'

// 政策合规校验 VO
export interface PolComplianceCkVO {
  id: number // 主键ID
  complianceCkId: string // 校验ID
  relSchemeId: string // 关联方案ID
  relSchemeName: string // 关联方案名称
  ckPolIds: string // 校验政策IDs
  ckPolNames: string // 校验政策名称
  ckStatus: string // 校验状态
  riskLevel: string // 风险等级
  riskPtCount: number // 风险点数量
  complianceRate: number // 合规率
  ckConclusion: string // 校验结论
  ckUser: string // 校验人
  ckTime: Date // 校验时间
  rectificationSugg: string // 整改建议
  reviewStatus: string // 复核状态
  reviewUser: string // 复核人
  reviewTime: Date // 复核时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 政策合规校验 API
export const PolComplianceCkApi = {
  // 查询政策合规校验分页
  getPolComplianceCkPage: async (params: any) => {
    return await request.get({ url: `/datacenter/pol-compliance-ck/page`, params })
  },

  // 查询政策合规校验详情
  getPolComplianceCk: async (id: number) => {
    return await request.get({ url: `/datacenter/pol-compliance-ck/get?id=` + id })
  },

  // 新增政策合规校验
  createPolComplianceCk: async (data: PolComplianceCkVO) => {
    return await request.post({ url: `/datacenter/pol-compliance-ck/create`, data })
  },

  // 修改政策合规校验
  updatePolComplianceCk: async (data: PolComplianceCkVO) => {
    return await request.put({ url: `/datacenter/pol-compliance-ck/update`, data })
  },

  // 删除政策合规校验
  deletePolComplianceCk: async (id: number) => {
    return await request.delete({ url: `/datacenter/pol-compliance-ck/delete?id=` + id })
  },

  // 导出政策合规校验 Excel
  exportPolComplianceCk: async (params) => {
    return await request.download({ url: `/datacenter/pol-compliance-ck/export-excel`, params })
  },
}