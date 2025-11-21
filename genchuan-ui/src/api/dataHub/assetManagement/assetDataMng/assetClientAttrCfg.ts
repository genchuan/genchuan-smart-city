import request from '@/config/axios'

// 资产客户端属性配置 VO
export interface AssetClientAttrCfgVO {
  id: number // 主键ID
  assetClientAttrId: string // 客户端属性ID
  relAssetId: string // 关联资产ID
  relAssetName: string // 关联资产名称
  attrName: string // 属性名称
  attrCode: string // 属性代码
  attrDataType: string // 属性数据类型
  attrValue: string // 属性值
  attrPurpose: string // 属性用途
  clientIp: string // 客户端IP
  cfgTime: Date // 配置时间
  operUser: string // 操作人
  updateUser: string // 更新人
  updatedTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 资产客户端属性配置 API
export const AssetClientAttrCfgApi = {
  // 查询资产客户端属性配置分页
  getAssetClientAttrCfgPage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-client-attr-cfg/page`, params })
  },

  // 查询资产客户端属性配置详情
  getAssetClientAttrCfg: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-client-attr-cfg/get?id=` + id })
  },

  // 新增资产客户端属性配置
  createAssetClientAttrCfg: async (data: AssetClientAttrCfgVO) => {
    return await request.post({ url: `/datacenter/asset-client-attr-cfg/create`, data })
  },

  // 修改资产客户端属性配置
  updateAssetClientAttrCfg: async (data: AssetClientAttrCfgVO) => {
    return await request.put({ url: `/datacenter/asset-client-attr-cfg/update`, data })
  },

  // 删除资产客户端属性配置
  deleteAssetClientAttrCfg: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-client-attr-cfg/delete?id=` + id })
  },

  // 导出资产客户端属性配置 Excel
  exportAssetClientAttrCfg: async (params) => {
    return await request.download({ url: `/datacenter/asset-client-attr-cfg/export-excel`, params })
  },
  // 导入资产客户端属性配置 Excel
  importAssetClientAttrCfg: async (data: any) => {
    return await request.upload({ url: `/datacenter/asset-client-attr-cfg/import`, data })
  },
  // 下载资产客户端属性配置 Excel 模板
  importAssetClientAttrCfgTemplate: async () => {
    return await request.download({ url: `/datacenter/asset-client-attr-cfg/import-template` })
  },
  // 获取资产客户端属性列表
  getAssetClientAttrCfgList: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-client-attr-cfg/list`, params })
  },
}
