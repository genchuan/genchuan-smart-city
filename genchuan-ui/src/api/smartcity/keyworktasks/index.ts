import request from '@/config/axios'

// 重点工作任务 VO
export interface KeyWorkTasksVO {
  id: number // 主键
  taskNumber: string // 任务编号
  task: string // 任务名称
  taskDescription: string // 任务描述
  startTime: string // 开始时间
  atTheEnd: string // 结束时
  assessmentIndicators: string // 考核指标
  riskAssessment: string // 风险评估
  notes: string // 备注
}

// 重点工作任务 API
export const KeyWorkTasksApi = {
  // 查询重点工作任务分页
  getKeyWorkTasksPage: async (params: any) => {
    return await request.get({ url: `/smartcity/key-work-tasks/page`, params })
  },

  // 查询重点工作任务详情
  getKeyWorkTasks: async (id: number) => {
    return await request.get({ url: `/smartcity/key-work-tasks/get?id=` + id })
  },

  // 新增重点工作任务
  createKeyWorkTasks: async (data: KeyWorkTasksVO) => {
    return await request.post({ url: `/smartcity/key-work-tasks/create`, data })
  },

  // 修改重点工作任务
  updateKeyWorkTasks: async (data: KeyWorkTasksVO) => {
    return await request.put({ url: `/smartcity/key-work-tasks/update`, data })
  },

  // 删除重点工作任务
  deleteKeyWorkTasks: async (id: number) => {
    return await request.delete({ url: `/smartcity/key-work-tasks/delete?id=` + id })
  },

  // 导出重点工作任务 Excel
  exportKeyWorkTasks: async (params) => {
    return await request.download({ url: `/smartcity/key-work-tasks/export-excel`, params })
  }
}