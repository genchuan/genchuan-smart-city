import request from '@/config/axios'

// 案件调查 VO
export interface CaseInvestigationVO {
  id: number // ID
  caseId: string // 案件ID
  investigationLeader: string // 调查负责人
  investigationTeam: string // 调查组成员
  investigationStartTime: Date // 调查开始时间
  investigationEndTime: Date // 调查结束时间
  investigationDesc: string // 调查情况描述
  evidenceDesc: string // 证据情况描述
  testimonyDesc: string // 证人证言描述
  investigationResult: string // 调查结果
  treatmentSuggestion: string // 处理建议
}

// 案件调查 API
export const CaseInvestigationApi = {
  // 查询案件调查分页
  getCaseInvestigationPage: async (params: any) => {
    return await request.get({ url: `/smartcity/case-investigation/page`, params })
  },

  // 查询案件调查详情
  getCaseInvestigation: async (id: number) => {
    return await request.get({ url: `/smartcity/case-investigation/get?id=` + id })
  },

  // 新增案件调查
  createCaseInvestigation: async (data: CaseInvestigationVO) => {
    return await request.post({ url: `/smartcity/case-investigation/create`, data })
  },

  // 修改案件调查
  updateCaseInvestigation: async (data: CaseInvestigationVO) => {
    return await request.put({ url: `/smartcity/case-investigation/update`, data })
  },

  // 删除案件调查
  deleteCaseInvestigation: async (id: number) => {
    return await request.delete({ url: `/smartcity/case-investigation/delete?id=` + id })
  },

  // 导出案件调查 Excel
  exportCaseInvestigation: async (params) => {
    return await request.download({ url: `/smartcity/case-investigation/export-excel`, params })
  },
}