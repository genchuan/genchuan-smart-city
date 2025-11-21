import request from '@/config/axios'

// 资产空间数据 VO
export interface AssetSpatialDataVO {
  id: number // 主键ID
  assetSpatialId: string // 空间数据ID
  relAssetId: string // 关联资产ID
  relAssetName: string // 关联资产名称
  coordSystemType: string // 坐标系类型
  coordX: number // 坐标X
  coordY: number // 坐标Y
  elevation: number // 高程
  boundaryCoords: string // 边界坐标
  spatialDataSource: string // 空间数据来源
  inputTime: Date // 录入时间
  operUser: string // 操作人
  updateUser: string // 更新人
  updatedTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 资产空间数据 API
export const AssetSpatialDataApi = {
  // 查询资产空间数据分页
  getAssetSpatialDataPage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-spatial-data/page`, params })
  },

  // 查询资产空间数据详情
  getAssetSpatialData: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-spatial-data/get?id=` + id })
  },

  // 新增资产空间数据
  createAssetSpatialData: async (data: AssetSpatialDataVO) => {
    return await request.post({ url: `/datacenter/asset-spatial-data/create`, data })
  },

  // 修改资产空间数据
  updateAssetSpatialData: async (data: AssetSpatialDataVO) => {
    return await request.put({ url: `/datacenter/asset-spatial-data/update`, data })
  },

  // 删除资产空间数据
  deleteAssetSpatialData: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-spatial-data/delete?id=` + id })
  },

  // 导出资产空间数据 Excel
  exportAssetSpatialData: async (params) => {
    return await request.download({ url: `/datacenter/asset-spatial-data/export-excel`, params })
  },
  // 导入资产空间数据 Excel
  importAssetSpatialData: async (data) => {
    return await request.upload({ url: `/datacenter/asset-spatial-data/import`, data })
  },
  // 下载资产空间数据 Excel 模板
  importAssetSpatialDataTemplate: async () => {
    return await request.download({ url: `/datacenter/asset-spatial-data/import-template` })
  },
}
