import request from '@/config/axios'

// 经验信息录入 VO
export interface ExperienceInformationInputVO {
  id: number // 主键
  experienceTheme: string // 经验主题
  isArea: string // 所属领域
  experienceProvider: string // 经验提供方
  implementationTime: Date // 实施时间
  implementationLocation: string // 实施地点
  detailSteps: string // 详细步骤
  achieveResults: string // 取得成效
  keyPointsForReference: string // 可借鉴要点
}

// 经验信息录入 API
export const ExperienceInformationInputApi = {
  // 查询经验信息录入分页
  getExperienceInformationInputPage: async (params: any) => {
    return await request.get({ url: `/smartcity/experience-information-input/page`, params })
  },

  // 查询经验信息录入详情
  getExperienceInformationInput: async (id: number) => {
    return await request.get({ url: `/smartcity/experience-information-input/get?id=` + id })
  },

  // 新增经验信息录入
  createExperienceInformationInput: async (data: ExperienceInformationInputVO) => {
    return await request.post({ url: `/smartcity/experience-information-input/create`, data })
  },

  // 修改经验信息录入
  updateExperienceInformationInput: async (data: ExperienceInformationInputVO) => {
    return await request.put({ url: `/smartcity/experience-information-input/update`, data })
  },

  // 删除经验信息录入
  deleteExperienceInformationInput: async (id: number) => {
    return await request.delete({ url: `/smartcity/experience-information-input/delete?id=` + id })
  },

  // 导出经验信息录入 Excel
  exportExperienceInformationInput: async (params) => {
    return await request.download({ url: `/smartcity/experience-information-input/export-excel`, params })
  }
}