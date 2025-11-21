import request from '@/config/axios'

// 停车场信息管理 VO
export interface ParkingLotInformationVO {
  id: number // 主键
  name: string // 名称
  position: string // 位置
  contactNumber: string // 联系电话
  parkingSpaceNumber: string // 车位编号
  numberDevices: string // 监控设备数量
}

// 停车场信息管理 API
export const ParkingLotInformationApi = {
  // 查询停车场信息管理分页
  getParkingLotInformationPage: async (params: any) => {
    return await request.get({ url: `/smartcity/parking-lot-information/page`, params })
  },

  // 查询停车场信息管理详情
  getParkingLotInformation: async (id: number) => {
    return await request.get({ url: `/smartcity/parking-lot-information/get?id=` + id })
  },

  // 新增停车场信息管理
  createParkingLotInformation: async (data: ParkingLotInformationVO) => {
    return await request.post({ url: `/smartcity/parking-lot-information/create`, data })
  },

  // 修改停车场信息管理
  updateParkingLotInformation: async (data: ParkingLotInformationVO) => {
    return await request.put({ url: `/smartcity/parking-lot-information/update`, data })
  },

  // 删除停车场信息管理
  deleteParkingLotInformation: async (id: number) => {
    return await request.delete({ url: `/smartcity/parking-lot-information/delete?id=` + id })
  },

  // 导出停车场信息管理 Excel
  exportParkingLotInformation: async (params) => {
    return await request.download({ url: `/smartcity/parking-lot-information/export-excel`, params })
  }
}