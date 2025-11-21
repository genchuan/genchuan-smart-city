import request from '@/config/axios'

// 内涝安全 VO
export interface FloodSafetyVO {
  id: number // 主键
  monitoringPointName: string // 监测点名称
  locationOfMonitoringPoints: string // 监测点位置
  depthOfAccumulatedWater: string // 积水深度
  catchmentArea: string // 积水面积
  waterFlowVelocity: string // 水流速度
  trendOfWaterLevelChanges: string // 水位变化趋势
  rainfall: string // 降雨量
  rainfallIntensity: string // 降雨强度
  pipelineBlockageSituation: string // 管道堵塞情况
  pumpStationOperationStatus: string // 泵站运行状态
  equipmentFaultInformation: string // 设备故障信息
  warningLevel: string // 预警级别
  warningTime: Date // 预警时间

}

// 内涝安全 API
export const FloodSafetyApi = {
  // 查询内涝安全分页
  getFloodSafetyPage: async (params: any) => {
    return await request.get({ url: `/smartcity/flood-safety/page`, params })
  },

  // 查询内涝安全详情
  getFloodSafety: async (id: number) => {
    return await request.get({ url: `/smartcity/flood-safety/get?id=` + id })
  },

  // 新增内涝安全
  createFloodSafety: async (data: FloodSafetyVO) => {
    return await request.post({ url: `/smartcity/flood-safety/create`, data })
  },

  // 修改内涝安全
  updateFloodSafety: async (data: FloodSafetyVO) => {
    return await request.put({ url: `/smartcity/flood-safety/update`, data })
  },

  // 删除内涝安全
  deleteFloodSafety: async (id: number) => {
    return await request.delete({ url: `/smartcity/flood-safety/delete?id=` + id })
  },

  // 导出内涝安全 Excel
  exportFloodSafety: async (params) => {
    return await request.download({ url: `/smartcity/flood-safety/export-excel`, params })
  }
}