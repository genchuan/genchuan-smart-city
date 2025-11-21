import request from '@/config/axios'

// 资产分域 VO
export interface DomDivAssetVO {
  id: number // 主键ID
  domId: string // 分域ID
  domName: string // 分域名称
  domCode: string // 分域编码
  assetMajorId: string // 资产大类ID
  assetMajorName: string // 资产大类名称
  assetMinorId: string // 资产小类ID
  assetMinorName: string // 资产小类名称
  relAssetCount: number // 关联资产数量(个)
  relPtCount: number // 关联点位数量(个)
  relDeviceCount: number // 关联设备数量(个)
  deptCode: string // 资产运维部门代码
  deptName: string // 资产运维部门名称
  domStatus: boolean // 分域状态
  createTimeBiz: Date // 创建时间(业务)
  updateTimeBiz: Date // 更新时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 资产分域 API
export const DomDivAssetApi = {
  // 查询资产分域分页
  getDomDivAssetPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-div-asset/page`, params })
  },

  // 查询资产分域详情
  getDomDivAsset: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-div-asset/get?id=` + id })
  },

  // 新增资产分域
  createDomDivAsset: async (data: DomDivAssetVO) => {
    return await request.post({ url: `/datacenter/dom-div-asset/create`, data })
  },

  // 修改资产分域
  updateDomDivAsset: async (data: DomDivAssetVO) => {
    return await request.put({ url: `/datacenter/dom-div-asset/update`, data })
  },

  // 删除资产分域
  deleteDomDivAsset: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-div-asset/delete?id=` + id })
  },

  // 导出资产分域 Excel
  exportDomDivAsset: async (params) => {
    return await request.download({ url: `/datacenter/dom-div-asset/export-excel`, params })
  }
}
