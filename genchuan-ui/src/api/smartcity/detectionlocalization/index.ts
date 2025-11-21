import request from '@/config/axios'

// 故障检测与定位 VO
export interface DetectionLocalizationVO {
  id: number // 主键
  equipmentName: string // 设备名称
  equipmentNumber: string // 设备编号
  deviceLocation: string // 设备位置
  faultCode: string // 故障代码
  faultPhenomenon: string // 故障现象
  mtbf: Date // 故障时间
  faultType: string // 故障类型
  sensorData: string // 传感器数据
  scopeOfFaultImpact: string // 故障影响范围
  severityOfMalfunction: string // 故障严重程度
  locateInformation: string // 定位信息
  manuallyConfirmTheSituation: string // 人工确认情况
  maintenanceTime: Date // 维修时间
  repairResults: string // 维修结果
  restoreRunningTime: Date // 恢复运行时间
}

// 故障检测与定位 API
export const DetectionLocalizationApi = {
  // 查询故障检测与定位分页
  getDetectionLocalizationPage: async (params: any) => {
    return await request.get({ url: `/smartcity/detection-localization/page`, params })
  },

  // 查询故障检测与定位详情
  getDetectionLocalization: async (id: number) => {
    return await request.get({ url: `/smartcity/detection-localization/get?id=` + id })
  },

  // 新增故障检测与定位
  createDetectionLocalization: async (data: DetectionLocalizationVO) => {
    return await request.post({ url: `/smartcity/detection-localization/create`, data })
  },

  // 修改故障检测与定位
  updateDetectionLocalization: async (data: DetectionLocalizationVO) => {
    return await request.put({ url: `/smartcity/detection-localization/update`, data })
  },

  // 删除故障检测与定位
  deleteDetectionLocalization: async (id: number) => {
    return await request.delete({ url: `/smartcity/detection-localization/delete?id=` + id })
  },

  // 导出故障检测与定位 Excel
  exportDetectionLocalization: async (params) => {
    return await request.download({ url: `/smartcity/detection-localization/export-excel`, params })
  }
}