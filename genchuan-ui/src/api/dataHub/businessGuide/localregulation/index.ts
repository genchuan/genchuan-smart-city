import request from '@/config/axios'

// 地方法规信息 VO
export interface LocalRegulationVO {
  id: number // 主键ID
  localRegulationId: string // 法规ID
  regulationTitle: string // 法规标题
  regulationDocNo: string // 法规文号
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  issueDeptCode: string // 发布部门代码
  issueDeptName: string // 发布部门名称
  issueTime: Date // 发布时间
  implementTime: Date // 实施时间
  regulationFileId: string // 法规文件ID
  regulationFilePath: string // 法规文件路径
  regulationStatus: string // 法规状态
  regulationSummary: string // 法规摘要
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 地方法规信息 API
export const LocalRegulationApi = {
  // 查询地方法规信息分页
  getLocalRegulationPage: async (params: any) => {
    return await request.get({ url: `/datacenter/local-regulation/page`, params })
  },

  // 查询地方法规信息详情
  getLocalRegulation: async (id: number) => {
    return await request.get({ url: `/datacenter/local-regulation/get?id=` + id })
  },

  // 新增地方法规信息
  createLocalRegulation: async (data: LocalRegulationVO) => {
    return await request.post({ url: `/datacenter/local-regulation/create`, data })
  },

  // 修改地方法规信息
  updateLocalRegulation: async (data: LocalRegulationVO) => {
    return await request.put({ url: `/datacenter/local-regulation/update`, data })
  },

  // 删除地方法规信息
  deleteLocalRegulation: async (id: number) => {
    return await request.delete({ url: `/datacenter/local-regulation/delete?id=` + id })
  },

  // 导出地方法规信息 Excel
  exportLocalRegulation: async (params) => {
    return await request.download({ url: `/datacenter/local-regulation/export-excel`, params })
  }
}