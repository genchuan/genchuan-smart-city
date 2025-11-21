import request from '@/config/axios'

// 日常巡查计划信息 VO
export interface InspectDailyPlanVO {
  id: number // 主键ID
  dailyPlanId: string // 计划ID
  dailyPlanName: string // 计划名称
  dailyPlanCode: string // 计划编码
  areaCode: string // 所属区域代码
  areaName: string // 所属区域名称
  routeId: string // 关联路线ID
  routeName: string // 关联路线名称
  stdId: string // 关联标准ID
  stdName: string // 关联标准名称
  personId: string // 负责人员ID
  personName: string // 负责人员姓名
  planCycle: string // 计划周期
  executeStartTime: localtime // 执行开始时间
  executeEndTime: localtime // 执行结束时间
  planStatus: boolean // 计划状态
  startDate: Date // 开始日期
  endDate: Date // 结束日期
  createUser: string // 创建人
  bizCreateTime: Date // 创建时间
  updateUser: string // 更新人
  bizUpdateTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 日常巡查计划信息 API
export const InspectDailyPlanApi = {
  // 查询日常巡查计划信息分页
  getInspectDailyPlanPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-daily-plan/page`, params })
  },

  // 查询日常巡查计划信息详情
  getInspectDailyPlan: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-daily-plan/get?id=` + id })
  },

  // 新增日常巡查计划信息
  createInspectDailyPlan: async (data: InspectDailyPlanVO) => {
    return await request.post({ url: `/datacenter/inspect-daily-plan/create`, data })
  },

  // 修改日常巡查计划信息
  updateInspectDailyPlan: async (data: InspectDailyPlanVO) => {
    return await request.put({ url: `/datacenter/inspect-daily-plan/update`, data })
  },

  // 删除日常巡查计划信息
  deleteInspectDailyPlan: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-daily-plan/delete?id=` + id })
  },

  // 导出日常巡查计划信息 Excel
  exportInspectDailyPlan: async (params) => {
    return await request.download({ url: `/datacenter/inspect-daily-plan/export-excel`, params })
  }
}
