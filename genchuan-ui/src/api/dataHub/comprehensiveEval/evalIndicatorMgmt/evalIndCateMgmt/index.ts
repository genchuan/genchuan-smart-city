import request from '@/config/axios'

// 指标分类管理 VO
export interface EvalIdxCatVO {
  id: number // 主键ID
  idxCatId: string // 指标分类ID
  idxCatName: string // 指标分类名称
  catCode: string // 分类编码
  idxSystemId: string // 所属指标体系ID
  idxSystemName: string // 所属指标体系名称
  sortNum: number // 排序序号
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

// 指标分类管理 API
export const EvalIdxCatApi = {
  // 查询指标分类管理分页
  getEvalIdxCatPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-idx-cat/page`, params })
  },

  // 查询指标分类管理详情
  getEvalIdxCat: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-idx-cat/get?id=` + id })
  },

  // 新增指标分类管理
  createEvalIdxCat: async (data: EvalIdxCatVO) => {
    return await request.post({ url: `/datacenter/eval-idx-cat/create`, data })
  },

  // 修改指标分类管理
  updateEvalIdxCat: async (data: EvalIdxCatVO) => {
    return await request.put({ url: `/datacenter/eval-idx-cat/update`, data })
  },

  // 删除指标分类管理
  deleteEvalIdxCat: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-idx-cat/delete?id=` + id })
  },

  // 导出指标分类管理 Excel
  exportEvalIdxCat: async (params) => {
    return await request.download({ url: `/datacenter/eval-idx-cat/export-excel`, params })
  }
}
