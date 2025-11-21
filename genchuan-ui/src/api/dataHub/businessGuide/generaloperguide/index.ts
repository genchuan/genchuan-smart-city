import request from '@/config/axios'

// 通用操作指南 VO
export interface GeneralOperGuideVO {
  id: number // 主键ID
  generalOperGuideId: string // 指南ID
  guideTitle: string // 指南标题
  operScenario: string // 操作场景
  operSteps: string // 操作步骤
  applicableRole: string // 适用角色
  version: string // 版本号
  effectiveTime: Date // 生效时间
  guideAttachId: string // 指南附件ID
  guideAttachPath: string // 指南附件路径
  guideStatus: string // 指南状态
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 通用操作指南 API
export const GeneralOperGuideApi = {
  // 查询通用操作指南分页
  getGeneralOperGuidePage: async (params: any) => {
    return await request.get({ url: `/datacenter/general-oper-guide/page`, params })
  },

  // 查询通用操作指南详情
  getGeneralOperGuide: async (id: number) => {
    return await request.get({ url: `/datacenter/general-oper-guide/get?id=` + id })
  },

  // 新增通用操作指南
  createGeneralOperGuide: async (data: GeneralOperGuideVO) => {
    return await request.post({ url: `/datacenter/general-oper-guide/create`, data })
  },

  // 修改通用操作指南
  updateGeneralOperGuide: async (data: GeneralOperGuideVO) => {
    return await request.put({ url: `/datacenter/general-oper-guide/update`, data })
  },

  // 删除通用操作指南
  deleteGeneralOperGuide: async (id: number) => {
    return await request.delete({ url: `/datacenter/general-oper-guide/delete?id=` + id })
  },

  // 导出通用操作指南 Excel
  exportGeneralOperGuide: async (params) => {
    return await request.download({ url: `/datacenter/general-oper-guide/export-excel`, params })
  },
}