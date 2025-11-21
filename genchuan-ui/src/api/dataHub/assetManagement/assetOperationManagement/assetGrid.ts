import request from '@/config/axios'

// 资产关联网格 VO
export interface AssetGridVO {
  id: number // 主键ID
  assetRelGridId: string // 关联ID
  relAssetId: string // 关联资产ID
  relAssetName: string // 关联资产名称
  gridCode: string // 网格编码
  gridName: string // 网格名称
  gridLevel: string // 网格级别
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

// 资产关联网格 API
export const AssetGridApi = {
  // 查询资产关联网格分页
  getAssetGridPage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-grid/page`, params })
  },

  // 查询资产关联网格详情
  getAssetGrid: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-grid/get?id=` + id })
  },

  // 新增资产关联网格
  createAssetGrid: async (data: AssetGridVO) => {
    return await request.post({ url: `/datacenter/asset-grid/create`, data })
  },

  // 修改资产关联网格
  updateAssetGrid: async (data: AssetGridVO) => {
    return await request.put({ url: `/datacenter/asset-grid/update`, data })
  },

  // 删除资产关联网格
  deleteAssetGrid: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-grid/delete?id=` + id })
  },

  // 导出资产关联网格 Excel
  exportAssetGrid: async (params) => {
    return await request.download({ url: `/datacenter/asset-grid/export-excel`, params })
  },
}