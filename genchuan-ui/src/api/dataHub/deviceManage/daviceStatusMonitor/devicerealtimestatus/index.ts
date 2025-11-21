import request from '@/config/axios'

// 设备实时状态 VO
export interface DeviceRealTimeStatusVO {
  id: number // 主键ID
  realTimeStatusId: string // 状态ID
  deviceId: string // 设备ID
  deviceName: string // 设备名称
  deviceCode: string // 设备编码
  runStatus: string // 运行状态
  lastRptTime: Date // 最近上报时间
  realTimeParam1: string // 实时参数1
  realTimeParam2: string // 实时参数2
  statusUpdateTime: Date // 状态更新时间
  regionCode: string // 所属行政区划代码
  regionName: string // 所属行政区划名称
  gridId: string // 所属网格ID
  gridName: string // 所属网格名称
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备实时状态 API
export const DeviceRealTimeStatusApi = {
  // 查询设备实时状态分页
  getDeviceRealTimeStatusPage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-real-time-status/page`, params })
  },

  // 查询设备实时状态详情
  getDeviceRealTimeStatus: async (id: number) => {
    return await request.get({ url: `/datacenter/device-real-time-status/get?id=` + id })
  },

  // 新增设备实时状态
  createDeviceRealTimeStatus: async (data: DeviceRealTimeStatusVO) => {
    return await request.post({ url: `/datacenter/device-real-time-status/create`, data })
  },

  // 修改设备实时状态
  updateDeviceRealTimeStatus: async (data: DeviceRealTimeStatusVO) => {
    return await request.put({ url: `/datacenter/device-real-time-status/update`, data })
  },

  // 删除设备实时状态
  deleteDeviceRealTimeStatus: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-real-time-status/delete?id=` + id })
  },

  // 导出设备实时状态 Excel
  exportDeviceRealTimeStatus: async (params) => {
    return await request.download({
      url: `/datacenter/device-real-time-status/export-excel`,
      params
    })
  }
}
