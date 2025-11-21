import request from '@/config/axios'

// 按监测部件预警告警统计 VO
export interface EarlyWarnMonCompVO {
  id: number // 主键
  warnMonCompStatId: string // 统计ID
  statCycle: string // 统计周期
  monMajorId: string // 部件大类ID
  monMajorName: string // 部件大类名称
  monMinorId: string // 部件小类ID
  monMinorName: string // 部件小类名称
  totalWarnCount: number // 预警总数
  mainTriggerReason: string // 主要触发原因
  deviceCount: number // 涉及设备数
  recoveredCount: number // 已恢复数
  recoveryRate: number // 恢复率
  statUserId: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 按监测部件预警告警统计 API
export const EarlyWarnMonCompApi = {
  // 查询按监测部件预警告警统计分页
  getEarlyWarnMonCompPage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-mon-comp/page`, params })
  },

  // 查询按监测部件预警告警统计详情
  getEarlyWarnMonComp: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-mon-comp/get?id=` + id })
  },

  // 新增按监测部件预警告警统计
  createEarlyWarnMonComp: async (data: EarlyWarnMonCompVO) => {
    return await request.post({ url: `/datacenter/early-warn-mon-comp/create`, data })
  },

  // 修改按监测部件预警告警统计
  updateEarlyWarnMonComp: async (data: EarlyWarnMonCompVO) => {
    return await request.put({ url: `/datacenter/early-warn-mon-comp/update`, data })
  },

  // 删除按监测部件预警告警统计
  deleteEarlyWarnMonComp: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-mon-comp/delete?id=` + id })
  },

  // 导出按监测部件预警告警统计 Excel
  exportEarlyWarnMonComp: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-mon-comp/export-excel`, params })
  }
}
