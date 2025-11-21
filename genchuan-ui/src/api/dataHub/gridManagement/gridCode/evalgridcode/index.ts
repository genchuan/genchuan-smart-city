import request from '@/config/axios'

// 评价网格编码 VO
export interface EvalGridCodeVO {
  id: number // 主键ID
  evalCodeId: string // 编码ID
  evalGridCode: string // 评价网格标识码
  evalGridId: string // 所属评价网格ID
  areaFullCode: string // 行政区划完整代码
  seqCode: string // 顺序码
  typeCode: string // 类型码(A/B/C)
  generateTime: Date // 编码生成时间
  codeStatus: string // 编码状态
  remark: string // 备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 评价网格编码 API
export const EvalGridCodeApi = {
  // 查询评价网格编码分页
  getEvalGridCodePage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-grid-code/page`, params })
  },

  // 查询评价网格编码详情
  getEvalGridCode: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-grid-code/get?id=` + id })
  },

  // 新增评价网格编码
  createEvalGridCode: async (data: EvalGridCodeVO) => {
    return await request.post({ url: `/datacenter/eval-grid-code/create`, data })
  },

  // 修改评价网格编码
  updateEvalGridCode: async (data: EvalGridCodeVO) => {
    return await request.put({ url: `/datacenter/eval-grid-code/update`, data })
  },

  // 删除评价网格编码
  deleteEvalGridCode: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-grid-code/delete?id=` + id })
  },

  // 导出评价网格编码 Excel
  exportEvalGridCode: async (params) => {
    return await request.download({ url: `/datacenter/eval-grid-code/export-excel`, params })
  },
}
