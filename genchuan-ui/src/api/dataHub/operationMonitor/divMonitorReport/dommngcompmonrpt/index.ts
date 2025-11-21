import request from '@/config/axios'

// 管理部件分域监测报表 VO
export interface DomMngCompMonRptVO {
  id: number // 主键ID
  rptId: string // 报表ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  adminCode: string // 行政区划代码
  adminName: string // 行政区划名称
  mngCompMajorId: string // 管理部件大类ID
  mngCompMajorName: string // 管理部件大类名称
  mngCompMinorId: string // 管理部件小类ID
  mngCompMinorName: string // 管理部件小类名称
  domId: string // 分域ID
  domName: string // 分域名称
  relPtCount: number // 关联点位数量
  relDeviceCount: number // 关联设备数量
  deviceOnlineRate: number // 设备在线率
  compFaultEvtCount: number // 部件故障事件数
  faultRepairRate: number // 故障修复率
  rptCreateTime: Date // 报表生成时间
  rptCreateUser: number // 报表生成人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 管理部件分域监测报表 API
export const DomMngCompMonRptApi = {
  // 查询管理部件分域监测报表分页
  getDomMngCompMonRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-mng-comp-mon-rpt/page`, params })
  },

  // 查询管理部件分域监测报表详情
  getDomMngCompMonRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-mng-comp-mon-rpt/get?id=` + id })
  },

  // 新增管理部件分域监测报表
  createDomMngCompMonRpt: async (data: DomMngCompMonRptVO) => {
    return await request.post({ url: `/datacenter/dom-mng-comp-mon-rpt/create`, data })
  },

  // 修改管理部件分域监测报表
  updateDomMngCompMonRpt: async (data: DomMngCompMonRptVO) => {
    return await request.put({ url: `/datacenter/dom-mng-comp-mon-rpt/update`, data })
  },

  // 删除管理部件分域监测报表
  deleteDomMngCompMonRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-mng-comp-mon-rpt/delete?id=` + id })
  },

  // 导出管理部件分域监测报表 Excel
  exportDomMngCompMonRpt: async (params) => {
    return await request.download({ url: `/datacenter/dom-mng-comp-mon-rpt/export-excel`, params })
  }
}
