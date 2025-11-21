import request from '@/config/axios'

// 单元网格统计 VO
export interface UnitGridRptVO {
  id: number // 主键ID
  unitStatId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  regionLevel: string // 行政区划级别
  scale: string // 比例尺
  totalCount: number // 网格总数(个)
  inUseCount: number // 在用网格数(个)
  stoppedCount: number // 停用网格数(个)
  totalArea: number // 总面积(m²)
  avgArea: number // 平均面积(m²)
  newCount: number // 新增网格数(个)
  updateCount: number // 更新网格数(个)
  statUserId: string // 统计人
  statTime: Date // 统计时间
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 单元网格统计 API
export const UnitGridRptApi = {
  // 查询单元网格统计分页
  getUnitGridRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/unit-grid-rpt/page`, params })
  },

  // 查询单元网格统计详情
  getUnitGridRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/unit-grid-rpt/get?id=` + id })
  },

  // 新增单元网格统计
  createUnitGridRpt: async (data: UnitGridRptVO) => {
    return await request.post({ url: `/datacenter/unit-grid-rpt/create`, data })
  },

  // 修改单元网格统计
  updateUnitGridRpt: async (data: UnitGridRptVO) => {
    return await request.put({ url: `/datacenter/unit-grid-rpt/update`, data })
  },

  // 删除单元网格统计
  deleteUnitGridRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/unit-grid-rpt/delete?id=` + id })
  },

  // 导出单元网格统计 Excel
  exportUnitGridRpt: async (params) => {
    return await request.download({ url: `/datacenter/unit-grid-rpt/export-excel`, params })
  },
}
