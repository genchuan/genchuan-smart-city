import request from '@/config/axios'

// 管理事项分域监测报表 VO
export interface DomMngMatterRptVO {
  id: number // 主键
  rptId: string // 报表ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  adminCode: string // 行政区划代码
  adminName: string // 行政区划名称
  mngMatterMajorId: string // 管理事项大类ID
  mngMatterMajorName: string // 管理事项大类名称
  mngMatterMinorId: string // 管理事项小类ID
  mngMatterMinorName: string // 管理事项小类名称
  domId: string // 分域ID
  domName: string // 分域名称
  deptCode: string // 处置部门代码
  deptName: string // 处置部门名称
  totalEvtCount: number // 事件总数
  pendEvtCount: number // 待处置事件数
  handlEvtCount: number // 处置中事件数
  completedEvtCount: number // 已办结事件数
  evtCompleteRate: number // 事件办结率
  avgHandleEndure: number // 平均处置时长
  rptCreateTime: Date // 报表生成时间
  rptCreateUser: number // 报表生成人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 管理事项分域监测报表 API
export const DomMngMatterRptApi = {
  // 查询管理事项分域监测报表分页
  getDomMngMatterRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dom-mng-matter-rpt/page`, params })
  },

  // 查询管理事项分域监测报表详情
  getDomMngMatterRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/dom-mng-matter-rpt/get?id=` + id })
  },

  // 新增管理事项分域监测报表
  createDomMngMatterRpt: async (data: DomMngMatterRptVO) => {
    return await request.post({ url: `/datacenter/dom-mng-matter-rpt/create`, data })
  },

  // 修改管理事项分域监测报表
  updateDomMngMatterRpt: async (data: DomMngMatterRptVO) => {
    return await request.put({ url: `/datacenter/dom-mng-matter-rpt/update`, data })
  },

  // 删除管理事项分域监测报表
  deleteDomMngMatterRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/dom-mng-matter-rpt/delete?id=` + id })
  },

  // 导出管理事项分域监测报表 Excel
  exportDomMngMatterRpt: async (params) => {
    return await request.download({ url: `/datacenter/dom-mng-matter-rpt/export-excel`, params })
  }
}
