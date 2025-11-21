import request from '@/config/axios'

// 风险溯源路径 VO
export interface RiskTracePathVO {
  id: number // 主键
  tracePathId: string // 路径ID
  earlyWarnId: string // 预警ID
  earlyWarnName: string // 预警名称
  traceTime: Date // 溯源时间
  node1Content: string // 路径节点1（直接现象）
  node2Content: string // 路径节点2（直接原因）
  node3Content: string // 路径节点3（根本原因）
  traceConclusion: string // 溯源结论
  analystUserId: string // 分析人ID
  analystUserName: string // 分析人姓名
  isAutoAnalysis: string // 是否自动分析
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 风险溯源路径 API
export const RiskTracePathApi = {
  // 查询风险溯源路径分页
  getRiskTracePathPage: async (params: any) => {
    return await request.get({ url: `/datacenter/risk-trace-path/page`, params })
  },

  // 查询风险溯源路径详情
  getRiskTracePath: async (id: number) => {
    return await request.get({ url: `/datacenter/risk-trace-path/get?id=` + id })
  },

  // 新增风险溯源路径
  createRiskTracePath: async (data: RiskTracePathVO) => {
    return await request.post({ url: `/datacenter/risk-trace-path/create`, data })
  },

  // 修改风险溯源路径
  updateRiskTracePath: async (data: RiskTracePathVO) => {
    return await request.put({ url: `/datacenter/risk-trace-path/update`, data })
  },

  // 删除风险溯源路径
  deleteRiskTracePath: async (id: number) => {
    return await request.delete({ url: `/datacenter/risk-trace-path/delete?id=` + id })
  },

  // 导出风险溯源路径 Excel
  exportRiskTracePath: async (params) => {
    return await request.download({ url: `/datacenter/risk-trace-path/export-excel`, params })
  }
}
