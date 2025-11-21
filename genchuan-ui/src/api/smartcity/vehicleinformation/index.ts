import request from '@/config/axios'

// 车辆信息 VO
export interface VehicleInformationVO {
  id: number // 主键
  licensePlateNumber: string // 车牌号
  vehicleBrand: string // 车辆品牌
  model: string // 车辆型号
  vehicleType: string // 车辆类型
  vin: string // 车架号
  engineNo: string // 发动机号
  registrationDate: Date // 注册日期
  registerTheName: string // 登记车主姓名
  ownerIdNumberNumber: string // 车主身份证号
  carOwnerPhoneNumber: string // 车主联系电话
  vehicleColor: string // 车辆颜色
  seats: string // 座位数
  dateOfProduction: string // 出厂日期
  purchasePrice: string // 购置价格
  natureOfUse: string // 使用性质
  annualInspectionPeriod: string // 年检有效期
  periodOfInsurance: string // 保险有效期
  typesOfInsurance: string // 保险类型
  environmentalStandards: string // 环保排放标准
  vehicleUsage: string // 车辆用途
}

// 车辆信息 API
export const VehicleInformationApi = {
  // 查询车辆信息分页
  getVehicleInformationPage: async (params: any) => {
    return await request.get({ url: `/smartcity/vehicle-information/page`, params })
  },

  // 查询车辆信息详情
  getVehicleInformation: async (id: number) => {
    return await request.get({ url: `/smartcity/vehicle-information/get?id=` + id })
  },

  // 新增车辆信息
  createVehicleInformation: async (data: VehicleInformationVO) => {
    return await request.post({ url: `/smartcity/vehicle-information/create`, data })
  },

  // 修改车辆信息
  updateVehicleInformation: async (data: VehicleInformationVO) => {
    return await request.put({ url: `/smartcity/vehicle-information/update`, data })
  },

  // 删除车辆信息
  deleteVehicleInformation: async (id: number) => {
    return await request.delete({ url: `/smartcity/vehicle-information/delete?id=` + id })
  },

  // 导出车辆信息 Excel
  exportVehicleInformation: async (params) => {
    return await request.download({ url: `/smartcity/vehicle-information/export-excel`, params })
  }
}