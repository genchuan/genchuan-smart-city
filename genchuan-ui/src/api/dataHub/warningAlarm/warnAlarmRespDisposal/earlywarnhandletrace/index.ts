import request from '@/config/axios'

// 预警告警处置跟踪 VO
export interface EarlyWarnHandleTraceVO {
  id: number // 主键
  handleTraceId: string // 跟踪ID
  dispatchId: string // 派单ID
  dispatchNo: string // 派单编号
  earlyWarnId: string // 预警ID
  handleProgress: string // 处置进度
  progressDesc: string // 进度描述
  operUserId: string // 操作人ID
  operUserName: string // 操作人姓名
  operTime: Date // 操作时间
  attachUrl: string // 附件URL
  isKeyNode: string // 是否关键节点
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 预警告警处置跟踪 API
export const EarlyWarnHandleTraceApi = {
  // 查询预警告警处置跟踪分页
  getEarlyWarnHandleTracePage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-handle-trace/page`, params })
  },

  // 查询预警告警处置跟踪详情
  getEarlyWarnHandleTrace: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-handle-trace/get?id=` + id })
  },

  // 新增预警告警处置跟踪
  createEarlyWarnHandleTrace: async (data: EarlyWarnHandleTraceVO) => {
    return await request.post({ url: `/datacenter/early-warn-handle-trace/create`, data })
  },

  // 修改预警告警处置跟踪
  updateEarlyWarnHandleTrace: async (data: EarlyWarnHandleTraceVO) => {
    return await request.put({ url: `/datacenter/early-warn-handle-trace/update`, data })
  },

  // 删除预警告警处置跟踪
  deleteEarlyWarnHandleTrace: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-handle-trace/delete?id=` + id })
  },

  // 导出预警告警处置跟踪 Excel
  exportEarlyWarnHandleTrace: async (params) => {
    return await request.download({
      url: `/datacenter/early-warn-handle-trace/export-excel`,
      params
    })
  }
}
