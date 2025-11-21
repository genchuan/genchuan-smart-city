import request from '@/config/axios'

// 设备遥测数据 VO
export interface DeviceTelemetryDataVO {
  id: number // 主键ID
  telemetryDataId: string // 遥测数据ID
  deviceId: string // 设备ID
  deviceName: string // 设备名称
  deviceCode: string // 设备编码
  telemetryItemName: string // 遥测项名称
  telemetryItemCode: string // 遥测项代码
  telemetryValue: string // 遥测值
  dataType: string // 数据类型
  collectTime: Date // 采集时间
  dataSource: string // 数据来源
  storageCycle: number // 存储周期(天)
  expireStatus: string // 过期状态
  createTimeSys: Date // 创建时间
  updateTimeSys: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备遥测数据 API
export const DeviceTelemetryDataApi = {
  // 查询设备遥测数据分页
  getDeviceTelemetryDataPage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-telemetry-data/page`, params })
  },

  // 查询设备遥测数据详情
  getDeviceTelemetryData: async (id: number) => {
    return await request.get({ url: `/datacenter/device-telemetry-data/get?id=` + id })
  },

  // 新增设备遥测数据
  createDeviceTelemetryData: async (data: DeviceTelemetryDataVO) => {
    return await request.post({ url: `/datacenter/device-telemetry-data/create`, data })
  },

  // 修改设备遥测数据
  updateDeviceTelemetryData: async (data: DeviceTelemetryDataVO) => {
    return await request.put({ url: `/datacenter/device-telemetry-data/update`, data })
  },

  // 删除设备遥测数据
  deleteDeviceTelemetryData: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-telemetry-data/delete?id=` + id })
  },

  // 导出设备遥测数据 Excel
  exportDeviceTelemetryData: async (params) => {
    return await request.download({ url: `/datacenter/device-telemetry-data/export-excel`, params })
  }
}
