import request from '@/config/axios'

// 设备关联监测部件 VO
export interface DeviceMonCompVO {
  id: number // 主键ID
  deviceRelMonCompId: string // 关联ID
  deviceId: string // 设备ID
  deviceName: string // 设备名称
  deviceCode: string // 设备编码
  monCompId: string // 监测部件ID
  monCompName: string // 监测部件名称
  monCompCode: string // 监测部件编码
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

// 设备关联监测部件 API
export const DeviceMonCompApi = {
  // 查询设备关联监测部件分页
  getDeviceMonCompPage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-mon-comp/page`, params })
  },

  // 查询设备关联监测部件详情
  getDeviceMonComp: async (id: number) => {
    return await request.get({ url: `/datacenter/device-mon-comp/get?id=` + id })
  },

  // 新增设备关联监测部件
  createDeviceMonComp: async (data: DeviceMonCompVO) => {
    return await request.post({ url: `/datacenter/device-mon-comp/create`, data })
  },

  // 修改设备关联监测部件
  updateDeviceMonComp: async (data: DeviceMonCompVO) => {
    return await request.put({ url: `/datacenter/device-mon-comp/update`, data })
  },

  // 删除设备关联监测部件
  deleteDeviceMonComp: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-mon-comp/delete?id=` + id })
  },

  // 导出设备关联监测部件 Excel
  exportDeviceMonComp: async (params) => {
    return await request.download({ url: `/datacenter/device-mon-comp/export-excel`, params })
  }
}
