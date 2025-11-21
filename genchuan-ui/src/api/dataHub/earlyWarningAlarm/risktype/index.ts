import request from '@/config/axios'

// 风险类型库管理 VO
export interface RiskTypeVO {
  id: number // 主键ID
  riskTypeId: string // 风险类型ID
  riskTypeName: string // 风险类型名称
  riskCode: string // 风险编码
  domainId: string // 所属分域ID
  domainName: string // 所属分域名称
  riskDesc: string // 风险描述
  responseMeasures: string // 应对措施
  enableStatus: string // 启用状态
}

// 风险类型库管理 API
export const RiskTypeApi = {
  // 查询风险类型库管理分页
  getRiskTypePage: async (params: any) => {
    return await request.get({ url: `/datacenter/risk-type/page`, params })
  },

  // 查询风险类型库管理详情
  getRiskType: async (id: number) => {
    return await request.get({ url: `/datacenter/risk-type/get?id=` + id })
  },

  // 新增风险类型库管理
  createRiskType: async (data: RiskTypeVO) => {
    return await request.post({ url: `/datacenter/risk-type/create`, data })
  },

  // 修改风险类型库管理
  updateRiskType: async (data: RiskTypeVO) => {
    return await request.put({ url: `/datacenter/risk-type/update`, data })
  },

  // 删除风险类型库管理
  deleteRiskType: async (id: number) => {
    return await request.delete({ url: `/datacenter/risk-type/delete?id=` + id })
  },

  // 导出风险类型库管理 Excel
  exportRiskType: async (params) => {
    return await request.download({ url: `/datacenter/risk-type/export-excel`, params })
  },
}
