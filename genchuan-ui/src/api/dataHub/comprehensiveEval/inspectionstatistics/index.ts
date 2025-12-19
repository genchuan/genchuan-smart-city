import request from '@/config/axios'

// 环卫考核统计结果 VO
export interface InspectionStatisticsVO {
  id: number // 主键ID
  inspectionDate: string // 考核日期
  areaType: string // 场所类型
  areaName: string // 区域/地点名称
  totalScore: number // 该场所总得分
  maxScore: number // 该类型满分
  weight: number // 权重
  scoreWeighted: number // 最终得分
  sampleCount: number // 样本数
  inspectionStatus: string // 考核状态
}

// 环卫考核统计结果 API
export const InspectionStatisticsApi = {
  // 查询环卫考核统计结果分页
  getInspectionStatisticsPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspection-statistics/page`, params })
  },

  // 查询环卫考核统计结果详情
  getInspectionStatistics: async (id: number) => {
    return await request.get({ url: `/datacenter/inspection-statistics/get?id=` + id })
  },

  // 新增环卫考核统计结果
  createInspectionStatistics: async (data: InspectionStatisticsVO) => {
    return await request.post({ url: `/datacenter/inspection-statistics/create`, data })
  },

  // 修改环卫考核统计结果
  updateInspectionStatistics: async (data: InspectionStatisticsVO) => {
    return await request.put({ url: `/datacenter/inspection-statistics/update`, data })
  },

  // 删除环卫考核统计结果
  deleteInspectionStatistics: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspection-statistics/delete?id=` + id })
  },

  // 导出环卫考核统计结果 Excel
  exportInspectionStatistics: async (params) => {
    return await request.download({ url: `/datacenter/inspection-statistics/export-excel`, params })
  }
}
