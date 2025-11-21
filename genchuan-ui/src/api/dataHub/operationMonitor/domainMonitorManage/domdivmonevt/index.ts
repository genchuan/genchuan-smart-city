import request from '@/config/axios'

// 监测事件分域 VO
export interface DomDivMonEvtVO {
  id: number // 主键ID
  domId: string // 分域ID
  domName: string // 分域名称
  domCode: string // 分域编码
  monEvtMajorId: string // 监测事件大类ID
  monEvtMajorName: string // 监测事件大类名称
  monEvtMinorId: string // 监测事件小类ID
  monEvtMinorName: string // 监测事件小类名称
  relPtCount: number // 关联点位数量(个)
  relEvtCount: number // 关联事件数量(件)
  evtCompleteRate: number // 事件办结率(%)
  deptCode: string // 管控部门代码
  deptName: string // 管控部门名称
  domStatus: boolean // 分域状态
  createTimeBiz: Date // 创建时间(业务)
  updateTimeBiz: Date // 更新时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 监测事件分域 API
export const DomDivMonEvtApi = {
  // 查询监测事件分域分页
  getDomDivMonEvtPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-div-mon-evt/page`, params })
  },

  // 查询监测事件分域详情
  getDomDivMonEvt: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-div-mon-evt/get?id=` + id })
  },

  // 新增监测事件分域
  createDomDivMonEvt: async (data: DomDivMonEvtVO) => {
    return await request.post({ url: `/datacenter/dom-div-mon-evt/create`, data })
  },

  // 修改监测事件分域
  updateDomDivMonEvt: async (data: DomDivMonEvtVO) => {
    return await request.put({ url: `/datacenter/dom-div-mon-evt/update`, data })
  },

  // 删除监测事件分域
  deleteDomDivMonEvt: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-div-mon-evt/delete?id=` + id })
  },

  // 导出监测事件分域 Excel
  exportDomDivMonEvt: async (params) => {
    return await request.download({ url: `/datacenter/dom-div-mon-evt/export-excel`, params })
  }
}
