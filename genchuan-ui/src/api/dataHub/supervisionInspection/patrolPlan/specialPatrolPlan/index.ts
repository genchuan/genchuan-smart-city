import request from '@/config/axios'

// 专项巡查计划信息 VO
export interface InspectSpecPlanVO {
  id: number // 主键ID
  specPlanId: string // 计划ID
  specPlanName: string // 计划名称
  specPlanCode: string // 计划编码
  specTopic: string // 专项主题
  areaCode: string // 所属区域代码
  areaName: string // 所属区域名称
  stdId: string // 关联标准ID
  stdName: string // 关联标准名称
  personIds: string // 负责人员IDs
  personNames: string // 负责人员Names
  executeStartDate: Date // 执行开始日期
  executeEndDate: Date // 执行结束日期
  dailyExecuteTime: string // 每日执行时间
  planStatus: boolean // 计划状态
  createUser: string // 创建人
  bizCreateTime: Date // 创建时间
  updateUser: string // 更新人
  bizUpdateTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 专项巡查计划信息 API
export const InspectSpecPlanApi = {
  // 查询专项巡查计划信息分页
  getInspectSpecPlanPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-spec-plan/page`, params })
  },

  // 查询专项巡查计划信息详情
  getInspectSpecPlan: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-spec-plan/get?id=` + id })
  },

  // 新增专项巡查计划信息
  createInspectSpecPlan: async (data: InspectSpecPlanVO) => {
    return await request.post({ url: `/datacenter/inspect-spec-plan/create`, data })
  },

  // 修改专项巡查计划信息
  updateInspectSpecPlan: async (data: InspectSpecPlanVO) => {
    return await request.put({ url: `/datacenter/inspect-spec-plan/update`, data })
  },

  // 删除专项巡查计划信息
  deleteInspectSpecPlan: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-spec-plan/delete?id=` + id })
  },

  // 导出专项巡查计划信息 Excel
  exportInspectSpecPlan: async (params) => {
    return await request.download({ url: `/datacenter/inspect-spec-plan/export-excel`, params })
  }
}
