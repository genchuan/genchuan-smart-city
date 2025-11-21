import request from '@/config/axios'

// 救援车辆分类配置 VO
export interface EmerVehicleCatVO {
  id: number // 自增主键ID
  vehicleCatId: string // 分类ID
  parentCatId: string // 父分类ID
  catLevel: string // 分类层级
  catCode: string // 分类编码
  catName: string // 分类名称
  catDesc: string // 分类说明
  applyRegionCode: string // 适用区域代码
  applyRegionName: string // 适用区域名称
}

// 救援车辆分类配置 API
export const EmerVehicleCatApi = {
  // 查询救援车辆分类配置分页
  getEmerVehicleCatPage: async (params: any) => {
    return await request.get({ url: `/datacenter/emer-vehicle-cat/page`, params })
  },

  // 查询救援车辆分类配置详情
  getEmerVehicleCat: async (id: number) => {
    return await request.get({ url: `/datacenter/emer-vehicle-cat/get?id=` + id })
  },

  // 新增救援车辆分类配置
  createEmerVehicleCat: async (data: EmerVehicleCatVO) => {
    return await request.post({ url: `/datacenter/emer-vehicle-cat/create`, data })
  },

  // 修改救援车辆分类配置
  updateEmerVehicleCat: async (data: EmerVehicleCatVO) => {
    return await request.put({ url: `/datacenter/emer-vehicle-cat/update`, data })
  },

  // 删除救援车辆分类配置
  deleteEmerVehicleCat: async (id: number) => {
    return await request.delete({ url: `/datacenter/emer-vehicle-cat/delete?id=` + id })
  },

  // 导出救援车辆分类配置 Excel
  exportEmerVehicleCat: async (params) => {
    return await request.download({ url: `/datacenter/emer-vehicle-cat/export-excel`, params })
  },
}