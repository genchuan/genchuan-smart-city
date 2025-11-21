import request from '@/config/axios'

// 动态信息分类 VO
export interface DynamicInformationClassificationVO {
  id: number // 主键
  messageSubject: string // 信息主题
  industrySector: string // 行业领域
  informationSources: string // 信息来源
  urgency: string // 紧急程度
}

// 动态信息分类 API
export const DynamicInformationClassificationApi = {
  // 查询动态信息分类分页
  getDynamicInformationClassificationPage: async (params: any) => {
    return await request.get({ url: `/smartcity/dynamic-information-classification/page`, params })
  },

  // 查询动态信息分类详情
  getDynamicInformationClassification: async (id: number) => {
    return await request.get({ url: `/smartcity/dynamic-information-classification/get?id=` + id })
  },

  // 新增动态信息分类
  createDynamicInformationClassification: async (data: DynamicInformationClassificationVO) => {
    return await request.post({ url: `/smartcity/dynamic-information-classification/create`, data })
  },

  // 修改动态信息分类
  updateDynamicInformationClassification: async (data: DynamicInformationClassificationVO) => {
    return await request.put({ url: `/smartcity/dynamic-information-classification/update`, data })
  },

  // 删除动态信息分类
  deleteDynamicInformationClassification: async (id: number) => {
    return await request.delete({ url: `/smartcity/dynamic-information-classification/delete?id=` + id })
  },

  // 导出动态信息分类 Excel
  exportDynamicInformationClassification: async (params) => {
    return await request.download({ url: `/smartcity/dynamic-information-classification/export-excel`, params })
  }
}