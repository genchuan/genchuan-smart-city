import request from '@/config/axios'

// 通知公告发布 VO
export interface PublicNoticeVO {
  id: number // 主键ID
  noticeId: string // 通知ID
  noticeTitle: string // 通知标题
  noticeContent: string // 通知内容
  deptCode: string // 发布部门编码
  deptName: string // 发布部门名称
  publishUserId: number // 发布人ID
  publishUserName: string // 发布人姓名
  publishTime: Date // 发布时间
  noticeStatus: number // 通知状态
  effectiveTime: Date // 生效时间
  expireTime: Date // 失效时间
  attachPath: string // 附件路径
  remark: string // 备注，补充说明
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 通知公告发布 API
export const PublicNoticeApi = {
  // 查询通知公告发布分页
  getPublicNoticePage: async (params: any) => {
    return await request.get({ url: `/datacenter/public-notice/page`, params })
  },

  // 查询通知公告发布详情
  getPublicNotice: async (id: number) => {
    return await request.get({ url: `/datacenter/public-notice/get?id=` + id })
  },

  // 新增通知公告发布
  createPublicNotice: async (data: PublicNoticeVO) => {
    return await request.post({ url: `/datacenter/public-notice/create`, data })
  },

  // 修改通知公告发布
  updatePublicNotice: async (data: PublicNoticeVO) => {
    return await request.put({ url: `/datacenter/public-notice/update`, data })
  },

  // 删除通知公告发布
  deletePublicNotice: async (id: number) => {
    return await request.delete({ url: `/datacenter/public-notice/delete?id=` + id })
  },

  // 导出通知公告发布 Excel
  exportPublicNotice: async (params) => {
    return await request.download({ url: `/datacenter/public-notice/export-excel`, params })
  },
}