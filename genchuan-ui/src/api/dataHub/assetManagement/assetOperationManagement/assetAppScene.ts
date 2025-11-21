import request from '@/config/axios'

// 资产关联应用场景 VO
export interface AssetAppSceneVO {
  id: number // 主键ID
  assetRelAppSceneId: string // 关联ID
  relAssetId: string // 关联资产ID
  relAssetName: string // 关联资产名称
  appSceneId: string // 应用场景ID
  appSceneCode: string // 应用场景编码
  appSceneName: string // 应用场景名称
  relTime: Date // 关联时间
  operUser: string // 操作人
  relDesc: string // 关联说明
  updateUser: string // 更新人
  updatedTime: Date // 更新时间
  extCat1: string // 分类扩展字段1）
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 资产关联应用场景 API
export const AssetAppSceneApi = {
  // 查询资产关联应用场景分页
  getAssetAppScenePage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-app-scene/page`, params })
  },

  // 查询资产关联应用场景详情
  getAssetAppScene: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-app-scene/get?id=` + id })
  },

  // 新增资产关联应用场景
  createAssetAppScene: async (data: AssetAppSceneVO) => {
    return await request.post({ url: `/datacenter/asset-app-scene/create`, data })
  },

  // 修改资产关联应用场景
  updateAssetAppScene: async (data: AssetAppSceneVO) => {
    return await request.put({ url: `/datacenter/asset-app-scene/update`, data })
  },

  // 删除资产关联应用场景
  deleteAssetAppScene: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-app-scene/delete?id=` + id })
  },

  // 导出资产关联应用场景 Excel
  exportAssetAppScene: async (params) => {
    return await request.download({ url: `/datacenter/asset-app-scene/export-excel`, params })
  },
  //批量删除资产关联应用场景
  deleteAssetAppScenes: async (ids: number[]) => {
    return await request.delete({url: `/datacenter/asset-app-scene/delete-batch`,data: ids })
  },
}
