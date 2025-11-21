import request from '@/config/axios'

// 事件处理 VO
export interface EventProcessingVO {
  id: number // 主键
  eventHandlingNumber: string // 事件处理编号
  relatedEventReportingId: string // 关联事件上报编号
  processingDepartment: string // 处理部门
  processingPersonnel: string // 处理人员
  receptionTime: string // 接收时间
  handlingMeasures: string // 处理措施
}

// 事件处理 API
export const EventProcessingApi = {
  // 查询事件处理分页
  getEventProcessingPage: async (params: any) => {
    return await request.get({ url: `/smartcity/event-processing/page`, params })
  },

  // 查询事件处理详情
  getEventProcessing: async (id: number) => {
    return await request.get({ url: `/smartcity/event-processing/get?id=` + id })
  },

  // 新增事件处理
  createEventProcessing: async (data: EventProcessingVO) => {
    return await request.post({ url: `/smartcity/event-processing/create`, data })
  },

  // 修改事件处理
  updateEventProcessing: async (data: EventProcessingVO) => {
    return await request.put({ url: `/smartcity/event-processing/update`, data })
  },

  // 删除事件处理
  deleteEventProcessing: async (id: number) => {
    return await request.delete({ url: `/smartcity/event-processing/delete?id=` + id })
  },

  // 导出事件处理 Excel
  exportEventProcessing: async (params) => {
    return await request.download({ url: `/smartcity/event-processing/export-excel`, params })
  }
}