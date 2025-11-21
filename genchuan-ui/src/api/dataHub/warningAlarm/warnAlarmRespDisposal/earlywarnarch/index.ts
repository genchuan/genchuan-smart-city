import request from '@/config/axios'

// 预警告警处置归档 VO
export interface EarlyWarnArchVO {
  id: number // 主键
  archId: string // 归档ID
  archNo: string // 归档编号
  earlyWarnId: string // 预警ID
  earlyWarnName: string // 预警名称
  warnType: string // 预警类型
  warnLevel: string // 预警等级
  regionName: string // 所在区域
  handleUserName: string // 处置人
  comprehensiveScore: number // 综合评估得分
  releaseStatus: string // 解除状态
  archTime: Date // 归档时间
  archUserId: string // 归档人ID
  archUserName: string // 归档人姓名
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 预警告警处置归档 API
export const EarlyWarnArchApi = {
  // 查询预警告警处置归档分页
  getEarlyWarnArchPage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-arch/page`, params })
  },

  // 查询预警告警处置归档详情
  getEarlyWarnArch: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-arch/get?id=` + id })
  },

  // 新增预警告警处置归档
  createEarlyWarnArch: async (data: EarlyWarnArchVO) => {
    return await request.post({ url: `/datacenter/early-warn-arch/create`, data })
  },

  // 修改预警告警处置归档
  updateEarlyWarnArch: async (data: EarlyWarnArchVO) => {
    return await request.put({ url: `/datacenter/early-warn-arch/update`, data })
  },

  // 删除预警告警处置归档
  deleteEarlyWarnArch: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-arch/delete?id=` + id })
  },

  // 导出预警告警处置归档 Excel
  exportEarlyWarnArch: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-arch/export-excel`, params })
  }
}
