import request from '@/config/axios'

// 设备客户端属性配置 VO
export interface DeviceClientAttrVO {
  id: number // 主键ID
  clientAttrCfgId: string // 属性配置ID
  deviceId: string // 设备ID
  deviceName: string // 设备名称
  deviceCode: string // 设备编码
  attrName: string // 属性名称
  attrCode: string // 属性代码
  attrValue: string // 属性值
  dataType: string // 数据类型
  isRequired: string // 是否必选
  attrDesc: string // 属性说明
  cfgTime: Date // 配置时间
  cfgUser: string // 配置人
  updateTimeSys: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备客户端属性配置 API
export const DeviceClientAttrApi = {
  // 查询设备客户端属性配置分页
  getDeviceClientAttrPage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-client-attr/page`, params })
  },

  // 查询设备客户端属性配置详情
  getDeviceClientAttr: async (id: number) => {
    return await request.get({ url: `/datacenter/device-client-attr/get?id=` + id })
  },

  // 新增设备客户端属性配置
  createDeviceClientAttr: async (data: DeviceClientAttrVO) => {
    return await request.post({ url: `/datacenter/device-client-attr/create`, data })
  },

  // 修改设备客户端属性配置
  updateDeviceClientAttr: async (data: DeviceClientAttrVO) => {
    return await request.put({ url: `/datacenter/device-client-attr/update`, data })
  },

  // 删除设备客户端属性配置
  deleteDeviceClientAttr: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-client-attr/delete?id=` + id })
  },

  // 导出设备客户端属性配置 Excel
  exportDeviceClientAttr: async (params) => {
    return await request.download({ url: `/datacenter/device-client-attr/export-excel`, params })
  }
}
