import request from '@/config/axios'

// 设备数据质量检查 VO
export interface DeviceDataQualCkVO {
  id: number // 主键ID
  qualCkId: string // 质量检查ID
  deviceId: string // 设备ID
  deviceName: string // 设备名称
  deviceCode: string // 设备编码
  ckBatch: string // 检查批次
  ckTime: Date // 检查时间
  ckItem: string // 检查项
  ckResult: string // 检查结果
  errorDesc: string // 错误描述
  rectifySugg: string // 整改建议
  ckUser: string // 检查人
  rectifyStatus: string // 整改状态
  rectifyTime: Date // 整改时间
  rectifyUser: string // 整改人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备数据质量检查 API
export const DeviceDataQualCkApi = {
  // 查询设备数据质量检查分页
  getDeviceDataQualCkPage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-data-qual-ck/page`, params })
  },

  // 查询设备数据质量检查详情
  getDeviceDataQualCk: async (id: number) => {
    return await request.get({ url: `/datacenter/device-data-qual-ck/get?id=` + id })
  },

  // 新增设备数据质量检查
  createDeviceDataQualCk: async (data: DeviceDataQualCkVO) => {
    return await request.post({ url: `/datacenter/device-data-qual-ck/create`, data })
  },

  // 修改设备数据质量检查
  updateDeviceDataQualCk: async (data: DeviceDataQualCkVO) => {
    return await request.put({ url: `/datacenter/device-data-qual-ck/update`, data })
  },

  // 删除设备数据质量检查
  deleteDeviceDataQualCk: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-data-qual-ck/delete?id=` + id })
  },

  // 导出设备数据质量检查 Excel
  exportDeviceDataQualCk: async (params) => {
    return await request.download({ url: `/datacenter/device-data-qual-ck/export-excel`, params })
  }
}
