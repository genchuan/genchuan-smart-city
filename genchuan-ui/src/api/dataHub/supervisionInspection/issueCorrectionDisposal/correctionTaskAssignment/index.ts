import request from '@/config/axios'

// 巡查巡检整改任务派发 VO
export interface InspectRectifyTaskVO {
  id: number // 主键
  rectifyTaskId: string // 整改任务ID
  rectifyTaskCode: string // 整改任务编码
  problemId: string // 关联问题ID
  problemCode: string // 关联问题编码
  problemName: string // 关联问题名称
  deptCode: string // 责任部门代码
  deptName: string // 责任部门名称
  leaderUserId: string // 整改负责人ID
  leaderUserName: string // 整改负责人姓名
  rectifyDeadlineHour: number // 整改时限（小时）
  rectifyDeadlineTime: Date // 整改截止时间
  dispatchDesc: string // 派发说明
  rectifyStatus: string // 整改任务状态
  dispatchUser: string // 派发人
  dispatchTime: Date // 派发时间
  bizCreateTime: Date // 创建时间
  bizUpdateTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检整改任务派发 API
export const InspectRectifyTaskApi = {
  // 查询巡查巡检整改任务派发分页
  getInspectRectifyTaskPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-rectify-task/page`, params })
  },

  // 查询巡查巡检整改任务派发详情
  getInspectRectifyTask: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-rectify-task/get?id=` + id })
  },

  // 新增巡查巡检整改任务派发
  createInspectRectifyTask: async (data: InspectRectifyTaskVO) => {
    return await request.post({ url: `/datacenter/inspect-rectify-task/create`, data })
  },

  // 修改巡查巡检整改任务派发
  updateInspectRectifyTask: async (data: InspectRectifyTaskVO) => {
    return await request.put({ url: `/datacenter/inspect-rectify-task/update`, data })
  },

  // 删除巡查巡检整改任务派发
  deleteInspectRectifyTask: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-rectify-task/delete?id=` + id })
  },

  // 导出巡查巡检整改任务派发 Excel
  exportInspectRectifyTask: async (params) => {
    return await request.download({ url: `/datacenter/inspect-rectify-task/export-excel`, params })
  }
}
