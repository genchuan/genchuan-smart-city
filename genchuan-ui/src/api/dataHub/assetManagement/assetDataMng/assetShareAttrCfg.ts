import request from '@/config/axios'

// 资产共享属性配置 VO
export interface AssetShareAttrCfgVO {
  id: number // 主键ID
  assetShareAttrId: string // 共享属性ID
  relAssetId: string // 关联资产ID
  relAssetName: string // 关联资产名称
  attrName: string // 属性名称
  attrCode: string // 属性代码
  attrValue: string // 属性值
  shareObjType: string // 共享对象类型
  shareObjId: string // 共享对象ID
  shareObjName: string // 共享对象名称
  sharePerm: string // 共享权限
  cfgTime: Date // 配置时间
  operUser: string // 操作人
  updateUser: string // 更新人
  updatedTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 资产共享属性配置 API
export const AssetShareAttrCfgApi = {
  // 查询资产共享属性配置分页
  getAssetShareAttrCfgPage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-share-attr-cfg/page`, params })
  },

  // 查询资产共享属性配置详情
  getAssetShareAttrCfg: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-share-attr-cfg/get?id=` + id })
  },

  // 新增资产共享属性配置
  createAssetShareAttrCfg: async (data: AssetShareAttrCfgVO) => {
    return await request.post({ url: `/datacenter/asset-share-attr-cfg/create`, data })
  },

  // 修改资产共享属性配置
  updateAssetShareAttrCfg: async (data: AssetShareAttrCfgVO) => {
    return await request.put({ url: `/datacenter/asset-share-attr-cfg/update`, data })
  },

  // 删除资产共享属性配置
  deleteAssetShareAttrCfg: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-share-attr-cfg/delete?id=` + id })
  },

  // 导出资产共享属性配置 Excel
  exportAssetShareAttrCfg: async (params) => {
    return await request.download({ url: `/datacenter/asset-share-attr-cfg/export-excel`, params })
  },
  // 导入资产共享属性配置 Excel
  importAssetShareAttrCfg: async (data: any) => {
    return await request.upload({ url: `/datacenter/asset-share-attr-cfg/import`, data })
  },
  // 下载资产共享属性配置 Excel 模板
  importAssetShareAttrCfgTemplate: async () => {
    return await request.download({ url: `/datacenter/asset-share-attr-cfg/import-template` })
  },
}
