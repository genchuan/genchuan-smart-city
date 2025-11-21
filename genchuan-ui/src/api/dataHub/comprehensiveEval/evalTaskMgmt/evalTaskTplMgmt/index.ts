import request from '@/config/axios'

// 任务模板管理 VO
export interface EvalTaskTemplateVO {
  id: number // 主键ID
  taskTemplateId: string // 模板ID
  taskTemplateName: string // 模板名称
  taskTemplateCode: string // 模板编码
  applyObjectType: string // 适用对象类型
  idxSystemId: string // 关联指标体系ID
  idxSystemName: string // 关联指标体系名称
  evalSubjectId: string // 关联评价主体ID
  evalSubjectName: string // 关联评价主体名称
  taskCycle: string // 任务周期
  customCycle: string // 自定义周期
  templateDesc: string // 模板描述
  enableStatus: string // 启用状态
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  updateUserBiz: string // 更新人(业务)
  updateTimeBiz: Date // 更新时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 任务模板管理 API
export const EvalTaskTemplateApi = {
  // 查询任务模板管理分页
  getEvalTaskTemplatePage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-task-template/page`, params })
  },

  // 查询任务模板管理详情
  getEvalTaskTemplate: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-task-template/get?id=` + id })
  },

  // 新增任务模板管理
  createEvalTaskTemplate: async (data: EvalTaskTemplateVO) => {
    return await request.post({ url: `/datacenter/eval-task-template/create`, data })
  },

  // 修改任务模板管理
  updateEvalTaskTemplate: async (data: EvalTaskTemplateVO) => {
    return await request.put({ url: `/datacenter/eval-task-template/update`, data })
  },

  // 删除任务模板管理
  deleteEvalTaskTemplate: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-task-template/delete?id=` + id })
  },

  // 导出任务模板管理 Excel
  exportEvalTaskTemplate: async (params) => {
    return await request.download({ url: `/datacenter/eval-task-template/export-excel`, params })
  }
}
