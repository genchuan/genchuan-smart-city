import request from '@/config/axios'

// 经验信息分类 VO
export interface ClassificationOfExperienceInformationVO {
  id: number // 主键
  sector: string // 所属行业
  applicationScenarios: string // 应用场景
  empiricalNature: string // 经验性质
  applicableObjects: string // 适用对象
  sourceChannel: string // 来源渠道
}

// 经验信息分类 API
export const ClassificationOfExperienceInformationApi = {
  // 查询经验信息分类分页
  getClassificationOfExperienceInformationPage: async (params: any) => {
    return await request.get({ url: `/smartcity/classification-of-experience-information/page`, params })
  },

  // 查询经验信息分类详情
  getClassificationOfExperienceInformation: async (id: number) => {
    return await request.get({ url: `/smartcity/classification-of-experience-information/get?id=` + id })
  },

  // 新增经验信息分类
  createClassificationOfExperienceInformation: async (data: ClassificationOfExperienceInformationVO) => {
    return await request.post({ url: `/smartcity/classification-of-experience-information/create`, data })
  },

  // 修改经验信息分类
  updateClassificationOfExperienceInformation: async (data: ClassificationOfExperienceInformationVO) => {
    return await request.put({ url: `/smartcity/classification-of-experience-information/update`, data })
  },

  // 删除经验信息分类
  deleteClassificationOfExperienceInformation: async (id: number) => {
    return await request.delete({ url: `/smartcity/classification-of-experience-information/delete?id=` + id })
  },

  // 导出经验信息分类 Excel
  exportClassificationOfExperienceInformation: async (params) => {
    return await request.download({ url: `/smartcity/classification-of-experience-information/export-excel`, params })
  }
}