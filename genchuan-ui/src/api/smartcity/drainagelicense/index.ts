import request from '@/config/axios'

// 排水电子许可证信息 VO
export interface DrainageLicenseVO {
  id: number // ID
  licenseNo: string // 许可证编号
  startDate: Date // 有效期开始日期
  endDate: Date // 有效期结束日期
  drainageType: string // 许可排水类型
  approvalUnit: string // 审批单位
  licenseStatus: string // 状态
}

// 排水电子许可证信息 API
export const DrainageLicenseApi = {
  // 查询排水电子许可证信息分页
  getDrainageLicensePage: async (params: any) => {
    return await request.get({ url: `/smartcity/drainage-license/page`, params })
  },

  // 查询排水电子许可证信息详情
  getDrainageLicense: async (id: number) => {
    return await request.get({ url: `/smartcity/drainage-license/get?id=` + id })
  },

  // 新增排水电子许可证信息
  createDrainageLicense: async (data: DrainageLicenseVO) => {
    return await request.post({ url: `/smartcity/drainage-license/create`, data })
  },

  // 修改排水电子许可证信息
  updateDrainageLicense: async (data: DrainageLicenseVO) => {
    return await request.put({ url: `/smartcity/drainage-license/update`, data })
  },

  // 删除排水电子许可证信息
  deleteDrainageLicense: async (id: number) => {
    return await request.delete({ url: `/smartcity/drainage-license/delete?id=` + id })
  },

  // 导出排水电子许可证信息 Excel
  exportDrainageLicense: async (params) => {
    return await request.download({ url: `/smartcity/drainage-license/export-excel`, params })
  }
}