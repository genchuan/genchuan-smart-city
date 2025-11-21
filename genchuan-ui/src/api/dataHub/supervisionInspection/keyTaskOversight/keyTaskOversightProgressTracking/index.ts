import request from '@/config/axios'

// 巡查巡检督办进度跟踪视图 VO
export interface InspectSupvTrackViewVO {
  id: number // 主键
  supvTaskId: string // 督办任务ID
  supvTaskCode: string // 督办任务编码
  problemName: string // 关联问题名称
  supvUserName: string // 督办人姓名
  leaderUserName: string // 整改负责人姓名
  supvStatus: string // 督办任务状态
  supvDeadlineTime: Date // 督办截止时间
  isOverdue: boolean // 是否超期
  latestSupvTime: Date // 最新督办进展时间
  latestSupvType: string // 最新督办进展类型
  latestSupvContent: string // 最新督办进展内容
  supvOperCount: number // 督办操作次数
  rectifyStatus: string // 关联整改任务状态
}

// 巡查巡检督办进度跟踪视图 API
export const InspectSupvTrackViewApi = {
  // 查询巡查巡检督办进度跟踪视图分页
  getInspectSupvTrackViewPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-supv-track-view/page`, params })
  },

  // 查询巡查巡检督办进度跟踪视图详情
  getInspectSupvTrackView: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-supv-track-view/get?id=` + id })
  },

  // 新增巡查巡检督办进度跟踪视图
  createInspectSupvTrackView: async (data: InspectSupvTrackViewVO) => {
    return await request.post({ url: `/datacenter/inspect-supv-track-view/create`, data })
  },

  // 修改巡查巡检督办进度跟踪视图
  updateInspectSupvTrackView: async (data: InspectSupvTrackViewVO) => {
    return await request.put({ url: `/datacenter/inspect-supv-track-view/update`, data })
  },

  // 删除巡查巡检督办进度跟踪视图
  deleteInspectSupvTrackView: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-supv-track-view/delete?id=` + id })
  },

  // 导出巡查巡检督办进度跟踪视图 Excel
  exportInspectSupvTrackView: async (params) => {
    return await request.download({
      url: `/datacenter/inspect-supv-track-view/export-excel`,
      params
    })
  }
}
