import request from '@/config/axios'

// 养护考核评价 VO
export interface AssessmentAndEvaluationVO {
  id: number // 主键
  maintenancePersonnelNumber: string // 养护人员编号
  maintenanceTaskNumber: string // 养护任务编号
  assessmentCycle: string // 考核周期
  assessmentScore: string // 考核得分
  assessmentLevel: string // 考核等级
  evaluationOpinion: string // 评价意见
  improvementSuggestions: string // 改进建议
}

// 养护考核评价 API
export const AssessmentAndEvaluationApi = {
  // 查询养护考核评价分页
  getAssessmentAndEvaluationPage: async (params: any) => {
    return await request.get({ url: `/smartcity/assessment-and-evaluation/page`, params })
  },

  // 查询养护考核评价详情
  getAssessmentAndEvaluation: async (id: number) => {
    return await request.get({ url: `/smartcity/assessment-and-evaluation/get?id=` + id })
  },

  // 新增养护考核评价
  createAssessmentAndEvaluation: async (data: AssessmentAndEvaluationVO) => {
    return await request.post({ url: `/smartcity/assessment-and-evaluation/create`, data })
  },

  // 修改养护考核评价
  updateAssessmentAndEvaluation: async (data: AssessmentAndEvaluationVO) => {
    return await request.put({ url: `/smartcity/assessment-and-evaluation/update`, data })
  },

  // 删除养护考核评价
  deleteAssessmentAndEvaluation: async (id: number) => {
    return await request.delete({ url: `/smartcity/assessment-and-evaluation/delete?id=` + id })
  },

  // 导出养护考核评价 Excel
  exportAssessmentAndEvaluation: async (params) => {
    return await request.download({ url: `/smartcity/assessment-and-evaluation/export-excel`, params })
  }
}