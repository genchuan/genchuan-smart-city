import request from '@/config/axios'

export const AlarmStatisticsApi = {
  // 查询风险类型库管理分页
  getAlarmLevelStatistics: async (id: number) => {
    return await request.get({ url: `/datacenter/alarm-list/alarm-level-statistics?id=` + id })
  },
}
