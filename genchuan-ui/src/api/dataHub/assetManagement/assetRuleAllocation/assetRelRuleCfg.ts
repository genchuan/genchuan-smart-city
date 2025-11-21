import request from '@/config/axios'

// 资产关联规则配置 VO
export interface AssetRelRuleCfgVO {
  id: number // 主键ID
  assetRelRuleId: string // 关联规则ID
  relAssetCatId: string // 关联资产分类ID
  relAssetCatName: string // 关联资产分类名称
  relObjectType: string // 关联对象类型
  relObjectName: string // 关联对象名称
  isRequired: string // 是否必选
  relQuantityLimit: number // 关联数量限制
  relObjectSourceTable: string // 关联对象来源表
  relDesc: string // 关联说明
  createUser: string // 创建人
  createdTime: Date // 创建时间
  updateUser: string // 更新人
  updatedTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 资产关联规则配置 API
export const AssetRelRuleCfgApi = {
  // 查询资产关联规则配置分页
  getAssetRelRuleCfgPage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-rel-rule-cfg/page`, params })
  },

  // 查询资产关联规则配置详情
  getAssetRelRuleCfg: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-rel-rule-cfg/get?id=` + id })
  },

  // 新增资产关联规则配置
  createAssetRelRuleCfg: async (data: AssetRelRuleCfgVO) => {
    return await request.post({ url: `/datacenter/asset-rel-rule-cfg/create`, data })
  },

  // 修改资产关联规则配置
  updateAssetRelRuleCfg: async (data: AssetRelRuleCfgVO) => {
    return await request.put({ url: `/datacenter/asset-rel-rule-cfg/update`, data })
  },

  // 删除资产关联规则配置
  deleteAssetRelRuleCfg: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-rel-rule-cfg/delete?id=` + id })
  },

  // 导出资产关联规则配置 Excel
  exportAssetRelRuleCfg: async (params) => {
    return await request.download({ url: `/datacenter/asset-rel-rule-cfg/export-excel`, params })
  },
  // 批量更新是否必选（0=可选 1=必选）
  batchUpdateIsRequired: async (data: { ids: number[]; isRequired: string }) => {
    return await request.put({ url: `/datacenter/asset-rel-rule-cfg/batch-update-required`, data })
  },
}
