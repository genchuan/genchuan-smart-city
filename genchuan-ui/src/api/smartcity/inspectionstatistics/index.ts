import request from '@/config/axios'

// 巡查分析统计 VO
export interface InspectionStatisticsVO {
  id: number // 主键
  patrolArea: string // 巡查区域
  patrolPersonnel: string // 巡查人员
  completionInspectionTasks: string // 巡查任务完成率
  averagePatrolDuration: string // 平均巡查时长
  numberProblemDiscoveries: string // 问题发现数量
  distributionProblems: string // 不同类型问题分布
  problemSolvingRate: string // 问题解决率
  repetitiveProblemRate: string // 重复问题发生率
  riskLevelAssessment: string // 风险等级评估
  suggestionsMeasures: string // 建议与改进措施
}

// 巡查分析统计 API
export const InspectionStatisticsApi = {
  // 查询巡查分析统计分页
  getInspectionStatisticsPage: async (params: any) => {
    return await request.get({ url: `/smartcity/inspection-statistics/page`, params })
  },

  // 查询巡查分析统计详情
  getInspectionStatistics: async (id: number) => {
    return await request.get({ url: `/smartcity/inspection-statistics/get?id=` + id })
  },

  // 新增巡查分析统计
  createInspectionStatistics: async (data: InspectionStatisticsVO) => {
    return await request.post({ url: `/smartcity/inspection-statistics/create`, data })
  },

  // 修改巡查分析统计
  updateInspectionStatistics: async (data: InspectionStatisticsVO) => {
    return await request.put({ url: `/smartcity/inspection-statistics/update`, data })
  },

  // 删除巡查分析统计
  deleteInspectionStatistics: async (id: number) => {
    return await request.delete({ url: `/smartcity/inspection-statistics/delete?id=` + id })
  },

  // 导出巡查分析统计 Excel
  exportInspectionStatistics: async (params) => {
    return await request.download({ url: `/smartcity/inspection-statistics/export-excel`, params })
  }
}