import request from '@/config/axios'

// 资产分类统计 VO
export interface AssetCatRptVO {
  id: number // 主键ID
  assetCatStatId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  assetMajorId: string // 资产大类ID
  assetMajorName: string // 资产大类名称
  assetMidId: string // 资产中类ID
  assetMidName: string // 资产中类名称
  assetMinorId: string // 资产小类ID
  assetMinorName: string // 资产小类名称
  totalAssetCount: number // 资产总数
  enabledAssetCount: number // 启用资产数
  disabledAssetCount: number // 禁用资产数
  newAssetCount: number // 新增资产数
  updateAssetCount: number // 更新资产数
  statTime: Date // 统计时间
  statUser: string // 统计人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 资产分类统计 API
export const AssetCatRptApi = {
  // 查询资产分类统计分页
  getAssetCatRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-cat-rpt/page`, params })
  },

  // 查询资产分类统计详情
  getAssetCatRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-cat-rpt/get?id=` + id })
  },

  // 新增资产分类统计
  createAssetCatRpt: async (data: AssetCatRptVO) => {
    return await request.post({ url: `/datacenter/asset-cat-rpt/create`, data })
  },

  // 修改资产分类统计
  updateAssetCatRpt: async (data: AssetCatRptVO) => {
    return await request.put({ url: `/datacenter/asset-cat-rpt/update`, data })
  },

  // 删除资产分类统计
  deleteAssetCatRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-cat-rpt/delete?id=` + id })
  },

  // 导出资产分类统计 Excel
  exportAssetCatRpt: async (params) => {
    return await request.download({ url: `/datacenter/asset-cat-rpt/export-excel`, params })
  },
}