import request from '@/config/axios'

// 考察计划管理 VO
export interface EvalInspectPlanVO {
  id: number // 主键ID
  inspectPlanId: string // 计划ID
  inspectPlanName: string // 计划名称
  inspectPlanCode: string // 计划编码
  evalTaskId: string // 关联任务ID
  evalTaskName: string // 关联任务名称
  evalObjectId: string // 考察对象ID
  evalObjectName: string // 考察对象名称
  inspectorId: string // 考察人员ID
  inspectorName: string // 考察人员姓名
  inspectTime: Date // 考察时间
  inspectMethod: string // 考察方式
  inspectContent: string // 考察内容
  planStatus: string // 计划状态
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  updateUserBiz: string // 更新人(业务)
  updateTimeBiz: Date // 更新时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 考察计划管理 API
export const EvalInspectPlanApi = {
  // 查询考察计划管理分页
  getEvalInspectPlanPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-inspect-plan/page`, params })
  },

  // 查询考察计划管理详情
  getEvalInspectPlan: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-inspect-plan/get?id=` + id })
  },

  // 新增考察计划管理
  createEvalInspectPlan: async (data: EvalInspectPlanVO) => {
    return await request.post({ url: `/datacenter/eval-inspect-plan/create`, data })
  },

  // 修改考察计划管理
  updateEvalInspectPlan: async (data: EvalInspectPlanVO) => {
    return await request.put({ url: `/datacenter/eval-inspect-plan/update`, data })
  },

  // 删除考察计划管理
  deleteEvalInspectPlan: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-inspect-plan/delete?id=` + id })
  },

  // 导出考察计划管理 Excel
  exportEvalInspectPlan: async (params) => {
    return await request.download({ url: `/datacenter/eval-inspect-plan/export-excel`, params })
  }
}
