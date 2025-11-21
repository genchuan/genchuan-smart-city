import request from '@/config/axios'

// 投诉举报 VO
export interface PublicComplainVO {
  id: number // 主键ID
  complainId: string // 投诉ID，唯一编码，UUID生成
  complainNo: string // 投诉单号，格式“TS+YYYYMMDD+6位流水号”
  appealTypeCode: string // 诉求类型编码，投诉类型，如“违章停车”，字典表（sys_dict）
  appealTypeName: string // 诉求类型名称，与类型编码同步，字典表（sys_dict）
  areaCode: string // 事发区域编码，事发区域国标编码，区域表（sys_area）
  areaName: string // 事发区域名称，与区域编码同步，区域表（sys_area）
  incidentAddr: string // 事发地址，详细地址，如“XX路12号门前”
  complainContent: string // 投诉内容，问题描述
  imgPath: string // 上传图片路径，多张图片用逗号分隔
  contactName: string // 联系人姓名，投诉人姓名
  contactPhone: string // 联系人电话，加密存储
  acceptDeptCode: string // 受理部门编码，受理部门编码，部门信息表（sys_org）
  acceptDeptName: string // 受理部门名称，与部门编码同步，部门信息表（sys_org）
  handleStatus: number // 处置状态：1=待受理，2=已受理，3=处置中，4=已办结，5=已驳回，流程状态
  handleResult: string // 处置结果，处置完成后填写结果
  submitTime: Date // 提交时间，市民提交时间
  acceptTime: Date // 受理时间，受理部门接单时间
  finishTime: Date // 办结时间，处置完成时间
  extCat1: string // 分类扩展字段1，预留，标识问题等级，如一般/紧急
  extCat2: string // 分类扩展字段2，预留，标识处置方式，如现场/线上
  extCommon1: string // 通用扩展字段1，预留，存储投诉来源，如APP/热线
  extCommon2: string // 通用扩展字段2，预留，存储处置人员ID
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