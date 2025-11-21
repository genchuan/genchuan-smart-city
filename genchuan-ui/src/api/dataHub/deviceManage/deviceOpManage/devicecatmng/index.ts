import request from '@/config/axios'

// 设备分类管理 VO
export interface DeviceCatMngVO {
  id: number // 主键ID
  catMngId: string // 分类管理ID
  deviceId: string // 设备ID
  deviceName: string // 设备名称
  deviceCode: string // 设备编码
  deviceMajorId: string // 设备大类ID
  deviceMajorName: string // 设备大类名称
  deviceMidId: string // 设备中类ID
  deviceMidName: string // 设备中类名称
  deviceMinorId: string // 设备小类ID
  deviceMinorName: string // 设备小类名称
  catTime: Date // 分类时间
  catUser: string // 分类人
  catStatus: string // 分类状态
  adjustTime: Date // 调整时间
  adjustUser: string // 调整人
  adjustReason: string // 调整原因
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备分类管理 API
export const DeviceCatMngApi = {
  // 查询设备分类管理分页
  getDeviceCatMngPage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-cat-mng/page`, params })
  },

  // 查询设备分类管理详情
  getDeviceCatMng: async (id: number) => {
    return await request.get({ url: `/datacenter/device-cat-mng/get?id=` + id })
  },

  // 新增设备分类管理
  createDeviceCatMng: async (data: DeviceCatMngVO) => {
    return await request.post({ url: `/datacenter/device-cat-mng/create`, data })
  },

  // 修改设备分类管理
  updateDeviceCatMng: async (data: DeviceCatMngVO) => {
    return await request.put({ url: `/datacenter/device-cat-mng/update`, data })
  },

  // 删除设备分类管理
  deleteDeviceCatMng: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-cat-mng/delete?id=` + id })
  },

  // 导出设备分类管理 Excel
  exportDeviceCatMng: async (params) => {
    return await request.download({ url: `/datacenter/device-cat-mng/export-excel`, params })
  }
}
