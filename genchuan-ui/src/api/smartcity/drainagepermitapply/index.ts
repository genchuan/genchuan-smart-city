import request from '@/config/axios'

// 排水许可证申请 VO
export interface DrainagePermitApplyVO {
  id: number // ID
  applyNo: string // 申请编号
  userName: string // 排水户名称
  waterQualityReport: string // 排水水质检测报告文件
  dailyDrainage: number // 日均排水量（吨）
  pollutionProof: string // 重点排污单位证明文件路径
  violationHistory: string // 历史违规记录
  applyStatus: string // 申请状态
  approver: string // 审核人
  approveTime: Date // 审核时间
  approveComment: string // 审核意见
}

// 排水许可证申请 API
export const DrainagePermitApplyApi = {
  // 查询排水许可证申请分页
  getDrainagePermitApplyPage: async (params: any) => {
    return await request.get({ url: `/smartcity/drainage-permit-apply/page`, params })
  },

  // 查询排水许可证申请详情
  getDrainagePermitApply: async (id: number) => {
    return await request.get({ url: `/smartcity/drainage-permit-apply/get?id=` + id })
  },

  // 新增排水许可证申请
  createDrainagePermitApply: async (data: DrainagePermitApplyVO) => {
    return await request.post({ url: `/smartcity/drainage-permit-apply/create`, data })
  },

  // 修改排水许可证申请
  updateDrainagePermitApply: async (data: DrainagePermitApplyVO) => {
    return await request.put({ url: `/smartcity/drainage-permit-apply/update`, data })
  },

  // 删除排水许可证申请
  deleteDrainagePermitApply: async (id: number) => {
    return await request.delete({ url: `/smartcity/drainage-permit-apply/delete?id=` + id })
  },

  // 导出排水许可证申请 Excel
  exportDrainagePermitApply: async (params) => {
    return await request.download({ url: `/smartcity/drainage-permit-apply/export-excel`, params })
  },
}