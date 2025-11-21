import request from '@/config/axios'

// 扩展网格划分 VO
export interface ExtGridDivVO {
  id: number // 主键ID
  extGridId: string // 扩展网格ID
  extGridName: string // 扩展网格名称
  extType: string // 扩展网格类型
  includedBasicIds: string // 所含基础网格IDs
  basicGridType: string // 基础网格类型
  area: number // 面积(m²)
  boundaryCoords: string // 边界坐标
  applyReason: string // 申请原因
  auditStatus: string // 审核状态
  applyUserId: string // 申请人ID
  applyTime: Date // 申请时间
  auditUserId: string // 审核人ID
  auditTime: Date // 审核时间
  auditOpinion: string // 审核意见
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
}

// 扩展网格划分 API
export const ExtGridDivApi = {
  // 查询扩展网格划分分页
  getExtGridDivPage: async (params: any) => {
    return await request.get({ url: `/datacenter/ext-grid-div/page`, params })
  },

  // 查询扩展网格划分详情
  getExtGridDiv: async (id: number) => {
    return await request.get({ url: `/datacenter/ext-grid-div/get?id=` + id })
  },

  // 新增扩展网格划分
  createExtGridDiv: async (data: ExtGridDivVO) => {
    return await request.post({ url: `/datacenter/ext-grid-div/create`, data })
  },

  // 修改扩展网格划分
  updateExtGridDiv: async (data: ExtGridDivVO) => {
    return await request.put({ url: `/datacenter/ext-grid-div/update`, data })
  },

  // 删除扩展网格划分
  deleteExtGridDiv: async (id: number) => {
    return await request.delete({ url: `/datacenter/ext-grid-div/delete?id=` + id })
  },

  // 导出扩展网格划分 Excel
  exportExtGridDiv: async (params) => {
    return await request.download({ url: `/datacenter/ext-grid-div/export-excel`, params })
  },
}
