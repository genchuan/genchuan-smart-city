import request from '@/config/axios'

// 资产状态统计 VO
export interface AssetStatusRptVO {
  id: number // 主键ID
  assetStatusStatId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  assetType: string // 资产类型
  assetStatus: string // 资产状态
  assetCount: number // 资产数量
  assetRatio: number // 占比
  faultReason: string // 故障资产主要原因
  mntPriority: string // 维护优先级
  statTime: Date // 统计时间
  statUser: string // 统计人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 资产状态统计 API
export const AssetStatusRptApi = {
  // 查询资产状态统计分页
  getAssetStatusRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-status-rpt/page`, params })
  },

  // 查询资产状态统计详情
  getAssetStatusRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-status-rpt/get?id=` + id })
  },

  // 新增资产状态统计
  createAssetStatusRpt: async (data: AssetStatusRptVO) => {
    return await request.post({ url: `/datacenter/asset-status-rpt/create`, data })
  },

  // 修改资产状态统计
  updateAssetStatusRpt: async (data: AssetStatusRptVO) => {
    return await request.put({ url: `/datacenter/asset-status-rpt/update`, data })
  },

  // 删除资产状态统计
  deleteAssetStatusRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-status-rpt/delete?id=` + id })
  },

  // 导出资产状态统计 Excel
  exportAssetStatusRpt: async (params) => {
    return await request.download({ url: `/datacenter/asset-status-rpt/export-excel`, params })
  },
}