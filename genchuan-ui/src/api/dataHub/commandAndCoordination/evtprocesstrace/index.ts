import request from '@/config/axios'

// 事件过程回溯 VO
export interface EvtProcessTraceVO {
  id: number // 主键ID
  traceId: string // 回溯ID
  evtCode: string // 事件编码
  evtName: string // 事件名称
  evtTypeName: string // 事件类型名称
  rptLink: string // 接报环节
  analysisLink: string // 研判环节
  allocateLink: string // 分拨环节
  woLink: string // 工单环节
  dispatchLink: string // 调度环节
  traceUserId: string // 回溯人ID
  traceUserName: string // 回溯人姓名
  traceTime: Date // 回溯时间
  rptPath: string // 回溯报告路径
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 事件过程回溯 API
export const EvtProcessTraceApi = {
  // 查询事件过程回溯分页
  getEvtProcessTracePage: async (params: any) => {
    return await request.get({ url: `/datacenter/evt-process-trace/page`, params })
  },

  // 查询事件过程回溯详情
  getEvtProcessTrace: async (id: number) => {
    return await request.get({ url: `/datacenter/evt-process-trace/get?id=` + id })
  },

  // 新增事件过程回溯
  createEvtProcessTrace: async (data: EvtProcessTraceVO) => {
    return await request.post({ url: `/datacenter/evt-process-trace/create`, data })
  },

  // 修改事件过程回溯
  updateEvtProcessTrace: async (data: EvtProcessTraceVO) => {
    return await request.put({ url: `/datacenter/evt-process-trace/update`, data })
  },

  // 删除事件过程回溯
  deleteEvtProcessTrace: async (id: number) => {
    return await request.delete({ url: `/datacenter/evt-process-trace/delete?id=` + id })
  },

  // 导出事件过程回溯 Excel
  exportEvtProcessTrace: async (params) => {
    return await request.download({ url: `/datacenter/evt-process-trace/export-excel`, params })
  },
}