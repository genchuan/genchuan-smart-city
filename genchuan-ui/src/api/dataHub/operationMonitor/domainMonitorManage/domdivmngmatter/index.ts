import request from '@/config/axios'

// 管理事项分域 VO
export interface DomDivMngMatterVO {
  id: number // 主键ID
  domId: string // 分域ID
  domName: string // 分域名称
  domCode: string // 分域编码
  matterMajorId: string // 管理事项大类ID
  matterMajorName: string // 管理事项大类名称
  matterMinorId: string // 管理事项小类ID
  matterMinorName: string // 管理事项小类名称
  relPtCount: number // 关联点位数量(个)
  relEvtCount: number // 关联事件数量(件)
  deptCode: string // 处置部门代码
  deptName: string // 处置部门名称
  domStatus: boolean // 分域状态
  createTimeBiz: Date // 创建时间(业务)
  updateTimeBiz: Date // 更新时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 管理事项分域 API
export const DomDivMngMatterApi = {
  // 查询管理事项分域分页
  getDomDivMngMatterPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-div-mng-matter/page`, params })
  },

  // 查询管理事项分域详情
  getDomDivMngMatter: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-div-mng-matter/get?id=` + id })
  },

  // 新增管理事项分域
  createDomDivMngMatter: async (data: DomDivMngMatterVO) => {
    return await request.post({ url: `/datacenter/dom-div-mng-matter/create`, data })
  },

  // 修改管理事项分域
  updateDomDivMngMatter: async (data: DomDivMngMatterVO) => {
    return await request.put({ url: `/datacenter/dom-div-mng-matter/update`, data })
  },

  // 删除管理事项分域
  deleteDomDivMngMatter: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-div-mng-matter/delete?id=` + id })
  },

  // 导出管理事项分域 Excel
  exportDomDivMngMatter: async (params) => {
    return await request.download({ url: `/datacenter/dom-div-mng-matter/export-excel`, params })
  }
}
