import request from '@/config/axios'

// 事件列表 VO
export interface EventListVO {
  id: number // 主键
  eventNumber: string // 事件编号
  eventOccurrenceTime: Date // 事件发生时间
  locationOfTheIncident: string // 事件发生地点
  eventDescription: string // 事件描述
  discoverer: string // 发现人
  notes: string // 备注
}

// 事件列表 API
export const EventListApi = {
  // 查询事件列表分页
  getEventListPage: async (params: any) => {
    return await request.get({ url: `/smartcity/event-list/page`, params })
  },

  // 查询事件列表详情
  getEventList: async (id: number) => {
    return await request.get({ url: `/smartcity/event-list/get?id=` + id })
  },

  // 新增事件列表
  createEventList: async (data: EventListVO) => {
    return await request.post({ url: `/smartcity/event-list/create`, data })
  },

  // 修改事件列表
  updateEventList: async (data: EventListVO) => {
    return await request.put({ url: `/smartcity/event-list/update`, data })
  },

  // 删除事件列表
  deleteEventList: async (id: number) => {
    return await request.delete({ url: `/smartcity/event-list/delete?id=` + id })
  },

  // 导出事件列表 Excel
  exportEventList: async (params) => {
    return await request.download({ url: `/smartcity/event-list/export-excel`, params })
  }
}