import request from '@/config/axios'

// 监测设备 VO
export interface MonitoringDeviceVO {
  id: number // 主键
  deviceNumber: string // 设备编号
  deviceName: string // 设备名称
  deviceType: string // 设备类型
  manufacturer: string // 生产厂家
  maintenanceRecord: string // 维护记录
  createTime: Date // 安装时间
}

// 监测设备 API
export const MonitoringDeviceApi = {
  // 查询监测设备分页
  getMonitoringDevicePage: async (params: any) => {
    return await request.get({ url: `/smartcity/monitoring-device/page`, params })
  },

  // 查询监测设备详情
  getMonitoringDevice: async (id: number) => {
    return await request.get({ url: `/smartcity/monitoring-device/get?id=` + id })
  },

  // 新增监测设备
  createMonitoringDevice: async (data: MonitoringDeviceVO) => {
    return await request.post({ url: `/smartcity/monitoring-device/create`, data })
  },

  // 修改监测设备
  updateMonitoringDevice: async (data: MonitoringDeviceVO) => {
    return await request.put({ url: `/smartcity/monitoring-device/update`, data })
  },

  // 删除监测设备
  deleteMonitoringDevice: async (id: number) => {
    return await request.delete({ url: `/smartcity/monitoring-device/delete?id=` + id })
  },

  // 导出监测设备 Excel
  exportMonitoringDevice: async (params) => {
    return await request.download({ url: `/smartcity/monitoring-device/export-excel`, params })
  }
}