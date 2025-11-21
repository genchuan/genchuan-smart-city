import request from '@/config/axios'

// 行业典型经验 VO
export interface IndustryTypicalExpVO {
  id: number // 主键ID
  industryTypicalExpId: string // 经验ID
  expTitle: string // 经验标题
  industryFieldCode: string // 行业领域代码
  industryFieldName: string // 行业领域名称
  caseOrg: string // 案例单位
  expHighlights: string // 经验核心亮点
  implementationEffect: string // 经验实施效果
  attachId: string // 附件ID
  attachPath: string // 附件存储路径
  publishStatus: string // 发布状态
  likeCount: number // 点赞次数
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 行业典型经验 API
export const IndustryTypicalExpApi = {
  // 查询行业典型经验分页
  getIndustryTypicalExpPage: async (params: any) => {
    return await request.get({ url: `/datacenter/industry-typical-exp/page`, params })
  },

  // 查询行业典型经验详情
  getIndustryTypicalExp: async (id: number) => {
    return await request.get({ url: `/datacenter/industry-typical-exp/get?id=` + id })
  },

  // 新增行业典型经验
  createIndustryTypicalExp: async (data: IndustryTypicalExpVO) => {
    return await request.post({ url: `/datacenter/industry-typical-exp/create`, data })
  },

  // 修改行业典型经验
  updateIndustryTypicalExp: async (data: IndustryTypicalExpVO) => {
    return await request.put({ url: `/datacenter/industry-typical-exp/update`, data })
  },

  // 删除行业典型经验
  deleteIndustryTypicalExp: async (id: number) => {
    return await request.delete({ url: `/datacenter/industry-typical-exp/delete?id=` + id })
  },

  // 导出行业典型经验 Excel
  exportIndustryTypicalExp: async (params) => {
    return await request.download({ url: `/datacenter/industry-typical-exp/export-excel`, params })
  },
}