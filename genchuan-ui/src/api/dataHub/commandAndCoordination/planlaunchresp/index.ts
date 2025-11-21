import request from '@/config/axios'

// 预案启动响应 VO
export interface PlanLaunchRespVO {
  id: number // 自增主键ID
  respId: string // 响应ID
  respNo: string // 响应编号
  planId: string // 关联预案ID
  planName: string // 预案名称
  planVersion: string // 预案版本
  evtCode: string // 关联事件编码
  evtName: string // 事件名称
  launchReason: string // 启动原因
  respLevel: string // 响应等级
  launchUserId: string // 启动人ID
  launchUserName: string // 启动人姓名
  launchTime: Date // 启动时间
  respStatus: string // 响应状态
  terminateReason: string // 终止原因
  terminateTime: Date // 终止时间
  syncUnitIds: string // 需同步的联动单位ID，多个用逗号分隔
  syncUnitNames: string // 与单位IDs同步
  extCat1: string // 预留，如“响应指挥长”
  extCat2: string // 预留，如“响应指挥长”
  extCommon1: string // 预留，存储响应部署方案路径
  extCommon2: string // 预留，存储响应部署方案路径
}

// 预案启动响应 API
export const PlanLaunchRespApi = {
  // 查询预案启动响应分页
  getPlanLaunchRespPage: async (params: any) => {
    return await request.get({ url: `/datacenter/plan-launch-resp/page`, params })
  },

  // 查询预案启动响应详情
  getPlanLaunchResp: async (id: number) => {
    return await request.get({ url: `/datacenter/plan-launch-resp/get?id=` + id })
  },

  // 新增预案启动响应
  createPlanLaunchResp: async (data: PlanLaunchRespVO) => {
    return await request.post({ url: `/datacenter/plan-launch-resp/create`, data })
  },

  // 修改预案启动响应
  updatePlanLaunchResp: async (data: PlanLaunchRespVO) => {
    return await request.put({ url: `/datacenter/plan-launch-resp/update`, data })
  },

  // 删除预案启动响应
  deletePlanLaunchResp: async (id: number) => {
    return await request.delete({ url: `/datacenter/plan-launch-resp/delete?id=` + id })
  },

  // 导出预案启动响应 Excel
  exportPlanLaunchResp: async (params) => {
    return await request.download({ url: `/datacenter/plan-launch-resp/export-excel`, params })
  },
}