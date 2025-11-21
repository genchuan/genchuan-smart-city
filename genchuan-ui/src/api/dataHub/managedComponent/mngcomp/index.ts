import request from '@/config/axios'

// 管理部件统计 VO
export interface MngCompVO {
  id: number // 主键ID
  statMngCompId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  areaCode: string // 行政区划代码
  areaName: string // 行政区划名称
  compMajorId: string // 部件大类ID
  compMajorName: string // 部件大类名称
  compMinorId: string // 部件小类ID
  compMinorName: string // 部件小类名称
  totalCompCount: number // 部件总数
  normalCompCount: number // 完好部件数
  damagedCompCount: number // 破损部件数
  lostCompCount: number // 丢失部件数
  discardedCompCount: number // 废弃部件数
  newCompCount: number // 新增部件数
  updateCompCount: number // 更新部件数
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 管理部件统计 API
export const MngCompApi = {
  // 查询管理部件统计分页
  getMngCompPage: async (params: any) => {
    return await request.get({ url: `/datacenter/mng-comp/page`, params })
  },

  // 查询管理部件统计详情
  getMngComp: async (id: number) => {
    return await request.get({ url: `/datacenter/mng-comp/get?id=` + id })
  },

  // 新增管理部件统计
  createMngComp: async (data: MngCompVO) => {
    return await request.post({ url: `/datacenter/mng-comp/create`, data })
  },

  // 修改管理部件统计
  updateMngComp: async (data: MngCompVO) => {
    return await request.put({ url: `/datacenter/mng-comp/update`, data })
  },

  // 删除管理部件统计
  deleteMngComp: async (id: number) => {
    return await request.delete({ url: `/datacenter/mng-comp/delete?id=` + id })
  },

  // 导出管理部件统计 Excel
  exportMngComp: async (params) => {
    return await request.download({ url: `/datacenter/mng-comp/export-excel`, params })
  },
}