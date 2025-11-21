import request from '@/config/axios'

// 巡查计划管理 VO
export interface DevelopmentInspectionPlanVO {
  id: number // 主键
  patrolTime: string // 巡查时间
  region: string // 区域
  staff: string // 人员
}

// 巡查计划管理 API
export const DevelopmentInspectionPlanApi = {
  // 查询巡查计划管理分页
  getDevelopmentInspectionPlanPage: async (params: any) => {
    return await request.get({ url: `/smartcity/development-inspection-plan/page`, params })
  },

  // 查询巡查计划管理详情
  getDevelopmentInspectionPlan: async (id: number) => {
    return await request.get({ url: `/smartcity/development-inspection-plan/get?id=` + id })
  },

  // 新增巡查计划管理
  createDevelopmentInspectionPlan: async (data: DevelopmentInspectionPlanVO) => {
    return await request.post({ url: `/smartcity/development-inspection-plan/create`, data })
  },

  // 修改巡查计划管理
  updateDevelopmentInspectionPlan: async (data: DevelopmentInspectionPlanVO) => {
    return await request.put({ url: `/smartcity/development-inspection-plan/update`, data })
  },

  // 删除巡查计划管理
  deleteDevelopmentInspectionPlan: async (id: number) => {
    return await request.delete({ url: `/smartcity/development-inspection-plan/delete?id=` + id })
  },

  // 导出巡查计划管理 Excel
  exportDevelopmentInspectionPlan: async (params) => {
    return await request.download({ url: `/smartcity/development-inspection-plan/export-excel`, params })
  }
}