import request from '@/config/axios'

// 停车收费管理 VO
export interface ParkingFeeVO {
  id: number // 主键
  licensePlateNumber: string // 车牌号
  admissionTime: Date // 入场时间
  exitTime: Date // 出场时间
  parkingDuration: string // 停车时长
  feeAmount: string // 收费金额
  paymentMethod: string // 缴费方式
  actualReceivedAmount: string // 实收金额
  parkingSpaceNumber: string // 车位编号
}

// 停车收费管理 API
export const ParkingFeeApi = {
  // 查询停车收费管理分页
  getParkingFeePage: async (params: any) => {
    return await request.get({ url: `/smartcity/parking-fee/page`, params })
  },

  // 查询停车收费管理详情
  getParkingFee: async (id: number) => {
    return await request.get({ url: `/smartcity/parking-fee/get?id=` + id })
  },

  // 新增停车收费管理
  createParkingFee: async (data: ParkingFeeVO) => {
    return await request.post({ url: `/smartcity/parking-fee/create`, data })
  },

  // 修改停车收费管理
  updateParkingFee: async (data: ParkingFeeVO) => {
    return await request.put({ url: `/smartcity/parking-fee/update`, data })
  },

  // 删除停车收费管理
  deleteParkingFee: async (id: number) => {
    return await request.delete({ url: `/smartcity/parking-fee/delete?id=` + id })
  },

  // 导出停车收费管理 Excel
  exportParkingFee: async (params) => {
    return await request.download({ url: `/smartcity/parking-fee/export-excel`, params })
  }
}