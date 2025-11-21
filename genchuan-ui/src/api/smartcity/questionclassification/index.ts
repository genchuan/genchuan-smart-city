import request from '@/config/axios'

// 问题录入 VO
export interface QuestionClassificationVO {
  id: number // 主键
  isArea: string // 所属领域
  questionType: string // 问题类型
  urgency: string // 紧急程度
  involvingTheSubject: string // 涉及主体
}

// 问题录入 API
export const QuestionClassificationApi = {
  // 查询问题录入分页
  getQuestionClassificationPage: async (params: any) => {
    return await request.get({ url: `/smartcity/question-classification/page`, params })
  },

  // 查询问题录入详情
  getQuestionClassification: async (id: number) => {
    return await request.get({ url: `/smartcity/question-classification/get?id=` + id })
  },

  // 新增问题录入
  createQuestionClassification: async (data: QuestionClassificationVO) => {
    return await request.post({ url: `/smartcity/question-classification/create`, data })
  },

  // 修改问题录入
  updateQuestionClassification: async (data: QuestionClassificationVO) => {
    return await request.put({ url: `/smartcity/question-classification/update`, data })
  },

  // 删除问题录入
  deleteQuestionClassification: async (id: number) => {
    return await request.delete({ url: `/smartcity/question-classification/delete?id=` + id })
  },

  // 导出问题录入 Excel
  exportQuestionClassification: async (params) => {
    return await request.download({ url: `/smartcity/question-classification/export-excel`, params })
  }
}