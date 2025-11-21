import request from '@/config/axios'

// 网格专题图层管理 VO
export interface GridTopicLayerVO {
  id: number // 主键ID
  layerId: string // 图层ID
  layerName: string // 图层名称
  gridType: string // 网格类型
  scale: string // 比例尺
  boundaryStyleId: string // 边界线样式ID
  annotateStyleId: string // 注记样式ID
  displayStatus: string // 显示状态
  layerWo: number // 图层顺序
  createUserId: string // 创建人
  remark: string // 备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 网格专题图层管理 API
export const GridTopicLayerApi = {
  // 查询网格专题图层管理分页
  getGridTopicLayerPage: async (params: any) => {
    return await request.get({ url: `/datacenter/grid-topic-layer/page`, params })
  },

  // 查询网格专题图层管理详情
  getGridTopicLayer: async (id: number) => {
    return await request.get({ url: `/datacenter/grid-topic-layer/get?id=` + id })
  },

  // 新增网格专题图层管理
  createGridTopicLayer: async (data: GridTopicLayerVO) => {
    return await request.post({ url: `/datacenter/grid-topic-layer/create`, data })
  },

  // 修改网格专题图层管理
  updateGridTopicLayer: async (data: GridTopicLayerVO) => {
    return await request.put({ url: `/datacenter/grid-topic-layer/update`, data })
  },

  // 删除网格专题图层管理
  deleteGridTopicLayer: async (id: number) => {
    return await request.delete({ url: `/datacenter/grid-topic-layer/delete?id=` + id })
  },

  // 导出网格专题图层管理 Excel
  exportGridTopicLayer: async (params) => {
    return await request.download({ url: `/datacenter/grid-topic-layer/export-excel`, params })
  },
}
