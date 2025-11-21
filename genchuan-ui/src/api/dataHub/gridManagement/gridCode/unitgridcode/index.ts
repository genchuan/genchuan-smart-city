import request from '@/config/axios'

// 单元网格编码 VO
export interface UnitGridCodeVO {
  id: number // 主键ID
  unitCodeId: string // 编码ID
  unitGridCode: string // 单元网格标识码
  unitGridId: string // 所属单元网格ID
  areaFullCode: string // 行政区划完整代码
  seqCode: string // 顺序码
  generateTime: Date // 编码生成时间
  codeStatus: string // 编码状态
  remark: string // 备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 单元网格编码 API
export const UnitGridCodeApi = {
  // 查询单元网格编码分页
  getUnitGridCodePage: async (params: any) => {
    return await request.get({ url: `/datacenter/unit-grid-code/page`, params })
  },

  // 查询单元网格编码详情
  getUnitGridCode: async (id: number) => {
    return await request.get({ url: `/datacenter/unit-grid-code/get?id=` + id })
  },

  // 新增单元网格编码
  createUnitGridCode: async (data: UnitGridCodeVO) => {
    return await request.post({ url: `/datacenter/unit-grid-code/create`, data })
  },

  // 修改单元网格编码
  updateUnitGridCode: async (data: UnitGridCodeVO) => {
    return await request.put({ url: `/datacenter/unit-grid-code/update`, data })
  },

  // 删除单元网格编码
  deleteUnitGridCode: async (id: number) => {
    return await request.delete({ url: `/datacenter/unit-grid-code/delete?id=` + id })
  },

  // 导出单元网格编码 Excel
  exportUnitGridCode: async (params) => {
    return await request.download({ url: `/datacenter/unit-grid-code/export-excel`, params })
  },
}
