import request from '@/config/axios'

// 风险源档案 VO
export interface RiskSourceArchVO {
  id: number // 主键
  riskSourceId: string // 风险源ID
  riskSourceNo: string // 风险源编号
  riskSourceName: string // 风险源名称
  riskSourceType: string // 风险源类型
  riskLevel: string // 风险等级
  regionCode: string // 所在行政区划代码
  regionName: string // 所在行政区划名称
  locationDesc: string // 所在位置描述
  assetId: string // 关联资产ID
  assetName: string // 关联资产名称
  deptId: string // 责任部门ID
  deptName: string // 责任部门名称
  historyWarnCount: number // 历史预警次数
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 风险源档案 API
export const RiskSourceArchApi = {
  // 查询风险源档案分页
  getRiskSourceArchPage: async (params: any) => {
    return await request.get({ url: `/datacenter/risk-source-arch/page`, params })
  },

  // 查询风险源档案详情
  getRiskSourceArch: async (id: number) => {
    return await request.get({ url: `/datacenter/risk-source-arch/get?id=` + id })
  },

  // 新增风险源档案
  createRiskSourceArch: async (data: RiskSourceArchVO) => {
    return await request.post({ url: `/datacenter/risk-source-arch/create`, data })
  },

  // 修改风险源档案
  updateRiskSourceArch: async (data: RiskSourceArchVO) => {
    return await request.put({ url: `/datacenter/risk-source-arch/update`, data })
  },

  // 删除风险源档案
  deleteRiskSourceArch: async (id: number) => {
    return await request.delete({ url: `/datacenter/risk-source-arch/delete?id=` + id })
  },

  // 导出风险源档案 Excel
  exportRiskSourceArch: async (params) => {
    return await request.download({ url: `/datacenter/risk-source-arch/export-excel`, params })
  }
}
