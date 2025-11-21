import request from '@/config/axios'

// 资产关联管理部件 VO
export interface AssetMngCompVO {
  id: number // 主键ID
  assetRelMngCompId: string // 关联ID
  relAssetId: string // 关联资产ID
  relAssetName: string // 关联资产名称
  mngCompId: string // 管理部件ID
  mngCompCode: string // 管理部件编码
  mngCompName: string // 管理部件名称
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

// 资产关联管理部件 API
export const AssetMngCompApi = {
  // 查询资产关联管理部件分页
  getAssetMngCompPage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-mng-comp/page`, params })
  },

  // 查询资产关联管理部件详情
  getAssetMngComp: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-mng-comp/get?id=` + id })
  },

  // 新增资产关联管理部件
  createAssetMngComp: async (data: AssetMngCompVO) => {
    return await request.post({ url: `/datacenter/asset-mng-comp/create`, data })
  },

  // 修改资产关联管理部件
  updateAssetMngComp: async (data: AssetMngCompVO) => {
    return await request.put({ url: `/datacenter/asset-mng-comp/update`, data })
  },

  // 删除资产关联管理部件
  deleteAssetMngComp: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-mng-comp/delete?id=` + id })
  },

  // 导出资产关联管理部件 Excel
  exportAssetMngComp: async (params) => {
    return await request.download({ url: `/datacenter/asset-mng-comp/export-excel`, params })
  },
  //批量删除资产关联管理部件
  deleteAssetMngComps: async (ids: number[]) => {
    return await request.delete({url: `/datacenter/asset-mng-comp/delete-batch`,data: ids })
  },
  
}
