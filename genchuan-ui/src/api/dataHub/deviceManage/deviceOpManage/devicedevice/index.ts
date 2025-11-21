import request from '@/config/axios'

// 设备关联设备 VO
export interface DeviceDeviceVO {
  id: number // 主键ID
  deviceRelDeviceId: string // 关联ID
  mainDeviceId: string // 主设备ID
  mainDeviceName: string // 主设备名称
  mainDeviceCode: string // 主设备编码
  slaveDeviceId: string // 从设备ID
  slaveDeviceName: string // 从设备名称
  slaveDeviceCode: string // 从设备编码
  relType: string // 关联类型
  relTime: Date // 关联时间
  relUser: string // 关联人
  relStatus: string // 关联状态
  adjustTime: Date // 调整时间
  adjustUser: string // 调整人
  adjustReason: string // 调整原因
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备关联设备 API
export const DeviceDeviceApi = {
  // 查询设备关联设备分页
  getDeviceDevicePage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-device/page`, params })
  },

  // 查询设备关联设备详情
  getDeviceDevice: async (id: number) => {
    return await request.get({ url: `/datacenter/device-device/get?id=` + id })
  },

  // 新增设备关联设备
  createDeviceDevice: async (data: DeviceDeviceVO) => {
    return await request.post({ url: `/datacenter/device-device/create`, data })
  },

  // 修改设备关联设备
  updateDeviceDevice: async (data: DeviceDeviceVO) => {
    return await request.put({ url: `/datacenter/device-device/update`, data })
  },

  // 删除设备关联设备
  deleteDeviceDevice: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-device/delete?id=` + id })
  },

  // 导出设备关联设备 Excel
  exportDeviceDevice: async (params) => {
    return await request.download({ url: `/datacenter/device-device/export-excel`, params })
  }
}
