import request from '@/config/axios'

// 评价网格属性 VO
export interface EvalGridAttrVO {
  id: number // 主键ID
  evalAttrId: string // 属性ID
  evalGridId: string // 评价网格ID
  evalGridCode: string // 评价网格编码
  area: number // 面积(m²)
  gridType: string // 评价网格类型(A/B/C)
  initTime: Date // 初始时间
  endTime: Date // 终止时间
  mgGridList: string // 所含管理网格列表
  idxId: string // 关联评价指标ID
  idxName: string // 关联评价指标名称
  remark: string // 备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 评价网格属性 API
export const EvalGridAttrApi = {
  // 查询评价网格属性分页
  getEvalGridAttrPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-grid-attr/page`, params })
  },

  // 查询评价网格属性详情
  getEvalGridAttr: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-grid-attr/get?id=` + id })
  },

  // 新增评价网格属性
  createEvalGridAttr: async (data: EvalGridAttrVO) => {
    return await request.post({ url: `/datacenter/eval-grid-attr/create`, data })
  },

  // 修改评价网格属性
  updateEvalGridAttr: async (data: EvalGridAttrVO) => {
    return await request.put({ url: `/datacenter/eval-grid-attr/update`, data })
  },

  // 删除评价网格属性
  deleteEvalGridAttr: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-grid-attr/delete?id=` + id })
  },

  // 导出评价网格属性 Excel
  exportEvalGridAttr: async (params) => {
    return await request.download({ url: `/datacenter/eval-grid-attr/export-excel`, params })
  },
}
