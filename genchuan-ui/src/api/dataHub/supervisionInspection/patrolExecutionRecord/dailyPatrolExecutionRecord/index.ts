import request from '@/config/axios'

// 日常巡查执行记录 VO
export interface InspectDailyExecRecVO {
  id: number // 主键ID
  dailyExecId: string // 记录ID
  dailyExecCode: string // 执行记录编码
  taskId: string // 关联任务ID
  taskCode: string // 关联任务编码
  personId: string // 执行人员ID
  personName: string // 执行人员姓名
  stdId: string // 关联标准ID
  execStartTime: Date // 执行开始时间
  execCompleteTime: Date // 执行完成时间
  ckItemExec: string // 检查项执行情况
  execScore: number // 执行评分
  execRemark: string // 执行备注
  deviceCode: string // 关联设备编码
  execLocX: number // 执行地点坐标X
  execLocY: number // 执行地点坐标Y
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 日常巡查执行记录 API
export const InspectDailyExecRecApi = {
  // 查询日常巡查执行记录分页
  getInspectDailyExecRecPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-daily-exec-rec/page`, params })
  },

  // 查询日常巡查执行记录详情
  getInspectDailyExecRec: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-daily-exec-rec/get?id=` + id })
  },

  // 新增日常巡查执行记录
  createInspectDailyExecRec: async (data: InspectDailyExecRecVO) => {
    return await request.post({ url: `/datacenter/inspect-daily-exec-rec/create`, data })
  },

  // 修改日常巡查执行记录
  updateInspectDailyExecRec: async (data: InspectDailyExecRecVO) => {
    return await request.put({ url: `/datacenter/inspect-daily-exec-rec/update`, data })
  },

  // 删除日常巡查执行记录
  deleteInspectDailyExecRec: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-daily-exec-rec/delete?id=` + id })
  },

  // 导出日常巡查执行记录 Excel
  exportInspectDailyExecRec: async (params) => {
    return await request.download({
      url: `/datacenter/inspect-daily-exec-rec/export-excel`,
      params
    })
  }
}
