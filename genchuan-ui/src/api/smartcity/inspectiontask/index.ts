import request from '@/config/axios'

// 检查任务管理 VO
export interface InspectionTaskVO {
  id: number // ID
  taskId: string // 任务ID
  taskName: string // 任务名称
  initiatingDepartment: string // 发起部门
  plannedExecutionTime: string // 计划执行时间
  deadline: string // 截止时间
  sector: string // 所属行业
  numberObjects: string // 检查对象数量
  numberPersonnel: string // 执法人员数量
  inspectionItems: string // 检查事项
  completionStatus: string // 完成情况
  personInCharge: string // 负责人
}

// 检查任务管理 API
export const InspectionTaskApi = {
  // 查询检查任务分页
  getInspectionTaskPage: async (params: any) => {
    return await request.get({ url: `/smartcity/inspection-task/page`, params })
  },

  // 查询检查任务详情
  getInspectionTask: async (id: number) => {
    return await request.get({ url: `/smartcity/inspection-task/get?id=` + id })
  },

  // 新增检查任务
  createInspectionTask: async (data: InspectionTaskVO) => {
    return await request.post({ url: `/smartcity/inspection-task/create`, data })
  },

  // 修改检查任务
  updateInspectionTask: async (data: InspectionTaskVO) => {
    return await request.put({ url: `/smartcity/inspection-task/update`, data })
  },

  // 删除检查任务
  deleteInspectionTask: async (id: number) => {
    return await request.delete({ url: `/smartcity/inspection-task/delete?id=` + id })
  },

  // 导出检查任务 Excel
  exportInspectionTask: async (params) => {
    return await request.download({ url: `/smartcity/inspection-task/export-excel`, params })
  }
}
