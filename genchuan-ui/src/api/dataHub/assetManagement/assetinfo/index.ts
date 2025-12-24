import request from '@/config/axios'

// 资产信息 VO
export interface AssetInfoVO {
  id: number // 主键ID
  assetId: string // 资产ID
  entityType: string // 实体类型
  createdTime: number // 创建时间戳
  tenantEntityType: string // 租户实体类型
  customerId: string // 客户ID
  customerEntityType: string // 客户实体类型
  assetProfileId: string // 资产档案ID
  assetProfileEntityType: string // 资产档案实体类型
  assetName: string // 资产名称
  assetType: string // 资产类型
  assetLabel: string // 资产标签
  externalId: string // 外部ID
  version: number // 版本号
  customerTitle: string // 客户标题
  customerIsPublic: boolean // 客户是否公开
  assetProfileName: string // 资产档案名称
  additionalInfo: string // 附加信息
  attributes: string // 属性列表
  contextDevices: string // 关联设备列表
  tenantIdSys: number // 系统租户ID
}

// 资产信息 API
export const AssetInfoApi = {
  // 查询资产信息分页
  getAssetInfoPage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-info/page`, params })
  },

  // 查询资产信息详情
  getAssetInfo: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-info/get?id=` + id })
  },

  // 新增资产信息
  createAssetInfo: async (data: AssetInfoVO) => {
    return await request.post({ url: `/datacenter/asset-info/create`, data })
  },

  // 修改资产信息
  updateAssetInfo: async (data: AssetInfoVO) => {
    return await request.put({ url: `/datacenter/asset-info/update`, data })
  },

  // 删除资产信息
  deleteAssetInfo: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-info/delete?id=` + id })
  },

  // 导出资产信息 Excel
  exportAssetInfo: async (params) => {
    return await request.download({ url: `/datacenter/asset-info/export-excel`, params })
  },
}
