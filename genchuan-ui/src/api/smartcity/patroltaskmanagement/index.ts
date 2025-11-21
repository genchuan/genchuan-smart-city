import request from '@/config/axios'

// 巡査任务管理 VO
export interface PatrolTaskManagementVO {
  id: number // 主键
  task: string // 任务名称
  belongingPlan: string // 所属计划
  patrolArea: string // 巡查区域
  patrolTime: string // 巡查时间
  executive: string // 执行人员
  expectedDuration: string // 预计时长
  taskDescription: string // 任务描述
  keyInspectionPoints: string // 巡查重点
  listOfCarryingEquipment: string // 携带设备清单
  completionStatusDescription: string // 完成情况说明
  abnormalSituationRecord: string // 异常情况记录
  handlingMeasures: string // 处理措施
}

// 巡査任务管理 API
export const PatrolTaskManagementApi = {
  // 查询巡査任务管理分页
  getPatrolTaskManagementPage: async (params: any) => {
    return await request.get({ url: `/smartcity/patrol-task-management/page`, params })
  },

  // 查询巡査任务管理详情
  getPatrolTaskManagement: async (id: number) => {
    return await request.get({ url: `/smartcity/patrol-task-management/get?id=` + id })
  },

  // 新增巡査任务管理
  createPatrolTaskManagement: async (data: PatrolTaskManagementVO) => {
    return await request.post({ url: `/smartcity/patrol-task-management/create`, data })
  },

  // 修改巡査任务管理
  updatePatrolTaskManagement: async (data: PatrolTaskManagementVO) => {
    return await request.put({ url: `/smartcity/patrol-task-management/update`, data })
  },

  // 删除巡査任务管理
  deletePatrolTaskManagement: async (id: number) => {
    return await request.delete({ url: `/smartcity/patrol-task-management/delete?id=` + id })
  },

  // 导出巡査任务管理 Excel
  exportPatrolTaskManagement: async (params) => {
    return await request.download({ url: `/smartcity/patrol-task-management/export-excel`, params })
  }
}