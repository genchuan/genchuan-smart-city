import request from '@/config/axios'

// 双随机行政检查 VO
export interface InspectionObjectVO {
  id: number // ID
  entName: string // 企业名称
  creditCode: string // 统一社会信用代码
  legalPerson: string // 法定代表人
  regAddress: string // 注册地址
  businessScope: string // 经营范围
  industryType: string // 行业类型
  riskLevel: string // 风险等级
  contactPerson: string // 联系人
  contactPhone: string // 联系电话
}

// 双随机行政检查 API
export const InspectionObjectApi = {
  // 查询双随机行政检查分页
  getInspectionObjectPage: async (params: any) => {
    return await request.get({ url: `/smartcity/inspection-object/page`, params })
  },

  // 查询双随机行政检查详情
  getInspectionObject: async (id: number) => {
    return await request.get({ url: `/smartcity/inspection-object/get?id=` + id })
  },

  // 新增双随机行政检查
  createInspectionObject: async (data: InspectionObjectVO) => {
    return await request.post({ url: `/smartcity/inspection-object/create`, data })
  },

  // 修改双随机行政检查
  updateInspectionObject: async (data: InspectionObjectVO) => {
    return await request.put({ url: `/smartcity/inspection-object/update`, data })
  },

  // 删除双随机行政检查
  deleteInspectionObject: async (id: number) => {
    return await request.delete({ url: `/smartcity/inspection-object/delete?id=` + id })
  },

  // 导出双随机行政检查 Excel
  exportInspectionObject: async (params) => {
    return await request.download({ url: `/smartcity/inspection-object/export-excel`, params })
  }
}