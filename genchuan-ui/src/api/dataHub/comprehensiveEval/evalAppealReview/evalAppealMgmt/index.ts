import request from '@/config/axios'

// 申诉管理 VO
export interface EvalAppealVO {
  id: number // 主键ID
  appealId: string // 申诉ID
  appealCode: string // 申诉编号
  resultPublicId: string // 关联公示ID
  evalTaskId: string // 关联任务ID
  evalObjectId: string // 评价对象ID
  evalObjectName: string // 评价对象名称
  appealUserId: string // 申诉人ID
  appealUserName: string // 申诉人姓名
  appealReason: string // 申诉理由
  proofMats: string // 证明材料
  appealStatus: string // 申诉状态
  submitTime: Date // 提交时间
  cancelTime: Date // 撤销时间
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 申诉管理 API
export const EvalAppealApi = {
  // 查询申诉管理分页
  getEvalAppealPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-appeal/page`, params })
  },

  // 查询申诉管理详情
  getEvalAppeal: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-appeal/get?id=` + id })
  },

  // 新增申诉管理
  createEvalAppeal: async (data: EvalAppealVO) => {
    return await request.post({ url: `/datacenter/eval-appeal/create`, data })
  },

  // 修改申诉管理
  updateEvalAppeal: async (data: EvalAppealVO) => {
    return await request.put({ url: `/datacenter/eval-appeal/update`, data })
  },

  // 删除申诉管理
  deleteEvalAppeal: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-appeal/delete?id=` + id })
  },

  // 导出申诉管理 Excel
  exportEvalAppeal: async (params) => {
    return await request.download({ url: `/datacenter/eval-appeal/export-excel`, params })
  }
}
