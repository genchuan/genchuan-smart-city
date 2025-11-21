import request from '@/config/axios'

// 巡査人员管理 VO
export interface ManagementOfPatrolPersonnelVO {
  id: number // 主键
  fullName: string // 姓名
  gender: string // 性别
  dateOfBirth: Date // 出生日期
  contactInformation: string // 联系方式
  idNumber: string // 身份证号
  department: string // 所属部门
  position: string // 岗位名称
  entryTime: Date // 入职时间
  patrolArea: string // 巡查区域
  patrolRoute: string // 巡查路线
  inspectionShift: string // 巡查班次
  qualificationCertificateNumber: string // 资质证书编号
  certificateValidityPeriod: string // 证书有效期
  trainingRecords: string // 培训记录
  inspectionEquipmentNumber: string // 巡查设备编号
  violationRecord: string // 违规记录
  rewardAndPunishmentSituation: string // 奖惩情况
}

// 巡査人员管理 API
export const ManagementOfPatrolPersonnelApi = {
  // 查询巡査人员管理分页
  getManagementOfPatrolPersonnelPage: async (params: any) => {
    return await request.get({ url: `/smartcity/management-of-patrol-personnel/page`, params })
  },

  // 查询巡査人员管理详情
  getManagementOfPatrolPersonnel: async (id: number) => {
    return await request.get({ url: `/smartcity/management-of-patrol-personnel/get?id=` + id })
  },

  // 新增巡査人员管理
  createManagementOfPatrolPersonnel: async (data: ManagementOfPatrolPersonnelVO) => {
    return await request.post({ url: `/smartcity/management-of-patrol-personnel/create`, data })
  },

  // 修改巡査人员管理
  updateManagementOfPatrolPersonnel: async (data: ManagementOfPatrolPersonnelVO) => {
    return await request.put({ url: `/smartcity/management-of-patrol-personnel/update`, data })
  },

  // 删除巡査人员管理
  deleteManagementOfPatrolPersonnel: async (id: number) => {
    return await request.delete({ url: `/smartcity/management-of-patrol-personnel/delete?id=` + id })
  },

  // 导出巡査人员管理 Excel
  exportManagementOfPatrolPersonnel: async (params) => {
    return await request.download({ url: `/smartcity/management-of-patrol-personnel/export-excel`, params })
  }
}