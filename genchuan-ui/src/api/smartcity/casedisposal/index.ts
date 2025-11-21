import request from '@/config/axios'

// 案件处理 VO
export interface CaseDisposalVO {
  id: number // ID
  caseId: string // 案件ID
  disposalType: string // 处理类型
  disposalDepartment: string // 处理部门
  disposalPerson: string // 处理人
  disposalStartTime: Date // 处理开始时间
  disposalEndTime: Date // 处理结束时间
  disposalBasis: string // 处理依据
  disposalContent: string // 处理内容
  disposalResult: string // 处理结果
  penaltyAmount: number // 处罚金额
  penaltyType: string // 处罚类型
}

// 案件处理 API
export const CaseDisposalApi = {
  // 查询案件处理分页
  getCaseDisposalPage: async (params: any) => {
    return await request.get({ url: `/smartcity/case-disposal/page`, params })
  },

  // 查询案件处理详情
  getCaseDisposal: async (id: number) => {
    return await request.get({ url: `/smartcity/case-disposal/get?id=` + id })
  },

  // 新增案件处理
  createCaseDisposal: async (data: CaseDisposalVO) => {
    return await request.post({ url: `/smartcity/case-disposal/create`, data })
  },

  // 修改案件处理
  updateCaseDisposal: async (data: CaseDisposalVO) => {
    return await request.put({ url: `/smartcity/case-disposal/update`, data })
  },

  // 删除案件处理
  deleteCaseDisposal: async (id: number) => {
    return await request.delete({ url: `/smartcity/case-disposal/delete?id=` + id })
  },

  // 导出案件处理 Excel
  exportCaseDisposal: async (params) => {
    return await request.download({ url: `/smartcity/case-disposal/export-excel`, params })
  },
}