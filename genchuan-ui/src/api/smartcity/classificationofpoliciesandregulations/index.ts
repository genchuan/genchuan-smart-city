import request from '@/config/axios'

// 政策法规分类 VO
export interface ClassificationOfPoliciesAndRegulationsVO {
  id: number // 主键
  regulatoryCategory: string // 法规类别
  isArea: string // 所属领域
  theme: string // 主题
  applicableObjects: string // 适用对象
  levelOfEffectiveness: string // 效力层级
  departmentOfPublication: string // 发布部门
  implementationTime: string // 实施时间
  revocatoryDate: string // 废止时间 
}

// 政策法规分类 API
export const ClassificationOfPoliciesAndRegulationsApi = {
  // 查询政策法规分类分页
  getClassificationOfPoliciesAndRegulationsPage: async (params: any) => {
    return await request.get({ url: `/smartcity/classification-of-policies-and-regulations/page`, params })
  },

  // 查询政策法规分类详情
  getClassificationOfPoliciesAndRegulations: async (id: number) => {
    return await request.get({ url: `/smartcity/classification-of-policies-and-regulations/get?id=` + id })
  },

  // 新增政策法规分类
  createClassificationOfPoliciesAndRegulations: async (data: ClassificationOfPoliciesAndRegulationsVO) => {
    return await request.post({ url: `/smartcity/classification-of-policies-and-regulations/create`, data })
  },

  // 修改政策法规分类
  updateClassificationOfPoliciesAndRegulations: async (data: ClassificationOfPoliciesAndRegulationsVO) => {
    return await request.put({ url: `/smartcity/classification-of-policies-and-regulations/update`, data })
  },

  // 删除政策法规分类
  deleteClassificationOfPoliciesAndRegulations: async (id: number) => {
    return await request.delete({ url: `/smartcity/classification-of-policies-and-regulations/delete?id=` + id })
  },

  // 导出政策法规分类 Excel
  exportClassificationOfPoliciesAndRegulations: async (params) => {
    return await request.download({ url: `/smartcity/classification-of-policies-and-regulations/export-excel`, params })
  }
}