import request from '@/config/axios'

// 设备分域监测报表 VO
export interface DomDeviceMonRptVO {
  id: number // 主键
  rptId: string // 报表ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  adminCode: string // 行政区划代码
  adminName: string // 行政区划名称
  deviceMajorId: string // 设备大类ID
  deviceMajorName: string // 设备大类名称
  deviceMinorId: string // 设备小类ID
  deviceMinorName: string // 设备小类名称
  domId: string // 分域ID
  domName: string // 分域名称
  totalDeviceCount: number // 设备总数
  deviceOnlineRate: number // 设备在线率
  totalUploadFreq: number // 数据上传总频次
  uploadQualifiedRate: number // 数据上传达标率
  deviceAbnCount: number // 设备异常次数
  firmwareUpdatedCount: number // 固件更新设备数
  firmwareUpdateRate: number // 固件更新率
  rptCreateTime: Date // 报表生成时间
  rptCreateUser: number // 报表生成人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备分域监测报表 API
export const DomDeviceMonRptApi = {
  // 查询设备分域监测报表分页
  getDomDeviceMonRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-device-mon-rpt/page`, params })
  },

  // 查询设备分域监测报表详情
  getDomDeviceMonRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-device-mon-rpt/get?id=` + id })
  },

  // 新增设备分域监测报表
  createDomDeviceMonRpt: async (data: DomDeviceMonRptVO) => {
    return await request.post({ url: `/datacenter/dom-device-mon-rpt/create`, data })
  },

  // 修改设备分域监测报表
  updateDomDeviceMonRpt: async (data: DomDeviceMonRptVO) => {
    return await request.put({ url: `/datacenter/dom-device-mon-rpt/update`, data })
  },

  // 删除设备分域监测报表
  deleteDomDeviceMonRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-device-mon-rpt/delete?id=` + id })
  },

  // 导出设备分域监测报表 Excel
  exportDomDeviceMonRpt: async (params) => {
    return await request.download({ url: `/datacenter/dom-device-mon-rpt/export-excel`, params })
  }
}
