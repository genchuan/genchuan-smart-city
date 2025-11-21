import request from '@/config/axios'

// 事件研判分析 VO
export interface EvtAnalysisVO {
  id: number // 自增主键ID
  analysisId: string // 研判ID
  evtCode: string // 事件编码
  evtName: string // 事件名称
  analystId: string // 研判人ID
  analystName: string // 研判人姓名
  analysisTime: Date // 研判时间
  evtReason: string // 事件原因
  impactRange: string // 影响范围
  urgencyLevel: string // 紧急程度
  conclusion: string // 研判结论
  relMonDataId: string // 关联监测数据ID
  monDataAbstract: string // 监测数据摘要
  needAllocate: string // 是否需要分拨
}

// 事件研判分析 API
export const EvtAnalysisApi = {
  // 查询事件研判分析分页
  getEvtAnalysisPage: async (params: any) => {
    return await request.get({ url: `/datacenter/evt-analysis/page`, params })
  },

  // 查询事件研判分析详情
  getEvtAnalysis: async (id: number) => {
    return await request.get({ url: `/datacenter/evt-analysis/get?id=` + id })
  },

  // 新增事件研判分析
  createEvtAnalysis: async (data: EvtAnalysisVO) => {
    return await request.post({ url: `/datacenter/evt-analysis/create`, data })
  },

  // 修改事件研判分析
  updateEvtAnalysis: async (data: EvtAnalysisVO) => {
    return await request.put({ url: `/datacenter/evt-analysis/update`, data })
  },

  // 删除事件研判分析
  deleteEvtAnalysis: async (id: number) => {
    return await request.delete({ url: `/datacenter/evt-analysis/delete?id=` + id })
  },

  // 导出事件研判分析 Excel
  exportEvtAnalysis: async (params) => {
    return await request.download({ url: `/datacenter/evt-analysis/export-excel`, params })
  },
}