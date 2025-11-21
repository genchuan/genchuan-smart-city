import request from '@/config/axios'

// 车辆异常 VO
export interface VehicleAbnormalityVO {
  id: number // 主键
  vehicleNumber: string // 车辆编号
  abnormalOccurrenceTime: Date // 异常发生时间
  abnormalLocation: string // 异常地点
  abnormalDescription: string // 异常描述
  vehicleStatus: string // 车辆状态
  degreeOfImpact: string // 影响程度
  handlingMeasures: string // 处理措施
  processingTime: Date // 处理时间
  processingPersonnel: string // 处理人员
  maintenanceCosts: string // 维修费用
}

// 车辆异常 API
export const VehicleAbnormalityApi = {
  // 查询车辆异常分页
  getVehicleAbnormalityPage: async (params: any) => {
    return await request.get({ url: `/smartcity/vehicle-abnormality/page`, params })
  },

  // 查询车辆异常详情
  getVehicleAbnormality: async (id: number) => {
    return await request.get({ url: `/smartcity/vehicle-abnormality/get?id=` + id })
  },

  // 新增车辆异常
  createVehicleAbnormality: async (data: VehicleAbnormalityVO) => {
    return await request.post({ url: `/smartcity/vehicle-abnormality/create`, data })
  },

  // 修改车辆异常
  updateVehicleAbnormality: async (data: VehicleAbnormalityVO) => {
    return await request.put({ url: `/smartcity/vehicle-abnormality/update`, data })
  },

  // 删除车辆异常
  deleteVehicleAbnormality: async (id: number) => {
    return await request.delete({ url: `/smartcity/vehicle-abnormality/delete?id=` + id })
  },

  // 导出车辆异常 Excel
  exportVehicleAbnormality: async (params) => {
    return await request.download({ url: `/smartcity/vehicle-abnormality/export-excel`, params })
  }
}