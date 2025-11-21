import request from '@/config/axios'

// 巡查任务 VO
export interface InspectionTaskAVO {
  id: number // 主键
  taskNumber: string // 任务编号
  task: string // 任务名称
  taskDescription: string // 任务描述
  startingTimeA: string // 巡查地点开始时间
  endTimeB: string // 结束时间优先级
  inspectionItems: string // 检查项目
  notes: string // 备注
}

// 巡查任务 API
export const InspectionTaskAApi = {
  // 查询巡查任务分页
  getInspectionTaskAPage: async (params: any) => {
    return await request.get({ url: `/smartcity/inspection-task-a/page`, params })
  },

  // 查询巡查任务详情
  getInspectionTaskA: async (id: number) => {
    return await request.get({ url: `/smartcity/inspection-task-a/get?id=` + id })
  },

  // 新增巡查任务
  createInspectionTaskA: async (data: InspectionTaskAVO) => {
    return await request.post({ url: `/smartcity/inspection-task-a/create`, data })
  },

  // 修改巡查任务
  updateInspectionTaskA: async (data: InspectionTaskAVO) => {
    return await request.put({ url: `/smartcity/inspection-task-a/update`, data })
  },

  // 删除巡查任务
  deleteInspectionTaskA: async (id: number) => {
    return await request.delete({ url: `/smartcity/inspection-task-a/delete?id=` + id })
  },

  // 导出巡查任务 Excel
  exportInspectionTaskA: async (params) => {
    return await request.download({ url: `/smartcity/inspection-task-a/export-excel`, params })
  }
}