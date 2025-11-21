import request from '@/config/axios'

// 网格分域 VO
export interface DomDivGridVO {
  id: number // 主键ID
  domId: string // 分域ID
  domName: string // 分域名称
  domCode: string // 分域编码
  gridType: string // 网格类型
  gridId: string // 网格ID
  gridCode: string // 网格编码
  gridName: string // 网格名称
  adminCode: string // 所属行政区域代码
  adminName: string // 所属行政区域名称
  relPtCount: number // 关联点位数量(个)
  relDeviceCount: number // 关联设备数量(个)
  gridUserId: number // 网格员ID
  gridUserName: string // 网格员姓名
  domStatus: boolean // 分域状态
  createTimeSys: Date // 创建时间
  updateTimeSys: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 网格分域 API
export const DomDivGridApi = {
  // 查询网格分域分页
  getDomDivGridPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-div-grid/page`, params })
  },

  // 查询网格分域详情
  getDomDivGrid: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-div-grid/get?id=` + id })
  },

  // 新增网格分域
  createDomDivGrid: async (data: DomDivGridVO) => {
    return await request.post({ url: `/datacenter/dom-div-grid/create`, data })
  },

  // 修改网格分域
  updateDomDivGrid: async (data: DomDivGridVO) => {
    return await request.put({ url: `/datacenter/dom-div-grid/update`, data })
  },

  // 删除网格分域
  deleteDomDivGrid: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-div-grid/delete?id=` + id })
  },

  // 导出网格分域 Excel
  exportDomDivGrid: async (params) => {
    return await request.download({ url: `/datacenter/dom-div-grid/export-excel`, params })
  }
}
