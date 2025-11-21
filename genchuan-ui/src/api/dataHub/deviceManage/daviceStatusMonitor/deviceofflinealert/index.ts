import request from '@/config/axios'

// 设备离线预警 VO
export interface DeviceOfflineAlertVO {
  id: number // 主键ID
  offlineAlertId: string // 预警ID
  deviceId: string // 设备ID
  deviceName: string // 设备名称
  deviceCode: string // 设备编码
  offlineStartTime: Date // 离线开始时间
  offlineEndure: number // 离线时长(分钟)
  alertLevel: string // 预警级别
  alertUserId: string // 预警接收人ID
  alertUserName: string // 预警接收人姓名
  alertPushTime: Date // 预警推送时间
  alertStatus: string // 预警状态
  handleUserId: string // 处理人ID
  handleUserName: string // 处理人姓名
  handleTime: Date // 处理时间
  handleResult: string // 处理结果
  regionCode: string // 所属行政区划代码
  regionName: string // 所属行政区划名称
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备离线预警 API
export const DeviceOfflineAlertApi = {
  // 查询设备离线预警分页
  getDeviceOfflineAlertPage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-offline-alert/page`, params })
  },

  // 查询设备离线预警详情
  getDeviceOfflineAlert: async (id: number) => {
    return await request.get({ url: `/datacenter/device-offline-alert/get?id=` + id })
  },

  // 新增设备离线预警
  createDeviceOfflineAlert: async (data: DeviceOfflineAlertVO) => {
    return await request.post({ url: `/datacenter/device-offline-alert/create`, data })
  },

  // 修改设备离线预警
  updateDeviceOfflineAlert: async (data: DeviceOfflineAlertVO) => {
    return await request.put({ url: `/datacenter/device-offline-alert/update`, data })
  },

  // 删除设备离线预警
  deleteDeviceOfflineAlert: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-offline-alert/delete?id=` + id })
  },

  // 导出设备离线预警 Excel
  exportDeviceOfflineAlert: async (params) => {
    return await request.download({ url: `/datacenter/device-offline-alert/export-excel`, params })
  }
}
