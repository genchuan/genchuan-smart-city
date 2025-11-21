import request from '@/config/axios'

// 事件工单 VO
export interface EvtWoVO {
  id: number // 自增主键ID
  woId: string // 工单ID
  woNo: string // 工单编号
  evtCode: string // 事件编码
  evtTypeName: string // 事件类型名称
  allocateDeptCode: string // 分拨部门代码
  allocateDeptName: string // 分拨部门名称
  woStatus: string // 工单状态
  chargerId: string // 负责人ID
  chargerName: string // 负责人姓名
}

// 事件工单 API
export const EvtWoApi = {
  // 查询事件工单分页
  getEvtWoPage: async (params: any) => {
    return await request.get({ url: `/datacenter/evt-wo/page`, params })
  },

  // 查询事件工单详情
  getEvtWo: async (id: number) => {
    return await request.get({ url: `/datacenter/evt-wo/get?id=` + id })
  },

  // 新增事件工单
  createEvtWo: async (data: EvtWoVO) => {
    return await request.post({ url: `/datacenter/evt-wo/create`, data })
  },

  // 修改事件工单
  updateEvtWo: async (data: EvtWoVO) => {
    return await request.put({ url: `/datacenter/evt-wo/update`, data })
  },

  // 删除事件工单
  deleteEvtWo: async (id: number) => {
    return await request.delete({ url: `/datacenter/evt-wo/delete?id=` + id })
  },

  // 导出事件工单 Excel
  exportEvtWo: async (params) => {
    return await request.download({ url: `/datacenter/evt-wo/export-excel`, params })
  },
}