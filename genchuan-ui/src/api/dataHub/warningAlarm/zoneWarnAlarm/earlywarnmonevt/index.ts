import request from '@/config/axios'

// 按监测事件预警告警统计 VO
export interface EarlyWarnMonEvtVO {
  id: number // 主键
  warnMonEvtStatId: string // 统计ID
  statCycle: string // 统计周期
  evtMajorId: string // 事件大类ID
  evtMajorName: string // 事件大类名称
  evtMinorId: string // 事件小类ID
  evtMinorName: string // 事件小类名称
  totalWarnCount: number // 预警总数
  avgRespTime: number // 平均响应时间
  regionCount: number // 涉及区域数
  completedCount: number // 已办结数
  handleRate: number // 处置率
  statUserId: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 按监测事件预警告警统计 API
export const EarlyWarnMonEvtApi = {
  // 查询按监测事件预警告警统计分页
  getEarlyWarnMonEvtPage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-mon-evt/page`, params })
  },

  // 查询按监测事件预警告警统计详情
  getEarlyWarnMonEvt: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-mon-evt/get?id=` + id })
  },

  // 新增按监测事件预警告警统计
  createEarlyWarnMonEvt: async (data: EarlyWarnMonEvtVO) => {
    return await request.post({ url: `/datacenter/early-warn-mon-evt/create`, data })
  },

  // 修改按监测事件预警告警统计
  updateEarlyWarnMonEvt: async (data: EarlyWarnMonEvtVO) => {
    return await request.put({ url: `/datacenter/early-warn-mon-evt/update`, data })
  },

  // 删除按监测事件预警告警统计
  deleteEarlyWarnMonEvt: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-mon-evt/delete?id=` + id })
  },

  // 导出按监测事件预警告警统计 Excel
  exportEarlyWarnMonEvt: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-mon-evt/export-excel`, params })
  }
}
