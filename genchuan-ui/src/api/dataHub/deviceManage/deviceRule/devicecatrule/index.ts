import request from '@/config/axios'

// 设备分类规则配置 VO
export interface DeviceCatRuleVO {
  id: number // 主键ID
  catRuleId: string // 分类规则ID
  parentCatRuleId: string // 父类规则ID
  catLevel: string // 分类层级
  catCode: string // 分类代码
  catName: string // 分类名称
  catDesc: string // 分类说明
  enableStatus: string // 启用状态
  createTimeSys: Date // 创建时间
  updateTimeSys: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备分类规则配置 API
export const DeviceCatRuleApi = {
  // 查询设备分类规则配置分页
  getDeviceCatRulePage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-cat-rule/page`, params })
  },

  // 查询设备分类规则配置详情
  getDeviceCatRule: async (id: number) => {
    return await request.get({ url: `/datacenter/device-cat-rule/get?id=` + id })
  },

  // 新增设备分类规则配置
  createDeviceCatRule: async (data: DeviceCatRuleVO) => {
    return await request.post({ url: `/datacenter/device-cat-rule/create`, data })
  },

  // 修改设备分类规则配置
  updateDeviceCatRule: async (data: DeviceCatRuleVO) => {
    return await request.put({ url: `/datacenter/device-cat-rule/update`, data })
  },

  // 删除设备分类规则配置
  deleteDeviceCatRule: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-cat-rule/delete?id=` + id })
  },

  // 导出设备分类规则配置 Excel
  exportDeviceCatRule: async (params) => {
    return await request.download({ url: `/datacenter/device-cat-rule/export-excel`, params })
  },
}
