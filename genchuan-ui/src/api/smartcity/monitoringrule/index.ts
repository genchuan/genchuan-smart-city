import request from '@/config/axios'

// 监测项规则设置 VO
export interface MonitoringRuleVO {
  id: number // 主键
  waterLevelOverlimitWarning: string // 水位超限预警
  flowAbnormalityWarning: string // 流量异常预警
  waterQualityExceedanceWarning: string // 水质超标预警
}

// 监测项规则设置 API
export const MonitoringRuleApi = {
  // 查询监测项规则设置分页
  getMonitoringRulePage: async (params: any) => {
    return await request.get({ url: `/smartcity/monitoring-rule/page`, params })
  },

  // 查询监测项规则设置详情
  getMonitoringRule: async (id: number) => {
    return await request.get({ url: `/smartcity/monitoring-rule/get?id=` + id })
  },

  // 新增监测项规则设置
  createMonitoringRule: async (data: MonitoringRuleVO) => {
    return await request.post({ url: `/smartcity/monitoring-rule/create`, data })
  },

  // 修改监测项规则设置
  updateMonitoringRule: async (data: MonitoringRuleVO) => {
    return await request.put({ url: `/smartcity/monitoring-rule/update`, data })
  },

  // 删除监测项规则设置
  deleteMonitoringRule: async (id: number) => {
    return await request.delete({ url: `/smartcity/monitoring-rule/delete?id=` + id })
  },

  // 导出监测项规则设置 Excel
  exportMonitoringRule: async (params) => {
    return await request.download({ url: `/smartcity/monitoring-rule/export-excel`, params })
  }
}