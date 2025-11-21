import request from '@/config/axios'

// 巡查资源 VO
export interface PatrolResourcesVO {
  id: number // 主键
  resourceName: string // 资源名称
  resourceNumber: string // 资源编号
  resourceType: string // 资源类型
  geographicLocation: string // 地理位置
  regionalDivision: string // 区域划分
  specificationAndModel: string // 规格型号
}

// 巡查资源 API
export const PatrolResourcesApi = {
  // 查询巡查资源分页
  getPatrolResourcesPage: async (params: any) => {
    return await request.get({ url: `/smartcity/patrol-resources/page`, params })
  },

  // 查询巡查资源详情
  getPatrolResources: async (id: number) => {
    return await request.get({ url: `/smartcity/patrol-resources/get?id=` + id })
  },

  // 新增巡查资源
  createPatrolResources: async (data: PatrolResourcesVO) => {
    return await request.post({ url: `/smartcity/patrol-resources/create`, data })
  },

  // 修改巡查资源
  updatePatrolResources: async (data: PatrolResourcesVO) => {
    return await request.put({ url: `/smartcity/patrol-resources/update`, data })
  },

  // 删除巡查资源
  deletePatrolResources: async (id: number) => {
    return await request.delete({ url: `/smartcity/patrol-resources/delete?id=` + id })
  },

  // 导出巡查资源 Excel
  exportPatrolResources: async (params) => {
    return await request.download({ url: `/smartcity/patrol-resources/export-excel`, params })
  }
}