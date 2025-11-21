import request from '@/config/axios'

// 设备空间数据 VO
export interface DeviceSpatialDataVO {
  id: number // 主键ID
  deviceSpatialId: string // 空间数据ID
  deviceId: string // 设备ID
  deviceName: string // 设备名称
  deviceCode: string // 设备编码
  coordSystem: string // 坐标系类型
  coordX: number // 坐标X(度)
  coordY: number // 坐标Y(度)
  elevation: number // 高程(米)
  positionAccuracy: number // 定位精度(米)
  spatialDataFormat: string // 空间数据格式
  dataSource: string // 数据来源
  inputTime: Date // 录入时间
  inputUser: string // 录入人
  updateTimeSys: Date // 更新时间
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备空间数据 API
export const DeviceSpatialDataApi = {
  // 查询设备空间数据分页
  getDeviceSpatialDataPage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-spatial-data/page`, params })
  },

  // 查询设备空间数据详情
  getDeviceSpatialData: async (id: number) => {
    return await request.get({ url: `/datacenter/device-spatial-data/get?id=` + id })
  },

  // 新增设备空间数据
  createDeviceSpatialData: async (data: DeviceSpatialDataVO) => {
    return await request.post({ url: `/datacenter/device-spatial-data/create`, data })
  },

  // 修改设备空间数据
  updateDeviceSpatialData: async (data: DeviceSpatialDataVO) => {
    return await request.put({ url: `/datacenter/device-spatial-data/update`, data })
  },

  // 删除设备空间数据
  deleteDeviceSpatialData: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-spatial-data/delete?id=` + id })
  },

  // 导出设备空间数据 Excel
  exportDeviceSpatialData: async (params) => {
    return await request.download({ url: `/datacenter/device-spatial-data/export-excel`, params })
  }
}
