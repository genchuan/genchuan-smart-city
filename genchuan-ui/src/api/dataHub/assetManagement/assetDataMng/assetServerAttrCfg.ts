import request from '@/config/axios'

// 资产服务端属性配置 VO
export interface AssetServerAttrCfgVO {
  id: number // 主键ID
  assetServerAttrId: string // 服务端属性ID
  relAssetId: string // 关联资产ID
  relAssetName: string // 关联资产名称
  attrName: string // 属性名称
  attrCode: string // 属性代码
  attrDataType: string // 属性数据类型
  attrValue: string // 属性值
  collectFreq: string // 采集频率
  lastCollectTime: Date // 最后采集时间
  cfgTime: Date // 配置时间
  operUser: string // 操作人
  updateUser: string // 更新人
  updatedTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 资产服务端属性配置 API
export const AssetServerAttrCfgApi = {
  // 查询资产服务端属性配置分页
  getAssetServerAttrCfgPage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-server-attr-cfg/page`, params })
  },

  // 查询资产服务端属性配置详情
  getAssetServerAttrCfg: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-server-attr-cfg/get?id=` + id })
  },

  // 新增资产服务端属性配置
  createAssetServerAttrCfg: async (data: AssetServerAttrCfgVO) => {
    return await request.post({ url: `/datacenter/asset-server-attr-cfg/create`, data })
  },

  // 修改资产服务端属性配置
  updateAssetServerAttrCfg: async (data: AssetServerAttrCfgVO) => {
    return await request.put({ url: `/datacenter/asset-server-attr-cfg/update`, data })
  },

  // 删除资产服务端属性配置
  deleteAssetServerAttrCfg: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-server-attr-cfg/delete?id=` + id })
  },

  // 导出资产服务端属性配置 Excel
  exportAssetServerAttrCfg: async (params) => {
    return await request.download({ url: `/datacenter/asset-server-attr-cfg/export-excel`, params })
  },
  // 导入资产服务端属性配置 Excel
  importAssetServerAttrCfg: async (data: any) => {
    return await request.upload({ url: `/datacenter/asset-server-attr-cfg/import`, data })
  },
  // 下载资产服务端属性配置 Excel 模板
  importAssetServerAttrCfgTemplate: async () => {
    return await request.download({ url: `/datacenter/asset-server-attr-cfg/import-template` })
  },
  // 获取资产服务端属性列表
  getAssetClientAttrCfgList: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-server-attr-cfg/list`, params })
  }
}
