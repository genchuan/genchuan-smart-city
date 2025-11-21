import request from '@/config/axios'

// 养护计划 VO
export interface MaintenancePlanVO {
  id: number // 主键
  planNumber: string // 计划编号
  planName: string // 计划名称
  planFormulationDate: Date // 计划制定日期
  startDate: Date // 计划开始日期
  plannedEndDate: Date // 计划结束日期
  maintenancePersonnel: string // 负责养护人员
  maintainParcel: string // 养护地块
  maintenanceContent: string // 养护内容
  maintenanceFrequency: string // 养护频率
  maintenanceDemand: string // 养护资源需求
  planStatus: string // 计划状态
  plannedBudget: string // 计划预算
  approver: string // 审批人
  approvalOpinion: string // 审批意见
  approvalDate: Date // 审批日期
}

// 养护计划 API
export const MaintenancePlanApi = {
  // 查询养护计划分页
  getMaintenancePlanPage: async (params: any) => {
    return await request.get({ url: `/smartcity/maintenance-plan/page`, params })
  },

  // 查询养护计划详情
  getMaintenancePlan: async (id: number) => {
    return await request.get({ url: `/smartcity/maintenance-plan/get?id=` + id })
  },

  // 新增养护计划
  createMaintenancePlan: async (data: MaintenancePlanVO) => {
    return await request.post({ url: `/smartcity/maintenance-plan/create`, data })
  },

  // 修改养护计划
  updateMaintenancePlan: async (data: MaintenancePlanVO) => {
    return await request.put({ url: `/smartcity/maintenance-plan/update`, data })
  },

  // 删除养护计划
  deleteMaintenancePlan: async (id: number) => {
    return await request.delete({ url: `/smartcity/maintenance-plan/delete?id=` + id })
  },

  // 导出养护计划 Excel
  exportMaintenancePlan: async (params) => {
    return await request.download({ url: `/smartcity/maintenance-plan/export-excel`, params })
  }
}