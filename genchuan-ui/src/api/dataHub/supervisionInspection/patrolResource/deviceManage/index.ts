import request from '@/config/axios'

// 巡查巡检设备信息 VO
export interface InspectDeviceInfoVO {
  id: number // 主键ID
  deviceId: string // 设备ID
  deviceCode: string // 设备编码
  deviceName: string // 设备名称
  deviceType: string // 设备类型
  areaCode: string // 所属区域代码
  areaName: string // 所属区域名称
  gridId: string // 所属网格ID
  gridName: string // 所属网格名称
  personId: string // 关联人员ID
  personName: string // 关联人员姓名
  deviceStatus: string // 设备状态
  installTime: Date // 安装时间
  warrantyEndTime: Date // 保修截止时间
  createUser: string // 创建人
  bizCreateTime: Date // 创建时间
  updateUser: string // 更新人
  bizUpdateTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检设备信息 API
export const InspectDeviceInfoApi = {
  // 查询巡查巡检设备信息分页
  getInspectDeviceInfoPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-device-info/page`, params })
  },

  // 查询巡查巡检设备信息详情
  getInspectDeviceInfo: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-device-info/get?id=` + id })
  },

  // 新增巡查巡检设备信息
  createInspectDeviceInfo: async (data: InspectDeviceInfoVO) => {
    return await request.post({ url: `/datacenter/inspect-device-info/create`, data })
  },

  // 修改巡查巡检设备信息
  updateInspectDeviceInfo: async (data: InspectDeviceInfoVO) => {
    return await request.put({ url: `/datacenter/inspect-device-info/update`, data })
  },

  // 删除巡查巡检设备信息
  deleteInspectDeviceInfo: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-device-info/delete?id=` + id })
  },

  // 导出巡查巡检设备信息 Excel
  exportInspectDeviceInfo: async (params) => {
    return await request.download({ url: `/datacenter/inspect-device-info/export-excel`, params })
  }
}
