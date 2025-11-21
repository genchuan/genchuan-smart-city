import request from '@/config/axios'

// 按设备分域预警告警统计 VO
export interface EarlyWarnDeviceVO {
  id: number // 主键
  warnDeviceStatId: string // 统计ID
  statCycle: string // 统计周期
  deviceMajorId: string // 设备大类ID
  deviceMajorName: string // 设备大类名称
  deviceModelId: string // 设备型号ID
  deviceModelName: string // 设备型号名称
  totalWarnCount: number // 预警总数
  mainFaultType: string // 主要故障类型
  deviceCount: number // 涉及设备数
  onlineRepairCount: number // 在线修复数
  onlineRepairRate: number // 在线修复率
  statUserId: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 按设备分域预警告警统计 API
export const EarlyWarnDeviceApi = {
  // 查询按设备分域预警告警统计分页
  getEarlyWarnDevicePage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-device/page`, params })
  },

  // 查询按设备分域预警告警统计详情
  getEarlyWarnDevice: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-device/get?id=` + id })
  },

  // 新增按设备分域预警告警统计
  createEarlyWarnDevice: async (data: EarlyWarnDeviceVO) => {
    return await request.post({ url: `/datacenter/early-warn-device/create`, data })
  },

  // 修改按设备分域预警告警统计
  updateEarlyWarnDevice: async (data: EarlyWarnDeviceVO) => {
    return await request.put({ url: `/datacenter/early-warn-device/update`, data })
  },

  // 删除按设备分域预警告警统计
  deleteEarlyWarnDevice: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-device/delete?id=` + id })
  },

  // 导出按设备分域预警告警统计 Excel
  exportEarlyWarnDevice: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-device/export-excel`, params })
  }
}
