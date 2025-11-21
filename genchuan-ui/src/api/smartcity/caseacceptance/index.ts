import request from '@/config/axios'

// 案件受理 VO
export interface CaseAcceptanceVO {
  id: number // ID
  caseCode: string // 案件编号
  caseName: string // 案件名称
  caseType: string // 案件类型
  caseSource: string // 案件来源
  caseTime: Date // 案件时间
  caseLocation: string // 案件地点
  reportUnit: string // 报案单位
  reportPerson: string // 报案人
  reportPhone: string // 联系电话
  caseDesc: string // 案件描述
  caseStatus: string // 案件状态
}

// 案件受理 API
export const CaseAcceptanceApi = {
  // 查询案件受理分页
  getCaseAcceptancePage: async (params: any) => {
    return await request.get({ url: `/smartcity/case-acceptance/page`, params })
  },

  // 查询案件受理详情
  getCaseAcceptance: async (id: number) => {
    return await request.get({ url: `/smartcity/case-acceptance/get?id=` + id })
  },

  // 新增案件受理
  createCaseAcceptance: async (data: CaseAcceptanceVO) => {
    return await request.post({ url: `/smartcity/case-acceptance/create`, data })
  },

  // 修改案件受理
  updateCaseAcceptance: async (data: CaseAcceptanceVO) => {
    return await request.put({ url: `/smartcity/case-acceptance/update`, data })
  },

  // 删除案件受理
  deleteCaseAcceptance: async (id: number) => {
    return await request.delete({ url: `/smartcity/case-acceptance/delete?id=` + id })
  },

  // 导出案件受理 Excel
  exportCaseAcceptance: async (params) => {
    return await request.download({ url: `/smartcity/case-acceptance/export-excel`, params })
  }
}