import request from '@/config/axios'

// 按资产分域预警告警统计 VO
export interface EarlyWarnAssetVO {
  id: number // 主键
  warnAssetStatId: string // 统计ID
  statCycle: string // 统计周期
  assetMajorId: string // 资产大类ID
  assetMajorName: string // 资产大类名称
  assetMinorId: string // 资产小类ID
  assetMinorName: string // 资产小类名称
  totalWarnCount: number // 预警总数
  mainFaultType: string // 主要故障类型
  assetCount: number // 涉及资产数
  mntCost: number // 维修总成本
  repairedAssetCount: number // 已修复资产数
  repairRate: number // 修复率
  statUserId: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 按资产分域预警告警统计 API
export const EarlyWarnAssetApi = {
  // 查询按资产分域预警告警统计分页
  getEarlyWarnAssetPage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-asset/page`, params })
  },

  // 查询按资产分域预警告警统计详情
  getEarlyWarnAsset: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-asset/get?id=` + id })
  },

  // 新增按资产分域预警告警统计
  createEarlyWarnAsset: async (data: EarlyWarnAssetVO) => {
    return await request.post({ url: `/datacenter/early-warn-asset/create`, data })
  },

  // 修改按资产分域预警告警统计
  updateEarlyWarnAsset: async (data: EarlyWarnAssetVO) => {
    return await request.put({ url: `/datacenter/early-warn-asset/update`, data })
  },

  // 删除按资产分域预警告警统计
  deleteEarlyWarnAsset: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-asset/delete?id=` + id })
  },

  // 导出按资产分域预警告警统计 Excel
  exportEarlyWarnAsset: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-asset/export-excel`, params })
  }
}
