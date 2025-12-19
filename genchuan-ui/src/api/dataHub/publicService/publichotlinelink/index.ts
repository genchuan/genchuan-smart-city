import request from '@/config/axios'

// 热线对接 VO
export interface PublicHotlineLinkVO {
  id: number // 主键ID
  hotlineId: string // 热线ID
  hotlineNo: string // 热线单号
  platformNo: string // 平台单号
  appealTypeCode: string // 诉求类型编码
  appealTypeName: string // 诉求类型名称
  areaCode: string // 事发区域编码
  areaName: string // 事发区域名称
  appealContent: string // 诉求内容
  contactName: string // 联系人姓名
  contactPhone: string // 联系人电话
  hotlineSource: string // 热线来源
  syncStatus: number // 同步状态
  handleStatus: number // 处置状态
  acceptDeptCode: string // 受理部门编码
  acceptDeptName: string // 受理部门名称
  handleResult: string // 处置结果
  syncTime: Date // 同步时间
  finishTime: Date // 办结时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 热线对接 API
export const PublicHotlineLinkApi = {
  // 查询热线对接分页
  getPublicHotlineLinkPage: async (params: any) => {
    return await request.get({ url: `/datacenter/public-hotline-link/page`, params })
  },

  // 查询热线对接详情
  getPublicHotlineLink: async (id: number) => {
    return await request.get({ url: `/datacenter/public-hotline-link/get?id=` + id })
  },

  // 新增热线对接
  createPublicHotlineLink: async (data: PublicHotlineLinkVO) => {
    return await request.post({ url: `/datacenter/public-hotline-link/create`, data })
  },

  // 修改热线对接
  updatePublicHotlineLink: async (data: PublicHotlineLinkVO) => {
    return await request.put({ url: `/datacenter/public-hotline-link/update`, data })
  },

  // 删除热线对接
  deletePublicHotlineLink: async (id: number) => {
    return await request.delete({ url: `/datacenter/public-hotline-link/delete?id=` + id })
  },

  // 导出热线对接 Excel
  exportPublicHotlineLink: async (params) => {
    return await request.download({ url: `/datacenter/public-hotline-link/export-excel`, params })
  },
}