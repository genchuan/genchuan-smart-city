import request from '@/config/axios'

// 市政公用 VO
export interface MunicipalPublicUtilitiesVO {
  id: number // 主键
  nameMunicipalFacilities: string // 市政设施名称
  facilityType: string // 设施类型
  facilityLocation: string // 设施位置
  constructionTime: Date // 建设时间
  serviceLife: string // 使用年限
  bearingCapacity: string // 承载能力
  serviceScope: string // 服务范围
  servicePopulation: string // 服务人口
  resourceAllocation: string // 资源配置
  capitalInvestment: string // 资金投入
  budgetArrangement: string // 预算安排
  benefitsAssessment: string // 效益评估
  policyRegulatoryBasis: string // 政策法规依据
  industryStandardSpecifications: string // 行业标准规范
  potentialSafetyHazard: string // 安全隐患
  riskAssessment: string // 风险评估
  emergencyResponsePlan: string // 应急处置预案
}

// 市政公用 API
export const MunicipalPublicUtilitiesApi = {
  // 查询市政公用分页
  getMunicipalPublicUtilitiesPage: async (params: any) => {
    return await request.get({ url: `/smartcity/municipal-public-utilities/page`, params })
  },

  // 查询市政公用详情
  getMunicipalPublicUtilities: async (id: number) => {
    return await request.get({ url: `/smartcity/municipal-public-utilities/get?id=` + id })
  },

  // 新增市政公用
  createMunicipalPublicUtilities: async (data: MunicipalPublicUtilitiesVO) => {
    return await request.post({ url: `/smartcity/municipal-public-utilities/create`, data })
  },

  // 修改市政公用
  updateMunicipalPublicUtilities: async (data: MunicipalPublicUtilitiesVO) => {
    return await request.put({ url: `/smartcity/municipal-public-utilities/update`, data })
  },

  // 删除市政公用
  deleteMunicipalPublicUtilities: async (id: number) => {
    return await request.delete({ url: `/smartcity/municipal-public-utilities/delete?id=` + id })
  },

  // 导出市政公用 Excel
  exportMunicipalPublicUtilities: async (params) => {
    return await request.download({ url: `/smartcity/municipal-public-utilities/export-excel`, params })
  }
}