import request from '@/config/axios'

// 跨域经验交流 VO
export interface CrossDomExpVO {
  id: number // 主键ID
  crossDomExpId: string // 经验ID
  expTitle: string // 经验标题
  expFieldCode: string // 经验领域代码
  expFieldName: string // 经验领域名称
  sourceCity: string // 来源城市
  expDetail: string // 经验详细内容
  applicableScenario: string // 适用场景
  attachId: string // 附件ID
  attachPath: string // 附件路径
  publishStatus: string // 发布状态
  viewCount: number // 浏览次数
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 跨域经验交流 API
export const CrossDomExpApi = {
  // 查询跨域经验交流分页
  getCrossDomExpPage: async (params: any) => {
    return await request.get({ url: `/datacenter/cross-dom-exp/page`, params })
  },

  // 查询跨域经验交流详情
  getCrossDomExp: async (id: number) => {
    return await request.get({ url: `/datacenter/cross-dom-exp/get?id=` + id })
  },

  // 新增跨域经验交流
  createCrossDomExp: async (data: CrossDomExpVO) => {
    return await request.post({ url: `/datacenter/cross-dom-exp/create`, data })
  },

  // 修改跨域经验交流
  updateCrossDomExp: async (data: CrossDomExpVO) => {
    return await request.put({ url: `/datacenter/cross-dom-exp/update`, data })
  },

  // 删除跨域经验交流
  deleteCrossDomExp: async (id: number) => {
    return await request.delete({ url: `/datacenter/cross-dom-exp/delete?id=` + id })
  },

  // 导出跨域经验交流 Excel
  exportCrossDomExp: async (params) => {
    return await request.download({ url: `/datacenter/cross-dom-exp/export-excel`, params })
  },
}