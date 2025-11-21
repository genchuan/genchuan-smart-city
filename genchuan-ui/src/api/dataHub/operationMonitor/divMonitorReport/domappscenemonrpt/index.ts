import request from '@/config/axios'

// 应用场景分域监测报表 VO
export interface DomAppSceneMonRptVO {
  id: number // 主键
  rptId: string // 报表ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  adminCode: string // 行政区划代码
  adminName: string // 行政区划名称
  appSceneMajorId: string // 应用场景大类ID
  appSceneMajorName: string // 应用场景大类名称
  appSceneMidId: string // 应用场景中类ID
  appSceneMidName: string // 应用场景中类名称
  appSceneMinorId: string // 应用场景小类ID
  appSceneMinorName: string // 应用场景小类名称
  domId: string // 分域ID
  domName: string // 分域名称
  totalDeviceCount: number // 关联设备总数
  deviceOnlineRate: number // 设备在线率
  sceneEvtCount: number // 场景事件总数
  evtHandleRate: number // 事件处置率
  resDispatchCount: number // 资源调度次数
  sceneHealthRate: number // 场景健康度
  rptCreateTime: Date // 报表生成时间
  rptCreateUser: number // 报表生成人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 应用场景分域监测报表 API
export const DomAppSceneMonRptApi = {
  // 查询应用场景分域监测报表分页
  getDomAppSceneMonRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-app-scene-mon-rpt/page`, params })
  },

  // 查询应用场景分域监测报表详情
  getDomAppSceneMonRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-app-scene-mon-rpt/get?id=` + id })
  },

  // 新增应用场景分域监测报表
  createDomAppSceneMonRpt: async (data: DomAppSceneMonRptVO) => {
    return await request.post({ url: `/datacenter/dom-app-scene-mon-rpt/create`, data })
  },

  // 修改应用场景分域监测报表
  updateDomAppSceneMonRpt: async (data: DomAppSceneMonRptVO) => {
    return await request.put({ url: `/datacenter/dom-app-scene-mon-rpt/update`, data })
  },

  // 删除应用场景分域监测报表
  deleteDomAppSceneMonRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-app-scene-mon-rpt/delete?id=` + id })
  },

  // 导出应用场景分域监测报表 Excel
  exportDomAppSceneMonRpt: async (params) => {
    return await request.download({ url: `/datacenter/dom-app-scene-mon-rpt/export-excel`, params })
  }
}
