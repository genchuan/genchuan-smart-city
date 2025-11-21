import request from '@/config/axios'

// 桥梁安全 VO
export interface BridgeSafetyVO {
  id: number // 主键
  bridgeName: string // 桥梁名称
  bridgeNumber: string // 桥梁编号
  bridgeLocation: string // 桥梁位置
  bridgeType: string // 桥梁类型
  constructionTime: Date // 建造时间
  designLoad: string // 设计荷载
  conditionStructuralComponents: string // 结构部件状况
  basicSettlementData: string // 基础沉降数据
  cableForce: string // 索力
}

// 桥梁安全 API
export const BridgeSafetyApi = {
  // 查询桥梁安全分页
  getBridgeSafetyPage: async (params: any) => {
    return await request.get({ url: `/smartcity/bridge-safety/page`, params })
  },

  // 查询桥梁安全详情
  getBridgeSafety: async (id: number) => {
    return await request.get({ url: `/smartcity/bridge-safety/get?id=` + id })
  },

  // 新增桥梁安全
  createBridgeSafety: async (data: BridgeSafetyVO) => {
    return await request.post({ url: `/smartcity/bridge-safety/create`, data })
  },

  // 修改桥梁安全
  updateBridgeSafety: async (data: BridgeSafetyVO) => {
    return await request.put({ url: `/smartcity/bridge-safety/update`, data })
  },

  // 删除桥梁安全
  deleteBridgeSafety: async (id: number) => {
    return await request.delete({ url: `/smartcity/bridge-safety/delete?id=` + id })
  },

  // 导出桥梁安全 Excel
  exportBridgeSafety: async (params) => {
    return await request.download({ url: `/smartcity/bridge-safety/export-excel`, params })
  }
}