import request from '@/config/axios'

// 按管理部件预警告警统计 VO
export interface EarlyWarnMngCompVO {
  id: number // 主键
  warnMngCompStatId: string // 统计ID
  statCycle: string // 统计周期
  compMajorId: string // 部件大类ID
  compMajorName: string // 部件大类名称
  compMinorId: string // 部件小类ID
  compMinorName: string // 部件小类名称
  totalWarnCount: number // 预警总数
  mainFaultType: string // 主要故障类型
  mainRegionCode: string // 主要分布区域代码
  mainRegionName: string // 主要分布区域名称
  completedCount: number // 已办结数
  handleRate: number // 处置率
  statUserId: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 按管理部件预警告警统计 API
export const EarlyWarnMngCompApi = {
  // 查询按管理部件预警告警统计分页
  getEarlyWarnMngCompPage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-mng-comp/page`, params })
  },

  // 查询按管理部件预警告警统计详情
  getEarlyWarnMngComp: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-mng-comp/get?id=` + id })
  },

  // 新增按管理部件预警告警统计
  createEarlyWarnMngComp: async (data: EarlyWarnMngCompVO) => {
    return await request.post({ url: `/datacenter/early-warn-mng-comp/create`, data })
  },

  // 修改按管理部件预警告警统计
  updateEarlyWarnMngComp: async (data: EarlyWarnMngCompVO) => {
    return await request.put({ url: `/datacenter/early-warn-mng-comp/update`, data })
  },

  // 删除按管理部件预警告警统计
  deleteEarlyWarnMngComp: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-mng-comp/delete?id=` + id })
  },

  // 导出按管理部件预警告警统计 Excel
  exportEarlyWarnMngComp: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-mng-comp/export-excel`, params })
  }
}
