import request from '@/config/axios'

// 应急物资分类配置 VO
export interface EmerMatCatVO {
  id: number // 自增主键ID
  matCatId: string // 分类ID
  parentCatId: string // 父分类ID
  catLevel: string // 分类层级，1（大类）/2（中类）/3（小类）
  catCode: string // 分类编码
  catName: string // 分类名称
  catDesc: string // 分类说明
  applyRegionCode: string // 适用区域代码
  applyRegionName: string // 适用区域名称
}

// 应急物资分类配置 API
export const EmerMatCatApi = {
  // 查询应急物资分类配置分页
  getEmerMatCatPage: async (params: any) => {
    return await request.get({ url: `/datacenter/emer-mat-cat/page`, params })
  },

  // 查询应急物资分类配置详情
  getEmerMatCat: async (id: number) => {
    return await request.get({ url: `/datacenter/emer-mat-cat/get?id=` + id })
  },

  // 新增应急物资分类配置
  createEmerMatCat: async (data: EmerMatCatVO) => {
    return await request.post({ url: `/datacenter/emer-mat-cat/create`, data })
  },

  // 修改应急物资分类配置
  updateEmerMatCat: async (data: EmerMatCatVO) => {
    return await request.put({ url: `/datacenter/emer-mat-cat/update`, data })
  },

  // 删除应急物资分类配置
  deleteEmerMatCat: async (id: number) => {
    return await request.delete({ url: `/datacenter/emer-mat-cat/delete?id=` + id })
  },

  // 导出应急物资分类配置 Excel
  exportEmerMatCat: async (params) => {
    return await request.download({ url: `/datacenter/emer-mat-cat/export-excel`, params })
  },
}