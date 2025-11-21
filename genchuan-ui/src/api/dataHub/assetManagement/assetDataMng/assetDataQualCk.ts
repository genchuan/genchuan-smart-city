import request from '@/config/axios'

// 资产数据质量检查 VO
export interface AssetDataQualCkVO {
  id: number // 主键ID
  assetDataQualId: string // 质量检查ID
  relAssetId: string // 关联资产ID
  relAssetName: string // 关联资产名称
  ckDataType: string // 检查数据类型
  ckItemName: string // 检查项名称
  ckItemCode: string // 检查项代码
  ckResult: string // 检查结果
  errorDesc: string // 错误描述
  ckTime: Date // 检查时间
  ckMethod: string // 检查方式
  handleStatus: string // 处理状态
  handleUser: string // 处理人
  handleTime: Date // 处理时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 资产数据质量检查 API
export const AssetDataQualCkApi = {
  // 查询资产数据质量检查分页
  getAssetDataQualCkPage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-data-qual-ck/page`, params })
  },

  // 查询资产数据质量检查详情
  getAssetDataQualCk: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-data-qual-ck/get?id=` + id })
  },

  // 新增资产数据质量检查
  createAssetDataQualCk: async (data: AssetDataQualCkVO) => {
    return await request.post({ url: `/datacenter/asset-data-qual-ck/create`, data })
  },

  // 修改资产数据质量检查
  updateAssetDataQualCk: async (data: AssetDataQualCkVO) => {
    return await request.put({ url: `/datacenter/asset-data-qual-ck/update`, data })
  },

  // 删除资产数据质量检查
  deleteAssetDataQualCk: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-data-qual-ck/delete?id=` + id })
  },

  // 导出资产数据质量检查 Excel
  exportAssetDataQualCk: async (params) => {
    return await request.download({ url: `/datacenter/asset-data-qual-ck/export-excel`, params })
  },
}