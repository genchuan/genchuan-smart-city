import request from '@/config/axios'

// 巡查巡检重点督办任务 VO
export interface InspectSupvTaskVO {
  id: number // 主键
  supvTaskId: string // 督办任务ID
  supvTaskCode: string // 督办任务编码
  rectifyTaskId: string // 关联整改任务ID
  rectifyTaskCode: string // 关联整改任务编码
  problemId: string // 关联问题ID
  problemName: string // 关联问题名称
  supvUserId: string // 督办人ID
  supvUserName: string // 督办人姓名
  supvFrequency: string // 督办频次
  supvDeadlineTime: Date // 督办截止时间
  supvRequirement: string // 督办要求
  supvStatus: string // 督办任务状态
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检重点督办任务 API
export const InspectSupvTaskApi = {
  // 查询巡查巡检重点督办任务分页
  getInspectSupvTaskPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-supv-task/page`, params })
  },

  // 查询巡查巡检重点督办任务详情
  getInspectSupvTask: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-supv-task/get?id=` + id })
  },

  // 新增巡查巡检重点督办任务
  createInspectSupvTask: async (data: InspectSupvTaskVO) => {
    return await request.post({ url: `/datacenter/inspect-supv-task/create`, data })
  },

  // 修改巡查巡检重点督办任务
  updateInspectSupvTask: async (data: InspectSupvTaskVO) => {
    return await request.put({ url: `/datacenter/inspect-supv-task/update`, data })
  },

  // 删除巡查巡检重点督办任务
  deleteInspectSupvTask: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-supv-task/delete?id=` + id })
  },

  // 导出巡查巡检重点督办任务 Excel
  exportInspectSupvTask: async (params) => {
    return await request.download({ url: `/datacenter/inspect-supv-task/export-excel`, params })
  }
}
