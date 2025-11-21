import request from '@/config/axios'

// 资产关联行政区划 VO
export interface AssetAreaVO {
  id: number // 主键ID
  assetRelRegionId: string // 关联ID
  relAssetId: string // 关联资产ID
  relAssetName: string // 关联资产名称
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  regionLevel: string // 行政区划级别
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

// 资产关联行政区划 API
export const AssetAreaApi = {
  // 查询资产关联行政区划分页
  getAssetAreaPage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-area/page`, params })
  },

  // 查询资产关联行政区划详情
  getAssetArea: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-area/get?id=` + id })
  },

  // 新增资产关联行政区划
  createAssetArea: async (data: AssetAreaVO) => {
    return await request.post({ url: `/datacenter/asset-area/create`, data })
  },

  // 修改资产关联行政区划
  updateAssetArea: async (data: AssetAreaVO) => {
    return await request.put({ url: `/datacenter/asset-area/update`, data })
  },

  // 删除资产关联行政区划
  deleteAssetArea: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-area/delete?id=` + id })
  },

  // 导出资产关联行政区划 Excel
  exportAssetArea: async (params) => {
    return await request.download({ url: `/datacenter/asset-area/export-excel`, params })
  },
}
