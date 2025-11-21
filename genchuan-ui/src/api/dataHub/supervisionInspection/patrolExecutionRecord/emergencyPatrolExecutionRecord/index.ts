import request from '@/config/axios'

// 应急巡查执行记录 VO
export interface InspectEmerExecRecVO {
  id: number // 主键
  emerExecId: string // 记录ID
  emerExecCode: string // 记录编码
  emerPlanId: string // 关联应急计划ID
  emerPlanName: string // 关联应急计划名称
  emerEvtId: string // 关联应急事件ID
  emerEvtName: string // 关联应急事件名称
  taskId: string // 关联任务ID
  taskCode: string // 关联任务编码
  personId: string // 执行人员ID
  personName: string // 执行人员姓名
  execStartTime: Date // 执行开始时间
  execCompleteTime: Date // 执行完成时间
  emerCkDetail: string // 应急检查项明细
  urgencyLevel: string // 紧急级别
  emerProblemDesc: string // 应急问题描述
  disposalSugg: string // 处置建议
  deviceCode: string // 执行设备编码
  emerLocX: number // 应急地点坐标X
  emerLocY: number // 应急地点坐标Y
  isPushedCmd: boolean // 是否已推送指挥中心
  pushTime: Date // 推送时间
  bizCreateUser: string // 创建人
  bizCreateTime: Date // 创建时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 应急巡查执行记录 API
export const InspectEmerExecRecApi = {
  // 查询应急巡查执行记录分页
  getInspectEmerExecRecPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-emer-exec-rec/page`, params })
  },

  // 查询应急巡查执行记录详情
  getInspectEmerExecRec: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-emer-exec-rec/get?id=` + id })
  },

  // 新增应急巡查执行记录
  createInspectEmerExecRec: async (data: InspectEmerExecRecVO) => {
    return await request.post({ url: `/datacenter/inspect-emer-exec-rec/create`, data })
  },

  // 修改应急巡查执行记录
  updateInspectEmerExecRec: async (data: InspectEmerExecRecVO) => {
    return await request.put({ url: `/datacenter/inspect-emer-exec-rec/update`, data })
  },

  // 删除应急巡查执行记录
  deleteInspectEmerExecRec: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-emer-exec-rec/delete?id=` + id })
  },

  // 导出应急巡查执行记录 Excel
  exportInspectEmerExecRec: async (params) => {
    return await request.download({ url: `/datacenter/inspect-emer-exec-rec/export-excel`, params })
  }
}
