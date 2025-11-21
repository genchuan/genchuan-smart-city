import request from '@/config/axios'

// 风险传播链 VO
export interface RiskPropagateChainVO {
  id: number // 主键
  propagateChainId: string // 传播链ID
  propagateChainNo: string // 传播链编号
  sourceWarnId: string // 源头预警ID
  sourceWarnName: string // 源头预警名称
  nodeSeq: number // 传播节点序号
  nodeContent: string // 传播节点内容
  nodeType: string // 节点类型
  occurTime: Date // 发生时间
  impactRange: string // 影响范围
  deviceId: string // 关联设备ID
  deviceName: string // 关联设备名称
  recUserId: string // 记录人ID
  recUserName: string // 记录人姓名
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 风险传播链 API
export const RiskPropagateChainApi = {
  // 查询风险传播链分页
  getRiskPropagateChainPage: async (params: any) => {
    return await request.get({ url: `/datacenter/risk-propagate-chain/page`, params })
  },

  // 查询风险传播链详情
  getRiskPropagateChain: async (id: number) => {
    return await request.get({ url: `/datacenter/risk-propagate-chain/get?id=` + id })
  },

  // 新增风险传播链
  createRiskPropagateChain: async (data: RiskPropagateChainVO) => {
    return await request.post({ url: `/datacenter/risk-propagate-chain/create`, data })
  },

  // 修改风险传播链
  updateRiskPropagateChain: async (data: RiskPropagateChainVO) => {
    return await request.put({ url: `/datacenter/risk-propagate-chain/update`, data })
  },

  // 删除风险传播链
  deleteRiskPropagateChain: async (id: number) => {
    return await request.delete({ url: `/datacenter/risk-propagate-chain/delete?id=` + id })
  },

  // 导出风险传播链 Excel
  exportRiskPropagateChain: async (params) => {
    return await request.download({ url: `/datacenter/risk-propagate-chain/export-excel`, params })
  }
}
