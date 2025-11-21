import request from '@/config/axios'

// 本地行业动态 VO
export interface LocalDynamicVO {
  id: number // 主键ID
  localDynamicId: string // 动态ID
  dynamicTitle: string // 动态标题
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
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

// 本地行业动态 API
export const LocalDynamicApi = {
  // 查询本地行业动态分页
  getLocalDynamicPage: async (params: any) => {
    return await request.get({ url: `/datacenter/local-dynamic/page`, params })
  },

  // 查询本地行业动态详情
  getLocalDynamic: async (id: number) => {
    return await request.get({ url: `/datacenter/local-dynamic/get?id=` + id })
  },

  // 新增本地行业动态
  createLocalDynamic: async (data: LocalDynamicVO) => {
    return await request.post({ url: `/datacenter/local-dynamic/create`, data })
  },

  // 修改本地行业动态
  updateLocalDynamic: async (data: LocalDynamicVO) => {
    return await request.put({ url: `/datacenter/local-dynamic/update`, data })
  },

  // 删除本地行业动态
  deleteLocalDynamic: async (id: number) => {
    return await request.delete({ url: `/datacenter/local-dynamic/delete?id=` + id })
  },

  // 导出本地行业动态 Excel
  exportLocalDynamic: async (params) => {
    return await request.download({ url: `/datacenter/local-dynamic/export-excel`, params })
  }
}