import request from '@/config/axios'

// 执法文书 VO
export interface LawDocumentVO {
  id: number // ID
  caseId: string // 案件ID
  documentType: string // 文书类型
  documentCode: string // 文书编号
  documentTitle: string // 文书标题
  documentContent: string // 文书内容
  documentCreator: string // 创建人
  approver: string // 审批人
  approvalTime: Date // 审批时间
  approvalStatus: string // 审批状态
  signatory: string // 签署人
  signTime: Date // 签署时间
  sealStatus: string // 盖章状态
  sealTime: Date // 盖章时间
  printStatus: string // 打印状态
  printTimes: number // 打印次数
}

// 执法文书 API
export const LawDocumentApi = {
  // 查询执法文书分页
  getLawDocumentPage: async (params: any) => {
    return await request.get({ url: `/smartcity/law-document/page`, params })
  },

  // 查询执法文书详情
  getLawDocument: async (id: number) => {
    return await request.get({ url: `/smartcity/law-document/get?id=` + id })
  },

  // 新增执法文书
  createLawDocument: async (data: LawDocumentVO) => {
    return await request.post({ url: `/smartcity/law-document/create`, data })
  },

  // 修改执法文书
  updateLawDocument: async (data: LawDocumentVO) => {
    return await request.put({ url: `/smartcity/law-document/update`, data })
  },

  // 删除执法文书
  deleteLawDocument: async (id: number) => {
    return await request.delete({ url: `/smartcity/law-document/delete?id=` + id })
  },

  // 导出执法文书 Excel
  exportLawDocument: async (params) => {
    return await request.download({ url: `/smartcity/law-document/export-excel`, params })
  },
}