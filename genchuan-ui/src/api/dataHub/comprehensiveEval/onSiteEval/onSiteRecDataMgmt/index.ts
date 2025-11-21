import request from '@/config/axios'

// 考察记录数据 VO
export interface EvalInspectRecVO {
  id: number // 主键ID
  inspectRecId: string // 记录ID
  inspectPlanId: string // 关联计划ID
  inspectPlanName: string // 关联计划名称
  evalObjectId: string // 考察对象ID
  evalObjectName: string // 考察对象名称
  idxItemId: string // 关联指标项ID
  idxItemName: string // 关联指标项名称
  inspectScore: number // 考察得分
  inspectLevel: string // 考察等级
  problemDesc: string // 问题描述
  onSitePhotos: string // 现场照片
  inspectTime: Date // 考察时间
  inspectorId: string // 考察人员ID
  inspectorName: string // 考察人员姓名
  recStatus: string // 记录状态
  auditorId: string // 审核人ID
  auditorName: string // 审核人姓名
  auditOpinion: string // 审核意见
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 考察记录数据 API
export const EvalInspectRecApi = {
  // 查询考察记录数据分页
  getEvalInspectRecPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-inspect-rec/page`, params })
  },

  // 查询考察记录数据详情
  getEvalInspectRec: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-inspect-rec/get?id=` + id })
  },

  // 新增考察记录数据
  createEvalInspectRec: async (data: EvalInspectRecVO) => {
    return await request.post({ url: `/datacenter/eval-inspect-rec/create`, data })
  },

  // 修改考察记录数据
  updateEvalInspectRec: async (data: EvalInspectRecVO) => {
    return await request.put({ url: `/datacenter/eval-inspect-rec/update`, data })
  },

  // 删除考察记录数据
  deleteEvalInspectRec: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-inspect-rec/delete?id=` + id })
  },

  // 导出考察记录数据 Excel
  exportEvalInspectRec: async (params) => {
    return await request.download({ url: `/datacenter/eval-inspect-rec/export-excel`, params })
  }
}
