import request from '@/config/axios'

// 扩展网格编码 VO
export interface ExtGridCodeVO {
  id: number // 主键ID
  extCodeId: string // 编码ID
  extGridCode: string // 扩展网格标识码
  extGridId: string // 所属扩展网格ID
  areaId: number // 关联行政区划ID
  areaFullCode: string // 行政区划完整代码
  seqCode: string // 顺序码
  typeCode: string // 类型码(G/Z/S/Q)
  generateTime: Date // 编码生成时间
  codeStatus: string // 编码状态
  remark: string // 备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 扩展网格编码 API
export const ExtGridCodeApi = {
  // 查询扩展网格编码分页
  getExtGridCodePage: async (params: any) => {
    return await request.get({ url: `/datacenter/ext-grid-code/page`, params })
  },

  // 查询扩展网格编码详情
  getExtGridCode: async (id: number) => {
    return await request.get({ url: `/datacenter/ext-grid-code/get?id=` + id })
  },

  // 新增扩展网格编码
  createExtGridCode: async (data: ExtGridCodeVO) => {
    return await request.post({ url: `/datacenter/ext-grid-code/create`, data })
  },

  // 修改扩展网格编码
  updateExtGridCode: async (data: ExtGridCodeVO) => {
    return await request.put({ url: `/datacenter/ext-grid-code/update`, data })
  },

  // 删除扩展网格编码
  deleteExtGridCode: async (id: number) => {
    return await request.delete({ url: `/datacenter/ext-grid-code/delete?id=` + id })
  },

  // 导出扩展网格编码 Excel
  exportExtGridCode: async (params) => {
    return await request.download({ url: `/datacenter/ext-grid-code/export-excel`, params })
  },
}
