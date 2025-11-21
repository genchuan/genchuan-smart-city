import request from '@/config/axios'

// 设备属性规则配置 VO
export interface DeviceAttrRuleVO {
  id: number // 主键ID
  attrRuleId: string // 属性规则ID
  deviceCatId: string // 设备分类ID
  deviceCatName: string // 设备分类名称
  attrName: string // 属性名称
  attrCode: string // 属性代码
  dataType: string // 数据类型
  fieldLength: number // 字段长度
  isRequired: string // 是否必选
  unit: string // 计量单位
  valueRange: string // 值域范围
  defaultValue: string // 默认值
  attrDesc: string // 属性说明
  enableStatus: string // 启用状态
  createTimeSys: Date // 创建时间
  updateTimeSys: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备属性规则配置 API
export const DeviceAttrRuleApi = {
  // 查询设备属性规则配置分页
  getDeviceAttrRulePage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-attr-rule/page`, params })
  },

  // 查询设备属性规则配置详情
  getDeviceAttrRule: async (id: number) => {
    return await request.get({ url: `/datacenter/device-attr-rule/get?id=` + id })
  },

  // 新增设备属性规则配置
  createDeviceAttrRule: async (data: DeviceAttrRuleVO) => {
    return await request.post({ url: `/datacenter/device-attr-rule/create`, data })
  },

  // 修改设备属性规则配置
  updateDeviceAttrRule: async (data: DeviceAttrRuleVO) => {
    return await request.put({ url: `/datacenter/device-attr-rule/update`, data })
  },

  // 删除设备属性规则配置
  deleteDeviceAttrRule: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-attr-rule/delete?id=` + id })
  },

  // 导出设备属性规则配置 Excel
  exportDeviceAttrRule: async (params) => {
    return await request.download({ url: `/datacenter/device-attr-rule/export-excel`, params })
  },
}
