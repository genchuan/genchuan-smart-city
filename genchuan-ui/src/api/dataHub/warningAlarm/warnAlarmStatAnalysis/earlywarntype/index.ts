import request from '@/config/axios'

// 预警告警类型维度统计 VO
export interface EarlyWarnTypeVO {
  id: number // 主键
  typeStatId: string // 统计ID
  statCycle: string // 统计周期
  warnType: string // 预警类型
  warnTypeCode: string // 预警类型编码
  totalWarnCount: number // 预警总数
  proportion: number // 占比
  completedCount: number // 已办结数
  handleRate: number // 处置率
  mainRegionName: string // 主要分布区域名称
  mainRegionCount: number // 主要分布区域数量
  statUserId: string // 统计人
  statTime: Date // 统计时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 预警告警类型维度统计 API
export const EarlyWarnTypeApi = {
  // 查询预警告警类型维度统计分页
  getEarlyWarnTypePage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-type/page`, params })
  },

  // 查询预警告警类型维度统计详情
  getEarlyWarnType: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-type/get?id=` + id })
  },

  // 新增预警告警类型维度统计
  createEarlyWarnType: async (data: EarlyWarnTypeVO) => {
    return await request.post({ url: `/datacenter/early-warn-type/create`, data })
  },

  // 修改预警告警类型维度统计
  updateEarlyWarnType: async (data: EarlyWarnTypeVO) => {
    return await request.put({ url: `/datacenter/early-warn-type/update`, data })
  },

  // 删除预警告警类型维度统计
  deleteEarlyWarnType: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-type/delete?id=` + id })
  },

  // 导出预警告警类型维度统计 Excel
  exportEarlyWarnType: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-type/export-excel`, params })
  }
}
