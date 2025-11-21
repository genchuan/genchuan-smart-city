import request from '@/config/axios'

// 网格标识码注记配置 VO
export interface GridCodeAnnotateVO {
  id: number // 主键ID
  annotateId: string // 注记ID
  scale: string // 比例尺
  gridType: string // 网格类型
  fontHeight: number // 字高(mm)
  fontType: string // 字型
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

// 网格标识码注记配置 API
export const GridCodeAnnotateApi = {
  // 查询网格标识码注记配置分页
  getGridCodeAnnotatePage: async (params: any) => {
    return await request.get({ url: `/datacenter/grid-code-annotate/page`, params })
  },

  // 查询网格标识码注记配置详情
  getGridCodeAnnotate: async (id: number) => {
    return await request.get({ url: `/datacenter/grid-code-annotate/get?id=` + id })
  },

  // 新增网格标识码注记配置
  createGridCodeAnnotate: async (data: GridCodeAnnotateVO) => {
    return await request.post({ url: `/datacenter/grid-code-annotate/create`, data })
  },

  // 修改网格标识码注记配置
  updateGridCodeAnnotate: async (data: GridCodeAnnotateVO) => {
    return await request.put({ url: `/datacenter/grid-code-annotate/update`, data })
  },

  // 删除网格标识码注记配置
  deleteGridCodeAnnotate: async (id: number) => {
    return await request.delete({ url: `/datacenter/grid-code-annotate/delete?id=` + id })
  },

  // 导出网格标识码注记配置 Excel
  exportGridCodeAnnotate: async (params) => {
    return await request.download({ url: `/datacenter/grid-code-annotate/export-excel`, params })
  },
}
