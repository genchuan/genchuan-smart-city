import request from '@/config/axios'

// 检查对象库管理 VO
export interface InspectionObjectLibVO {
  id: number // ID
  entName: string // 企业名称
  creditCode: string // 统一社会信用代码
  legalPerson: string // 法定代表人
  regAddress: string // 注册地址
  businessScope: string // 经营范围
  industryType: string // 行业类型
  riskLevel: string // 风险等级
}

// 双随机行政检查 API
export const InspectionObjectLibApi = {
  // 查询双随机行政检查分页
  getInspectionObjectLibPage: async (params: any) => {
    return await request.get({ url: `/smartcity/inspection-object-lib/page`, params })
  },

  // 查询双随机行政检查详情
  getInspectionObjectLib: async (id: number) => {
    return await request.get({ url: `/smartcity/inspection-object-lib/get?id=` + id })
  },

  // 新增双随机行政检查
  createInspectionObjectLib: async (data: InspectionObjectVO) => {
    return await request.post({ url: `/smartcity/inspection-object-lib/create`, data })
  },

  // 修改双随机行政检查
  updateInspectionObjectLib: async (data: InspectionObjectVO) => {
    return await request.put({ url: `/smartcity/inspection-object-lib/update`, data })
  },

  // 删除双随机行政检查
  deleteInspectionObjectLib: async (id: number) => {
    return await request.delete({ url: `/smartcity/inspection-object-lib/delete?id=` + id })
  },

  // 导出双随机行政检查 Excel
  exportInspectionObjectLib: async (params) => {
    return await request.download({ url: `/smartcity/inspection-object-lib/export-excel`, params })
  }
}
