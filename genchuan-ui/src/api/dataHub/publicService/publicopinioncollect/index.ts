import request from '@/config/axios'

// 民意征集 VO
export interface PublicOpinionCollectVO {
  id: number // 主键ID
  collectId: string // 征集ID 
  collectTopic: string // 征集主题 
  deptCode: string // 征集单位编码 
  deptName: string // 征集单位名称 
  areaCode: string // 征集范围编码 
  areaName: string // 征集范围名称 
  collectContent: string // 征集内容 
  startTime: Date // 开始时间 
  endTime: Date // 结束时间 
  collectStatus: number // 征集状态 
  resultContent: string // 结果公示内容 
  publishResultTime: Date // 公示时间 
  publishUserId: number // 发布人ID 
  publishTime: Date // 发布时间 
  remark: string // 备注，补充说明 
  extCat1: string // 分类扩展字段1 
  extCat2: string // 分类扩展字段2 
  extCommon1: string // 通用扩展字段1  
  extCommon2: string // 通用扩展字段2 
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 民意征集 API
export const PublicOpinionCollectApi = {
  // 查询民意征集分页
  getPublicOpinionCollectPage: async (params: any) => {
    return await request.get({ url: `/datacenter/public-opinion-collect/page`, params })
  },

  // 查询民意征集详情
  getPublicOpinionCollect: async (id: number) => {
    return await request.get({ url: `/datacenter/public-opinion-collect/get?id=` + id })
  },

  // 新增民意征集
  createPublicOpinionCollect: async (data: PublicOpinionCollectVO) => {
    return await request.post({ url: `/datacenter/public-opinion-collect/create`, data })
  },

  // 修改民意征集
  updatePublicOpinionCollect: async (data: PublicOpinionCollectVO) => {
    return await request.put({ url: `/datacenter/public-opinion-collect/update`, data })
  },

  // 删除民意征集
  deletePublicOpinionCollect: async (id: number) => {
    return await request.delete({ url: `/datacenter/public-opinion-collect/delete?id=` + id })
  },

  // 导出民意征集 Excel
  exportPublicOpinionCollect: async (params) => {
    return await request.download({ url: `/datacenter/public-opinion-collect/export-excel`, params })
  },
}