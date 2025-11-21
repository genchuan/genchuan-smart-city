import request from '@/config/axios'

// 设备分域 VO
export interface DomDivDeviceVO {
  id: number // 主键ID
  domId: string // 分域ID
  domName: string // 分域名称
  domCode: string // 分域编码
  deviceMajorId: string // 设备大类ID
  deviceMajorName: string // 设备大类名称
  deviceMinorId: string // 设备小类ID
  deviceMinorName: string // 设备小类名称
  relDeviceCount: number // 关联设备数量(个)
  deviceOnlineRate: number // 设备在线率(%)
  relPtCount: number // 关联点位数量(个)
  deptCode: string // 设备管理部门代码
  deptName: string // 设备管理部门名称
  domStatus: boolean // 分域状态
  createTimeBiz: Date // 创建时间(业务)
  updateTimeBiz: Date // 更新时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备分域 API
export const DomDivDeviceApi = {
  // 查询设备分域分页
  getDomDivDevicePage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-div-device/page`, params })
  },

  // 查询设备分域详情
  getDomDivDevice: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-div-device/get?id=` + id })
  },

  // 新增设备分域
  createDomDivDevice: async (data: DomDivDeviceVO) => {
    return await request.post({ url: `/datacenter/dom-div-device/create`, data })
  },

  // 修改设备分域
  updateDomDivDevice: async (data: DomDivDeviceVO) => {
    return await request.put({ url: `/datacenter/dom-div-device/update`, data })
  },

  // 删除设备分域
  deleteDomDivDevice: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-div-device/delete?id=` + id })
  },

  // 导出设备分域 Excel
  exportDomDivDevice: async (params) => {
    return await request.download({ url: `/datacenter/dom-div-device/export-excel`, params })
  }
}
