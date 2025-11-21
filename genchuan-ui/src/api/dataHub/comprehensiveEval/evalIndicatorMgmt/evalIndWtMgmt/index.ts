import request from '@/config/axios'

// 指标权重管理 VO
export interface EvalIdxWeightVO {
  id: number // 主键ID
  idxWeightId: string // 权重ID
  idxSystemId: string // 所属指标体系ID
  idxSystemName: string // 所属指标体系名称
  weightType: string // 权重类型
  relateId: string // 关联ID
  relateName: string // 关联名称
  weightValue: number // 权重值(%)
  weightDesc: string // 权重说明
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  updateUserBiz: string // 更新人(业务)
  updateTimeBiz: Date // 更新时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 指标权重管理 API
export const EvalIdxWeightApi = {
  // 查询指标权重管理分页
  getEvalIdxWeightPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-idx-weight/page`, params })
  },

  // 查询指标权重管理详情
  getEvalIdxWeight: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-idx-weight/get?id=` + id })
  },

  // 新增指标权重管理
  createEvalIdxWeight: async (data: EvalIdxWeightVO) => {
    return await request.post({ url: `/datacenter/eval-idx-weight/create`, data })
  },

  // 修改指标权重管理
  updateEvalIdxWeight: async (data: EvalIdxWeightVO) => {
    return await request.put({ url: `/datacenter/eval-idx-weight/update`, data })
  },

  // 删除指标权重管理
  deleteEvalIdxWeight: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-idx-weight/delete?id=` + id })
  },

  // 导出指标权重管理 Excel
  exportEvalIdxWeight: async (params) => {
    return await request.download({ url: `/datacenter/eval-idx-weight/export-excel`, params })
  }
}
