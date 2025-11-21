import request from '@/config/axios'

// 救援设备信息 VO
export interface EmerDeviceInfoVO {
  id: number // 自增主键ID
  deviceId: string // 设备ID
  deviceCode: string // 设备编码
  deviceName: string // 设备名称
  catId: string // 分类ID
  catName: string // 分类名称
  applyRegionCode: string // 适用区域代码
  applyRegionName: string // 适用区域名称
  deviceModel: string // 设备型号
  deviceCount: number // 设备数量
  availableDeviceCount: number // 可用数量
  deviceStatus: string // 设备状态
  createUser: string // 创建人
  updateUser: string // 更新人
}

// 救援设备信息 API
export const EmerDeviceInfoApi = {
  // 查询救援设备信息分页
  getEmerDeviceInfoPage: async (params: any) => {
    return await request.get({ url: `/datacenter/emer-device-info/page`, params })
  },

  // 查询救援设备信息详情
  getEmerDeviceInfo: async (id: number) => {
    return await request.get({ url: `/datacenter/emer-device-info/get?id=` + id })
  },

  // 新增救援设备信息
  createEmerDeviceInfo: async (data: EmerDeviceInfoVO) => {
    return await request.post({ url: `/datacenter/emer-device-info/create`, data })
  },

  // 修改救援设备信息
  updateEmerDeviceInfo: async (data: EmerDeviceInfoVO) => {
    return await request.put({ url: `/datacenter/emer-device-info/update`, data })
  },

  // 删除救援设备信息
  deleteEmerDeviceInfo: async (id: number) => {
    return await request.delete({ url: `/datacenter/emer-device-info/delete?id=` + id })
  },

  // 导出救援设备信息 Excel
  exportEmerDeviceInfo: async (params) => {
    return await request.download({ url: `/datacenter/emer-device-info/export-excel`, params })
  },
}