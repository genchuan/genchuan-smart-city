import request from '@/config/axios'

// 标准项管理 VO
export interface EvalStdItemVO {
  id: number // 主键ID
  stdItemId: string // 标准项ID
  stdItemName: string // 标准项名称
  stdItemCode: string // 标准项编码
  stdCatId: string // 所属标准分类ID
  stdCatName: string // 所属标准分类名称
  stdLevel: string // 标准等级
  scoreMin: number // 分数下限
  scoreMax: number // 分数上限
  stdDesc: string // 标准描述
  sortNum: number // 排序序号
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

// 标准项管理 API
export const EvalStdItemApi = {
  // 查询标准项管理分页
  getEvalStdItemPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-std-item/page`, params })
  },

  // 查询标准项管理详情
  getEvalStdItem: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-std-item/get?id=` + id })
  },

  // 新增标准项管理
  createEvalStdItem: async (data: EvalStdItemVO) => {
    return await request.post({ url: `/datacenter/eval-std-item/create`, data })
  },

  // 修改标准项管理
  updateEvalStdItem: async (data: EvalStdItemVO) => {
    return await request.put({ url: `/datacenter/eval-std-item/update`, data })
  },

  // 删除标准项管理
  deleteEvalStdItem: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-std-item/delete?id=` + id })
  },

  // 导出标准项管理 Excel
  exportEvalStdItem: async (params) => {
    return await request.download({ url: `/datacenter/eval-std-item/export-excel`, params })
  }
}
