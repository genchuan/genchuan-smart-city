import request from '@/config/axios'

// 巡查分析 VO
export interface InspectionAnalysisVO {
  id: number // 主键ID
  inspector: string // 巡查人员
  cycleStartTime: Date // 巡查开始时间
  cycleEndTime: Date // 巡查结束时间
  completedTaskCount: number // 完成巡查任务数
  totalMileageKm: number // 巡查总里程（km）
  totalDurationH: number // 巡查总时长（h）
  foundProblemCount: number // 发现问题总数
}

// 巡查分析 API
export const InspectionAnalysisApi = {
  // 查询巡查分析分页
  getInspectionAnalysisPage: async (params: any) => {
    return await request.get({ url: `/smartcity/inspection-analysis/page`, params })
  },

  // 查询巡查分析详情
  getInspectionAnalysis: async (id: number) => {
    return await request.get({ url: `/smartcity/inspection-analysis/get?id=` + id })
  },

  // 新增巡查分析
  createInspectionAnalysis: async (data: InspectionAnalysisVO) => {
    return await request.post({ url: `/smartcity/inspection-analysis/create`, data })
  },

  // 修改巡查分析
  updateInspectionAnalysis: async (data: InspectionAnalysisVO) => {
    return await request.put({ url: `/smartcity/inspection-analysis/update`, data })
  },

  // 删除巡查分析
  deleteInspectionAnalysis: async (id: number) => {
    return await request.delete({ url: `/smartcity/inspection-analysis/delete?id=` + id })
  },

  // 导出巡查分析 Excel
  exportInspectionAnalysis: async (params) => {
    return await request.download({ url: `/smartcity/inspection-analysis/export-excel`, params })
  }
}
