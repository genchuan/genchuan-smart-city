import request from '@/config/axios'

// 管理网格编码 VO
export interface MngGridCodeVO {
  id: number // 主键ID
  mgCodeId: string // 编码ID
  mgGridCode: string // 管理网格标识码
  mngGridId: string // 所属管理网格ID
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

// 管理网格编码 API
export const MngGridCodeApi = {
  // 查询管理网格编码分页
  getMngGridCodePage: async (params: any) => {
    return await request.get({ url: `/datacenter/mng-grid-code/page`, params })
  },

  // 查询管理网格编码详情
  getMngGridCode: async (id: number) => {
    return await request.get({ url: `/datacenter/mng-grid-code/get?id=` + id })
  },

  // 新增管理网格编码
  createMngGridCode: async (data: MngGridCodeVO) => {
    return await request.post({ url: `/datacenter/mng-grid-code/create`, data })
  },

  // 修改管理网格编码
  updateMngGridCode: async (data: MngGridCodeVO) => {
    return await request.put({ url: `/datacenter/mng-grid-code/update`, data })
  },

  // 删除管理网格编码
  deleteMngGridCode: async (id: number) => {
    return await request.delete({ url: `/datacenter/mng-grid-code/delete?id=` + id })
  },

  // 导出管理网格编码 Excel
  exportMngGridCode: async (params) => {
    return await request.download({ url: `/datacenter/mng-grid-code/export-excel`, params })
  },
}
