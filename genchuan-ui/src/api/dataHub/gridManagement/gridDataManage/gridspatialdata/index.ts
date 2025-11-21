/**
 * gridspatialdata.ts
 * 说明：
 * - 保留原有接口：page/get/create/update/delete/export
 * - 新增了若干前端预计需要但后端可能未实现的接口：importGeojson、validateBoundary、computeTopoRelation、previewParseFile、getGridTypes
 * - 如果后端未实现这些接口，前端会捕获错误并使用 mock 数据（参见 mockGridSpatialData.ts）
 */

import request from '@/config/axios'

// 网格空间数据 VO
export interface GridSpatialDataVO {
  id?: number // 主键ID
  spatialId?: string // 空间数据ID
  gridId?: string // 关联网格ID
  gridType?: string // 网格类型
  boundaryCoords?: string // 边界坐标 (格式 "x1,y1;x2,y2;...")
  topoRelation?: string // 拓扑关系 (逗号分隔的邻接ID列表)
  coordSystem?: string // 坐标系
  dataFormat?: string // 数据格式
  importTime?: string
  updateTime?: string
  remark?: string
  extCat1?: string
  extCat2?: string
  extCommon1?: string
  extCommon2?: string
}

// 分页查询参数类型（可扩展）
export interface GridSpatialDataPageQuery {
  pageNo?: number
  pageSize?: number
  sortField?: string
  sortOrder?: 'asc' | 'desc'|''
  spatialId?: string
  gridId?: string
  gridType?: string
  dataFormat?: string
  hasTopo?: boolean // 是否有拓扑关系筛选（前端可用）
  // 其它字段按需追加
}

export const GridSpatialDataApi = {
  // 查询网格空间数据分页（支持 sortField, sortOrder）
  getGridSpatialDataPage: async (params: GridSpatialDataPageQuery) => {
    return await request.get({ url: `/datacenter/grid-spatial-data/page`, params })
  },

  // 查询网格空间数据详情
  getGridSpatialData: async (id: number) => {
    return await request.get({ url: `/datacenter/grid-spatial-data/get?id=` + id })
  },

  // 新增网格空间数据
  createGridSpatialData: async (data: GridSpatialDataVO) => {
    return await request.post({ url: `/datacenter/grid-spatial-data/create`, data })
  },

  // 修改网格空间数据
  updateGridSpatialData: async (data: GridSpatialDataVO) => {
    return await request.put({ url: `/datacenter/grid-spatial-data/update`, data })
  },

  // 删除网格空间数据
  deleteGridSpatialData: async (id: number) => {
    return await request.delete({ url: `/datacenter/grid-spatial-data/delete?id=` + id })
  },

  // 导出网格空间数据 Excel / GeoJSON / SHP (后端根据 params.format 返回)
  exportGridSpatialData: async (params: any) => {
    return await request.download({ url: `/datacenter/grid-spatial-data/export`, params })
  },

  // ========== 新增（可能后端未实现，前端预定义） ==========

  // 导入 GeoJSON / SHP（file: FormData），解析并返回解析后的边界预览以及校验结果
  // 后端未实现时，前端会捕获异常并使用 mock 解析结果做 UI 预览
  importSpatialFile: async (formData: FormData) => {
    // 注意：调用此接口时后端必须支持 multipart/form-data
    return await request.post({ url: `/datacenter/grid-spatial-data/import-file`, data: formData })
  },

  // 单个边界坐标校验（闭合、坐标系、无重叠等） -> 返回 { ok: boolean, message?: string }
  validateBoundary: async (coords: { boundaryCoords: string; coordSystem?: string }) => {
    return await request.post({ url: `/datacenter/grid-spatial-data/validate-boundary`, data: coords })
  },

  // 计算拓扑关系（给定 gridId 或边界坐标，返回邻接网格列表）
  computeTopoRelation: async (params: { gridId?: string; boundaryCoords?: string }) => {
    return await request.post({ url: `/datacenter/grid-spatial-data/compute-topo`, data: params })
  },

  // 前端文件预解析（如果你想在前端解析 geojson 可先用此接口，若后端未实现，前端会 fallback 到本地解析）
  previewParseFile: async (formData: FormData) => {
    return await request.post({ url: `/datacenter/grid-spatial-data/preview-parse`, data: formData })
  },

  // 获取支持的网格类型 / 数据格式（用于搜索下拉）
  getGridTypes: async () => {
    return await request.get({ url: `/datacenter/grid-spatial-data/grid-types` })
  },
}
export default GridSpatialDataApi
