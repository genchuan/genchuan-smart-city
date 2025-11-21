import request from '@/config/axios'

// 巡查计划 VO
export interface InspectionInspectionPlanVO {
  id: number // 主键
  inspectionProject: string // 巡查项目
  patrolLocation: string // 巡查地点
  inspectionCycle: string // 巡查周期
  patrolTime: Date // 巡查时间
  inspectionMethod: string // 巡查方式
  notes: string // 备注
}

// 巡查计划 API
export const InspectionInspectionPlanApi = {
  // 查询巡查计划分页
  getInspectionInspectionPlanPage: async (params: any) => {
    return await request.get({ url: `/smartcity/inspection-inspection-plan/page`, params })
  },

  // 查询巡查计划详情
  getInspectionInspectionPlan: async (id: number) => {
    return await request.get({ url: `/smartcity/inspection-inspection-plan/get?id=` + id })
  },

  // 新增巡查计划
  createInspectionInspectionPlan: async (data: InspectionInspectionPlanVO) => {
    return await request.post({ url: `/smartcity/inspection-inspection-plan/create`, data })
  },

  // 修改巡查计划
  updateInspectionInspectionPlan: async (data: InspectionInspectionPlanVO) => {
    return await request.put({ url: `/smartcity/inspection-inspection-plan/update`, data })
  },

  // 删除巡查计划
  deleteInspectionInspectionPlan: async (id: number) => {
    return await request.delete({ url: `/smartcity/inspection-inspection-plan/delete?id=` + id })
  },

  // 导出巡查计划 Excel
  exportInspectionInspectionPlan: async (params) => {
    return await request.download({ url: `/smartcity/inspection-inspection-plan/export-excel`, params })
  }
}