import request from '@/config/axios'

// 预警告警自定义统计配置 VO
export interface EarlyWarnCustomCfgVO {
  id: number // 主键
  customStatCfgId: string // 配置ID
  cfgName: string // 配置名称
  statCycle: string // 统计周期
  statDimensions: string // 统计维度
  filterConditions: string // 筛选条件
  groupDimensions: string // 分组维度
  statIndicators: string // 统计指标
  createUserId: string // 创建人ID
  createUserName: string // 创建人姓名
  isFrequentlyUsed: string // 是否常用
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 预警告警自定义统计配置 API
export const EarlyWarnCustomCfgApi = {
  // 查询预警告警自定义统计配置分页
  getEarlyWarnCustomCfgPage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-custom-cfg/page`, params })
  },

  // 查询预警告警自定义统计配置详情
  getEarlyWarnCustomCfg: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-custom-cfg/get?id=` + id })
  },

  // 新增预警告警自定义统计配置
  createEarlyWarnCustomCfg: async (data: EarlyWarnCustomCfgVO) => {
    return await request.post({ url: `/datacenter/early-warn-custom-cfg/create`, data })
  },

  // 修改预警告警自定义统计配置
  updateEarlyWarnCustomCfg: async (data: EarlyWarnCustomCfgVO) => {
    return await request.put({ url: `/datacenter/early-warn-custom-cfg/update`, data })
  },

  // 删除预警告警自定义统计配置
  deleteEarlyWarnCustomCfg: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-custom-cfg/delete?id=` + id })
  },

  // 导出预警告警自定义统计配置 Excel
  exportEarlyWarnCustomCfg: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-custom-cfg/export-excel`, params })
  }
}
