import request from '@/config/axios'

// 园林绿化 VO
export interface LandscapingVO {
  id: number // 主键
  greenSpaceName: string // 绿地名称
  greenbeltType: string // 绿地类型
  position: string // 位置
  area: string // 面积
  greeningRate: string // 绿地率
  greenCoverageRate: string // 绿化覆盖率
  plantName: string // 植物名称
  familyAndGenus: string // 科属
  quantity: string // 数量
  plantingDensity: string // 种植密度
  totalInvestmentOfTheProject: string // 项目总投资
  landCost: string // 土地成本
  seedlingProcurementCost: string // 苗木采购成本
}

// 园林绿化 API
export const LandscapingApi = {
  // 查询园林绿化分页
  getLandscapingPage: async (params: any) => {
    return await request.get({ url: `/smartcity/landscaping/page`, params })
  },

  // 查询园林绿化详情
  getLandscaping: async (id: number) => {
    return await request.get({ url: `/smartcity/landscaping/get?id=` + id })
  },

  // 新增园林绿化
  createLandscaping: async (data: LandscapingVO) => {
    return await request.post({ url: `/smartcity/landscaping/create`, data })
  },

  // 修改园林绿化
  updateLandscaping: async (data: LandscapingVO) => {
    return await request.put({ url: `/smartcity/landscaping/update`, data })
  },

  // 删除园林绿化
  deleteLandscaping: async (id: number) => {
    return await request.delete({ url: `/smartcity/landscaping/delete?id=` + id })
  },

  // 导出园林绿化 Excel
  exportLandscaping: async (params) => {
    return await request.download({ url: `/smartcity/landscaping/export-excel`, params })
  }
}