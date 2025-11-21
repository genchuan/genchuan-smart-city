import request from '@/config/axios'

// 网格分域监测报表 VO
export interface DomGridMonRptVO {
  id: number // 主键ID
  rptId: string // 报表ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  adminCode: string // 行政区划代码
  adminName: string // 行政区划名称
  gridType: string // 网格类型
  domId: string // 分域ID
  domName: string // 分域名称
  gridCode: string // 网格编码
  gridUserName: string // 网格员姓名
  monPtCount: number // 监测点位数量
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

// 网格分域监测报表 API
export const DomGridMonRptApi = {
  // 查询网格分域监测报表分页
  getDomGridMonRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-grid-mon-rpt/page`, params })
  },

  // 查询网格分域监测报表详情
  getDomGridMonRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-grid-mon-rpt/get?id=` + id })
  },

  // 新增网格分域监测报表
  createDomGridMonRpt: async (data: DomGridMonRptVO) => {
    return await request.post({ url: `/datacenter/dom-grid-mon-rpt/create`, data })
  },

  // 修改网格分域监测报表
  updateDomGridMonRpt: async (data: DomGridMonRptVO) => {
    return await request.put({ url: `/datacenter/dom-grid-mon-rpt/update`, data })
  },

  // 删除网格分域监测报表
  deleteDomGridMonRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-grid-mon-rpt/delete?id=` + id })
  },

  // 导出网格分域监测报表 Excel
  exportDomGridMonRpt: async (params) => {
    return await request.download({ url: `/datacenter/dom-grid-mon-rpt/export-excel`, params })
  }
}
