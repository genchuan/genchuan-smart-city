import request from '@/config/axios'

// 国内行业动态 VO
export interface DomesticDynamicVO {
  id: number // 主键ID
  domesticDynamicId: string // 动态ID
  dynamicTitle: string // 动态标题
  dynamicTag: string // 动态标签
  dynamicSource: string // 动态来源
  releaseTime: Date // 发布时间
  dynamicDetail: string // 动态详细内容
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

// 国内行业动态 API
export const DomesticDynamicApi = {
  // 查询国内行业动态分页
  getDomesticDynamicPage: async (params: any) => {
    return await request.get({ url: `/datacenter/domestic-dynamic/page`, params })
  },

  // 查询国内行业动态详情
  getDomesticDynamic: async (id: number) => {
    return await request.get({ url: `/datacenter/domestic-dynamic/get?id=` + id })
  },

  // 新增国内行业动态
  createDomesticDynamic: async (data: DomesticDynamicVO) => {
    return await request.post({ url: `/datacenter/domestic-dynamic/create`, data })
  },

  // 修改国内行业动态
  updateDomesticDynamic: async (data: DomesticDynamicVO) => {
    return await request.put({ url: `/datacenter/domestic-dynamic/update`, data })
  },

  // 删除国内行业动态
  deleteDomesticDynamic: async (id: number) => {
    return await request.delete({ url: `/datacenter/domestic-dynamic/delete?id=` + id })
  },

  // 导出国内行业动态 Excel
  exportDomesticDynamic: async (params) => {
    return await request.download({ url: `/datacenter/domestic-dynamic/export-excel`, params })
  }
}