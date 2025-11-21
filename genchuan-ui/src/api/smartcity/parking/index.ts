import request from '@/config/axios'

// 停车管理用户管理与服务 VO
export interface ParkingVO {
  id: number // 主键
  fullName: string // 姓名
  gender: string // 性别
  phoneNumber: string // 手机号码
  eMail: string // 电子邮箱
  idNumber: string // 身份证号
  registrationTime: Date // 注册时间
  customerType: string // 用户类型
  userLevel: string // 用户等级
  points: string // 积分
  balance: string // 余额
  address: string // 地址
  serviceEvaluation: string // 服务评价
}

// 停车管理用户管理与服务 API
export const ParkingApi = {
  // 查询停车管理用户管理与服务分页
  getParkingPage: async (params: any) => {
    return await request.get({ url: `/smartcity/parking/page`, params })
  },

  // 查询停车管理用户管理与服务详情
  getParking: async (id: number) => {
    return await request.get({ url: `/smartcity/parking/get?id=` + id })
  },

  // 新增停车管理用户管理与服务
  createParking: async (data: ParkingVO) => {
    return await request.post({ url: `/smartcity/parking/create`, data })
  },

  // 修改停车管理用户管理与服务
  updateParking: async (data: ParkingVO) => {
    return await request.put({ url: `/smartcity/parking/update`, data })
  },

  // 删除停车管理用户管理与服务
  deleteParking: async (id: number) => {
    return await request.delete({ url: `/smartcity/parking/delete?id=` + id })
  },

  // 导出停车管理用户管理与服务 Excel
  exportParking: async (params) => {
    return await request.download({ url: `/smartcity/parking/export-excel`, params })
  }
}