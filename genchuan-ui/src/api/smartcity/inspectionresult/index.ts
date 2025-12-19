import request from '@/config/axios'

// 检查结果管理 VO
export interface InspectionResultVO {
  id: number // ID
  inspectionNumber: string // 检查结果编号
  taskNumber: string // 检查任务编号
  inspectionContent: string // 检查内容
  inspectionResult: string // 检查结果
  inspectionTime: Date // 检查时间
  inspectionLocation: string // 检查地点
  disposalOpinion: string // 处理意见
  officerNumber: string // 执法人员编号
}

// 检查结果管理 API
export const InspectionResultApi = {
  // 查询检查结果分页
  getInspectionResultPage: async (params: any) => {
    return await request.get({ url: `/smartcity/inspection-result/page`, params })
  },

  // 查询检查结果详情
  getInspectionResult: async (id: number) => {
    return await request.get({ url: `/smartcity/inspection-result/get?id=` + id })
  },

  // 新增检查结果
  createInspectionResult: async (data: InspectionResultVO) => {
    return await request.post({ url: `/smartcity/inspection-result/create`, data })
  },

  // 修改检查结果
  updateInspectionResult: async (data: InspectionResultVO) => {
    return await request.put({ url: `/smartcity/inspection-result/update`, data })
  },

  // 删除检查结果
  deleteInspectionResult: async (id: number) => {
    return await request.delete({ url: `/smartcity/inspection-result/delete?id=` + id })
  },

  // 导出检查结果 Excel
  exportInspectionResult: async (params) => {
    return await request.download({ url: `/smartcity/inspection-result/export-excel`, params })
  }
}
