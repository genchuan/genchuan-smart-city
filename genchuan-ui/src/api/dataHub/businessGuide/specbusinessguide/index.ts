import request from '@/config/axios'

// 专项业务指南 VO
export interface SpecBusinessGuideVO {
  id: number // 主键ID
  specBusinessGuideId: string // 指南ID
  guideTitle: string // 指南标题
  businessFieldCode: string // 业务领域代码
  businessFieldName: string // 业务领域名称
  relatedTableName: string // 关联业务表名
  relatedTableCode: string // 关联业务表编码
  businessProcessDesc: string // 业务流程说明
  operSteps: string // 操作步骤
  dataSpec: string // 业务数据录入规范
  applicableRole: string // 适用角色
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

// 专项业务指南 API
export const SpecBusinessGuideApi = {
  // 查询专项业务指南分页
  getSpecBusinessGuidePage: async (params: any) => {
    return await request.get({ url: `/datacenter/spec-business-guide/page`, params })
  },

  // 查询专项业务指南详情
  getSpecBusinessGuide: async (id: number) => {
    return await request.get({ url: `/datacenter/spec-business-guide/get?id=` + id })
  },

  // 新增专项业务指南
  createSpecBusinessGuide: async (data: SpecBusinessGuideVO) => {
    return await request.post({ url: `/datacenter/spec-business-guide/create`, data })
  },

  // 修改专项业务指南
  updateSpecBusinessGuide: async (data: SpecBusinessGuideVO) => {
    return await request.put({ url: `/datacenter/spec-business-guide/update`, data })
  },

  // 删除专项业务指南
  deleteSpecBusinessGuide: async (id: number) => {
    return await request.delete({ url: `/datacenter/spec-business-guide/delete?id=` + id })
  },

  // 导出专项业务指南 Excel
  exportSpecBusinessGuide: async (params) => {
    return await request.download({ url: `/datacenter/spec-business-guide/export-excel`, params })
  },
}