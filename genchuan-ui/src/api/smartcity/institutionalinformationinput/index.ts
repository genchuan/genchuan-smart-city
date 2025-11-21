import request from '@/config/axios'

// 机构信息录入 VO
export interface InstitutionalInformationInputVO {
  id: number // 主键
  institutionName: string // 机构名称
  unifiedSocialCreditCode: string // 统一社会信用代码
  establishmentTime: Date // 成立时间
  natureOfInstitution: string // 机构性质
  natureOfBusiness: string // 经营范围
  registeredCapital: string // 注册资本
  legalRepresentative: string // 法定代表人
  companyRegisteredAddress: string // 注册地址
}

// 机构信息录入 API
export const InstitutionalInformationInputApi = {
  // 查询机构信息录入分页
  getInstitutionalInformationInputPage: async (params: any) => {
    return await request.get({ url: `/smartcity/institutional-information-input/page`, params })
  },

  // 查询机构信息录入详情
  getInstitutionalInformationInput: async (id: number) => {
    return await request.get({ url: `/smartcity/institutional-information-input/get?id=` + id })
  },

  // 新增机构信息录入
  createInstitutionalInformationInput: async (data: InstitutionalInformationInputVO) => {
    return await request.post({ url: `/smartcity/institutional-information-input/create`, data })
  },

  // 修改机构信息录入
  updateInstitutionalInformationInput: async (data: InstitutionalInformationInputVO) => {
    return await request.put({ url: `/smartcity/institutional-information-input/update`, data })
  },

  // 删除机构信息录入
  deleteInstitutionalInformationInput: async (id: number) => {
    return await request.delete({ url: `/smartcity/institutional-information-input/delete?id=` + id })
  },

  // 导出机构信息录入 Excel
  exportInstitutionalInformationInput: async (params) => {
    return await request.download({ url: `/smartcity/institutional-information-input/export-excel`, params })
  }
}