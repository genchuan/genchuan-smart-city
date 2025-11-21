import request from '@/config/axios'

// 监测部件统计报 VO
export interface MonCompRptVO {
  id: number // 自增主键ID
  statId: string // 唯一编码
  statCycle: string // 统计周期类型
  statCycleName: string // 统计周期的中文描述
  regionCode: string // 行政区划代码
  regionName: string // 与行政区划代码关联，自动同步区域名称
  compMajorId: string // 关联监测部件分类配置表的大类ID
  compMajorName: string // 与部件大类ID关联，自动同步名称
  compMinorId: string // 关联监测部件分类配置表的小类ID(钻取统计时必填)
  compMinorName: string // 与部件小类ID关联，自动同步名称
  totalCompCount: number // 该维度下监测部件总数量
  normalCompCount: number // 状态为“正常”的部件数量
  abnCompCount: number // 状态为“异常”的部件数量
  mntCompCount: number // 状态为“维护”的部件数量
  discardCompCount: number // 状态为“废弃”的部件数量
  newCompCount: number // 统计周期内新增的部件数量（创建时间在周期内）
  updateCompCount: number // 统计周期内更新的部件数量（更新时间在周期内）
  statUser: string // 生成报表的用户ID
  statTime: Date // 报表生成时间，格式：yyyy-MM-dd HH:mm:ss
  rptRemark: string // 报表说明，如“统计范围：XX市建成区监测部件”
  extCat1: string // 预留字段，存储统计额外维度（如“统计范围”）
  extCat2: string // 预留字段，存储统计额外维度（如“统计范围”）
  extCommon1: string // 预留通用字段，存储额外统计信息
  extCommon2: string // 预留通用字段，存储额外统计信息
}

// 监测部件统计报 API
export const MonCompRptApi = {
  // 查询监测部件统计报分页
  getMonCompRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/mon-comp-rpt/page`, params })
  },

  // 查询监测部件统计报详情
  getMonCompRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/mon-comp-rpt/get?id=` + id })
  },

  // 新增监测部件统计报
  createMonCompRpt: async (data: MonCompRptVO) => {
    return await request.post({ url: `/datacenter/mon-comp-rpt/create`, data })
  },

  // 修改监测部件统计报
  updateMonCompRpt: async (data: MonCompRptVO) => {
    return await request.put({ url: `/datacenter/mon-comp-rpt/update`, data })
  },

  // 删除监测部件统计报
  deleteMonCompRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/mon-comp-rpt/delete?id=` + id })
  },

  // 导出监测部件统计报 Excel
  exportMonCompRpt: async (params) => {
    return await request.download({ url: `/datacenter/mon-comp-rpt/export-excel`, params })
  },
}