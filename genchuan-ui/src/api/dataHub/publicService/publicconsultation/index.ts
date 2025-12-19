import request from '@/config/axios'

// 咨询建议 VO
export interface PublicConsultationVO {
  id: number // 主键ID
  consultId: string // 咨询ID
  consultNo: string // 咨询单号
  consultTypeCode: string // 咨询类型编码
  consultTypeName: string // 咨询类型名称
  consultContent: string // 咨询内容
  contactName: string // 联系人姓名
  contactPhone: string // 联系人电话
  acceptDeptCode: string // 受理部门编码
  acceptDeptName: string // 受理部门名称
  replyContent: string // 回复内容
  consultStatus: number // 咨询状态
  submitTime: Date // 提交时间
  replyTime: Date // 回复时间
  remark: string // 备注，补充说明
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 咨询建议 API
export const PublicConsultationApi = {
  // 查询咨询建议分页
  getPublicConsultationPage: async (params: any) => {
    return await request.get({ url: `/datacenter/public-consultation/page`, params })
  },

  // 查询咨询建议详情
  getPublicConsultation: async (id: number) => {
    return await request.get({ url: `/datacenter/public-consultation/get?id=` + id })
  },

  // 新增咨询建议
  createPublicConsultation: async (data: PublicConsultationVO) => {
    return await request.post({ url: `/datacenter/public-consultation/create`, data })
  },

  // 修改咨询建议
  updatePublicConsultation: async (data: PublicConsultationVO) => {
    return await request.put({ url: `/datacenter/public-consultation/update`, data })
  },

  // 删除咨询建议
  deletePublicConsultation: async (id: number) => {
    return await request.delete({ url: `/datacenter/public-consultation/delete?id=` + id })
  },

  // 导出咨询建议 Excel
  exportPublicConsultation: async (params) => {
    return await request.download({ url: `/datacenter/public-consultation/export-excel`, params })
  },
}