import request from '@/config/axios'

// 设备运行状态统计 VO
export interface DeviceOperRptVO {
  id: number // 主键ID
  operStatId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  deviceMajorId: string // 设备大类ID
  deviceMajorName: string // 设备大类名称
  totalDeviceCount: number // 设备总数量(台)
  avgOnlineCount: number // 平均在线设备数(台)
  deviceOnlineRate: number // 设备在线率(%)
  totalOfflineEndure: number // 总离线时长(分钟)
  avgOfflineEndure: number // 平均离线时长(分钟)
  generalAlertCount: number // 一般预警数(条)
  importantAlertCount: number // 重要预警数(条)
  urgentAlertCount: number // 紧急预警数(条)
  handledAlertCount: number // 已处理预警数(条)
  alertHandleRate: number // 预警处置率(%)
  statTime: Date // 统计时间
  statUser: string // 统计人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备运行状态统计 API
export const DeviceOperRptApi = {
  // 查询设备运行状态统计分页
  getDeviceOperRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-oper-rpt/page`, params })
  },

  // 查询设备运行状态统计详情
  getDeviceOperRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/device-oper-rpt/get?id=` + id })
  },

  // 新增设备运行状态统计
  createDeviceOperRpt: async (data: DeviceOperRptVO) => {
    return await request.post({ url: `/datacenter/device-oper-rpt/create`, data })
  },

  // 修改设备运行状态统计
  updateDeviceOperRpt: async (data: DeviceOperRptVO) => {
    return await request.put({ url: `/datacenter/device-oper-rpt/update`, data })
  },

  // 删除设备运行状态统计
  deleteDeviceOperRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-oper-rpt/delete?id=` + id })
  },

  // 导出设备运行状态统计 Excel
  exportDeviceOperRpt: async (params) => {
    return await request.download({ url: `/datacenter/device-oper-rpt/export-excel`, params })
  }
}
