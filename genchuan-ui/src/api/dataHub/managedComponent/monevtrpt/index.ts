import request from '@/config/axios'

// 监测事件统计报 VO
export interface MonEvtRptVO {
  id: number // 自增主键ID
  statId: string // 唯一编码
  statCycle: string // 统计周期类型
  statCycleName: string // 如“2025年09月”
  regionCode: string // 统计区域的行政区划代码
  regionName: string // 与行政区划代码关联
  evtMajorId: string // 关联监测事件分类配置表的大类ID
  evtMajorName: string // 与事件大类ID关联
  evtMinorId: string // 关联监测事件分类配置表的小类ID（钻取时必填）
  evtMinorName: string // 与事件小类ID关联
  deptCode: string // 事件处置部门统一社会信用代码
  deptName: string // 与处置部门代码关联
  totalRptCount: number // 统计周期内该维度下事件上报总数
  pendCount: number // 状态为“待处置”的事件数量
  handlCount: number // 状态为“处置中”的事件数量
  completedCount: number // 状态为“已办结”的事件数量
  rejectedCount: number // 状态为“已驳回”的事件数量
  level1Count: number // 事件等级为“一级”的数量
}

// 监测事件统计报 API
export const MonEvtRptApi = {
  // 查询监测事件统计报分页
  getMonEvtRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/mon-evt-rpt/page`, params })
  },

  // 查询监测事件统计报详情
  getMonEvtRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/mon-evt-rpt/get?id=` + id })
  },

  // 新增监测事件统计报
  createMonEvtRpt: async (data: MonEvtRptVO) => {
    return await request.post({ url: `/datacenter/mon-evt-rpt/create`, data })
  },

  // 修改监测事件统计报
  updateMonEvtRpt: async (data: MonEvtRptVO) => {
    return await request.put({ url: `/datacenter/mon-evt-rpt/update`, data })
  },

  // 删除监测事件统计报
  deleteMonEvtRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/mon-evt-rpt/delete?id=` + id })
  },

  // 导出监测事件统计报 Excel
  exportMonEvtRpt: async (params) => {
    return await request.download({ url: `/datacenter/mon-evt-rpt/export-excel`, params })
  },
}