import request from '@/config/axios'

// 巡查计划管理 VO
export interface InspectionPlanManagementVO {
  id: number // 主键
  planName: string // 计划名称
  patrolArea: string // 巡查区域
  inspectionCycle: string // 巡查周期
  scheduledStartTime: string // 计划开始时间
  plannedEndTime: string // 计划结束时间
  arrangementOfPatrolPersonnel: string // 巡查人员安排
  inspectionContent: string // 巡查内容
  inspectionStandards: string // 巡查标准
  emergencyResponsePlan: string // 应急处置预案
  notes: string // 备注
}

// 巡查计划管理 API
export const InspectionPlanManagementApi = {
  // 查询巡查计划管理分页
  getInspectionPlanManagementPage: async (params: any) => {
    return await request.get({ url: `/smartcity/inspection-plan-management/page`, params })
  },

  // 查询巡查计划管理详情
  getInspectionPlanManagement: async (id: number) => {
    return await request.get({ url: `/smartcity/inspection-plan-management/get?id=` + id })
  },

  // 新增巡查计划管理
  createInspectionPlanManagement: async (data: InspectionPlanManagementVO) => {
    return await request.post({ url: `/smartcity/inspection-plan-management/create`, data })
  },

  // 修改巡查计划管理
  updateInspectionPlanManagement: async (data: InspectionPlanManagementVO) => {
    return await request.put({ url: `/smartcity/inspection-plan-management/update`, data })
  },

  // 删除巡查计划管理
  deleteInspectionPlanManagement: async (id: number) => {
    return await request.delete({ url: `/smartcity/inspection-plan-management/delete?id=` + id })
  },

  // 导出巡查计划管理 Excel
  exportInspectionPlanManagement: async (params) => {
    return await request.download({ url: `/smartcity/inspection-plan-management/export-excel`, params })
  }
}