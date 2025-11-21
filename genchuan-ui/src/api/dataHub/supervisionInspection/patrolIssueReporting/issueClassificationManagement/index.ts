import request from '@/config/axios'

// 巡查巡检问题分级记录 VO
export interface InspectProblemLevelRecVO {
  id: number // 主键
  problemLevelId: string // 分级ID
  problemId: string // 关联问题ID
  problemCode: string // 关联问题编码
  problemName: string // 关联问题名称
  problemLevel: string // 问题级别
  levelDesc: string // 级别描述
  handleDeadline: number // 处置时限
  levelBasis: string // 分级依据
  levelUser: string // 分级人
  levelTime: Date // 分级时间
  isPushRemind: boolean // 是否推送提醒
  pushRemindTime: Date // 提醒推送时间
  problemStatusChange: string // 问题状态变更
  bizCreateUser: string // 创建人
  bizCreateTime: Date // 创建时间
  bizUpdateUser: string // 更新人
  bizUpdateTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检问题分级记录 API
export const InspectProblemLevelRecApi = {
  // 查询巡查巡检问题分级记录分页
  getInspectProblemLevelRecPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-problem-level-rec/page`, params })
  },

  // 查询巡查巡检问题分级记录详情
  getInspectProblemLevelRec: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-problem-level-rec/get?id=` + id })
  },

  // 新增巡查巡检问题分级记录
  createInspectProblemLevelRec: async (data: InspectProblemLevelRecVO) => {
    return await request.post({ url: `/datacenter/inspect-problem-level-rec/create`, data })
  },

  // 修改巡查巡检问题分级记录
  updateInspectProblemLevelRec: async (data: InspectProblemLevelRecVO) => {
    return await request.put({ url: `/datacenter/inspect-problem-level-rec/update`, data })
  },

  // 删除巡查巡检问题分级记录
  deleteInspectProblemLevelRec: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-problem-level-rec/delete?id=` + id })
  },

  // 导出巡查巡检问题分级记录 Excel
  exportInspectProblemLevelRec: async (params) => {
    return await request.download({
      url: `/datacenter/inspect-problem-level-rec/export-excel`,
      params
    })
  }
}
