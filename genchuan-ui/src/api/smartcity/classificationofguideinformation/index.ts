import request from '@/config/axios'

// 指南信息分类 VO
export interface ClassificationOfGuideInformationVO {
  id: number // 主键
  applications: string // 适用领域
  applicationScenarios: string // 应用场景
  targetAudience: string // 受众群体
  natureOfTheGuide: string // 指南性质
}

// 指南信息分类 API
export const ClassificationOfGuideInformationApi = {
  // 查询指南信息分类分页
  getClassificationOfGuideInformationPage: async (params: any) => {
    return await request.get({ url: `/smartcity/classification-of-guide-information/page`, params })
  },

  // 查询指南信息分类详情
  getClassificationOfGuideInformation: async (id: number) => {
    return await request.get({ url: `/smartcity/classification-of-guide-information/get?id=` + id })
  },

  // 新增指南信息分类
  createClassificationOfGuideInformation: async (data: ClassificationOfGuideInformationVO) => {
    return await request.post({ url: `/smartcity/classification-of-guide-information/create`, data })
  },

  // 修改指南信息分类
  updateClassificationOfGuideInformation: async (data: ClassificationOfGuideInformationVO) => {
    return await request.put({ url: `/smartcity/classification-of-guide-information/update`, data })
  },

  // 删除指南信息分类
  deleteClassificationOfGuideInformation: async (id: number) => {
    return await request.delete({ url: `/smartcity/classification-of-guide-information/delete?id=` + id })
  },

  // 导出指南信息分类 Excel
  exportClassificationOfGuideInformation: async (params) => {
    return await request.download({ url: `/smartcity/classification-of-guide-information/export-excel`, params })
  }
}