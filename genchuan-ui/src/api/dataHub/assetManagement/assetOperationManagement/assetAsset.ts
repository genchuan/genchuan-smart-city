import request from '@/config/axios'

// 资产关联资产 VO
export interface AssetAssetVO {
  id: number // 主键ID
  assetRelAssetId: string // 关联ID
  slaveAssetId: string // 从资产ID
  slaveAssetName: string // 从资产名称
  masterAssetId: string // 主资产ID
  masterAssetName: string // 主资产名称
  relType: string // 关联类型
  relTime: Date // 关联时间
  operUser: string // 操作人
  relDesc: string // 关联说明
  updateUser: string // 更新人
  updatedTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 资产关联资产 API
export const AssetAssetApi = {
  // 查询资产关联资产分页
  getAssetAssetPage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-asset/page`, params })
  },

  // 查询资产关联资产详情
  getAssetAsset: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-asset/get?id=` + id })
  },

  // 新增资产关联资产
  createAssetAsset: async (data: AssetAssetVO) => {
    return await request.post({ url: `/datacenter/asset-asset/create`, data })
  },

  // 修改资产关联资产
  updateAssetAsset: async (data: AssetAssetVO) => {
    return await request.put({ url: `/datacenter/asset-asset/update`, data })
  },

  // 删除资产关联资产
  deleteAssetAsset: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-asset/delete?id=` + id })
  },

  // 导出资产关联资产 Excel
  exportAssetAsset: async (params) => {
    return await request.download({ url: `/datacenter/asset-asset/export-excel`, params })
  },
}