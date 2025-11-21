import request from '@/config/axios'

// 按应用场景预警告警统计 VO
export interface EarlyWarnAppSceneVO {
  id: number // 主键
  warnAppSceneStatId: string // 统计ID
  statCycle: string // 统计周期
  sceneMajorId: string // 场景大类ID
  sceneMajorName: string // 场景大类名称
  sceneMinorId: string // 场景小类ID
  sceneMinorName: string // 场景小类名称
  totalWarnCount: number // 预警总数
  mainWarnType: string // 主要预警类型
  completedCount: number // 已办结数
  successRate: number // 处置成功率
  gridCount: number // 涉及网格数
  statUserId: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 按应用场景预警告警统计 API
export const EarlyWarnAppSceneApi = {
  // 查询按应用场景预警告警统计分页
  getEarlyWarnAppScenePage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-app-scene/page`, params })
  },

  // 查询按应用场景预警告警统计详情
  getEarlyWarnAppScene: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-app-scene/get?id=` + id })
  },

  // 新增按应用场景预警告警统计
  createEarlyWarnAppScene: async (data: EarlyWarnAppSceneVO) => {
    return await request.post({ url: `/datacenter/early-warn-app-scene/create`, data })
  },

  // 修改按应用场景预警告警统计
  updateEarlyWarnAppScene: async (data: EarlyWarnAppSceneVO) => {
    return await request.put({ url: `/datacenter/early-warn-app-scene/update`, data })
  },

  // 删除按应用场景预警告警统计
  deleteEarlyWarnAppScene: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-app-scene/delete?id=` + id })
  },

  // 导出按应用场景预警告警统计 Excel
  exportEarlyWarnAppScene: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-app-scene/export-excel`, params })
  }
}
