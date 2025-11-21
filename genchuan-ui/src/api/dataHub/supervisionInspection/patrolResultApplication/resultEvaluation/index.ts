import request from '@/config/axios'

// 巡查巡检结果评估 VO
export interface InspectResultEvalVO {
  id: number // 主键ID
  evalId: string // 评估ID
  evalCode: string // 评估编码
  evalCycle: string // 评估周期
  evalCycleName: string // 评估周期名称
  areaCode: string // 所属区域代码
  areaName: string // 所属区域名称
  dailyQualifiedRate: number // 日常巡查合格率
  specRectifyRate: number // 专项巡查整改率
  level1TimelyRate: number // 一级问题处置及时率
  problemConcentratedArea: string // 问题集中区域
  mainProblemType: string // 主要问题类型
  evalOpinion: string // 评估意见
  evalUser: string // 评估人
  evalTime: Date // 评估时间
  evalRptUrl: string // 评估报告URL
  createUser: string // 创建人
  bizCreateTime: Date // 创建时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检结果评估 API
export const InspectResultEvalApi = {
  // 查询巡查巡检结果评估分页
  getInspectResultEvalPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-result-eval/page`, params })
  },

  // 查询巡查巡检结果评估详情
  getInspectResultEval: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-result-eval/get?id=` + id })
  },

  // 新增巡查巡检结果评估
  createInspectResultEval: async (data: InspectResultEvalVO) => {
    return await request.post({ url: `/datacenter/inspect-result-eval/create`, data })
  },

  // 修改巡查巡检结果评估
  updateInspectResultEval: async (data: InspectResultEvalVO) => {
    return await request.put({ url: `/datacenter/inspect-result-eval/update`, data })
  },

  // 删除巡查巡检结果评估
  deleteInspectResultEval: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-result-eval/delete?id=` + id })
  },

  // 导出巡查巡检结果评估 Excel
  exportInspectResultEval: async (params) => {
    return await request.download({ url: `/datacenter/inspect-result-eval/export-excel`, params })
  }
}
