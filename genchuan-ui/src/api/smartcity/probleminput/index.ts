import request from '@/config/axios'

// 问题录入 VO
export interface ProblemInputVO {
  id: number // 主键
  questionTitle: string // 问题标题
  problemDescription: string // 问题描述
  questionTime: Date // 提问时间
  questioner: string // 提问人
  isArea: string // 所属领域
  urgency: string // 紧急程度
  questionType: string // 问题类型
}

// 问题录入 API
export const ProblemInputApi = {
  // 查询问题录入分页
  getProblemInputPage: async (params: any) => {
    return await request.get({ url: `/smartcity/problem-input/page`, params })
  },

  // 查询问题录入详情
  getProblemInput: async (id: number) => {
    return await request.get({ url: `/smartcity/problem-input/get?id=` + id })
  },

  // 新增问题录入
  createProblemInput: async (data: ProblemInputVO) => {
    return await request.post({ url: `/smartcity/problem-input/create`, data })
  },

  // 修改问题录入
  updateProblemInput: async (data: ProblemInputVO) => {
    return await request.put({ url: `/smartcity/problem-input/update`, data })
  },

  // 删除问题录入
  deleteProblemInput: async (id: number) => {
    return await request.delete({ url: `/smartcity/problem-input/delete?id=` + id })
  },

  // 导出问题录入 Excel
  exportProblemInput: async (params) => {
    return await request.download({ url: `/smartcity/problem-input/export-excel`, params })
  }
}