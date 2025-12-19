import request from '@/config/axios'

// 投诉举报 VO
export interface PublicComplainVO {
  id: number // 主键ID
  complainId: string // 投诉ID
  complainNo: string // 投诉单号
  appealTypeCode: string // 诉求类型编码
  appealTypeName: string // 诉求类型名称
  areaCode: string // 事发区域编码
  areaName: string // 事发区域名称
  incidentAddr: string // 事发地址
  complainContent: string // 投诉内容
  imgPath: string // 上传图片路径
  contactName: string // 联系人姓名
  contactPhone: string // 联系人电话
  acceptDeptCode: string // 受理部门编码
  acceptDeptName: string // 受理部门名称
  handleStatus: number // 处置状态
  handleResult: string // 处置结果
  submitTime: Date // 提交时间
  acceptTime: Date // 受理时间
  finishTime: Date // 办结时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 投诉举报 API
export const PublicComplainApi = {
  // 查询投诉举报分页
  getPublicComplainPage: async (params: any) => {
    return await request.get({ url: `/datacenter/public-complain/page`, params })
  },

  // 查询投诉举报详情
  getPublicComplain: async (id: number) => {
    return await request.get({ url: `/datacenter/public-complain/get?id=` + id })
  },

  // 新增投诉举报
  createPublicComplain: async (data: PublicComplainVO) => {
    return await request.post({ url: `/datacenter/public-complain/create`, data })
  },

  // 修改投诉举报
  updatePublicComplain: async (data: PublicComplainVO) => {
    return await request.put({ url: `/datacenter/public-complain/update`, data })
  },

  // 删除投诉举报
  deletePublicComplain: async (id: number) => {
    return await request.delete({ url: `/datacenter/public-complain/delete?id=` + id })
  },

  // 导出投诉举报 Excel
  exportPublicComplain: async (params) => {
    return await request.download({ url: `/datacenter/public-complain/export-excel`, params })
  },
}