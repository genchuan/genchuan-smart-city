import request from '@/config/axios'

// 调度方案 VO
export interface DispatchPlanVO {
  id: number // 自增主键ID
  planId: string // 方案ID
  planNo: string // 方案编号
  respId: string // 关联响应ID
  respNo: string // 响应编号
  planName: string // 方案名称
  demandDesc: string // 调度需求描述
  resType: string // 资源类型
  resId: string // 资源ID
  resName: string // 资源名称
  dispatchQuantity: number // 调度数量
  dispatchDest: string // 调配目的地
  destCoordX: number // 目的地坐标X
  destCoordY: number // 目的地坐标Y
  chargerId: string // 负责人ID
  chargerName: string // 负责人姓名
  requireCompleteTime: Date // 要求完成时间
  planStatus: string // 方案状态
  createUser: string // 创建人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 调度方案 API
export const DispatchPlanApi = {
  // 查询调度方案分页
  getDispatchPlanPage: async (params: any) => {
    return await request.get({ url: `/datacenter/dispatch-plan/page`, params })
  },

  // 查询调度方案详情
  getDispatchPlan: async (id: number) => {
    return await request.get({ url: `/datacenter/dispatch-plan/get?id=` + id })
  },

  // 新增调度方案
  createDispatchPlan: async (data: DispatchPlanVO) => {
    return await request.post({ url: `/datacenter/dispatch-plan/create`, data })
  },

  // 修改调度方案
  updateDispatchPlan: async (data: DispatchPlanVO) => {
    return await request.put({ url: `/datacenter/dispatch-plan/update`, data })
  },

  // 删除调度方案
  deleteDispatchPlan: async (id: number) => {
    return await request.delete({ url: `/datacenter/dispatch-plan/delete?id=` + id })
  },

  // 导出调度方案 Excel
  exportDispatchPlan: async (params) => {
    return await request.download({ url: `/datacenter/dispatch-plan/export-excel`, params })
  },
}