import request from '@/config/axios'

// 任务统筹管理 VO
export interface TaskOverallMngVO {
  id: number // 主键ID
  overallId: string // 统筹ID
  taskNo: string // 任务编号
  taskName: string // 任务名称
  taskType: string // 任务类型
  planId: string // 关联方案ID
  commandId: string // 关联指令ID
  crossId: string // 关联跨域ID
  taskStatus: string // 任务状态
  priority: string // 优先级
  chargerId: string // 任务负责人账号
  chargerName: string // 任务负责人姓名
  planCompleteTime: Date // 计划完成时间
  actualCompleteTime: Date // 实际完成时间
  delayEndure: string // 延迟时长
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 任务统筹管理 API
export const TaskOverallMngApi = {
  // 查询任务统筹管理分页
  getTaskOverallMngPage: async (params: any) => {
    return await request.get({ url: `/datacenter/task-overall-mng/page`, params })
  },

  // 查询任务统筹管理详情
  getTaskOverallMng: async (id: number) => {
    return await request.get({ url: `/datacenter/task-overall-mng/get?id=` + id })
  },

  // 新增任务统筹管理
  createTaskOverallMng: async (data: TaskOverallMngVO) => {
    return await request.post({ url: `/datacenter/task-overall-mng/create`, data })
  },

  // 修改任务统筹管理
  updateTaskOverallMng: async (data: TaskOverallMngVO) => {
    return await request.put({ url: `/datacenter/task-overall-mng/update`, data })
  },

  // 删除任务统筹管理
  deleteTaskOverallMng: async (id: number) => {
    return await request.delete({ url: `/datacenter/task-overall-mng/delete?id=` + id })
  },

  // 导出任务统筹管理 Excel
  exportTaskOverallMng: async (params) => {
    return await request.download({ url: `/datacenter/task-overall-mng/export-excel`, params })
  },
}