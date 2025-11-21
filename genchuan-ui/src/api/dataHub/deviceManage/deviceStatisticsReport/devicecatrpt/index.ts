import request from '@/config/axios'

// 设备分类统计 VO
export interface DeviceCatRptVO {
  id: number // 主键ID
  catStatId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  deviceMajorId: string // 设备大类ID
  deviceMajorName: string // 设备大类名称
  deviceMidId: string // 设备中类ID
  deviceMidName: string // 设备中类名称
  deviceMinorId: string // 设备小类ID
  deviceMinorName: string // 设备小类名称
  totalDeviceCount: number // 设备总数(台)
  onlineDeviceCount: number // 在线设备数(台)
  offlineDeviceCount: number // 离线设备数(台)
  faultDeviceCount: number // 故障设备数(台)
  newDeviceCount: number // 新增设备数(台)
  scrappedDeviceCount: number // 报废设备数(台)
  statTime: Date // 统计时间
  statUser: string // 统计人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备分类统计 API
export const DeviceCatRptApi = {
  // 查询设备分类统计分页
  getDeviceCatRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-cat-rpt/page`, params })
  },

  // 查询设备分类统计详情
  getDeviceCatRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/device-cat-rpt/get?id=` + id })
  },

  // 新增设备分类统计
  createDeviceCatRpt: async (data: DeviceCatRptVO) => {
    return await request.post({ url: `/datacenter/device-cat-rpt/create`, data })
  },

  // 修改设备分类统计
  updateDeviceCatRpt: async (data: DeviceCatRptVO) => {
    return await request.put({ url: `/datacenter/device-cat-rpt/update`, data })
  },

  // 删除设备分类统计
  deleteDeviceCatRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-cat-rpt/delete?id=` + id })
  },

  // 导出设备分类统计 Excel
  exportDeviceCatRpt: async (params) => {
    return await request.download({ url: `/datacenter/device-cat-rpt/export-excel`, params })
  }
}
