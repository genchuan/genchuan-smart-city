import request from '@/config/axios'

// 监测事件分域监测报表 VO
export interface DomMonEvtMonRptVO {
  id: number // 主键
  rptId: string // 报表ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  adminCode: string // 行政区划代码
  adminName: string // 行政区划名称
  monEvtMajorId: string // 监测事件大类ID
  monEvtMajorName: string // 监测事件大类名称
  monEvtMinorId: string // 监测事件小类ID
  monEvtMinorName: string // 监测事件小类名称
  domId: string // 分域ID
  domName: string // 分域名称
  totalEvtCount: number // 事件总数
  highIncidenceCode: string // 高发区域代码
  highIncidenceName: string // 高发区域名称
  highIncidenceRatio: number // 高发区域事件占比
  evtCompleteRate: number // 事件办结率
  avgRespEndure: number // 平均响应时长
  rptCreateTime: Date // 报表生成时间
  rptCreateUser: number // 报表生成人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 监测事件分域监测报表 API
export const DomMonEvtMonRptApi = {
  // 查询监测事件分域监测报表分页
  getDomMonEvtMonRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-mon-evt-mon-rpt/page`, params })
  },

  // 查询监测事件分域监测报表详情
  getDomMonEvtMonRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-mon-evt-mon-rpt/get?id=` + id })
  },

  // 新增监测事件分域监测报表
  createDomMonEvtMonRpt: async (data: DomMonEvtMonRptVO) => {
    return await request.post({ url: `/datacenter/dom-mon-evt-mon-rpt/create`, data })
  },

  // 修改监测事件分域监测报表
  updateDomMonEvtMonRpt: async (data: DomMonEvtMonRptVO) => {
    return await request.put({ url: `/datacenter/dom-mon-evt-mon-rpt/update`, data })
  },

  // 删除监测事件分域监测报表
  deleteDomMonEvtMonRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-mon-evt-mon-rpt/delete?id=` + id })
  },

  // 导出监测事件分域监测报表 Excel
  exportDomMonEvtMonRpt: async (params) => {
    return await request.download({ url: `/datacenter/dom-mon-evt-mon-rpt/export-excel`, params })
  }
}
