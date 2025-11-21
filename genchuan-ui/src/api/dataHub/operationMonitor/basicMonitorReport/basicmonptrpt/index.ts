import request from '@/config/axios'

// 基础监测点位报表 VO
export interface BasicMonPtRptVO {
  id: number // 主键ID
  rptId: string // 报表ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  adminCode: string // 行政区划代码
  adminName: string // 行政区划名称
  ptType: string // 点位类型
  totalPtCount: number // 点位总数(个)
  enablePtCount: number // 启用点位数量(个)
  disablePtCount: number // 停用点位数量(个)
  newPtCount: number // 新增点位数量(个)
  relDeviceCount: number // 关联设备总数(个)
  rptCreateTime: Date // 报表生成时间
  rptCreateUser: number // 报表生成人
  rptRemark: string // 报表备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 基础监测点位报表 API
export const BasicMonPtRptApi = {
  // 查询基础监测点位报表分页
  getBasicMonPtRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/basic-mon-pt-rpt/page`, params })
  },

  // 查询基础监测点位报表详情
  getBasicMonPtRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/basic-mon-pt-rpt/get?id=` + id })
  },

  // 新增基础监测点位报表
  createBasicMonPtRpt: async (data: BasicMonPtRptVO) => {
    return await request.post({ url: `/datacenter/basic-mon-pt-rpt/create`, data })
  },

  // 修改基础监测点位报表
  updateBasicMonPtRpt: async (data: BasicMonPtRptVO) => {
    return await request.put({ url: `/datacenter/basic-mon-pt-rpt/update`, data })
  },

  // 删除基础监测点位报表
  deleteBasicMonPtRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/basic-mon-pt-rpt/delete?id=` + id })
  },

  // 导出基础监测点位报表 Excel
  exportBasicMonPtRpt: async (params) => {
    return await request.download({ url: `/datacenter/basic-mon-pt-rpt/export-excel`, params })
  }
}
