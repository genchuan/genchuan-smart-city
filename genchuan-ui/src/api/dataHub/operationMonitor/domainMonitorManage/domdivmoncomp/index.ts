import request from '@/config/axios'

// 监测部件分域 VO
export interface DomDivMonCompVO {
  id: number // 主键ID
  domId: string // 分域ID
  domName: string // 分域名称
  domCode: string // 分极编码
  monCompMajorId: string // 监测部件大类ID
  monCompMajorName: string // 监测部件大类名称
  monCompMinorId: string // 监测部件小类ID
  monCompMinorName: string // 监测部件小类名称
  relPtCount: number // 关联点位数量(个)
  relDeviceCount: number // 关联设备数量(个)
  deviceOnlineRate: number // 设备在线率(%)
  deptCode: string // 维护部门代码
  deptName: string // 维护部门名称
  domStatus: boolean // 分域状态
  createTimeBiz: Date // 创建时间(业务)
  updateTimeBiz: Date // 更新时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 监测部件分域 API
export const DomDivMonCompApi = {
  // 查询监测部件分域分页
  getDomDivMonCompPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-div-mon-comp/page`, params })
  },

  // 查询监测部件分域详情
  getDomDivMonComp: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-div-mon-comp/get?id=` + id })
  },

  // 新增监测部件分域
  createDomDivMonComp: async (data: DomDivMonCompVO) => {
    return await request.post({ url: `/datacenter/dom-div-mon-comp/create`, data })
  },

  // 修改监测部件分域
  updateDomDivMonComp: async (data: DomDivMonCompVO) => {
    return await request.put({ url: `/datacenter/dom-div-mon-comp/update`, data })
  },

  // 删除监测部件分域
  deleteDomDivMonComp: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-div-mon-comp/delete?id=` + id })
  },

  // 导出监测部件分域 Excel
  exportDomDivMonComp: async (params) => {
    return await request.download({ url: `/datacenter/dom-div-mon-comp/export-excel`, params })
  }
}
