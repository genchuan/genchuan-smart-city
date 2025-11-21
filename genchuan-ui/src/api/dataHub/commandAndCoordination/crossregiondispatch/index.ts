import request from '@/config/axios'

// 跨域调度协调 VO
export interface CrossRegionDispatchVO {
  id: number // 自增主键ID
  coordinateId: string // 协调ID
  coordinateNo: string // 协调编号
  applyUnitId: string // 申请单位ID
  applyUnitName: string // 申请单位名称
  cooperUnitId: string // 协作单位ID
  cooperUnitName: string // 协作单位名称
  applyReason: string // 申请理由
  resType: string // 所需资源类型
  resName: string // 所需资源名称
  requireQuantity: number // 所需数量
  cooperRegion: string // 协作区域
  applyStatus: string // 申请状态
  applyUserId: string // 申请人ID
  applyUserName: string // 申请人姓名
  applyTime: Date // 申请时间
  auditUserId: string // 审核人ID
  auditUserName: string // 审核人姓名
  auditTime: Date // 审核时间
  auditOpinion: string // 审核意见
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 跨域调度协调 API
export const CrossRegionDispatchApi = {
  // 查询跨域调度协调分页
  getCrossRegionDispatchPage: async (params: any) => {
    return await request.get({ url: `/datacenter/cross-region-dispatch/page`, params })
  },

  // 查询跨域调度协调详情
  getCrossRegionDispatch: async (id: number) => {
    return await request.get({ url: `/datacenter/cross-region-dispatch/get?id=` + id })
  },

  // 新增跨域调度协调
  createCrossRegionDispatch: async (data: CrossRegionDispatchVO) => {
    return await request.post({ url: `/datacenter/cross-region-dispatch/create`, data })
  },

  // 修改跨域调度协调
  updateCrossRegionDispatch: async (data: CrossRegionDispatchVO) => {
    return await request.put({ url: `/datacenter/cross-region-dispatch/update`, data })
  },

  // 删除跨域调度协调
  deleteCrossRegionDispatch: async (id: number) => {
    return await request.delete({ url: `/datacenter/cross-region-dispatch/delete?id=` + id })
  },

  // 导出跨域调度协调 Excel
  exportCrossRegionDispatch: async (params) => {
    return await request.download({ url: `/datacenter/cross-region-dispatch/export-excel`, params })
  },
}