import request from '@/config/axios'

// 市容环卫 VO
export interface EnvironmentalSanitationVO {
  id: number // 主键
  facilityName: string // 设施名称
  facilityLocation: string // 设施位置
  quantity: string // 数量
  constructionTime: Date // 建设时间
  serviceLife: string // 使用年限
  processingCapacity: string // 处理能力
  maintenanceRequirements: string // 维护需求
  annualBudgetAmount: string // 年度预算金额
  costBenefitAnalysis: string // 成本效益分析
  assessmentSurEnvironment: string // 对周边环境影响评估
  energyConservationTargets: string // 节能减排指标
}

// 市容环卫 API
export const EnvironmentalSanitationApi = {
  // 查询市容环卫分页
  getEnvironmentalSanitationPage: async (params: any) => {
    return await request.get({ url: `/smartcity/environmental-sanitation/page`, params })
  },

  // 查询市容环卫详情
  getEnvironmentalSanitation: async (id: number) => {
    return await request.get({ url: `/smartcity/environmental-sanitation/get?id=` + id })
  },

  // 新增市容环卫
  createEnvironmentalSanitation: async (data: EnvironmentalSanitationVO) => {
    return await request.post({ url: `/smartcity/environmental-sanitation/create`, data })
  },

  // 修改市容环卫
  updateEnvironmentalSanitation: async (data: EnvironmentalSanitationVO) => {
    return await request.put({ url: `/smartcity/environmental-sanitation/update`, data })
  },

  // 删除市容环卫
  deleteEnvironmentalSanitation: async (id: number) => {
    return await request.delete({ url: `/smartcity/environmental-sanitation/delete?id=` + id })
  },

  // 导出市容环卫 Excel
  exportEnvironmentalSanitation: async (params) => {
    return await request.download({ url: `/smartcity/environmental-sanitation/export-excel`, params })
  }
}