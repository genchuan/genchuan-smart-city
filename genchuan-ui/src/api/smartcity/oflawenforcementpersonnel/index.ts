import request from '@/config/axios'

// 执法人员管理 VO
export interface OfLawEnforcementPersonnelVO {
  id: number // 主键
  fullName: string // 姓名
  gender: string // 性别
  idNumber: string // 身份证号
  contactInformation: string // 联系方式
  belongingLawDepartment: string // 所属执法部门
  position: string // 职务
  lawEnforcementNumber: string // 执法证编号
  validityCertificate: Date // 执法证有效期
  entryTime: string // 入职时间
  politicalStatus: string // 政治面貌
  educationalBackground: string // 学历
  major: string // 专业
  trainingExperience: string // 培训经历
  resultOfExamination: string // 考核成绩
  rewardsRecord: string // 奖惩记录
  violationRegulations: string // 违规违纪情况
  responsibleArea: string // 负责区域
  lawEnforcementType: string // 执法类型
  emergencyContactName: string // 紧急联系人姓名
  emergencyContactPhone: string // 紧急联系人电话
}

// 执法人员管理 API
export const OfLawEnforcementPersonnelApi = {
  // 查询执法人员管理分页
  getOfLawEnforcementPersonnelPage: async (params: any) => {
    return await request.get({ url: `/smartcity/of-law-enforcement-personnel/page`, params })
  },

  // 查询执法人员管理详情
  getOfLawEnforcementPersonnel: async (id: number) => {
    return await request.get({ url: `/smartcity/of-law-enforcement-personnel/get?id=` + id })
  },

  // 新增执法人员管理
  createOfLawEnforcementPersonnel: async (data: OfLawEnforcementPersonnelVO) => {
    return await request.post({ url: `/smartcity/of-law-enforcement-personnel/create`, data })
  },

  // 修改执法人员管理
  updateOfLawEnforcementPersonnel: async (data: OfLawEnforcementPersonnelVO) => {
    return await request.put({ url: `/smartcity/of-law-enforcement-personnel/update`, data })
  },

  // 删除执法人员管理
  deleteOfLawEnforcementPersonnel: async (id: number) => {
    return await request.delete({ url: `/smartcity/of-law-enforcement-personnel/delete?id=` + id })
  },

  // 导出执法人员管理 Excel
  exportOfLawEnforcementPersonnel: async (params) => {
    return await request.download({ url: `/smartcity/of-law-enforcement-personnel/export-excel`, params })
  }
}