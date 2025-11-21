import request from '@/config/axios'

// 巡查结果 VO
export interface InspectionResultsAVO {
  id: number // 主键
  number: string // 编号
  patrolPersonnel: string // 巡查人员
  patrolTime: Date // 巡查时间
  patrolLocation: string // 巡查地点
  resourceName: string // 资源名称
  resourceNumber: string // 资源编号
  inspectionItems: string // 检查项目
  inspectionResults: string // 检查结果
}

// 巡查结果 API
export const InspectionResultsAApi = {
  // 查询巡查结果分页
  getInspectionResultsAPage: async (params: any) => {
    return await request.get({ url: `/smartcity/inspection-results-a/page`, params })
  },

  // 查询巡查结果详情
  getInspectionResultsA: async (id: number) => {
    return await request.get({ url: `/smartcity/inspection-results-a/get?id=` + id })
  },

  // 新增巡查结果
  createInspectionResultsA: async (data: InspectionResultsAVO) => {
    return await request.post({ url: `/smartcity/inspection-results-a/create`, data })
  },

  // 修改巡查结果
  updateInspectionResultsA: async (data: InspectionResultsAVO) => {
    return await request.put({ url: `/smartcity/inspection-results-a/update`, data })
  },

  // 删除巡查结果
  deleteInspectionResultsA: async (id: number) => {
    return await request.delete({ url: `/smartcity/inspection-results-a/delete?id=` + id })
  },

  // 导出巡查结果 Excel
  exportInspectionResultsA: async (params) => {
    return await request.download({ url: `/smartcity/inspection-results-a/export-excel`, params })
  }
}