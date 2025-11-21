import request from '@/config/axios'

// 应急巡查计划信息 VO
export interface InspectEmerPlanVO {
  id: number // 主键ID
  emerPlanId: string // 计划ID
  emerPlanName: string // 计划名称
  emerPlanCode: string // 计划编码
  emerTopic: string // 应急主题
  emerEvtId: string // 关联应急事件ID
  emerEvtName: string // 关联应急事件名称
  areaCode: string // 所属区域代码
  areaName: string // 所属区域名称
  stdId: string // 关联标准ID
  stdName: string // 关联标准名称
  personIds: string // 负责人员IDs
  personNames: string // 负责人员Names
  executeTime: Date // 执行时间
  executeDeadline: Date // 执行截止时间
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

// 应急巡查计划信息 API
export const InspectEmerPlanApi = {
  // 查询应急巡查计划信息分页
  getInspectEmerPlanPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-emer-plan/page`, params })
  },

  // 查询应急巡查计划信息详情
  getInspectEmerPlan: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-emer-plan/get?id=` + id })
  },

  // 新增应急巡查计划信息
  createInspectEmerPlan: async (data: InspectEmerPlanVO) => {
    return await request.post({ url: `/datacenter/inspect-emer-plan/create`, data })
  },

  // 修改应急巡查计划信息
  updateInspectEmerPlan: async (data: InspectEmerPlanVO) => {
    return await request.put({ url: `/datacenter/inspect-emer-plan/update`, data })
  },

  // 删除应急巡查计划信息
  deleteInspectEmerPlan: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-emer-plan/delete?id=` + id })
  },

  // 导出应急巡查计划信息 Excel
  exportInspectEmerPlan: async (params) => {
    return await request.download({ url: `/datacenter/inspect-emer-plan/export-excel`, params })
  }
}
