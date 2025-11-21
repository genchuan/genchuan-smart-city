import request from '@/config/axios'

// 巡查人员 VO
export interface PatrolPersonnelVO {
  id: number // 主键
  number: string // 编号
  fullName: string // 姓名
  gender: string // 性别
  age: string // 年龄
  contactInformation: string // 联系方式
  certificateTime: Date // 上岗时间证书编号
  inspectionRemarks: string // 巡查区域备注
}

// 巡查人员 API
export const PatrolPersonnelApi = {
  // 查询巡查人员分页
  getPatrolPersonnelPage: async (params: any) => {
    return await request.get({ url: `/smartcity/patrol-personnel/page`, params })
  },

  // 查询巡查人员详情
  getPatrolPersonnel: async (id: number) => {
    return await request.get({ url: `/smartcity/patrol-personnel/get?id=` + id })
  },

  // 新增巡查人员
  createPatrolPersonnel: async (data: PatrolPersonnelVO) => {
    return await request.post({ url: `/smartcity/patrol-personnel/create`, data })
  },

  // 修改巡查人员
  updatePatrolPersonnel: async (data: PatrolPersonnelVO) => {
    return await request.put({ url: `/smartcity/patrol-personnel/update`, data })
  },

  // 删除巡查人员
  deletePatrolPersonnel: async (id: number) => {
    return await request.delete({ url: `/smartcity/patrol-personnel/delete?id=` + id })
  },

  // 导出巡查人员 Excel
  exportPatrolPersonnel: async (params) => {
    return await request.download({ url: `/smartcity/patrol-personnel/export-excel`, params })
  }
}