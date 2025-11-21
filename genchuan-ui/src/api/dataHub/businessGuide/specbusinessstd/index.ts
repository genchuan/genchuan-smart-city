import request from '@/config/axios'

// 专项业务标准 VO
export interface SpecBusinessStdVO {
  id: number // 主键ID
  specBusinessStdId: string // 标准ID
  stdName: string // 标准名称
  businessFieldCode: string // 业务领域代码
  businessFieldName: string // 业务领域名称
  stdVersion: string // 标准版本
  effectiveTime: Date // 生效时间
  stdClause: string // 标准核心条款
  execRequirement: string // 执行要求
  acceptIdx: string // 验收指标
  stdFileId: string // 标准文件ID
  stdFilePath: string // 标准文件路径
  stdStatus: string // 标准状态
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 专项业务标准 API
export const SpecBusinessStdApi = {
  // 查询专项业务标准分页
  getSpecBusinessStdPage: async (params: any) => {
    return await request.get({ url: `/datacenter/spec-business-std/page`, params })
  },

  // 查询专项业务标准详情
  getSpecBusinessStd: async (id: number) => {
    return await request.get({ url: `/datacenter/spec-business-std/get?id=` + id })
  },

  // 新增专项业务标准
  createSpecBusinessStd: async (data: SpecBusinessStdVO) => {
    return await request.post({ url: `/datacenter/spec-business-std/create`, data })
  },

  // 修改专项业务标准
  updateSpecBusinessStd: async (data: SpecBusinessStdVO) => {
    return await request.put({ url: `/datacenter/spec-business-std/update`, data })
  },

  // 删除专项业务标准
  deleteSpecBusinessStd: async (id: number) => {
    return await request.delete({ url: `/datacenter/spec-business-std/delete?id=` + id })
  },

  // 导出专项业务标准 Excel
  exportSpecBusinessStd: async (params) => {
    return await request.download({ url: `/datacenter/spec-business-std/export-excel`, params })
  }
}