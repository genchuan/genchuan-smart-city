import request from '@/config/axios'

// 救援车辆信息 VO
export interface EmerVehicleInfoVO {
  id: number // 自增主键ID
  vehicleId: string // 车辆ID
  vehicleCode: string // 车辆编码
  vehicleName: string // 车辆名称
  catId: string // 分类ID
  catName: string // 分类名称
  applyRegionCode: string // 适用区域代码
  applyRegionName: string // 适用区域名称
  vehicleModel: string // 车辆型号
  vehicleCount: number // 车辆数量
  availableVehicleCount: number // 可用数量
  vehicleStatus: string // 车辆状态
  createUser: string // 创建人
  updateUser: string // 更新人
}

// 救援车辆信息 API
export const EmerVehicleInfoApi = {
  // 查询救援车辆信息分页
  getEmerVehicleInfoPage: async (params: any) => {
    return await request.get({ url: `/datacenter/emer-vehicle-info/page`, params })
  },

  // 查询救援车辆信息详情
  getEmerVehicleInfo: async (id: number) => {
    return await request.get({ url: `/datacenter/emer-vehicle-info/get?id=` + id })
  },

  // 新增救援车辆信息
  createEmerVehicleInfo: async (data: EmerVehicleInfoVO) => {
    return await request.post({ url: `/datacenter/emer-vehicle-info/create`, data })
  },

  // 修改救援车辆信息
  updateEmerVehicleInfo: async (data: EmerVehicleInfoVO) => {
    return await request.put({ url: `/datacenter/emer-vehicle-info/update`, data })
  },

  // 删除救援车辆信息
  deleteEmerVehicleInfo: async (id: number) => {
    return await request.delete({ url: `/datacenter/emer-vehicle-info/delete?id=` + id })
  },

  // 导出救援车辆信息 Excel
  exportEmerVehicleInfo: async (params) => {
    return await request.download({ url: `/datacenter/emer-vehicle-info/export-excel`, params })
  },
}