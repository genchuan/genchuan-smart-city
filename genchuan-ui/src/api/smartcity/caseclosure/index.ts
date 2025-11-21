import request from '@/config/axios'

// 案件结案 VO
export interface CaseClosureVO {
  id: number // ID
  caseId: string // 案件ID
  closureReason: string // 结案原因
  closureDepartment: string // 结案部门
  closurePerson: string // 结案人
  closureTime: Date // 结案时间
  approvalPerson: string // 审批人
  approvalTime: Date // 审批时间
  approvalOpinion: string // 审批意见
  archiveNumber: string // 归档编号
  archiveLocation: string // 归档位置
}

// 案件结案 API
export const CaseClosureApi = {
  // 查询案件结案分页
  getCaseClosurePage: async (params: any) => {
    return await request.get({ url: `/smartcity/case-closure/page`, params })
  },

  // 查询案件结案详情
  getCaseClosure: async (id: number) => {
    return await request.get({ url: `/smartcity/case-closure/get?id=` + id })
  },

  // 新增案件结案
  createCaseClosure: async (data: CaseClosureVO) => {
    return await request.post({ url: `/smartcity/case-closure/create`, data })
  },

  // 修改案件结案
  updateCaseClosure: async (data: CaseClosureVO) => {
    return await request.put({ url: `/smartcity/case-closure/update`, data })
  },

  // 删除案件结案
  deleteCaseClosure: async (id: number) => {
    return await request.delete({ url: `/smartcity/case-closure/delete?id=` + id })
  },

  // 导出案件结案 Excel
  exportCaseClosure: async (params) => {
    return await request.download({ url: `/smartcity/case-closure/export-excel`, params })
  },
}