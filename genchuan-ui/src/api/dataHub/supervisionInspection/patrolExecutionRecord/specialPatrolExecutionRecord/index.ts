import request from '@/config/axios'

// 专项巡查执行记录 VO
export interface InspectSpecExecRecVO {
  id: number // 主键
  specExecId: string // 记录ID
  specExecCode: string // 记录编码
  specPlanId: string // 关联专项计划ID
  specPlanName: string // 关联专项计划名称
  taskId: string // 关联任务ID
  taskCode: string // 关联任务编码
  personId: string // 执行人员ID
  personName: string // 执行人员姓名
  stdId: string // 关联标准ID
  stdName: string // 关联标准名称
  execStartTime: Date // 执行开始时间
  execCompleteTime: Date // 执行完成时间
  ckItemDetail: string // 检查项执行明细
  unqualifiedCount: number // 不合格项数量
  unqualifiedDetail: string // 不合格项详情
  execScore: number // 执行评分
  specExecRemark: string // 专项执行备注
  deviceCode: string // 执行设备编码
  execLocX: number // 执行地点坐标X
  execLocY: number // 执行地点坐标Y
  bizCreateUser: string // 创建人
  bizCreateTime: Date // 创建时间
  bizUpdateUser: string // 更新人
  bizUpdateTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 专项巡查执行记录 API
export const InspectSpecExecRecApi = {
  // 查询专项巡查执行记录分页
  getInspectSpecExecRecPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-spec-exec-rec/page`, params })
  },

  // 查询专项巡查执行记录详情
  getInspectSpecExecRec: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-spec-exec-rec/get?id=` + id })
  },

  // 新增专项巡查执行记录
  createInspectSpecExecRec: async (data: InspectSpecExecRecVO) => {
    return await request.post({ url: `/datacenter/inspect-spec-exec-rec/create`, data })
  },

  // 修改专项巡查执行记录
  updateInspectSpecExecRec: async (data: InspectSpecExecRecVO) => {
    return await request.put({ url: `/datacenter/inspect-spec-exec-rec/update`, data })
  },

  // 删除专项巡查执行记录
  deleteInspectSpecExecRec: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-spec-exec-rec/delete?id=` + id })
  },

  // 导出专项巡查执行记录 Excel
  exportInspectSpecExecRec: async (params) => {
    return await request.download({ url: `/datacenter/inspect-spec-exec-rec/export-excel`, params })
  }
}
