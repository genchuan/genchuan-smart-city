import request from '@/config/axios'

// 国际行业动态 VO
export interface InternationalDynamicVO {
  id: number // 主键ID
  internationalDynamicId: string // 动态ID
  dynamicTitle: string // 动态标题
  dynamicTitleEn: string // 英文标题
  sourceCountry: string // 来源国家地区
  dynamicSource: string // 动态来源
  releaseTime: Date // 发布时间
  dynamicDetail: string // 动态详细内容
  dynamicDetailEn: string // 英文详情
  coverImgId: string // 封面图片ID
  coverImgPath: string // 封面图片路径
  dynamicStatus: string // 动态状态
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 国际行业动态 API
export const InternationalDynamicApi = {
  // 查询国际行业动态分页
  getInternationalDynamicPage: async (params: any) => {
    return await request.get({ url: `/datacenter/international-dynamic/page`, params })
  },

  // 查询国际行业动态详情
  getInternationalDynamic: async (id: number) => {
    return await request.get({ url: `/datacenter/international-dynamic/get?id=` + id })
  },

  // 新增国际行业动态
  createInternationalDynamic: async (data: InternationalDynamicVO) => {
    return await request.post({ url: `/datacenter/international-dynamic/create`, data })
  },

  // 修改国际行业动态
  updateInternationalDynamic: async (data: InternationalDynamicVO) => {
    return await request.put({ url: `/datacenter/international-dynamic/update`, data })
  },

  // 删除国际行业动态
  deleteInternationalDynamic: async (id: number) => {
    return await request.delete({ url: `/datacenter/international-dynamic/delete?id=` + id })
  },

  // 导出国际行业动态 Excel
  exportInternationalDynamic: async (params) => {
    return await request.download({ url: `/datacenter/international-dynamic/export-excel`, params })
  }
}