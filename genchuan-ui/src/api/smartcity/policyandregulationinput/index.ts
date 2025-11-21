import request from '@/config/axios'

// 政策法规录入 VO
export interface PolicyAndRegulationInputVO {
  id: number // 主键
  developingAgencies: string // 制定机关
  nameOfPolicyAndRegulation: string // 政策法规名称
  documentNumber: string // 文号
  releaseDate: Date // 发布日期
  effectiveDate: Date // 生效日期
  expiringDate: string // 失效日期
  regulatoryCategory: string // 法规类别
  isArea: string // 所属领域
  scopeOfApplication: string // 适用范围
  mainContent: string // 正文内容
}

// 政策法规录入 API
export const PolicyAndRegulationInputApi = {
  // 查询政策法规录入分页
  getPolicyAndRegulationInputPage: async (params: any) => {
    return await request.get({ url: `/smartcity/policy-and-regulation-input/page`, params })
  },

  // 查询政策法规录入详情
  getPolicyAndRegulationInput: async (id: number) => {
    return await request.get({ url: `/smartcity/policy-and-regulation-input/get?id=` + id })
  },

  // 新增政策法规录入
  createPolicyAndRegulationInput: async (data: PolicyAndRegulationInputVO) => {
    return await request.post({ url: `/smartcity/policy-and-regulation-input/create`, data })
  },

  // 修改政策法规录入
  updatePolicyAndRegulationInput: async (data: PolicyAndRegulationInputVO) => {
    return await request.put({ url: `/smartcity/policy-and-regulation-input/update`, data })
  },

  // 删除政策法规录入
  deletePolicyAndRegulationInput: async (id: number) => {
    return await request.delete({ url: `/smartcity/policy-and-regulation-input/delete?id=` + id })
  },

  // 导出政策法规录入 Excel
  exportPolicyAndRegulationInput: async (params) => {
    return await request.download({ url: `/smartcity/policy-and-regulation-input/export-excel`, params })
  }
}