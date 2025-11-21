import request from '@/config/axios'

// 按设备分域分析研判统计 VO
export interface AnalysisDeviceVO {
  id: number // 主键ID
  statAnalysisId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  deviceMajorId: string // 设备大类ID
  deviceMajorName: string // 设备大类名称
  deviceMinorId: string // 设备小类ID
  deviceMinorName: string // 设备小类名称
  totalDeviceCount: number // 设备总数量
  deviceOnlineRate: number // 设备在线率
  dataUploadFreq: number // 数据上传频率
  deviceFaultRate: number // 设备故障率
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 按设备分域分析研判统计 API
export const AnalysisDeviceApi = {
  // 查询按设备分域分析研判统计分页
  getAnalysisDevicePage: async (params: any) => {
    return await request.get({ url: `/datacenter/analysis-device/page`, params })
  },

  // 查询按设备分域分析研判统计详情
  getAnalysisDevice: async (id: number) => {
    return await request.get({ url: `/datacenter/analysis-device/get?id=` + id })
  },

  // 新增按设备分域分析研判统计
  createAnalysisDevice: async (data: AnalysisDeviceVO) => {
    return await request.post({ url: `/datacenter/analysis-device/create`, data })
  },

  // 修改按设备分域分析研判统计
  updateAnalysisDevice: async (data: AnalysisDeviceVO) => {
    return await request.put({ url: `/datacenter/analysis-device/update`, data })
  },

  // 删除按设备分域分析研判统计
  deleteAnalysisDevice: async (id: number) => {
    return await request.delete({ url: `/datacenter/analysis-device/delete?id=` + id })
  },

  // 导出按设备分域分析研判统计 Excel
  exportAnalysisDevice: async (params) => {
    return await request.download({ url: `/datacenter/analysis-device/export-excel`, params })
  },
}