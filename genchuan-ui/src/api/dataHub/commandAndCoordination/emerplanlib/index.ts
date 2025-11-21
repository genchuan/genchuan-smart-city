import request from '@/config/axios'

// 预案库 VO
export interface EmerPlanLibVO {
  id: number // 自增主键ID
  planId: string // 预案ID
  planNo: string // 预案编号
  planName: string // 预案标准名称
  catId: string // 分类ID
  catName: string // 分类名称
  applyRegionCode: string // 适用区域代码
  applyRegionName: string // 适用区域名称
  planVersion: string // 预案版本
  effectiveTime: Date // 生效时间
  expireTime: Date // 失效时间
  planDocPath: string // 预案文档路径
}

// 预案库 API
export const EmerPlanLibApi = {
  // 查询预案库分页
  getEmerPlanLibPage: async (params: any) => {
    return await request.get({ url: `/datacenter/emer-plan-lib/page`, params })
  },

  // 查询预案库详情
  getEmerPlanLib: async (id: number) => {
    return await request.get({ url: `/datacenter/emer-plan-lib/get?id=` + id })
  },

  // 新增预案库
  createEmerPlanLib: async (data: EmerPlanLibVO) => {
    return await request.post({ url: `/datacenter/emer-plan-lib/create`, data })
  },

  // 修改预案库
  updateEmerPlanLib: async (data: EmerPlanLibVO) => {
    return await request.put({ url: `/datacenter/emer-plan-lib/update`, data })
  },

  // 删除预案库
  deleteEmerPlanLib: async (id: number) => {
    return await request.delete({ url: `/datacenter/emer-plan-lib/delete?id=` + id })
  },

  // 导出预案库 Excel
  exportEmerPlanLib: async (params) => {
    return await request.download({ url: `/datacenter/emer-plan-lib/export-excel`, params })
  },
}