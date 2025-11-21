import request from '@/config/axios'

// 预警告警等级维度统计 VO
export interface EarlyWarnLevelVO {
  id: number // 主键
  levelStatId: string // 统计ID
  statCycle: string // 统计周期
  warnLevel: string // 预警等级
  warnLevelCode: string // 预警等级编码
  totalWarnCount: number // 预警总数
  proportion: number // 占比
  completedCount: number // 已办结数
  handleRate: number // 处置率
  avgHandleEndure: number // 平均处置时长
  mainWarnType: string // 主要预警类型
  statUserId: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 预警告警等级维度统计 API
export const EarlyWarnLevelApi = {
  // 查询预警告警等级维度统计分页
  getEarlyWarnLevelPage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-level/page`, params })
  },

  // 查询预警告警等级维度统计详情
  getEarlyWarnLevel: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-level/get?id=` + id })
  },

  // 新增预警告警等级维度统计
  createEarlyWarnLevel: async (data: EarlyWarnLevelVO) => {
    return await request.post({ url: `/datacenter/early-warn-level/create`, data })
  },

  // 修改预警告警等级维度统计
  updateEarlyWarnLevel: async (data: EarlyWarnLevelVO) => {
    return await request.put({ url: `/datacenter/early-warn-level/update`, data })
  },

  // 删除预警告警等级维度统计
  deleteEarlyWarnLevel: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-level/delete?id=` + id })
  },

  // 导出预警告警等级维度统计 Excel
  exportEarlyWarnLevel: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-level/export-excel`, params })
  }
}
