import request from '@/config/axios'

// 应用场景分域 VO
export interface DomDivAppSceneVO {
  id: number // 主键ID
  domId: string // 分域ID
  domName: string // 分域名称
  domCode: string // 分域编码
  appSceneMajorId: string // 应用场景大类ID
  appSceneMajorName: string // 应用场景大类名称
  appSceneMinorId: string // 应用场景小类ID
  appSceneMinorName: string // 应用场景小类名称
  relPtCount: number // 关联点位数量(个)
  relDeviceCount: number // 关联设备数量(个)
  relEvtCount: number // 关联事件数量(件)
  sceneMngrId: number // 场景负责人ID
  sceneMngrName: string // 场景负责人姓名
  deptCode: string // 主管部门代码
  deptName: string // 主管部门名称
  domStatus: boolean // 分域状态
  createTimeBiz: Date // 创建时间(业务)
  updateTimeBiz: Date // 更新时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 应用场景分域 API
export const DomDivAppSceneApi = {
  // 查询应用场景分域分页
  getDomDivAppScenePage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-div-app-scene/page`, params })
  },

  // 查询应用场景分域详情
  getDomDivAppScene: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-div-app-scene/get?id=` + id })
  },

  // 新增应用场景分域
  createDomDivAppScene: async (data: DomDivAppSceneVO) => {
    return await request.post({ url: `/datacenter/dom-div-app-scene/create`, data })
  },

  // 修改应用场景分域
  updateDomDivAppScene: async (data: DomDivAppSceneVO) => {
    return await request.put({ url: `/datacenter/dom-div-app-scene/update`, data })
  },

  // 删除应用场景分域
  deleteDomDivAppScene: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-div-app-scene/delete?id=` + id })
  },

  // 导出应用场景分域 Excel
  exportDomDivAppScene: async (params) => {
    return await request.download({ url: `/datacenter/dom-div-app-scene/export-excel`, params })
  }
}
