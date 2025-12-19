import request from '@/config/axios'

// 资源匹配调度 VO
export interface ResMatchScheduleVO {
  id: number // 主键ID
  resScheduleId: string // 调度ID
  relSchemeId: string // 关联方案ID
  relSchemeName: string // 关联方案名称
  resDemandDetail: string // 资源需求明细
  matchedResDetail: string // 匹配资源明细
  scheduleStatus: string // 调度状态
  matchFailReason: string // 匹配失败原因
  scheduleWoId: string // 调度工单ID
  scheduleUser: string // 调度人
  scheduleTime: Date // 调度时间
  completeTime: Date // 完成时间
  resMngr: string // 资源负责人
  resMngrName: string // 资源负责人姓名
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 资源匹配调度 API
export const ResMatchScheduleApi = {
  // 查询资源匹配调度分页
  getResMatchSchedulePage: async (params: any) => {
    return await request.get({ url: `/datacenter/res-match-schedule/page`, params })
  },

  // 查询资源匹配调度详情
  getResMatchSchedule: async (id: number) => {
    return await request.get({ url: `/datacenter/res-match-schedule/get?id=` + id })
  },

  // 新增资源匹配调度
  createResMatchSchedule: async (data: ResMatchScheduleVO) => {
    return await request.post({ url: `/datacenter/res-match-schedule/create`, data })
  },

  // 修改资源匹配调度
  updateResMatchSchedule: async (data: ResMatchScheduleVO) => {
    return await request.put({ url: `/datacenter/res-match-schedule/update`, data })
  },

  // 删除资源匹配调度
  deleteResMatchSchedule: async (id: number) => {
    return await request.delete({ url: `/datacenter/res-match-schedule/delete?id=` + id })
  },

  // 导出资源匹配调度 Excel
  exportResMatchSchedule: async (params) => {
    return await request.download({ url: `/datacenter/res-match-schedule/export-excel`, params })
  }
}