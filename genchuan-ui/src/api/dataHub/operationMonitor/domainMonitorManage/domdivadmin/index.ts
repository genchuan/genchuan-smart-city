import request from '@/config/axios'

// 行政区划分域 VO
export interface DomDivAdminVO {
  id: number // 主键ID
  domId: string // 分域ID
  domName: string // 分域名称
  domCode: string // 分域编码
  adminLevel: string // 行政区域级别
  adminCode: string // 行政区域代码
  adminName: string // 行政区域名称
  relPtCount: number // 关联点位数量(个)
  relDeviceCount: number // 关联设备数量(个)
  mngrId: number // 分域负责人ID
  mngrName: string // 分域负责人姓名
  domStatus: boolean // 分域状态
  createTimeSys: Date // 创建时间
  updateTimeSys: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 行政区划分域 API
export const DomDivAdminApi = {
  // 查询行政区划分域分页
  getDomDivAdminPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-div-admin/page`, params })
  },

  // 查询行政区划分域详情
  getDomDivAdmin: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-div-admin/get?id=` + id })
  },

  // 新增行政区划分域
  createDomDivAdmin: async (data: DomDivAdminVO) => {
    return await request.post({ url: `/datacenter/dom-div-admin/create`, data })
  },

  // 修改行政区划分域
  updateDomDivAdmin: async (data: DomDivAdminVO) => {
    return await request.put({ url: `/datacenter/dom-div-admin/update`, data })
  },

  // 删除行政区划分域
  deleteDomDivAdmin: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-div-admin/delete?id=` + id })
  },

  // 导出行政区划分域 Excel
  exportDomDivAdmin: async (params) => {
    return await request.download({ url: `/datacenter/dom-div-admin/export-excel`, params })
  }
}
