import request from '@/config/axios'

// 设备关联资产 VO
export interface DeviceAssetVO {
  id: number // 主键ID
  deviceRelAssetId: string // 关联ID
  deviceId: string // 设备ID
  deviceName: string // 设备名称
  deviceCode: string // 设备编码
  assetId: string // 资产ID
  assetName: string // 资产名称
  assetCode: string // 资产编码
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

// 设备关联资产 API
export const DeviceAssetApi = {
  // 查询设备关联资产分页
  getDeviceAssetPage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-asset/page`, params })
  },

  // 查询设备关联资产详情
  getDeviceAsset: async (id: number) => {
    return await request.get({ url: `/datacenter/device-asset/get?id=` + id })
  },

  // 新增设备关联资产
  createDeviceAsset: async (data: DeviceAssetVO) => {
    return await request.post({ url: `/datacenter/device-asset/create`, data })
  },

  // 修改设备关联资产
  updateDeviceAsset: async (data: DeviceAssetVO) => {
    return await request.put({ url: `/datacenter/device-asset/update`, data })
  },

  // 删除设备关联资产
  deleteDeviceAsset: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-asset/delete?id=` + id })
  },

  // 导出设备关联资产 Excel
  exportDeviceAsset: async (params) => {
    return await request.download({ url: `/datacenter/device-asset/export-excel`, params })
  }
}
