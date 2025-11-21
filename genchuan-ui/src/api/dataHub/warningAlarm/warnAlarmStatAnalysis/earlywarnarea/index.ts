import request from '@/config/axios'

// 预警告警区域维度统计 VO
export interface EarlyWarnAreaVO {
  id: number // 主键
  regionStatId: string // 统计ID
  statCycle: string // 统计周期
  adminLevel: string // 行政区划级别
  adminCode: string // 行政区划代码
  adminName: string // 行政区划名称
  totalWarnCount: number // 预警总数
  criticalCount: number // 严重等级数
  completedCount: number // 已办结数
  handleRate: number // 处置率
  mainWarnType: string // 主要预警类型
  statUserId: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 预警告警区域维度统计 API
export const EarlyWarnAreaApi = {
  // 查询预警告警区域维度统计分页
  getEarlyWarnAreaPage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-area/page`, params })
  },

  // 查询预警告警区域维度统计详情
  getEarlyWarnArea: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-area/get?id=` + id })
  },

  // 新增预警告警区域维度统计
  createEarlyWarnArea: async (data: EarlyWarnAreaVO) => {
    return await request.post({ url: `/datacenter/early-warn-area/create`, data })
  },

  // 修改预警告警区域维度统计
  updateEarlyWarnArea: async (data: EarlyWarnAreaVO) => {
    return await request.put({ url: `/datacenter/early-warn-area/update`, data })
  },

  // 删除预警告警区域维度统计
  deleteEarlyWarnArea: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-area/delete?id=` + id })
  },

  // 导出预警告警区域维度统计 Excel
  exportEarlyWarnArea: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-area/export-excel`, params })
  }
}
