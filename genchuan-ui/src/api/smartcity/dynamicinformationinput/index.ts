import request from '@/config/axios'

// 动态信息录入 VO
export interface DynamicInformationInputVO {
  id: number // 主键
  title: string // 标题
  releaseTime: Date // 发布时间
  publishingSubject: string // 发布主体
  contentOverview: string // 内容概述
}

// 动态信息录入 API
export const DynamicInformationInputApi = {
  // 查询动态信息录入分页
  getDynamicInformationInputPage: async (params: any) => {
    return await request.get({ url: `/smartcity/dynamic-information-input/page`, params })
  },

  // 查询动态信息录入详情
  getDynamicInformationInput: async (id: number) => {
    return await request.get({ url: `/smartcity/dynamic-information-input/get?id=` + id })
  },

  // 新增动态信息录入
  createDynamicInformationInput: async (data: DynamicInformationInputVO) => {
    return await request.post({ url: `/smartcity/dynamic-information-input/create`, data })
  },

  // 修改动态信息录入
  updateDynamicInformationInput: async (data: DynamicInformationInputVO) => {
    return await request.put({ url: `/smartcity/dynamic-information-input/update`, data })
  },

  // 删除动态信息录入
  deleteDynamicInformationInput: async (id: number) => {
    return await request.delete({ url: `/smartcity/dynamic-information-input/delete?id=` + id })
  },

  // 导出动态信息录入 Excel
  exportDynamicInformationInput: async (params) => {
    return await request.download({ url: `/smartcity/dynamic-information-input/export-excel`, params })
  }
}