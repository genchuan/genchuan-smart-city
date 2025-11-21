import request from '@/config/axios'

// 设备关联行政区划 VO
export interface DeviceAreaVO {
  id: number // 主键ID
  deviceRelRegionId: string // 关联ID
  deviceId: string // 设备ID
  deviceName: string // 设备名称
  deviceCode: string // 设备编码
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  regionLevel: string // 行政区划级别
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

// 设备关联行政区划 API
export const DeviceAreaApi = {
  // 查询设备关联行政区划分页
  getDeviceAreaPage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-area/page`, params })
  },

  // 查询设备关联行政区划详情
  getDeviceArea: async (id: number) => {
    return await request.get({ url: `/datacenter/device-area/get?id=` + id })
  },

  // 新增设备关联行政区划
  createDeviceArea: async (data: DeviceAreaVO) => {
    return await request.post({ url: `/datacenter/device-area/create`, data })
  },

  // 修改设备关联行政区划
  updateDeviceArea: async (data: DeviceAreaVO) => {
    return await request.put({ url: `/datacenter/device-area/update`, data })
  },

  // 删除设备关联行政区划
  deleteDeviceArea: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-area/delete?id=` + id })
  },

  // 导出设备关联行政区划 Excel
  exportDeviceArea: async (params) => {
    return await request.download({ url: `/datacenter/device-area/export-excel`, params })
  }
}
