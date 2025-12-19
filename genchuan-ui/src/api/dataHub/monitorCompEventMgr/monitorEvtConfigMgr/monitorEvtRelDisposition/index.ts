import request from '@/config/axios'

// 监测事件关联处置 VO
export interface EventDispositionVO {
  id: number // 主键ID
  eventTypeId: string // 事件类型ID
  eventTypeName: string // 事件类型名称
  divisionCode: string // 行政区划编码
  divisionName: string // 行政区划名称
  processModelId: string // 流程模型ID
  processModelName: string // 流程模型名称
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 监测事件关联处置 API
export const EventDispositionApi = {
  // 查询监测事件关联处置分页
  getEventDispositionPage: async (params: any) => {
    return await request.get({ url: `/datacenter/event-disposition/page`, params })
  },

  // 查询监测事件关联处置详情
  getEventDisposition: async (id: number) => {
    return await request.get({ url: `/datacenter/event-disposition/get?id=` + id })
  },

  // 新增监测事件关联处置
  createEventDisposition: async (data: EventDispositionVO) => {
    return await request.post({ url: `/datacenter/event-disposition/create`, data })
  },

  // 修改监测事件关联处置
  updateEventDisposition: async (data: EventDispositionVO) => {
    return await request.put({ url: `/datacenter/event-disposition/update`, data })
  },

  // 删除监测事件关联处置
  deleteEventDisposition: async (id: number) => {
    return await request.delete({ url: `/datacenter/event-disposition/delete?id=` + id })
  },

  // 导出监测事件关联处置 Excel
  exportEventDisposition: async (params) => {
    return await request.download({ url: `/datacenter/event-disposition/export-excel`, params })
  }
}
