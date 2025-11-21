import request from '@/config/axios'

// 任务管理 VO
export interface EvalTaskVO {
  id: number // 主键ID
  evalTaskId: string // 任务ID
  evalTaskName: string // 任务名称
  evalTaskCode: string // 任务编码
  taskTemplateId: string // 关联模板ID
  taskTemplateName: string // 关联模板名称
  objectScope: string // 评价对象范围
  relateObjectIds: string // 关联对象IDs
  relateObjectNames: string // 关联对象Names
  taskStartTime: Date // 任务开始时间
  taskEndTime: Date // 任务结束时间
  taskStatus: string // 任务状态
  dataCollectMethod: string // 数据采集方式
  taskDesc: string // 任务描述
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  updateUserBiz: string // 更新人(业务)
  updateTimeBiz: Date // 更新时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 任务管理 API
export const EvalTaskApi = {
  // 查询任务管理分页
  getEvalTaskPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-task/page`, params })
  },

  // 查询任务管理详情
  getEvalTask: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-task/get?id=` + id })
  },

  // 新增任务管理
  createEvalTask: async (data: EvalTaskVO) => {
    return await request.post({ url: `/datacenter/eval-task/create`, data })
  },

  // 修改任务管理
  updateEvalTask: async (data: EvalTaskVO) => {
    return await request.put({ url: `/datacenter/eval-task/update`, data })
  },

  // 删除任务管理
  deleteEvalTask: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-task/delete?id=` + id })
  },

  // 导出任务管理 Excel
  exportEvalTask: async (params) => {
    return await request.download({ url: `/datacenter/eval-task/export-excel`, params })
  }
}
