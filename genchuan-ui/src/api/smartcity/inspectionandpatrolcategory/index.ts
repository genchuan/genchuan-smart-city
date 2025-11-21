import request from '@/config/axios'

// 巡查巡检类 VO
export interface InspectionAndPatrolCategoryVO {
  id: number // 主键
  number: string // 编号
  inspectionAndPatrolTime: Date // 巡查巡检时间
  patrolPersonnel: string // 巡查巡检人员
  patrolInspectionArea: string // 巡查巡检区域
  inspectionAndPatrolContent: string // 巡查巡检内容
  abnormalSituationDescription: string // 异常情况描述
  notes: string // 备注
}

// 巡查巡检类 API
export const InspectionAndPatrolCategoryApi = {
  // 查询巡查巡检类分页
  getInspectionAndPatrolCategoryPage: async (params: any) => {
    return await request.get({ url: `/smartcity/inspection-and-patrol-category/page`, params })
  },

  // 查询巡查巡检类详情
  getInspectionAndPatrolCategory: async (id: number) => {
    return await request.get({ url: `/smartcity/inspection-and-patrol-category/get?id=` + id })
  },

  // 新增巡查巡检类
  createInspectionAndPatrolCategory: async (data: InspectionAndPatrolCategoryVO) => {
    return await request.post({ url: `/smartcity/inspection-and-patrol-category/create`, data })
  },

  // 修改巡查巡检类
  updateInspectionAndPatrolCategory: async (data: InspectionAndPatrolCategoryVO) => {
    return await request.put({ url: `/smartcity/inspection-and-patrol-category/update`, data })
  },

  // 删除巡查巡检类
  deleteInspectionAndPatrolCategory: async (id: number) => {
    return await request.delete({ url: `/smartcity/inspection-and-patrol-category/delete?id=` + id })
  },

  // 导出巡查巡检类 Excel
  exportInspectionAndPatrolCategory: async (params) => {
    return await request.download({ url: `/smartcity/inspection-and-patrol-category/export-excel`, params })
  }
}