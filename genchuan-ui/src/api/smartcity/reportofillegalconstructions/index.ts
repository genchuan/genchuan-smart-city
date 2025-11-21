import request from '@/config/axios'

// 违建上报 VO
export interface ReportOfIllegalConstructionsVO {
  id: number // 主键
  reportNumber: string // 上报编号
  reportperson: string // 上报人
  contactReporter: string // 上报人联系方式
  reportTime: Date // 上报时间
  detailedAddressConstruction: string // 违建详细地址
  constructionIsLocated: string // 违建所在区域
}

// 违建上报 API
export const ReportOfIllegalConstructionsApi = {
  // 查询违建上报分页
  getReportOfIllegalConstructionsPage: async (params: any) => {
    return await request.get({ url: `/smartcity/report-of-illegal-constructions/page`, params })
  },

  // 查询违建上报详情
  getReportOfIllegalConstructions: async (id: number) => {
    return await request.get({ url: `/smartcity/report-of-illegal-constructions/get?id=` + id })
  },

  // 新增违建上报
  createReportOfIllegalConstructions: async (data: ReportOfIllegalConstructionsVO) => {
    return await request.post({ url: `/smartcity/report-of-illegal-constructions/create`, data })
  },

  // 修改违建上报
  updateReportOfIllegalConstructions: async (data: ReportOfIllegalConstructionsVO) => {
    return await request.put({ url: `/smartcity/report-of-illegal-constructions/update`, data })
  },

  // 删除违建上报
  deleteReportOfIllegalConstructions: async (id: number) => {
    return await request.delete({ url: `/smartcity/report-of-illegal-constructions/delete?id=` + id })
  },

  // 导出违建上报 Excel
  exportReportOfIllegalConstructions: async (params) => {
    return await request.download({ url: `/smartcity/report-of-illegal-constructions/export-excel`, params })
  }
}