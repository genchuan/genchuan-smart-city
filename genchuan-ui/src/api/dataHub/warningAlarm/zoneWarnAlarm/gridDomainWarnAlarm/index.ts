import request from '@/config/axios'

// 按网格分域预警告警统计 VO
export interface EarlyWarnGridVO {
  id: number // 主键
  warnGridStatId: string // 统计ID
  statCycle: string // 统计周期
  gridType: string // 网格类型
  gridId: string // 网格ID
  gridCode: string // 网格编码
  gridName: string // 网格名称
  streetCode: string // 所属街道代码
  streetName: string // 所属街道名称
  totalWarnCount: number // 预警总数
  completedCount: number // 已办结数
  pendCount: number // 待处置数
  mainWarnType: string // 主要预警类型
  handleRate: number // 处置率
  statUserId: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 按网格分域预警告警统计 API
export const EarlyWarnGridApi = {
  // 查询按网格分域预警告警统计分页
  getEarlyWarnGridPage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-grid/page`, params })
  },

  // 查询按网格分域预警告警统计详情
  getEarlyWarnGrid: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-grid/get?id=` + id })
  },

  // 新增按网格分域预警告警统计
  createEarlyWarnGrid: async (data: EarlyWarnGridVO) => {
    return await request.post({ url: `/datacenter/early-warn-grid/create`, data })
  },

  // 修改按网格分域预警告警统计
  updateEarlyWarnGrid: async (data: EarlyWarnGridVO) => {
    return await request.put({ url: `/datacenter/early-warn-grid/update`, data })
  },

  // 删除按网格分域预警告警统计
  deleteEarlyWarnGrid: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-grid/delete?id=` + id })
  },

  // 导出按网格分域预警告警统计 Excel
  exportEarlyWarnGrid: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-grid/export-excel`, params })
  }
}
