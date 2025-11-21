import request from '@/config/axios'

// 预案分类配置 VO
export interface EmerPlanCatVO {
  id: number // 自增主键ID
  planCatId: string // 分类ID
  parentCatId: string // 父分类ID
  catLevel: string // 分类层级
  catCode: string // 分类编码
  catName: string // 分类名称
  catDesc: string // 分类说明
  applyRegionCode: string // 适用区域代码
  applyRegionName: string // 适用区域名称
}

// 预案分类配置 API
export const EmerPlanCatApi = {
  // 查询预案分类配置分页
  getEmerPlanCatPage: async (params: any) => {
    return await request.get({ url: `/datacenter/emer-plan-cat/page`, params })
  },

  // 查询预案分类配置详情
  getEmerPlanCat: async (id: number) => {
    return await request.get({ url: `/datacenter/emer-plan-cat/get?id=` + id })
  },

  // 新增预案分类配置
  createEmerPlanCat: async (data: EmerPlanCatVO) => {
    return await request.post({ url: `/datacenter/emer-plan-cat/create`, data })
  },

  // 修改预案分类配置
  updateEmerPlanCat: async (data: EmerPlanCatVO) => {
    return await request.put({ url: `/datacenter/emer-plan-cat/update`, data })
  },

  // 删除预案分类配置
  deleteEmerPlanCat: async (id: number) => {
    return await request.delete({ url: `/datacenter/emer-plan-cat/delete?id=` + id })
  },

  // 导出预案分类配置 Excel
  exportEmerPlanCat: async (params) => {
    return await request.download({ url: `/datacenter/emer-plan-cat/export-excel`, params })
  },
}