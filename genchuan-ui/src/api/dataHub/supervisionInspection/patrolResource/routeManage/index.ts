import request from '@/config/axios'

// 巡查巡检路线信息 VO
export interface InspectRouteInfoVO {
  id: number // 主键
  routeId: string // 路线ID
  routeName: string // 路线名称
  routeCode: string // 路线编码
  areaCode: string // 所属行政区划代码
  areaName: string // 所属行政区划名称
  startPtName: string // 起点名称
  startPtX: number // 起点坐标X
  startPtY: number // 起点坐标Y
  endPtName: string // 终点名称
  endPtX: number // 终点坐标X
  endPtY: number // 终点坐标Y
  passGridIds: string // 途经网格IDs
  passGridNames: string // 途经网格名称
  estimateEndure: number // 预计时长
  inspectFrequency: string // 巡查频次
  personId: string // 负责人员ID
  personName: string // 负责人员姓名
  routeStatus: boolean // 路线状态
  createUser: string // 创建人
  bizCreateTime: Date // 创建时间（业务）
  updateUser: string // 更新人
  bizUpdateTime: Date // 更新时间（业务）
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检路线信息 API
export const InspectRouteInfoApi = {
  // 查询巡查巡检路线信息分页
  getInspectRouteInfoPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-route-info/page`, params })
  },

  // 查询巡查巡检路线信息详情
  getInspectRouteInfo: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-route-info/get?id=` + id })
  },

  // 新增巡查巡检路线信息
  createInspectRouteInfo: async (data: InspectRouteInfoVO) => {
    return await request.post({ url: `/datacenter/inspect-route-info/create`, data })
  },

  // 修改巡查巡检路线信息
  updateInspectRouteInfo: async (data: InspectRouteInfoVO) => {
    return await request.put({ url: `/datacenter/inspect-route-info/update`, data })
  },

  // 删除巡查巡检路线信息
  deleteInspectRouteInfo: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-route-info/delete?id=` + id })
  },

  // 导出巡查巡检路线信息 Excel
  exportInspectRouteInfo: async (params) => {
    return await request.download({ url: `/datacenter/inspect-route-info/export-excel`, params })
  }
}
