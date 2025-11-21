import request from '@/config/axios'

// 预警告警处置效率统计 VO
export interface EarlyWarnHandleEffVO {
  id: number // 主键
  efficacyStatId: string // 统计ID
  statCycle: string // 统计周期
  handleDeptId: string // 处置部门ID
  handleDeptName: string // 处置部门名称
  totalHandleCount: number // 处置预警总数
  completedCount: number // 已办结数
  handleRate: number // 处置率
  avgHandleEndure: number // 平均处置时长
  criticalAvgEndure: number // 严重等级平均时长
  mainHandleType: string // 主要处置类型
  mainTypeProportion: number // 主要处置类型占比
  statUserId: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 预警告警处置效率统计 API
export const EarlyWarnHandleEffApi = {
  // 查询预警告警处置效率统计分页
  getEarlyWarnHandleEffPage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-handle-eff/page`, params })
  },

  // 查询预警告警处置效率统计详情
  getEarlyWarnHandleEff: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-handle-eff/get?id=` + id })
  },

  // 新增预警告警处置效率统计
  createEarlyWarnHandleEff: async (data: EarlyWarnHandleEffVO) => {
    return await request.post({ url: `/datacenter/early-warn-handle-eff/create`, data })
  },

  // 修改预警告警处置效率统计
  updateEarlyWarnHandleEff: async (data: EarlyWarnHandleEffVO) => {
    return await request.put({ url: `/datacenter/early-warn-handle-eff/update`, data })
  },

  // 删除预警告警处置效率统计
  deleteEarlyWarnHandleEff: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-handle-eff/delete?id=` + id })
  },

  // 导出预警告警处置效率统计 Excel
  exportEarlyWarnHandleEff: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-handle-eff/export-excel`, params })
  }
}
