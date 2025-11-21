import request from '@/config/axios'

// 预警告警解除 VO
export interface EarlyWarnReleaseVO {
  id: number // 主键
  releaseId: string // 解除ID
  earlyWarnId: string // 预警ID
  earlyWarnName: string // 预警名称
  applyUserId: string // 解除申请人ID
  applyUserName: string // 解除申请人姓名
  auditUserId: string // 解除审核人ID
  auditUserName: string // 解除审核人姓名
  releaseStatus: string // 解除状态
  releaseReason: string // 解除原因
  applyTime: Date // 申请时间
  auditTime: Date // 审核时间
  rejectReason: string // 驳回原因
  isRecurrence: string // 是否复发
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 预警告警解除 API
export const EarlyWarnReleaseApi = {
  // 查询预警告警解除分页
  getEarlyWarnReleasePage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-release/page`, params })
  },

  // 查询预警告警解除详情
  getEarlyWarnRelease: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-release/get?id=` + id })
  },

  // 新增预警告警解除
  createEarlyWarnRelease: async (data: EarlyWarnReleaseVO) => {
    return await request.post({ url: `/datacenter/early-warn-release/create`, data })
  },

  // 修改预警告警解除
  updateEarlyWarnRelease: async (data: EarlyWarnReleaseVO) => {
    return await request.put({ url: `/datacenter/early-warn-release/update`, data })
  },

  // 删除预警告警解除
  deleteEarlyWarnRelease: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-release/delete?id=` + id })
  },

  // 导出预警告警解除 Excel
  exportEarlyWarnRelease: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-release/export-excel`, params })
  }
}
