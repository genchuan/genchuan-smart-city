import request from '@/config/axios'

// 常见问题解答 VO
export interface FaqVO {
  id: number // 主键ID
  faqId: string // FAQ ID
  questionTypeCode: string // 问题类型代码
  questionTypeName: string // 问题类型名称
  questionDesc: string // 问题描述
  answerSolution: string // 解答方案
  relatedGuideId: string // 关联指南ID
  relatedGuideName: string // 关联指南名称
  sortNum: number // 排序序号
  isTop: string // 是否置顶
  viewCount: number // 查看次数
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 常见问题解答 API
export const FaqApi = {
  // 查询常见问题解答分页
  getFaqPage: async (params: any) => {
    return await request.get({ url: `/datacenter/faq/page`, params })
  },

  // 查询常见问题解答详情
  getFaq: async (id: number) => {
    return await request.get({ url: `/datacenter/faq/get?id=` + id })
  },

  // 新增常见问题解答
  createFaq: async (data: FaqVO) => {
    return await request.post({ url: `/datacenter/faq/create`, data })
  },

  // 修改常见问题解答
  updateFaq: async (data: FaqVO) => {
    return await request.put({ url: `/datacenter/faq/update`, data })
  },

  // 删除常见问题解答
  deleteFaq: async (id: number) => {
    return await request.delete({ url: `/datacenter/faq/delete?id=` + id })
  },

  // 导出常见问题解答 Excel
  exportFaq: async (params) => {
    return await request.download({ url: `/datacenter/faq/export-excel`, params })
  },
}