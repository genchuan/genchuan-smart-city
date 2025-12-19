import request from '@/config/axios'

// 服务信息发布 VO
export interface PublicSvcInfoVO {
  id: number // 主键ID
  svcInfoId: string // 服务信息ID
  svcTitle: string // 服务标题
  svcTypeCode: string // 服务类型编码
  svcTypeName: string // 服务类型名称
  svcContent: string // 服务内容
  svcProvider: string // 服务提供单位
  contactPhone: string // 联系电话
  effectiveTime: Date // 生效时间
  expireTime: Date // 失效时间
  publishUserId: number // 发布人ID
  publishTime: Date // 发布时间
  infoStatus: number // 信息状态
  remark: string // 备注，补充说明
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 服务信息发布 API
export const PublicSvcInfoApi = {
  // 查询服务信息发布分页
  getPublicSvcInfoPage: async (params: any) => {
    return await request.get({ url: `/datacenter/public-svc-info/page`, params })
  },

  // 查询服务信息发布详情
  getPublicSvcInfo: async (id: number) => {
    return await request.get({ url: `/datacenter/public-svc-info/get?id=` + id })
  },

  // 新增服务信息发布
  createPublicSvcInfo: async (data: PublicSvcInfoVO) => {
    return await request.post({ url: `/datacenter/public-svc-info/create`, data })
  },

  // 修改服务信息发布
  updatePublicSvcInfo: async (data: PublicSvcInfoVO) => {
    return await request.put({ url: `/datacenter/public-svc-info/update`, data })
  },

  // 删除服务信息发布
  deletePublicSvcInfo: async (id: number) => {
    return await request.delete({ url: `/datacenter/public-svc-info/delete?id=` + id })
  },

  // 导出服务信息发布 Excel
  exportPublicSvcInfo: async (params) => {
    return await request.download({ url: `/datacenter/public-svc-info/export-excel`, params })
  },
}