import request from '@/config/axios'

// 按管理事项预警告警统计 VO
export interface EarlyWarnMatterVO {
  id: number // 主键
  warnMngMatterStatId: string // 统计ID
  statCycle: string // 统计周期
  matterMajorId: string // 事项大类ID
  matterMajorName: string // 事项大类名称
  matterMinorId: string // 事项小类ID
  matterMinorName: string // 事项小类名称
  totalWarnCount: number // 预警总数
  completedCount: number // 已办结数
  overtimeCount: number // 超时未处置数
  handleRate: number // 处置率
  mainDeptCode: string // 主要处置部门代码
  mainDeptName: string // 主要处置部门名称
  statUserId: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 按管理事项预警告警统计 API
export const EarlyWarnMatterApi = {
  // 查询按管理事项预警告警统计分页
  getEarlyWarnMatterPage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-matter/page`, params })
  },

  // 查询按管理事项预警告警统计详情
  getEarlyWarnMatter: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-matter/get?id=` + id })
  },

  // 新增按管理事项预警告警统计
  createEarlyWarnMatter: async (data: EarlyWarnMatterVO) => {
    return await request.post({ url: `/datacenter/early-warn-matter/create`, data })
  },

  // 修改按管理事项预警告警统计
  updateEarlyWarnMatter: async (data: EarlyWarnMatterVO) => {
    return await request.put({ url: `/datacenter/early-warn-matter/update`, data })
  },

  // 删除按管理事项预警告警统计
  deleteEarlyWarnMatter: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-matter/delete?id=` + id })
  },

  // 导出按管理事项预警告警统计 Excel
  exportEarlyWarnMatter: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-matter/export-excel`, params })
  }
}
