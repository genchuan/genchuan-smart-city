import request from '@/config/axios'

// 报告归档导出 VO
export interface EvalRptArchVO {
  id: number // 主键ID
  rptArchId: string // 归档ID
  rptArchCode: string // 归档编号
  evalRptId: string // 关联报告ID
  evalRptName: string // 报告名称
  evalObjectId: string // 评价对象ID
  evalObjectName: string // 评价对象名称
  archLocation: string // 归档位置
  archTime: Date // 归档时间
  archMethod: string // 归档方式
  rptVersion: string // 报告版本
  archStatus: string // 归档状态
  failReason: string // 失败原因
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 报告归档导出 API
export const EvalRptArchApi = {
  // 查询报告归档导出分页
  getEvalRptArchPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-rpt-arch/page`, params })
  },

  // 查询报告归档导出详情
  getEvalRptArch: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-rpt-arch/get?id=` + id })
  },

  // 新增报告归档导出
  createEvalRptArch: async (data: EvalRptArchVO) => {
    return await request.post({ url: `/datacenter/eval-rpt-arch/create`, data })
  },

  // 修改报告归档导出
  updateEvalRptArch: async (data: EvalRptArchVO) => {
    return await request.put({ url: `/datacenter/eval-rpt-arch/update`, data })
  },

  // 删除报告归档导出
  deleteEvalRptArch: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-rpt-arch/delete?id=` + id })
  },

  // 导出报告归档导出 Excel
  exportEvalRptArch: async (params) => {
    return await request.download({ url: `/datacenter/eval-rpt-arch/export-excel`, params })
  }
}
