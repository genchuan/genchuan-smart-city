import request from '@/config/axios'

// 执法监督 VO
export interface LawEnforcementSupervisionVO {
  id: number // 主键
  supervisionId: string // 监督编号
  eventNumber: string // 执法事件编号
  officials: string // 执法人员
  personnel: string // 监督人员
  time: string // 监督时间
  method: string // 监督方式
  integrityCollection: string // 证据采集完整性
  satisfactionInvolved: string // 当事人满意度
  resultEvaluation: string // 监督结果评价
  violationDescription: string // 违规行为描述
}

// 执法监督 API
export const LawEnforcementSupervisionApi = {
  // 查询执法监督分页
  getLawEnforcementSupervisionPage: async (params: any) => {
    return await request.get({ url: `/smartcity/law-enforcement-supervision/page`, params })
  },

  // 查询执法监督详情
  getLawEnforcementSupervision: async (id: number) => {
    return await request.get({ url: `/smartcity/law-enforcement-supervision/get?id=` + id })
  },

  // 新增执法监督
  createLawEnforcementSupervision: async (data: LawEnforcementSupervisionVO) => {
    return await request.post({ url: `/smartcity/law-enforcement-supervision/create`, data })
  },

  // 修改执法监督
  updateLawEnforcementSupervision: async (data: LawEnforcementSupervisionVO) => {
    return await request.put({ url: `/smartcity/law-enforcement-supervision/update`, data })
  },

  // 删除执法监督
  deleteLawEnforcementSupervision: async (id: number) => {
    return await request.delete({ url: `/smartcity/law-enforcement-supervision/delete?id=` + id })
  },

  // 导出执法监督 Excel
  exportLawEnforcementSupervision: async (params) => {
    return await request.download({ url: `/smartcity/law-enforcement-supervision/export-excel`, params })
  }
}