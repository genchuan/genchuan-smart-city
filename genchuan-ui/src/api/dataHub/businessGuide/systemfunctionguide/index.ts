import request from '@/config/axios'

// 系统功能指南 VO
export interface SystemFunctionGuideVO {
  id: number // 主键ID
  systemFunctionGuideId: string // 指南ID
  guideTitle: string // 指南标题
  relatedFunctionId: string // 关联功能ID
  relatedFunctionName: string // 关联功能名称
  functionPurpose: string // 功能用途说明
  operSteps: string // 操作步骤
  commonProblems: string // 常见问题及解决方案
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

// 系统功能指南 API
export const SystemFunctionGuideApi = {
  // 查询系统功能指南分页
  getSystemFunctionGuidePage: async (params: any) => {
    return await request.get({ url: `/datacenter/system-function-guide/page`, params })
  },

  // 查询系统功能指南详情
  getSystemFunctionGuide: async (id: number) => {
    return await request.get({ url: `/datacenter/system-function-guide/get?id=` + id })
  },

  // 新增系统功能指南
  createSystemFunctionGuide: async (data: SystemFunctionGuideVO) => {
    return await request.post({ url: `/datacenter/system-function-guide/create`, data })
  },

  // 修改系统功能指南
  updateSystemFunctionGuide: async (data: SystemFunctionGuideVO) => {
    return await request.put({ url: `/datacenter/system-function-guide/update`, data })
  },

  // 删除系统功能指南
  deleteSystemFunctionGuide: async (id: number) => {
    return await request.delete({ url: `/datacenter/system-function-guide/delete?id=` + id })
  },

  // 导出系统功能指南 Excel
  exportSystemFunctionGuide: async (params) => {
    return await request.download({ url: `/datacenter/system-function-guide/export-excel`, params })
  },
}