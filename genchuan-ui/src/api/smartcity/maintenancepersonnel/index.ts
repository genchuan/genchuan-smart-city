import request from '@/config/axios'

// 养护人员 VO
export interface MaintenancePersonnelVO {
  id: number // 主键
  personnelId: string // 人员编号
  personnelName: string // 人员姓名
  gender: string // 性别
  contactInformation: string // 联系方式
  idNumber: string // 身份证号
  maintainTheLandParcel: string // 养护地块
}

// 养护人员 API
export const MaintenancePersonnelApi = {
  // 查询养护人员分页
  getMaintenancePersonnelPage: async (params: any) => {
    return await request.get({ url: `/smartcity/maintenance-personnel/page`, params })
  },

  // 查询养护人员详情
  getMaintenancePersonnel: async (id: number) => {
    return await request.get({ url: `/smartcity/maintenance-personnel/get?id=` + id })
  },

  // 新增养护人员
  createMaintenancePersonnel: async (data: MaintenancePersonnelVO) => {
    return await request.post({ url: `/smartcity/maintenance-personnel/create`, data })
  },

  // 修改养护人员
  updateMaintenancePersonnel: async (data: MaintenancePersonnelVO) => {
    return await request.put({ url: `/smartcity/maintenance-personnel/update`, data })
  },

  // 删除养护人员
  deleteMaintenancePersonnel: async (id: number) => {
    return await request.delete({ url: `/smartcity/maintenance-personnel/delete?id=` + id })
  },

  // 导出养护人员 Excel
  exportMaintenancePersonnel: async (params) => {
    return await request.download({ url: `/smartcity/maintenance-personnel/export-excel`, params })
  }
}