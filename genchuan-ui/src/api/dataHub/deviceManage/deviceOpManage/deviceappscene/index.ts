import request from '@/config/axios'

// 设备关联应用场景 VO
export interface DeviceAppSceneVO {
  id: number // 主键ID
  deviceRelAppSceneId: string // 关联ID
  deviceId: string // 设备ID
  deviceName: string // 设备名称
  deviceCode: string // 设备编码
  appSceneId: string // 应用场景ID
  appSceneName: string // 应用场景名称
  appSceneCode: string // 应用场景编码
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

// 设备关联应用场景 API
export const DeviceAppSceneApi = {
  // 查询设备关联应用场景分页
  getDeviceAppScenePage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-app-scene/page`, params })
  },

  // 查询设备关联应用场景详情
  getDeviceAppScene: async (id: number) => {
    return await request.get({ url: `/datacenter/device-app-scene/get?id=` + id })
  },

  // 新增设备关联应用场景
  createDeviceAppScene: async (data: DeviceAppSceneVO) => {
    return await request.post({ url: `/datacenter/device-app-scene/create`, data })
  },

  // 修改设备关联应用场景
  updateDeviceAppScene: async (data: DeviceAppSceneVO) => {
    return await request.put({ url: `/datacenter/device-app-scene/update`, data })
  },

  // 删除设备关联应用场景
  deleteDeviceAppScene: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-app-scene/delete?id=` + id })
  },

  // 导出设备关联应用场景 Excel
  exportDeviceAppScene: async (params) => {
    return await request.download({ url: `/datacenter/device-app-scene/export-excel`, params })
  }
}
