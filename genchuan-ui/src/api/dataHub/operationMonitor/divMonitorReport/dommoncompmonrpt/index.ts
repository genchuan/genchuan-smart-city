import request from '@/config/axios'

// 监测部件分域监测报表 VO
export interface DomMonCompMonRptVO {
  id: number // 主键
  rptId: string // 报表ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  adminCode: string // 行政区划代码
  adminName: string // 行政区划名称
  monCompMajorId: string // 监测部件大类ID
  monCompMajorName: string // 监测部件大类名称
  monCompMinorId: string // 监测部件小类ID
  monCompMinorName: string // 监测部件小类名称
  domId: string // 分域ID
  domName: string // 分域名称
  totalDeviceCount: number // 设备总数
  deviceOnlineRate: number // 设备在线率
  totalUploadCount: number // 数据上传总数
  uploadQualifiedRate: number // 数据上传达标率
  faultEvtCount: number // 故障事件数
  calibrateCount: number // 校准次数
  rptCreateTime: Date // 报表生成时间
  rptCreateUser: number // 报表生成人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 监测部件分域监测报表 API
export const DomMonCompMonRptApi = {
  // 查询监测部件分域监测报表分页
  getDomMonCompMonRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-mon-comp-mon-rpt/page`, params })
  },

  // 查询监测部件分域监测报表详情
  getDomMonCompMonRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-mon-comp-mon-rpt/get?id=` + id })
  },

  // 新增监测部件分域监测报表
  createDomMonCompMonRpt: async (data: DomMonCompMonRptVO) => {
    return await request.post({ url: `/datacenter/dom-mon-comp-mon-rpt/create`, data })
  },

  // 修改监测部件分域监测报表
  updateDomMonCompMonRpt: async (data: DomMonCompMonRptVO) => {
    return await request.put({ url: `/datacenter/dom-mon-comp-mon-rpt/update`, data })
  },

  // 删除监测部件分域监测报表
  deleteDomMonCompMonRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-mon-comp-mon-rpt/delete?id=` + id })
  },

  // 导出监测部件分域监测报表 Excel
  exportDomMonCompMonRpt: async (params) => {
    return await request.download({ url: `/datacenter/dom-mon-comp-mon-rpt/export-excel`, params })
  }
}
