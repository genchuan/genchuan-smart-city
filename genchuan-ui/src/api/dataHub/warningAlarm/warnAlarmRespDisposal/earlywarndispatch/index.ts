import request from '@/config/axios'

// 预警告警派单 VO
export interface EarlyWarnDispatchVO {
  id: number // 主键
  dispatchId: string // 派单ID
  dispatchNo: string // 派单编号
  earlyWarnId: string // 预警ID
  earlyWarnName: string // 预警名称
  dispatchType: string // 派单类型
  receiveDeptId: string // 接收部门ID
  receiveDeptName: string // 接收部门名称
  handleUserId: string // 处置人ID
  handleUserName: string // 处置人姓名
  dispatchStatus: string // 派单状态
  handleLimit: number // 处置时限
  dispatchTime: Date // 派单时间
  receiveTime: Date // 接收时间
  rejectReason: string // 拒单原因
  dispatchUserId: string // 派单人ID
  dispatchUserName: string // 派单人姓名
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 预警告警派单 API
export const EarlyWarnDispatchApi = {
  // 查询预警告警派单分页
  getEarlyWarnDispatchPage: async (params: any) => {
    return await request.get({ url: `/datacenter/early-warn-dispatch/page`, params })
  },

  // 查询预警告警派单详情
  getEarlyWarnDispatch: async (id: number) => {
    return await request.get({ url: `/datacenter/early-warn-dispatch/get?id=` + id })
  },

  // 新增预警告警派单
  createEarlyWarnDispatch: async (data: EarlyWarnDispatchVO) => {
    return await request.post({ url: `/datacenter/early-warn-dispatch/create`, data })
  },

  // 修改预警告警派单
  updateEarlyWarnDispatch: async (data: EarlyWarnDispatchVO) => {
    return await request.put({ url: `/datacenter/early-warn-dispatch/update`, data })
  },

  // 删除预警告警派单
  deleteEarlyWarnDispatch: async (id: number) => {
    return await request.delete({ url: `/datacenter/early-warn-dispatch/delete?id=` + id })
  },

  // 导出预警告警派单 Excel
  exportEarlyWarnDispatch: async (params) => {
    return await request.download({ url: `/datacenter/early-warn-dispatch/export-excel`, params })
  }
}
