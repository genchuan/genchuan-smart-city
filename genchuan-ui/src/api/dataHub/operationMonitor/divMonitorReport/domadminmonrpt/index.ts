import request from '@/config/axios'

// 行政区划分域监测报表 VO
export interface DomAdminMonRptVO {
  id: number // 主键ID
  rptId: string // 报表ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  adminCode: string // 行政区划代码
  adminName: string // 行政区划名称
  adminLevel: string // 行政区划层级
  domId: string // 分域ID
  domName: string // 分域名称
  monPtCount: number // 监测点位数量
  deviceTotalCount: number // 设备总数
  deviceOnlineRate: number // 设备在线率
  evtTotalCount: number // 事件总数
  evtCompleteRate: number // 事件办结率
  rptCreateTime: Date // 报表生成时间
  rptCreateUser: number // 报表生成人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 行政区划分域监测报表 API
export const DomAdminMonRptApi = {
  // 查询行政区划分域监测报表分页
  getDomAdminMonRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-admin-mon-rpt/page`, params })
  },

  // 查询行政区划分域监测报表详情
  getDomAdminMonRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-admin-mon-rpt/get?id=` + id })
  },

  // 新增行政区划分域监测报表
  createDomAdminMonRpt: async (data: DomAdminMonRptVO) => {
    return await request.post({ url: `/datacenter/dom-admin-mon-rpt/create`, data })
  },

  // 修改行政区划分域监测报表
  updateDomAdminMonRpt: async (data: DomAdminMonRptVO) => {
    return await request.put({ url: `/datacenter/dom-admin-mon-rpt/update`, data })
  },

  // 删除行政区划分域监测报表
  deleteDomAdminMonRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-admin-mon-rpt/delete?id=` + id })
  },

  // 导出行政区划分域监测报表 Excel
  exportDomAdminMonRpt: async (params) => {
    return await request.download({ url: `/datacenter/dom-admin-mon-rpt/export-excel`, params })
  }
}
