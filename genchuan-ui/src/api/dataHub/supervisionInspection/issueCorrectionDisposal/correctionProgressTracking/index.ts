import request from '@/config/axios'

// 巡查巡检整改进度跟踪视图 VO
export interface InspectRectifyTrackVO {
  id: number // 主键
  rectifyTaskId: string // 整改任务ID
  rectifyTaskCode: string // 整改任务编码
  problemName: string // 关联问题名称
  deptName: string // 责任部门名称
  leaderUserName: string // 整改负责人姓名
  rectifyStatus: string // 整改任务状态
  rectifyDeadlineHour: number // 整改时限（小时）
  rectifyDeadlineTime: Date // 整改截止时间
  remainTimeMin: number // 剩余时间（分钟）
  isOverdue: boolean // 是否超期
  latestProgressTime: Date // 最新进展时间
  latestProgressDesc: string // 最新进展描述
  latestProgressPhoto: string // 最新进展照片URL
  remindCount: number // 催办次数
  lastRemindTime: Date // 最后催办时间
  dispatchTime: Date // 派发时间
}

// 巡查巡检整改进度跟踪视图 API
export const InspectRectifyTrackApi = {
  // 查询巡查巡检整改进度跟踪视图分页
  getInspectRectifyTrackPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-rectify-track/page`, params })
  },

  // 查询巡查巡检整改进度跟踪视图详情
  getInspectRectifyTrack: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-rectify-track/get?id=` + id })
  },

  // 新增巡查巡检整改进度跟踪视图
  createInspectRectifyTrack: async (data: InspectRectifyTrackVO) => {
    return await request.post({ url: `/datacenter/inspect-rectify-track/create`, data })
  },

  // 修改巡查巡检整改进度跟踪视图
  updateInspectRectifyTrack: async (data: InspectRectifyTrackVO) => {
    return await request.put({ url: `/datacenter/inspect-rectify-track/update`, data })
  },

  // 删除巡查巡检整改进度跟踪视图
  deleteInspectRectifyTrack: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-rectify-track/delete?id=` + id })
  },

  // 导出巡查巡检整改进度跟踪视图 Excel
  exportInspectRectifyTrack: async (params) => {
    return await request.download({ url: `/datacenter/inspect-rectify-track/export-excel`, params })
  }
}
