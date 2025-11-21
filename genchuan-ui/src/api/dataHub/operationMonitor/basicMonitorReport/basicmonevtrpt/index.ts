import request from '@/config/axios'

// 基础监测事件报 VO
export interface BasicMonEvtRptVO {
  id: number // 主键ID
  rptId: string // 报表ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  adminCode: string // 行政区划代码
  adminName: string // 行政区划名称
  evtType: string // 事件类型
  totalEvtCount: number // 事件总数
  pendEvtCount: number // 待处置事件数
  handlEvtCount: number // 处置中事件数
  completedEvtCount: number // 已办结事件数
  evtCompleteRate: number // 事件办结率(%)
  avgDealEndure: number // 平均处置时长(分钟)
  rptCreateTime: Date // 报表生成时间
  rptCreateUser: number // 报表生成人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 基础监测事件报 API
export const BasicMonEvtRptApi = {
  // 查询基础监测事件报分页
  getBasicMonEvtRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/basic-mon-evt-rpt/page`, params })
  },

  // 查询基础监测事件报详情
  getBasicMonEvtRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/basic-mon-evt-rpt/get?id=` + id })
  },

  // 新增基础监测事件报
  createBasicMonEvtRpt: async (data: BasicMonEvtRptVO) => {
    return await request.post({ url: `/datacenter/basic-mon-evt-rpt/create`, data })
  },

  // 修改基础监测事件报
  updateBasicMonEvtRpt: async (data: BasicMonEvtRptVO) => {
    return await request.put({ url: `/datacenter/basic-mon-evt-rpt/update`, data })
  },

  // 删除基础监测事件报
  deleteBasicMonEvtRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/basic-mon-evt-rpt/delete?id=` + id })
  },

  // 导出基础监测事件报 Excel
  exportBasicMonEvtRpt: async (params) => {
    return await request.download({ url: `/datacenter/basic-mon-evt-rpt/export-excel`, params })
  }
}
