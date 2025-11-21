import request from '@/config/axios'

// 结果关联推送 VO
export interface EvalResultPushVO {
  id: number // 主键ID
  resultPushId: string // 推送ID
  resultPushCode: string // 推送编号
  resultArchId: string // 关联存档ID
  evalObjectId: string // 评价对象ID
  evalObjectName: string // 评价对象名称
  pushTarget: string // 推送目标
  pushMethod: string // 推送方式
  pushAddress: string // 推送地址
  pushContent: string // 推送内容
  pushStatus: string // 推送状态
  pushTime: Date // 推送时间
  failReason: string // 失败原因
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 结果关联推送 API
export const EvalResultPushApi = {
  // 查询结果关联推送分页
  getEvalResultPushPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-result-push/page`, params })
  },

  // 查询结果关联推送详情
  getEvalResultPush: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-result-push/get?id=` + id })
  },

  // 新增结果关联推送
  createEvalResultPush: async (data: EvalResultPushVO) => {
    return await request.post({ url: `/datacenter/eval-result-push/create`, data })
  },

  // 修改结果关联推送
  updateEvalResultPush: async (data: EvalResultPushVO) => {
    return await request.put({ url: `/datacenter/eval-result-push/update`, data })
  },

  // 删除结果关联推送
  deleteEvalResultPush: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-result-push/delete?id=` + id })
  },

  // 导出结果关联推送 Excel
  exportEvalResultPush: async (params) => {
    return await request.download({ url: `/datacenter/eval-result-push/export-excel`, params })
  }
}
