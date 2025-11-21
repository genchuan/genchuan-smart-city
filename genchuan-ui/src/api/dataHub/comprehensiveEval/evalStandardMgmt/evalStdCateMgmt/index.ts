import request from '@/config/axios'

// 标准分类管理 VO
export interface EvalStdCatVO {
  id: number // 主键ID
  stdCatId: string // 标准分类ID
  stdCatName: string // 标准分类名称
  stdCatCode: string // 分类编码
  idxSystemId: string // 适用指标体系ID
  idxSystemName: string // 适用指标体系名称
  catDesc: string // 分类描述
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

// 标准分类管理 API
export const EvalStdCatApi = {
  // 查询标准分类管理分页
  getEvalStdCatPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-std-cat/page`, params })
  },

  // 查询标准分类管理详情
  getEvalStdCat: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-std-cat/get?id=` + id })
  },

  // 新增标准分类管理
  createEvalStdCat: async (data: EvalStdCatVO) => {
    return await request.post({ url: `/datacenter/eval-std-cat/create`, data })
  },

  // 修改标准分类管理
  updateEvalStdCat: async (data: EvalStdCatVO) => {
    return await request.put({ url: `/datacenter/eval-std-cat/update`, data })
  },

  // 删除标准分类管理
  deleteEvalStdCat: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-std-cat/delete?id=` + id })
  },

  // 导出标准分类管理 Excel
  exportEvalStdCat: async (params) => {
    return await request.download({ url: `/datacenter/eval-std-cat/export-excel`, params })
  }
}
