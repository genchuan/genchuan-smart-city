import request from '@/config/axios'

// 设备关联网格 VO
export interface DeviceGridVO {
  id: number // 主键ID
  deviceRelGridId: string // 关联ID
  deviceId: string // 设备ID
  deviceName: string // 设备名称
  deviceCode: string // 设备编码
  gridId: string // 网格ID
  gridName: string // 网格名称
  gridCode: string // 网格编码
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

// 设备关联网格 API
export const DeviceGridApi = {
  // 查询设备关联网格分页
  getDeviceGridPage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-grid/page`, params })
  },

  // 查询设备关联网格详情
  getDeviceGrid: async (id: number) => {
    return await request.get({ url: `/datacenter/device-grid/get?id=` + id })
  },

  // 新增设备关联网格
  createDeviceGrid: async (data: DeviceGridVO) => {
    return await request.post({ url: `/datacenter/device-grid/create`, data })
  },

  // 修改设备关联网格
  updateDeviceGrid: async (data: DeviceGridVO) => {
    return await request.put({ url: `/datacenter/device-grid/update`, data })
  },

  // 删除设备关联网格
  deleteDeviceGrid: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-grid/delete?id=` + id })
  },

  // 导出设备关联网格 Excel
  exportDeviceGrid: async (params) => {
    return await request.download({ url: `/datacenter/device-grid/export-excel`, params })
  }
}
