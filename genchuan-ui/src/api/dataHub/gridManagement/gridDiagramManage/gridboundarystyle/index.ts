import request from '@/config/axios'

// 网格边界线样式配置 VO
export interface GridBoundaryStyleVO {
  id: number // 主键ID
  styleId: string // 样式ID
  scale: string // 比例尺
  gridType: string // 网格类型
  lineWidth: number // 线宽(mm)
  colorC: number // 颜色C值(%)
  colorM: number // 颜色M值(%)
  colorY: number // 颜色Y值(%)
  colorK: number // 颜色K值(%)
  createUserId: string // 创建人
  remark: string // 备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 网格边界线样式配置 API
export const GridBoundaryStyleApi = {
  // 查询网格边界线样式配置分页
  getGridBoundaryStylePage: async (params: any) => {
    return await request.get({ url: `/datacenter/grid-boundary-style/page`, params })
  },

  // 查询网格边界线样式配置详情
  getGridBoundaryStyle: async (id: number) => {
    return await request.get({ url: `/datacenter/grid-boundary-style/get?id=` + id })
  },

  // 新增网格边界线样式配置
  createGridBoundaryStyle: async (data: GridBoundaryStyleVO) => {
    return await request.post({ url: `/datacenter/grid-boundary-style/create`, data })
  },

  // 修改网格边界线样式配置
  updateGridBoundaryStyle: async (data: GridBoundaryStyleVO) => {
    return await request.put({ url: `/datacenter/grid-boundary-style/update`, data })
  },

  // 删除网格边界线样式配置
  deleteGridBoundaryStyle: async (id: number) => {
    return await request.delete({ url: `/datacenter/grid-boundary-style/delete?id=` + id })
  },

  // 导出网格边界线样式配置 Excel
  exportGridBoundaryStyle: async (params) => {
    return await request.download({ url: `/datacenter/grid-boundary-style/export-excel`, params })
  },
}
