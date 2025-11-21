import request from '@/config/axios'

// 管理部件分域 VO
export interface DomDivMngCompVO {
  id: number // 主键ID
  domId: string // 分域ID
  domName: string // 分域名称
  domCode: string // 分域编码
  compMajorId: string // 管理部件大类ID
  compMajorName: string // 管理部件大类名称
  compMinorId: string // 管理部件小类ID
  compMinorName: string // 管理部件小类名称
  relPtCount: number // 关联点位数量(个)
  relDeviceCount: number // 关联设备数量(个)
  deptCode: string // 主管部门代码
  deptName: string // 主管部门名称
  domStatus: boolean // 分域状态
  createTimeSys: Date // 创建时间
  updateTimeSys: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 管理部件分域 API
export const DomDivMngCompApi = {
  // 查询管理部件分域分页
  getDomDivMngCompPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-div-mng-comp/page`, params })
  },

  // 查询管理部件分域详情
  getDomDivMngComp: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-div-mng-comp/get?id=` + id })
  },

  // 新增管理部件分域
  createDomDivMngComp: async (data: DomDivMngCompVO) => {
    return await request.post({ url: `/datacenter/dom-div-mng-comp/create`, data })
  },

  // 修改管理部件分域
  updateDomDivMngComp: async (data: DomDivMngCompVO) => {
    return await request.put({ url: `/datacenter/dom-div-mng-comp/update`, data })
  },

  // 删除管理部件分域
  deleteDomDivMngComp: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-div-mng-comp/delete?id=` + id })
  },

  // 导出管理部件分域 Excel
  exportDomDivMngComp: async (params) => {
    return await request.download({ url: `/datacenter/dom-div-mng-comp/export-excel`, params })
  }
}
