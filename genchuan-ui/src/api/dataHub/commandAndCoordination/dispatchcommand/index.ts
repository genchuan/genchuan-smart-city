import request from '@/config/axios'

// 调度指令 VO
export interface DispatchCommandVO {
  id: number // 自增主键ID
  commandId: string // 指令ID
  commandNo: string // 指令编号
  planId: string // 关联方案ID
  planNo: string // 方案编号
  execUnitId: string // 执行单位ID
  execUnitName: string // 执行单位名称
  taskContent: string // 任务内容
  execRequirement: string // 执行要求
  commandStatus: string // 指令状态
  issueUserId: string // 下发人ID
  issueUserName: string // 下发人姓名
  issueTime: Date // 下发时间
  receiveUserId: string // 接收人ID
  receiveUserName: string // 接收人姓名
  receiveTime: Date // 接收时间
  execFeedback: string // 执行反馈
  completeTime: Date // 完成时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 调度指令 API
export const DispatchCommandApi = {
  // 查询调度指令分页
  getDispatchCommandPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dispatch-command/page`, params })
  },

  // 查询调度指令详情
  getDispatchCommand: async (id: number) => {
    return await request.get({ url: `/datacenter/dispatch-command/get?id=` + id })
  },

  // 新增调度指令
  createDispatchCommand: async (data: DispatchCommandVO) => {
    return await request.post({ url: `/datacenter/dispatch-command/create`, data })
  },

  // 修改调度指令
  updateDispatchCommand: async (data: DispatchCommandVO) => {
    return await request.put({ url: `/datacenter/dispatch-command/update`, data })
  },

  // 删除调度指令
  deleteDispatchCommand: async (id: number) => {
    return await request.delete({ url: `/datacenter/dispatch-command/delete?id=` + id })
  },

  // 导出调度指令 Excel
  exportDispatchCommand: async (params) => {
    return await request.download({ url: `/datacenter/dispatch-command/export-excel`, params })
  },
}