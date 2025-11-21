import request from '@/config/axios'

// 城市管理执法 VO
export interface LawEnforcementVO {
  id: number // 主键
  lawEnforcementNumber: string // 执法案件编号
  locationTheCase: string // 案件发生地点
  occurrenceTime: string // 发生时间
  informationInvolved: string // 涉事主体信息
  caseDetailsDescription: string // 案件详情描述
  durationOfCaseHandling: string // 案件处理时长
  numberComplaintsReports: string // 投诉举报次数
  complaintContent: string // 投诉内容
}

// 城市管理执法 API
export const LawEnforcementApi = {
  // 查询城市管理执法分页
  getLawEnforcementPage: async (params: any) => {
    return await request.get({ url: `/smartcity/law-enforcement/page`, params })
  },

  // 查询城市管理执法详情
  getLawEnforcement: async (id: number) => {
    return await request.get({ url: `/smartcity/law-enforcement/get?id=` + id })
  },

  // 新增城市管理执法
  createLawEnforcement: async (data: LawEnforcementVO) => {
    return await request.post({ url: `/smartcity/law-enforcement/create`, data })
  },

  // 修改城市管理执法
  updateLawEnforcement: async (data: LawEnforcementVO) => {
    return await request.put({ url: `/smartcity/law-enforcement/update`, data })
  },

  // 删除城市管理执法
  deleteLawEnforcement: async (id: number) => {
    return await request.delete({ url: `/smartcity/law-enforcement/delete?id=` + id })
  },

  // 导出城市管理执法 Excel
  exportLawEnforcement: async (params) => {
    return await request.download({ url: `/smartcity/law-enforcement/export-excel`, params })
  }
}