import request from '@/config/axios'

// 巡查巡检任务跟踪 VO
export interface InspectTaskTrackVO {
  id: number // 主键ID
  taskId: string // 任务ID
  taskCode: string // 任务编码
  taskName: string // 任务名称
  taskType: string // 任务类型
  personId: string // 负责人员ID
  personName: string // 负责人员姓名
  areaCode: string // 所属区域代码
  areaName: string // 所属区域名称
  taskStatus: string // 任务状态
  startTime: Date // 开始时间
  completeTime: Date // 完成时间
  deadlineTime: Date // 截止时间
  totalCkItem: number // 检查项总数
  completedCkItem: number // 已完成检查项
  uploadPhotoCount: number // 上传照片数
  isOvertime: number // 是否超时
  remindCount: number // 催办次数
  lastRemindTime: Date // 最后催办时间
}

// 巡查巡检任务跟踪 API
export const InspectTaskTrackApi = {
  // 查询巡查巡检任务跟踪分页
  getInspectTaskTrackPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-task-track/page`, params })
  },

  // 查询巡查巡检任务跟踪详情
  getInspectTaskTrack: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-task-track/get?id=` + id })
  },

  // 新增巡查巡检任务跟踪
  createInspectTaskTrack: async (data: InspectTaskTrackVO) => {
    return await request.post({ url: `/datacenter/inspect-task-track/create`, data })
  },

  // 修改巡查巡检任务跟踪
  updateInspectTaskTrack: async (data: InspectTaskTrackVO) => {
    return await request.put({ url: `/datacenter/inspect-task-track/update`, data })
  },

  // 删除巡查巡检任务跟踪
  deleteInspectTaskTrack: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-task-track/delete?id=` + id })
  },

  // 导出巡查巡检任务跟踪 Excel
  exportInspectTaskTrack: async (params) => {
    return await request.download({ url: `/datacenter/inspect-task-track/export-excel`, params })
  }
}
