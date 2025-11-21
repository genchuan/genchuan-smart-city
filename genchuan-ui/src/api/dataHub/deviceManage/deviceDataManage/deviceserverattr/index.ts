import request from '@/config/axios'

// 设备服务器属性配置 VO
export interface DeviceServerAttrVO {
  id: number // 主键ID
  serverAttrCfgId: string // 属性配置ID
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

// 设备服务器属性配置 API
export const DeviceServerAttrApi = {
  // 查询设备服务器属性配置分页
  getDeviceServerAttrPage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-server-attr/page`, params })
  },

  // 查询设备服务器属性配置详情
  getDeviceServerAttr: async (id: number) => {
    return await request.get({ url: `/datacenter/device-server-attr/get?id=` + id })
  },

  // 新增设备服务器属性配置
  createDeviceServerAttr: async (data: DeviceServerAttrVO) => {
    return await request.post({ url: `/datacenter/device-server-attr/create`, data })
  },

  // 修改设备服务器属性配置
  updateDeviceServerAttr: async (data: DeviceServerAttrVO) => {
    return await request.put({ url: `/datacenter/device-server-attr/update`, data })
  },

  // 删除设备服务器属性配置
  deleteDeviceServerAttr: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-server-attr/delete?id=` + id })
  },

  // 导出设备服务器属性配置 Excel
  exportDeviceServerAttr: async (params) => {
    return await request.download({ url: `/datacenter/device-server-attr/export-excel`, params })
  }
}
