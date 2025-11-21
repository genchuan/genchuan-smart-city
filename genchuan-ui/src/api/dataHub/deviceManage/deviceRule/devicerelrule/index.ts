import request from '@/config/axios'

// 设备关联规则配置 VO
export interface DeviceRelRuleVO {
  id: number // 主键ID
  relRuleId: string // 关联规则ID
  deviceCatId: string // 设备分类ID
  deviceCatName: string // 设备分类名称
  relObjType: string // 关联对象类型
  relObjId: string // 关联对象ID
  relObjName: string // 关联对象名称
  isRequired: string // 关联必选标识
  relCkRule: string // 关联校验规则
  enableStatus: string // 启用状态
  createTimeSys: Date // 创建时间
  updateTimeSys: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备关联规则配置 API
export const DeviceRelRuleApi = {
  // 查询设备关联规则配置分页
  getDeviceRelRulePage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-rel-rule/page`, params })
  },

  // 查询设备关联规则配置详情
  getDeviceRelRule: async (id: number) => {
    return await request.get({ url: `/datacenter/device-rel-rule/get?id=` + id })
  },

  // 新增设备关联规则配置
  createDeviceRelRule: async (data: DeviceRelRuleVO) => {
    return await request.post({ url: `/datacenter/device-rel-rule/create`, data })
  },

  // 修改设备关联规则配置
  updateDeviceRelRule: async (data: DeviceRelRuleVO) => {
    return await request.put({ url: `/datacenter/device-rel-rule/update`, data })
  },

  // 删除设备关联规则配置
  deleteDeviceRelRule: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-rel-rule/delete?id=` + id })
  },

  // 导出设备关联规则配置 Excel
  exportDeviceRelRule: async (params) => {
    return await request.download({ url: `/datacenter/device-rel-rule/export-excel`, params })
  },
}
