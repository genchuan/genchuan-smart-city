import request from '@/config/axios'

// 管理网格统计 VO
export interface MngGridRptVO {
  id: number // 主键ID
  mgStatId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  gridUserId: string // 网格员ID
  gridUserName: string // 网格员姓名
  unitCountRange: string // 所含单元数范围
  totalCount: number // 网格总数(个)
  totalArea: number // 总面积(m²)
  avgUnitCount: number // 平均所含单元数(个)
  totalUnitCount: number // 总所含单元网格数(个)
  gridUserCoverCount: number // 网格员覆盖数(个)
  newCount: number // 新增网格数(个)
  statUserId: string // 统计人
  statTime: Date // 统计时间
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 管理网格统计 API
export const MngGridRptApi = {
  // 查询管理网格统计分页
  getMngGridRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/mng-grid-rpt/page`, params })
  },

  // 查询管理网格统计详情
  getMngGridRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/mng-grid-rpt/get?id=` + id })
  },

  // 新增管理网格统计
  createMngGridRpt: async (data: MngGridRptVO) => {
    return await request.post({ url: `/datacenter/mng-grid-rpt/create`, data })
  },

  // 修改管理网格统计
  updateMngGridRpt: async (data: MngGridRptVO) => {
    return await request.put({ url: `/datacenter/mng-grid-rpt/update`, data })
  },

  // 删除管理网格统计
  deleteMngGridRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/mng-grid-rpt/delete?id=` + id })
  },

  // 导出管理网格统计 Excel
  exportMngGridRpt: async (params) => {
    return await request.download({ url: `/datacenter/mng-grid-rpt/export-excel`, params })
  },
}
