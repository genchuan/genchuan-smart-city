import request from '@/config/axios'

// 人员信息 VO
export interface PersonnelInformationVO {
  id: number // 主键
  fullName: string // 姓名
  gender: string // 性别
  dateOfBirth: Date // 出生日期
  idNumber: string // 身份证号
  nationality: string // 民族
  registeredResidenceAddress: string // 户籍地址
  currentResidentialAddress: string // 现居住地址
  contactNumber: string // 联系电话
  eMail: string // 电子邮箱
  maritalStatus: string // 婚姻状况
  educationalBackground: string // 学历
  almaMater: string // 毕业院校
  major: string // 专业
  workUnit: string // 工作单位
  career: string // 职业
  entryTime: Date // 入职时间
  resignationTime: Date // 离职时间（若已离职）
  emergencyContactName: string // 紧急联系人姓名
  emergencyContactPhone: string // 紧急联系人电话
  emergencyContactRelationship: string // 紧急联系人关系
}

// 人员信息 API
export const PersonnelInformationApi = {
  // 查询人员信息分页
  getPersonnelInformationPage: async (params: any) => {
    return await request.get({ url: `/smartcity/personnel-information/page`, params })
  },

  // 查询人员信息详情
  getPersonnelInformation: async (id: number) => {
    return await request.get({ url: `/smartcity/personnel-information/get?id=` + id })
  },

  // 新增人员信息
  createPersonnelInformation: async (data: PersonnelInformationVO) => {
    return await request.post({ url: `/smartcity/personnel-information/create`, data })
  },

  // 修改人员信息
  updatePersonnelInformation: async (data: PersonnelInformationVO) => {
    return await request.put({ url: `/smartcity/personnel-information/update`, data })
  },

  // 删除人员信息
  deletePersonnelInformation: async (id: number) => {
    return await request.delete({ url: `/smartcity/personnel-information/delete?id=` + id })
  },

  // 导出人员信息 Excel
  exportPersonnelInformation: async (params) => {
    return await request.download({ url: `/smartcity/personnel-information/export-excel`, params })
  }
}