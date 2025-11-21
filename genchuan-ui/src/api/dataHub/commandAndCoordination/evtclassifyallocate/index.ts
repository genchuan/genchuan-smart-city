import request from '@/config/axios'

// 事件分级分拨 VO
export interface EvtClassifyAllocateVO {
  id: number // 自增主键ID
  allocateId: string // 分拨ID
  evtCode: string // 事件编码
  evtTypeId: string // 事件类型ID
  evtTypeName: string // 事件类型名称
  urgencyLevel: string // 紧急程度
  allocateDeptCode: string // 分拨部门代码
  allocateDeptName: string // 分拨部门名称
  allocatorId: string // 分拨人ID
  allocatorName: string // 分拨人姓名
  allocateTime: Date // 分拨时间
  allocateDesc: string // 分拨说明
}

// 事件分级分拨 API
export const EvtClassifyAllocateApi = {
  // 查询事件分级分拨分页
  getEvtClassifyAllocatePage: async (params: any) => {
    return await request.get({ url: `/datacenter/evt-classify-allocate/page`, params })
  },

  // 查询事件分级分拨详情
  getEvtClassifyAllocate: async (id: number) => {
    return await request.get({ url: `/datacenter/evt-classify-allocate/get?id=` + id })
  },

  // 新增事件分级分拨
  createEvtClassifyAllocate: async (data: EvtClassifyAllocateVO) => {
    return await request.post({ url: `/datacenter/evt-classify-allocate/create`, data })
  },

  // 修改事件分级分拨
  updateEvtClassifyAllocate: async (data: EvtClassifyAllocateVO) => {
    return await request.put({ url: `/datacenter/evt-classify-allocate/update`, data })
  },

  // 删除事件分级分拨
  deleteEvtClassifyAllocate: async (id: number) => {
    return await request.delete({ url: `/datacenter/evt-classify-allocate/delete?id=` + id })
  },

  // 导出事件分级分拨 Excel
  exportEvtClassifyAllocate: async (params) => {
    return await request.download({ url: `/datacenter/evt-classify-allocate/export-excel`, params })
  },
}