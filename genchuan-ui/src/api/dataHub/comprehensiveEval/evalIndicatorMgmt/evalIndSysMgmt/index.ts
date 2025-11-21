import request from '@/config/axios'

// 指标体系管理 VO
export interface EvalIdxSystemVO {
  id: number // 主键ID
  idxSystemId: string // 指标体系ID
  idxSystemName: string // 指标体系名称
  systemCode: string // 体系编码
  applyObjectType: string // 适用对象类型
  systemVersion: string // 体系版本
  systemDesc: string // 体系描述
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

// 指标体系管理 API
export const EvalIdxSystemApi = {
  // 查询指标体系管理分页
  getEvalIdxSystemPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-idx-system/page`, params })
  },

  // 查询指标体系管理详情
  getEvalIdxSystem: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-idx-system/get?id=` + id })
  },

  // 新增指标体系管理
  createEvalIdxSystem: async (data: EvalIdxSystemVO) => {
    return await request.post({ url: `/datacenter/eval-idx-system/create`, data })
  },

  // 修改指标体系管理
  updateEvalIdxSystem: async (data: EvalIdxSystemVO) => {
    return await request.put({ url: `/datacenter/eval-idx-system/update`, data })
  },

  // 删除指标体系管理
  deleteEvalIdxSystem: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-idx-system/delete?id=` + id })
  },

  // 导出指标体系管理 Excel
  exportEvalIdxSystem: async (params) => {
    return await request.download({ url: `/datacenter/eval-idx-system/export-excel`, params })
  }
}
