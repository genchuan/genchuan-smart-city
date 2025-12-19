import request from '@/config/axios'

// 智能客服知识库 VO
export interface PublicAiSvcVO {
  id: number // 主键ID
  aiSvcId: string // 知识库ID 
  questionTypeCode: string // 问题类型编码 
  questionTypeName: string // 问题类型名称 
  questionKeyword: string // 问题关键词 
  stdQuestion: string // 标准问题 
  stdAnswer: string // 标准答案 
  attachPath: string // 附件路径 
  useCount: number // 使用次数 
  updateUserId: number // 更新人ID 
  updateUserName: string // 更新人姓名 
  enableStatus: number // 启用状态 
  remark: string // 备注，补充说明 
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2 
  extCommon1: string // 通用扩展字段1 
  extCommon2: string // 通用扩展字段2 
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 智能客服知识库 API
export const PublicAiSvcApi = {
  // 查询智能客服知识库分页
  getPublicAiSvcPage: async (params: any) => {
    return await request.get({ url: `/datacenter/public-ai-svc/page`, params })
  },

  // 查询智能客服知识库详情
  getPublicAiSvc: async (id: number) => {
    return await request.get({ url: `/datacenter/public-ai-svc/get?id=` + id })
  },

  // 新增智能客服知识库
  createPublicAiSvc: async (data: PublicAiSvcVO) => {
    return await request.post({ url: `/datacenter/public-ai-svc/create`, data })
  },

  // 修改智能客服知识库
  updatePublicAiSvc: async (data: PublicAiSvcVO) => {
    return await request.put({ url: `/datacenter/public-ai-svc/update`, data })
  },

  // 删除智能客服知识库
  deletePublicAiSvc: async (id: number) => {
    return await request.delete({ url: `/datacenter/public-ai-svc/delete?id=` + id })
  },

  // 导出智能客服知识库 Excel
  exportPublicAiSvc: async (params) => {
    return await request.download({ url: `/datacenter/public-ai-svc/export-excel`, params })
  },
}