import request from '@/config/axios'

// 处置结果评估 VO
export interface DisposalResultEvalVO {
  id: number // 主键ID
  evalId: string // 评估ID
  traceId: string // 关联回溯ID
  evtCode: string // 事件编码
  evtName: string // 事件名称
  efficacyScore: number // 处置效率得分
  efficacyDesc: string // 处置效率说明
  resScore: number // 资源利用得分
  resDesc: string // 资源利用说明
  effectScore: number // 效果达成得分
  effectDesc: string // 效果达成说明
  comprehensiveScore: number // 综合得分
  improveSugg: string // 改进建议
  evalUserId: string // 评估人ID
  evalUserName: string // 评估人姓名
  evalTime: Date // 评估时间
  rptPath: string // 评估报告路径
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 处置结果评估 API
export const DisposalResultEvalApi = {
  // 查询处置结果评估分页
  getDisposalResultEvalPage: async (params: any) => {
    return await request.get({ url: `/datacenter/disposal-result-eval/page`, params })
  },

  // 查询处置结果评估详情
  getDisposalResultEval: async (id: number) => {
    return await request.get({ url: `/datacenter/disposal-result-eval/get?id=` + id })
  },

  // 新增处置结果评估
  createDisposalResultEval: async (data: DisposalResultEvalVO) => {
    return await request.post({ url: `/datacenter/disposal-result-eval/create`, data })
  },

  // 修改处置结果评估
  updateDisposalResultEval: async (data: DisposalResultEvalVO) => {
    return await request.put({ url: `/datacenter/disposal-result-eval/update`, data })
  },

  // 删除处置结果评估
  deleteDisposalResultEval: async (id: number) => {
    return await request.delete({ url: `/datacenter/disposal-result-eval/delete?id=` + id })
  },

  // 导出处置结果评估 Excel
  exportDisposalResultEval: async (params) => {
    return await request.download({ url: `/datacenter/disposal-result-eval/export-excel`, params })
  },
}