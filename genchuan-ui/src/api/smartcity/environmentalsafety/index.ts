import request from '@/config/axios'

// 环卫设施安全 VO
export interface EnvironmentalSafetyVO {
  id: number // 主键
  facilityName: string // 设施名称
  facilityType: string // 设施类型
  facilityLocation: string // 设施位置
  facilityNumber: string // 设施编号
  runningState: string // 运行状态
  faultCode: string // 故障代码
  maintenanceRecord: string // 维修记录
  maintenanceTime: string // 维修时间
  odorConcentration: string // 异味浓度
  harmfulGasContent: string // 有害气体含量
  disinfectionRecord: string // 消毒记录
  flyAndInsectDensity: string // 蝇虫密度
  facilityUsageFrequency: string // 设施使用频率
  complaintRecord: string // 投诉记录
}

// 环卫设施安全 API
export const EnvironmentalSafetyApi = {
  // 查询环卫设施安全分页
  getEnvironmentalSafetyPage: async (params: any) => {
    return await request.get({ url: `/smartcity/environmental-safety/page`, params })
  },

  // 查询环卫设施安全详情
  getEnvironmentalSafety: async (id: number) => {
    return await request.get({ url: `/smartcity/environmental-safety/get?id=` + id })
  },

  // 新增环卫设施安全
  createEnvironmentalSafety: async (data: EnvironmentalSafetyVO) => {
    return await request.post({ url: `/smartcity/environmental-safety/create`, data })
  },

  // 修改环卫设施安全
  updateEnvironmentalSafety: async (data: EnvironmentalSafetyVO) => {
    return await request.put({ url: `/smartcity/environmental-safety/update`, data })
  },

  // 删除环卫设施安全
  deleteEnvironmentalSafety: async (id: number) => {
    return await request.delete({ url: `/smartcity/environmental-safety/delete?id=` + id })
  },

  // 导出环卫设施安全 Excel
  exportEnvironmentalSafety: async (params) => {
    return await request.download({ url: `/smartcity/environmental-safety/export-excel`, params })
  }
}