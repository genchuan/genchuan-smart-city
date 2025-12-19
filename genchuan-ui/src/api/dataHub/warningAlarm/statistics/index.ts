import request from '@/config/axios'

export const AlarmStatisticsApi = {
  // 查询风险类型库管理分页
  getAlarmLevelStatistics: async (id: number) => {
    return await request.get({ url: `/datacenter/warning-alert-list-table/level-statistics?id=` + id })
  },//datacenter/warning-alert-list-table/level-statistics
  getAlarmStatusStatistics: async (id: number) => {
    return await request.get({ url: `/datacenter/warning-alert-list-table/status-statistics?id=` + id })
  },
  //责任人下（用户）关联的告警预警等级分类统计datacenter/warning-alert-list-table/responsible-person-level-statistics
  // getLevelAlarmsOfResponsible: async (params:any) => {
  //   return await request.post({ url: `/datacenter/warning-alert-list-table/responsible-person-level-statistics` ,params })
  // },
  // 责任人下（用户）关联的告警预警等级分类统计（修改后）
  getLevelAlarmsOfResponsible: async (data: { responsiblePerson: string}) => {
    return await request.post({
      url: `/datacenter/warning-alert-list-table/responsible-person-level-statistics`,
      data // 关键修改：将参数放入data（请求体），而非params（查询参数）
    })
  },

}
