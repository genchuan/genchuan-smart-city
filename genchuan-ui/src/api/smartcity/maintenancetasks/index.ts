import request from '@/config/axios'

// 养护任务 VO
export interface MaintenanceTasksVO {
  id: number // 主键
  taskNumber: string // 任务编号
  task: string // 任务名称
  maintainTheLandParcel: string // 养护地块
  maintenancePersonnel: string // 养护人员
  taskStartTime: Date // 任务开始时间
  taskEndTime: Date // 任务结束时间
  taskContent: string // 任务内容
  requiredTools: string // 所需工具
  requiredMaterials: string // 所需材料
  taskPriority: string // 任务优先级
  status: string // 任务状态
  completionStatusDescription: string // 完成情况说明
}

// 养护任务 API
export const MaintenanceTasksApi = {
  // 查询养护任务分页
  getMaintenanceTasksPage: async (params: any) => {
    return await request.get({ url: `/smartcity/maintenance-tasks/page`, params })
  },

  // 查询养护任务详情
  getMaintenanceTasks: async (id: number) => {
    return await request.get({ url: `/smartcity/maintenance-tasks/get?id=` + id })
  },

  // 新增养护任务
  createMaintenanceTasks: async (data: MaintenanceTasksVO) => {
    return await request.post({ url: `/smartcity/maintenance-tasks/create`, data })
  },

  // 修改养护任务
  updateMaintenanceTasks: async (data: MaintenanceTasksVO) => {
    return await request.put({ url: `/smartcity/maintenance-tasks/update`, data })
  },

  // 删除养护任务
  deleteMaintenanceTasks: async (id: number) => {
    return await request.delete({ url: `/smartcity/maintenance-tasks/delete?id=` + id })
  },

  // 导出养护任务 Excel
  exportMaintenanceTasks: async (params) => {
    return await request.download({ url: `/smartcity/maintenance-tasks/export-excel`, params })
  }
}