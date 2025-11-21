import request from '@/config/axios'

// 预警告警时间维度统计 VO
export interface EarlyWarnTimeVO {
  id: number // 主键
  timeStatId: string // 统计ID
  timeDimension: string // 统计维度
  statCycleValue: string // 统计周期值
  statCycleName: string // 统计周期名称
  totalWarnCount: number // 预警总数
  mainWarnType: string // 主要预警类型
  mainTypeCount: number // 主要预警类型数量
  completedCount: number // 已办结数
  handleRate: number // 处置率
  statUserId: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 预警告警时间维度统计 API
export const EarlyWarnTimeApi = {
  // 查询预警告警时间维度统计分页
  getEarlyWarnTimePage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-time/page`, params })
  },

  // 查询预警告警时间维度统计详情
  getEarlyWarnTime: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-time/get?id=` + id })
  },

  // 新增预警告警时间维度统计
  createEarlyWarnTime: async (data: EarlyWarnTimeVO) => {
    return await request.post({ url: `/datacenter/early-warn-time/create`, data })
  },

  // 修改预警告警时间维度统计
  updateEarlyWarnTime: async (data: EarlyWarnTimeVO) => {
    return await request.put({ url: `/datacenter/early-warn-time/update`, data })
  },

  // 删除预警告警时间维度统计
  deleteEarlyWarnTime: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-time/delete?id=` + id })
  },

  // 导出预警告警时间维度统计 Excel
  exportEarlyWarnTime: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-time/export-excel`, params })
  }
}
