import request from '@/config/axios'

// 巡查任务管理 VO
export interface DevelopmentInspectionTaskVO {
  id: number // 主键
  plan: string // 巡查计划编号
  task: string // 巡查任务编号
  record: string // 巡查记录编号
  patrolTime: string // 巡查时间
  region: string // 区域
  staff: string // 人员
  foundProblems: string // 发现的问题
}

// 巡查任务管理 API
export const DevelopmentInspectionTaskApi = {
  // 查询巡查任务管理分页
  getDevelopmentInspectionTaskPage: async (params: any) => {
    return await request.get({ url: `/smartcity/development-inspection-task/page`, params })
  },

  // 查询巡查任务管理详情
  getDevelopmentInspectionTask: async (id: number) => {
    return await request.get({ url: `/smartcity/development-inspection-task/get?id=` + id })
  },

  // 新增巡查任务管理
  createDevelopmentInspectionTask: async (data: DevelopmentInspectionTaskVO) => {
    return await request.post({ url: `/smartcity/development-inspection-task/create`, data })
  },

  // 修改巡查任务管理
  updateDevelopmentInspectionTask: async (data: DevelopmentInspectionTaskVO) => {
    return await request.put({ url: `/smartcity/development-inspection-task/update`, data })
  },

  // 删除巡查任务管理
  deleteDevelopmentInspectionTask: async (id: number) => {
    return await request.delete({ url: `/smartcity/development-inspection-task/delete?id=` + id })
  },

  // 导出巡查任务管理 Excel
  exportDevelopmentInspectionTask: async (params) => {
    return await request.download({ url: `/smartcity/development-inspection-task/export-excel`, params })
  }
}
