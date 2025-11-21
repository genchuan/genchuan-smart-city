import request from '@/config/axios'

// 排水安全 VO
export interface DrainageSafetyVO {
  id: number // 主键
  warningNumber: string // 预警编号
  warningTime: string // 预警时间
  warningLevel: string // 预警级别
  warningArea: string // 预警区域
  monitoringPoints: string // 监测点位
  realTimeWaterLevel: string // 实时水位
}

// 排水安全 API
export const DrainageSafetyApi = {
  // 查询排水安全分页
  getDrainageSafetyPage: async (params: any) => {
    return await request.get({ url: `/smartcity/drainage-safety/page`, params })
  },

  // 查询排水安全详情
  getDrainageSafety: async (id: number) => {
    return await request.get({ url: `/smartcity/drainage-safety/get?id=` + id })
  },

  // 新增排水安全
  createDrainageSafety: async (data: DrainageSafetyVO) => {
    return await request.post({ url: `/smartcity/drainage-safety/create`, data })
  },

  // 修改排水安全
  updateDrainageSafety: async (data: DrainageSafetyVO) => {
    return await request.put({ url: `/smartcity/drainage-safety/update`, data })
  },

  // 删除排水安全
  deleteDrainageSafety: async (id: number) => {
    return await request.delete({ url: `/smartcity/drainage-safety/delete?id=` + id })
  },

  // 导出排水安全 Excel
  exportDrainageSafety: async (params) => {
    return await request.download({ url: `/smartcity/drainage-safety/export-excel`, params })
  }
}