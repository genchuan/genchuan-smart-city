import request from '@/config/axios'

// 设备共享属性配置 VO
export interface DeviceShareAttrVO {
  id: number // 主键ID
  shareAttrCfgId: string // 属性配置ID
  deviceId: string // 设备ID
  deviceName: string // 设备名称
  deviceCode: string // 设备编码
  attrName: string // 属性名称
  attrCode: string // 属性代码
  attrValue: string // 属性值
  dataType: string // 数据类型
  valueRange: string // 值域范围
  isRequired: string // 是否必选
  attrDesc: string // 属性说明
  mntDeptCode: string // 维护单位代码
  mntDeptName: string // 维护单位名称
  cfgTime: Date // 配置时间
  cfgUser: string // 配置人
  updateTimeSys: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备共享属性配置 API
export const DeviceShareAttrApi = {
  // 查询设备共享属性配置分页
  getDeviceShareAttrPage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-share-attr/page`, params })
  },

  // 查询设备共享属性配置详情
  getDeviceShareAttr: async (id: number) => {
    return await request.get({ url: `/datacenter/device-share-attr/get?id=` + id })
  },

  // 新增设备共享属性配置
  createDeviceShareAttr: async (data: DeviceShareAttrVO) => {
    return await request.post({ url: `/datacenter/device-share-attr/create`, data })
  },

  // 修改设备共享属性配置
  updateDeviceShareAttr: async (data: DeviceShareAttrVO) => {
    return await request.put({ url: `/datacenter/device-share-attr/update`, data })
  },

  // 删除设备共享属性配置
  deleteDeviceShareAttr: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-share-attr/delete?id=` + id })
  },

  // 导出设备共享属性配置 Excel
  exportDeviceShareAttr: async (params) => {
    return await request.download({ url: `/datacenter/device-share-attr/export-excel`, params })
  }
}
