import request from '@/config/axios'

// 按行政区划预警告警统计 VO
export interface EarlyWarnRegionVO {
  id: number // 主键ID
  warnRegionStatId: string // 统计ID
  statCycle: string // 统计周期
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  regionLevel: string // 行政区划级别
  totalWarnCount: number // 预警总数
  normalLevelCount: number // 一般预警数
  heavyLevelCount: number // 较重预警数
  seriousLevelCount: number // 严重预警数
  criticalLevelCount: number // 特别严重预警数
  completedCount: number // 已办结数
  pendCount: number // 待处置数
  handleRate: number // 处置率(%)
  statUserId: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 按行政区划预警告警统计 API
export const EarlyWarnRegionApi = {
  // 查询按行政区划预警告警统计分页
  getEarlyWarnRegionPage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-region/page`, params })
  },

  // 查询按行政区划预警告警统计详情
  getEarlyWarnRegion: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-region/get?id=` + id })
  },

  // 新增按行政区划预警告警统计
  createEarlyWarnRegion: async (data: EarlyWarnRegionVO) => {
    return await request.post({ url: `/datacenter/early-warn-region/create`, data })
  },

  // 修改按行政区划预警告警统计
  updateEarlyWarnRegion: async (data: EarlyWarnRegionVO) => {
    return await request.put({ url: `/datacenter/early-warn-region/update`, data })
  },

  // 删除按行政区划预警告警统计
  deleteEarlyWarnRegion: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-region/delete?id=` + id })
  },

  // 导出按行政区划预警告警统计 Excel
  exportEarlyWarnRegion: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-region/export-excel`, params })
  }
}
