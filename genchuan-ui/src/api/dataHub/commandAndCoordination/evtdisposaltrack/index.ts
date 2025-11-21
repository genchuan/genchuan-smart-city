import request from '@/config/axios'

// 事件处置跟踪 VO
export interface EvtDisposalTrackVO {
  id: number // 自增主键ID
  trackId: string // 跟踪ID
  woId: string // 工单ID
  handleStatus: string // 处置状态
  currentNode: string // 当前处置节点
  handlerName: string // 处置人员
  startTime: Date // 处置开始时间
  estCompleteTime: Date // 预计完成时间
}

// 事件处置跟踪 API
export const EvtDisposalTrackApi = {
  // 查询事件处置跟踪分页
  getEvtDisposalTrackPage: async (params: any) => {
    return await request.get({ url: `/datacenter/evt-disposal-track/page`, params })
  },

  // 查询事件处置跟踪详情
  getEvtDisposalTrack: async (id: number) => {
    return await request.get({ url: `/datacenter/evt-disposal-track/get?id=` + id })
  },

  // 新增事件处置跟踪
  createEvtDisposalTrack: async (data: EvtDisposalTrackVO) => {
    return await request.post({ url: `/datacenter/evt-disposal-track/create`, data })
  },

  // 修改事件处置跟踪
  updateEvtDisposalTrack: async (data: EvtDisposalTrackVO) => {
    return await request.put({ url: `/datacenter/evt-disposal-track/update`, data })
  },

  // 删除事件处置跟踪
  deleteEvtDisposalTrack: async (id: number) => {
    return await request.delete({ url: `/datacenter/evt-disposal-track/delete?id=` + id })
  },

  // 导出事件处置跟踪 Excel
  exportEvtDisposalTrack: async (params) => {
    return await request.download({ url: `/datacenter/evt-disposal-track/export-excel`, params })
  },
}