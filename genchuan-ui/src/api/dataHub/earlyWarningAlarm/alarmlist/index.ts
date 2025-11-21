import request from '@/config/axios'

// 预警告警列 VO
export interface AlarmListVO {
  id: number // 主键ID
  alarmId: string // 预警ID（UUID）
  alarmCode: string // 预警编号（AL+分域编码+8位流水号）
  riskTypeId: string // 风险类型ID
  riskTypeName: string // 风险类型名称
  alarmLevel: string // 预警等级（一般/较大/重大/特别重大）
  domainId: string // 所属分域ID
  domainName: string // 所属分域名称
  occurRegion: string // 发生区域
  gpsCoordinate: string // GPS坐标（经度,纬度）
  triggerTime: Date // 触发时间
  alarmStatus: string // 预警状态（待处置/处置中/已完成/已解除）
  triggerReason: string // 触发原因
  indicatorId: string // 关联指标ID
  handlerId: string // 处置责任人ID
  handlerName: string // 处置责任人姓名
  lastUpdateTime: Date // 最后更新时间
}

// 预警告警列 API
export const AlarmListApi = {
  // 查询预警告警列分页
  getAlarmListPage: async (params: any) => {
    return await request.get({ url: `/datacenter/alarm-list/page`, params })
  },

  // 查询预警告警列详情
  getAlarmList: async (id: number) => {
    return await request.get({ url: `/datacenter/alarm-list/get?id=` + id })
  },

  // 新增预警告警列
  createAlarmList: async (data: AlarmListVO) => {
    return await request.post({ url: `/datacenter/alarm-list/create`, data })
  },

  // 修改预警告警列
  updateAlarmList: async (data: AlarmListVO) => {
    return await request.put({ url: `/datacenter/alarm-list/update`, data })
  },

  // 删除预警告警列
  deleteAlarmList: async (id: number) => {
    return await request.delete({ url: `/datacenter/alarm-list/delete?id=` + id })
  },

  // 导出预警告警列 Excel
  exportAlarmList: async (params) => {
    return await request.download({ url: `/datacenter/alarm-list/export-excel`, params })
  },
}
