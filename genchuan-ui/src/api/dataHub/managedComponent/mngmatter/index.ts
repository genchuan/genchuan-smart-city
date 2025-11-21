import request from '@/config/axios'

// 管理事项统计 VO
export interface MngMatterVO {
  id: number // 主键ID
  statMngMatterId: string // 统计ID
  statCycle: string // 统计周期
  statCycleName: string // 统计周期名称
  areaCode: string // 行政区划代码
  areaName: string // 行政区划名称
  matterMajorId: string // 事项大类ID
  matterMajorName: string // 事项大类名称
  matterMinorId: string // 事项小类ID
  matterMinorName: string // 事项小类名称
  deptCode: string // 处置部门代码
  deptName: string // 处置部门名称
  totalRptCount: number // 上报总数
  pendCount: number // 待处置数
  handlCount: number // 处置中数
  completedCount: number // 已办结数
  rejectedCount: number // 已驳回数
  completeRate: number // 办结率
  avgHandleEndure: number // 平均处置时长
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 管理事项统计 API
export const MngMatterApi = {
  // 查询管理事项统计分页
  getMngMatterPage: async (params: any) => {
    return await request.get({ url: `/datacenter/mng-matter/page`, params })
  },

  // 查询管理事项统计详情
  getMngMatter: async (id: number) => {
    return await request.get({ url: `/datacenter/mng-matter/get?id=` + id })
  },

  // 新增管理事项统计
  createMngMatter: async (data: MngMatterVO) => {
    return await request.post({ url: `/datacenter/mng-matter/create`, data })
  },

  // 修改管理事项统计
  updateMngMatter: async (data: MngMatterVO) => {
    return await request.put({ url: `/datacenter/mng-matter/update`, data })
  },

  // 删除管理事项统计
  deleteMngMatter: async (id: number) => {
    return await request.delete({ url: `/datacenter/mng-matter/delete?id=` + id })
  },

  // 导出管理事项统计 Excel
  exportMngMatter: async (params) => {
    return await request.download({ url: `/datacenter/mng-matter/export-excel`, params })
  },
}