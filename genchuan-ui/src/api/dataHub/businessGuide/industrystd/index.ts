import request from '@/config/axios'

// 行业规范信息 VO
export interface IndustryStdVO {
  id: number // 主键ID
  industryStdId: string // 规范ID
  stdTitle: string // 规范标题
  industryTypeCode: string // 行业分类代码
  industryTypeName: string // 行业分类名称
  stdNo: string // 规范编号
  issueOrg: string // 发布机构
  issueTime: Date // 发布时间
  stdFileId: string // 规范文件ID
  stdFilePath: string // 规范文件路径
  stdStatus: string // 规范状态
  coreClause: string // 核心条款
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 行业规范信息 API
export const IndustryStdApi = {
  // 查询行业规范信息分页
  getIndustryStdPage: async (params: any) => {
    return await request.get({ url: `/datacenter/industry-std/page`, params })
  },

  // 查询行业规范信息详情
  getIndustryStd: async (id: number) => {
    return await request.get({ url: `/datacenter/industry-std/get?id=` + id })
  },

  // 新增行业规范信息
  createIndustryStd: async (data: IndustryStdVO) => {
    return await request.post({ url: `/datacenter/industry-std/create`, data })
  },

  // 修改行业规范信息
  updateIndustryStd: async (data: IndustryStdVO) => {
    return await request.put({ url: `/datacenter/industry-std/update`, data })
  },

  // 删除行业规范信息
  deleteIndustryStd: async (id: number) => {
    return await request.delete({ url: `/datacenter/industry-std/delete?id=` + id })
  },

  // 导出行业规范信息 Excel
  exportIndustryStd: async (params) => {
    return await request.download({ url: `/datacenter/industry-std/export-excel`, params })
  }
}