import request from '@/config/axios'

// 指标项管理 VO
export interface EvalIdxItemVO {
  id: number // 主键ID
  idxItemId: string // 指标项ID
  idxItemName: string // 指标项名称
  idxItemCode: string // 指标项编码
  idxCatId: string // 所属指标分类ID
  idxCatName: string // 所属指标分类名称
  idxType: string // 指标类型
  calcMethod: string // 计算方式
  dataSourceTable: string // 数据来源表
  dataSourceField: string // 数据来源字段
  qualifiedThreshold: string // 达标阈值
  idxDesc: string // 指标描述
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

// 指标项管理 API
export const EvalIdxItemApi = {
  // 查询指标项管理分页
  getEvalIdxItemPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-idx-item/page`, params })
  },

  // 查询指标项管理详情
  getEvalIdxItem: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-idx-item/get?id=` + id })
  },

  // 新增指标项管理
  createEvalIdxItem: async (data: EvalIdxItemVO) => {
    return await request.post({ url: `/datacenter/eval-idx-item/create`, data })
  },

  // 修改指标项管理
  updateEvalIdxItem: async (data: EvalIdxItemVO) => {
    return await request.put({ url: `/datacenter/eval-idx-item/update`, data })
  },

  // 删除指标项管理
  deleteEvalIdxItem: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-idx-item/delete?id=` + id })
  },

  // 导出指标项管理 Excel
  exportEvalIdxItem: async (params) => {
    return await request.download({ url: `/datacenter/eval-idx-item/export-excel`, params })
  }
}
