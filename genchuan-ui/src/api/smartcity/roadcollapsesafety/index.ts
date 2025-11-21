import request from '@/config/axios'

// 路面塌陷安全 VO
export interface RoadCollapseSafetyVO {
  id: number // 主键
  monitoringPointName: string // 监测点名称
  locationMonitoringPoints: string // 监测点位置
  roadCondition: string // 路面状况
  undergroundCavityInformation: string // 地下空洞信息
  soilMoisture: string // 土壤湿度
  soilDisplacementData: string // 土壤位移数据
  settlementSurroundingBuildings: string // 周边建筑物沉降情况
  changesGroundwaterLevel: string // 地下水位变化
  rainfallSituation: string // 降雨情况
  trafficFlow: string // 交通流量
  trafficHeavyVehicles: string // 重型车辆通行情况
  warningLevel: string // 预警级别
  warningTime: Date // 预警时间
  expertEvaluationOpinions: string // 专家评估意见
  historicalCollapseRecords: string // 历史塌陷记录
}

// 路面塌陷安全 API
export const RoadCollapseSafetyApi = {
  // 查询路面塌陷安全分页
  getRoadCollapseSafetyPage: async (params: any) => {
    return await request.get({ url: `/smartcity/road-collapse-safety/page`, params })
  },

  // 查询路面塌陷安全详情
  getRoadCollapseSafety: async (id: number) => {
    return await request.get({ url: `/smartcity/road-collapse-safety/get?id=` + id })
  },

  // 新增路面塌陷安全
  createRoadCollapseSafety: async (data: RoadCollapseSafetyVO) => {
    return await request.post({ url: `/smartcity/road-collapse-safety/create`, data })
  },

  // 修改路面塌陷安全
  updateRoadCollapseSafety: async (data: RoadCollapseSafetyVO) => {
    return await request.put({ url: `/smartcity/road-collapse-safety/update`, data })
  },

  // 删除路面塌陷安全
  deleteRoadCollapseSafety: async (id: number) => {
    return await request.delete({ url: `/smartcity/road-collapse-safety/delete?id=` + id })
  },

  // 导出路面塌陷安全 Excel
  exportRoadCollapseSafety: async (params) => {
    return await request.download({ url: `/smartcity/road-collapse-safety/export-excel`, params })
  }
}