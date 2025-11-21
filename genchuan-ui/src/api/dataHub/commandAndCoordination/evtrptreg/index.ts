import request from '@/config/axios'

// 事件接报登记 VO
export interface EvtRptRegVO {
  id: number // 自增主键ID
  rptId: string // 接报ID
  evtCode: string // 事件编码
  evtTypeId: string // 事件类型ID
  evtTypeName: string // 事件类型名称
  incidentRegionCode: string // 事发区域代码
  incidentRegionName: string // 事发区域名称
  incidentLocation: string // 事发位置
  incidentCoordX: number // 事发坐标X
  incidentCoordY: number // 事发坐标Y
  evtDesc: string // 事件描述
}

// 事件接报登记 API
export const EvtRptRegApi = {
  // 查询事件接报登记分页
  getEvtRptRegPage: async (params: any) => {
    return await request.get({ url: `/datacenter/evt-rpt-reg/page`, params })
  },

  // 查询事件接报登记详情
  getEvtRptReg: async (id: number) => {
    return await request.get({ url: `/datacenter/evt-rpt-reg/get?id=` + id })
  },

  // 新增事件接报登记
  createEvtRptReg: async (data: EvtRptRegVO) => {
    return await request.post({ url: `/datacenter/evt-rpt-reg/create`, data })
  },

  // 修改事件接报登记
  updateEvtRptReg: async (data: EvtRptRegVO) => {
    return await request.put({ url: `/datacenter/evt-rpt-reg/update`, data })
  },

  // 删除事件接报登记
  deleteEvtRptReg: async (id: number) => {
    return await request.delete({ url: `/datacenter/evt-rpt-reg/delete?id=` + id })
  },

  // 导出事件接报登记 Excel
  exportEvtRptReg: async (params) => {
    return await request.download({ url: `/datacenter/evt-rpt-reg/export-excel`, params })
  },
}