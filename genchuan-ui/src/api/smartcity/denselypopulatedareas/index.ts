import request from '@/config/axios'

// 人员密集场所安全 VO
export interface DenselyPopulatedAreasVO {
  id: number // 主键
  theName: string // 所名称
  typeOfVenue: string // 场所类型
  address: string // 地址
  realTimeNumberOfPeople: string // 实时人数
  occupantDensity: string // 人员密度
  personnelTurnoverSpeed: string // 人员流动速度
  personnelDistributionArea: string // 人员分布区域
  warningLevel: string // 预警级别
}

// 人员密集场所安全 API
export const DenselyPopulatedAreasApi = {
  // 查询人员密集场所安全分页
  getDenselyPopulatedAreasPage: async (params: any) => {
    return await request.get({ url: `/smartcity/densely-populated-areas/page`, params })
  },

  // 查询人员密集场所安全详情
  getDenselyPopulatedAreas: async (id: number) => {
    return await request.get({ url: `/smartcity/densely-populated-areas/get?id=` + id })
  },

  // 新增人员密集场所安全
  createDenselyPopulatedAreas: async (data: DenselyPopulatedAreasVO) => {
    return await request.post({ url: `/smartcity/densely-populated-areas/create`, data })
  },

  // 修改人员密集场所安全
  updateDenselyPopulatedAreas: async (data: DenselyPopulatedAreasVO) => {
    return await request.put({ url: `/smartcity/densely-populated-areas/update`, data })
  },

  // 删除人员密集场所安全
  deleteDenselyPopulatedAreas: async (id: number) => {
    return await request.delete({ url: `/smartcity/densely-populated-areas/delete?id=` + id })
  },

  // 导出人员密集场所安全 Excel
  exportDenselyPopulatedAreas: async (params) => {
    return await request.download({ url: `/smartcity/densely-populated-areas/export-excel`, params })
  }
}