import request from '@/config/axios'

// 报警处置类 VO
export interface AlarmHandlingCategoryVO {
  id: number // ID
  time: Date // 报警时间
  alarmSource: string // 报警来源
  riskLevel: string // 风险等级
  alarmDescription: string // 报警描述
  involvingRegions: string // 涉及区域
  disposalMeasures: string // 处置措施
  disposalResults: string // 处置结果
  notes: string // 备注
}

// 报警处置类 API
export const AlarmHandlingCategoryApi = {
  // 查询报警处置类分页
  getAlarmHandlingCategoryPage: async (params: any) => {
    return await request.get({ url: `/smartcity/alarm-handling-category/page`, params })
  },

  // 查询报警处置类详情
  getAlarmHandlingCategory: async (id: number) => {
    return await request.get({ url: `/smartcity/alarm-handling-category/get?id=` + id })
  },

  // 新增报警处置类
  createAlarmHandlingCategory: async (data: AlarmHandlingCategoryVO) => {
    return await request.post({ url: `/smartcity/alarm-handling-category/create`, data })
  },

  // 修改报警处置类
  updateAlarmHandlingCategory: async (data: AlarmHandlingCategoryVO) => {
    return await request.put({ url: `/smartcity/alarm-handling-category/update`, data })
  },

  // 删除报警处置类
  deleteAlarmHandlingCategory: async (id: number) => {
    return await request.delete({ url: `/smartcity/alarm-handling-category/delete?id=` + id })
  },

  // 导出报警处置类 Excel
  exportAlarmHandlingCategory: async (params) => {
    return await request.download({ url: `/smartcity/alarm-handling-category/export-excel`, params })
  }
}