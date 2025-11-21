import request from '@/config/axios'

// 事件上报 VO
export interface EventReportingVO {
  id: number // 主键
  eventNumber: string // 事件编号
  eventName: string // 事件名称
  eventType: string // 事件类型
  eventDescription: string // 事件描述
  occurrenceTime: Date // 发生时间
  place: string // 发生地点
  latitudeLongitude: string // 经纬度坐标
  reportperson: string // 上报人
}

// 事件上报 API
export const EventReportingApi = {
  // 查询事件上报分页
  getEventReportingPage: async (params: any) => {
    return await request.get({ url: `/smartcity/event-reporting/page`, params })
  },

  // 查询事件上报详情
  getEventReporting: async (id: number) => {
    return await request.get({ url: `/smartcity/event-reporting/get?id=` + id })
  },

  // 新增事件上报
  createEventReporting: async (data: EventReportingVO) => {
    return await request.post({ url: `/smartcity/event-reporting/create`, data })
  },

  // 修改事件上报
  updateEventReporting: async (data: EventReportingVO) => {
    return await request.put({ url: `/smartcity/event-reporting/update`, data })
  },

  // 删除事件上报
  deleteEventReporting: async (id: number) => {
    return await request.delete({ url: `/smartcity/event-reporting/delete?id=` + id })
  },

  // 导出事件上报 Excel
  exportEventReporting: async (params) => {
    return await request.download({ url: `/smartcity/event-reporting/export-excel`, params })
  }
}