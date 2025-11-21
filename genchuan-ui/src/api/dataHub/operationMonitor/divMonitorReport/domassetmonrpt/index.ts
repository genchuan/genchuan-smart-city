import request from '@/config/axios'

// 资产分域监测报表 VO
export interface DomAssetMonRptVO {
  id: number // 主键
  rptId: string // 报表ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  adminCode: string // 行政区划代码
  adminName: string // 行政区划名称
  assetMajorId: string // 资产大类ID
  assetMajorName: string // 资产大类名称
  assetMinorId: string // 资产小类ID
  assetMinorName: string // 资产小类名称
  domId: string // 分域ID
  domName: string // 分域名称
  totalAssetCount: number // 资产总数
  inUseAssetCount: number // 在用资产数
  assetInUseRate: number // 资产在用率
  totalMntCount: number // 维护总次数
  avgAssetLife: number // 平均资产寿命
  assetFaultEvtCount: number // 资产故障事件数
  rptCreateTime: Date // 报表生成时间
  rptCreateUser: number // 报表生成人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 资产分域监测报表 API
export const DomAssetMonRptApi = {
  // 查询资产分域监测报表分页
  getDomAssetMonRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-asset-mon-rpt/page`, params })
  },

  // 查询资产分域监测报表详情
  getDomAssetMonRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-asset-mon-rpt/get?id=` + id })
  },

  // 新增资产分域监测报表
  createDomAssetMonRpt: async (data: DomAssetMonRptVO) => {
    return await request.post({ url: `/datacenter/dom-asset-mon-rpt/create`, data })
  },

  // 修改资产分域监测报表
  updateDomAssetMonRpt: async (data: DomAssetMonRptVO) => {
    return await request.put({ url: `/datacenter/dom-asset-mon-rpt/update`, data })
  },

  // 删除资产分域监测报表
  deleteDomAssetMonRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-asset-mon-rpt/delete?id=` + id })
  },

  // 导出资产分域监测报表 Excel
  exportDomAssetMonRpt: async (params) => {
    return await request.download({ url: `/datacenter/dom-asset-mon-rpt/export-excel`, params })
  }
}
