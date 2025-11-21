import request from '@/config/axios'

// 网格数据质量检查 VO
export interface GridDataQualCkVO {
  id: number // 主键ID
  qualCkId: string // 质量检查ID
  gridId: string // 网格ID
  gridType: string // 网格类型
  gridCode: string // 网格编码
  ckItem: string // 检查项
  ckResult: string // 检查结果
  errorDesc: string // 错误描述
  rectifySugg: string // 整改建议
  ckTime: Date // 检查时间
  ckUserId: string // 检查人员
  rectifyStatus: string // 整改状态
  rectifyTime: Date // 整改时间
  rectifyUserId: string // 整改人员
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 网格数据质量检查 API
export const GridDataQualCkApi = {
  // 查询网格数据质量检查分页
  getGridDataQualCkPage: async (params: any) => {
    return await request.get({ url: `/datacenter/grid-data-qual-ck/page`, params })
  },

  // 查询网格数据质量检查详情
  getGridDataQualCk: async (id: number) => {
    return await request.get({ url: `/datacenter/grid-data-qual-ck/get?id=` + id })
  },

  // 新增网格数据质量检查
  createGridDataQualCk: async (data: GridDataQualCkVO) => {
    return await request.post({ url: `/datacenter/grid-data-qual-ck/create`, data })
  },

  // 修改网格数据质量检查
  updateGridDataQualCk: async (data: GridDataQualCkVO) => {
    return await request.put({ url: `/datacenter/grid-data-qual-ck/update`, data })
  },

  // 删除网格数据质量检查
  deleteGridDataQualCk: async (id: number) => {
    return await request.delete({ url: `/datacenter/grid-data-qual-ck/delete?id=` + id })
  },

  // 导出网格数据质量检查 Excel
  exportGridDataQualCk: async (params) => {
    return await request.download({ url: `/datacenter/grid-data-qual-ck/export-excel`, params })
  },
}
