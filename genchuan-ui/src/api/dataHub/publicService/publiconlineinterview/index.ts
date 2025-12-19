import request from '@/config/axios'

// 在线访谈 VO
export interface PublicOnlineInterviewVO {
  id: number // 主键ID
  interviewId: string // 访谈ID 
  interviewTopic: string // 访谈主题 
  deptCode: string // 访谈单位编码 
  deptName: string // 访谈单位名称 
  guestInfo: string // 嘉宾信息 
  interviewTime: Date // 访谈时间 
  interviewEndure: number // 访谈时长 
  interviewStatus: number // 访谈状态 
  questionCount: number // 提问总数 
  replyCount: number // 回复总数 
  recContent: string // 实录内容 
  recPublishTime: Date // 实录发布时间 
  publishUserId: number // 发布人ID 
  publishUserName: string // 发布人姓名 
  remark: string // 备注，补充说明 
  extCat1: string // 分类扩展字段1   
  extCat2: string // 分类扩展字段2   
  extCommon1: string // 通用扩展字段1 
  extCommon2: string // 通用扩展字段2  
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 在线访谈 API
export const PublicOnlineInterviewApi = {
  // 查询在线访谈分页
  getPublicOnlineInterviewPage: async (params: any) => {
    return await request.get({ url: `/datacenter/public-online-interview/page`, params })
  },

  // 查询在线访谈详情
  getPublicOnlineInterview: async (id: number) => {
    return await request.get({ url: `/datacenter/public-online-interview/get?id=` + id })
  },

  // 新增在线访谈
  createPublicOnlineInterview: async (data: PublicOnlineInterviewVO) => {
    return await request.post({ url: `/datacenter/public-online-interview/create`, data })
  },

  // 修改在线访谈
  updatePublicOnlineInterview: async (data: PublicOnlineInterviewVO) => {
    return await request.put({ url: `/datacenter/public-online-interview/update`, data })
  },

  // 删除在线访谈
  deletePublicOnlineInterview: async (id: number) => {
    return await request.delete({ url: `/datacenter/public-online-interview/delete?id=` + id })
  },

  // 导出在线访谈 Excel
  exportPublicOnlineInterview: async (params) => {
    return await request.download({ url: `/datacenter/public-online-interview/export-excel`, params })
  },
}
