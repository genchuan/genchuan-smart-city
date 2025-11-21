import request from '@/config/axios'

// 事件办结归档 VO
export interface EvtArchVO {
  id: number // 自增主键ID
  archId: string // 归档ID
  woId: string // 工单ID
  evtCode: string // 事件编码
  completeTime: Date // 办结时间
  completeDesc: string // 办结说明
}

// 事件办结归档 API
export const EvtArchApi = {
  // 查询事件办结归档分页
  getEvtArchPage: async (params: any) => {
    return await request.get({ url: `/datacenter/evt-arch/page`, params })
  },

  // 查询事件办结归档详情
  getEvtArch: async (id: number) => {
    return await request.get({ url: `/datacenter/evt-arch/get?id=` + id })
  },

  // 新增事件办结归档
  createEvtArch: async (data: EvtArchVO) => {
    return await request.post({ url: `/datacenter/evt-arch/create`, data })
  },

  // 修改事件办结归档
  updateEvtArch: async (data: EvtArchVO) => {
    return await request.put({ url: `/datacenter/evt-arch/update`, data })
  },

  // 删除事件办结归档
  deleteEvtArch: async (id: number) => {
    return await request.delete({ url: `/datacenter/evt-arch/delete?id=` + id })
  },

  // 导出事件办结归档 Excel
  exportEvtArch: async (params) => {
    return await request.download({ url: `/datacenter/evt-arch/export-excel`, params })
  },
}