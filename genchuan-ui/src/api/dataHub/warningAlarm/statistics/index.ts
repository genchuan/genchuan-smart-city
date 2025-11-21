import request from '@/config/axios'

export const AlarmStatisticsApi = {
  // 查询风险类型库管理分页
  getAlarmLevelStatistics: async (id: number) => {
    return await request.get({ url: `/datacenter/warning-alert-list-table/level-statistics?id=` + id })
  },//datacenter/warning-alert-list-table/level-statistics
  getAlarmStatusStatistics: async (id: number) => {
    return await request.get({ url: `/datacenter/warning-alert-list-table/status-statistics?id=` + id })
  },
}
