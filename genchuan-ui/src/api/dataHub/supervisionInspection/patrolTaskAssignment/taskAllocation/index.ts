import request from '@/config/axios'

// 巡查巡检任务分配记录 VO
export interface InspectTaskAllocateRecVO {
  id: number // 主键
  allocateId: string // 分配ID
  taskId: string // 任务ID
  taskCode: string // 任务编码
  oldPersonId: string // 原负责人员ID
  oldPersonName: string // 原负责人员姓名
  newPersonId: string // 新负责人员ID
  newPersonName: string // 新负责人员姓名
  allocateReason: string // 分配原因
  allocateTime: Date // 分配时间
  allocateUser: string // 分配人
  taskStatusChange: string // 任务状态变更
  taskType: string // 任务类型
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检任务分配记录 API
export const InspectTaskAllocateRecApi = {
  // 查询巡查巡检任务分配记录分页
  getInspectTaskAllocateRecPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-task-allocate-rec/page`, params })
  },

  // 查询巡查巡检任务分配记录详情
  getInspectTaskAllocateRec: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-task-allocate-rec/get?id=` + id })
  },

  // 新增巡查巡检任务分配记录
  createInspectTaskAllocateRec: async (data: InspectTaskAllocateRecVO) => {
    return await request.post({ url: `/datacenter/inspect-task-allocate-rec/create`, data })
  },

  // 修改巡查巡检任务分配记录
  updateInspectTaskAllocateRec: async (data: InspectTaskAllocateRecVO) => {
    return await request.put({ url: `/datacenter/inspect-task-allocate-rec/update`, data })
  },

  // 删除巡查巡检任务分配记录
  deleteInspectTaskAllocateRec: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-task-allocate-rec/delete?id=` + id })
  },

  // 导出巡查巡检任务分配记录 Excel
  exportInspectTaskAllocateRec: async (params) => {
    return await request.download({
      url: `/datacenter/inspect-task-allocate-rec/export-excel`,
      params
    })
  }
}
