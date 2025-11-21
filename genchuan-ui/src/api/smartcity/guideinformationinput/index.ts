import request from '@/config/axios'

// 指南信息录入 VO
export interface GuideInformationInputVO {
  id: number // 主键
  guideName: string // 指南名称
  scopeOfApplication: string // 适用范围
  publishingUnit: string // 发布单位
  releaseDate: string // 发布日期
  updateDate: string // 更新日期
  mainContentOverview: string // 主要内容概述
}

// 指南信息录入 API
export const GuideInformationInputApi = {
  // 查询指南信息录入分页
  getGuideInformationInputPage: async (params: any) => {
    return await request.get({ url: `/smartcity/guide-information-input/page`, params })
  },

  // 查询指南信息录入详情
  getGuideInformationInput: async (id: number) => {
    return await request.get({ url: `/smartcity/guide-information-input/get?id=` + id })
  },

  // 新增指南信息录入
  createGuideInformationInput: async (data: GuideInformationInputVO) => {
    return await request.post({ url: `/smartcity/guide-information-input/create`, data })
  },

  // 修改指南信息录入
  updateGuideInformationInput: async (data: GuideInformationInputVO) => {
    return await request.put({ url: `/smartcity/guide-information-input/update`, data })
  },

  // 删除指南信息录入
  deleteGuideInformationInput: async (id: number) => {
    return await request.delete({ url: `/smartcity/guide-information-input/delete?id=` + id })
  },

  // 导出指南信息录入 Excel
  exportGuideInformationInput: async (params) => {
    return await request.download({ url: `/smartcity/guide-information-input/export-excel`, params })
  }
}