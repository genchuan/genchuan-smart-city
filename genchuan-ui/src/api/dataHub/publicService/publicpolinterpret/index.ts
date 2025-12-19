import request from '@/config/axios'

// 政策解读发布 VO
export interface PublicPolInterpretVO {
  id: number // 主键ID
  interpretId: string // 解读ID
  interpretTitle: string // 解读标题
  polNo: string // 政策文号
  polName: string // 政策名称
  interpretContent: string // 解读内容
  publishDepartmentCode: string // 发布部门编码
  publishDepartmentName: string // 发布部门名称
  publishUserId: number // 发布人ID
  publishTime: Date // 发布时间
  interpretStatus: number // 解读状态
  effectiveTime: Date // 生效时间
  attachPath: string // 附件路径
  remark: string // 备注，补充说明
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 政策解读发布 API
export const PublicPolInterpretApi = {
  // 查询政策解读发布分页
  getPublicPolInterpretPage: async (params: any) => {
    return await request.get({ url: `/datacenter/public-pol-interpret/page`, params })
  },

  // 查询政策解读发布详情
  getPublicPolInterpret: async (id: number) => {
    return await request.get({ url: `/datacenter/public-pol-interpret/get?id=` + id })
  },

  // 新增政策解读发布
  createPublicPolInterpret: async (data: PublicPolInterpretVO) => {
    return await request.post({ url: `/datacenter/public-pol-interpret/create`, data })
  },

  // 修改政策解读发布
  updatePublicPolInterpret: async (data: PublicPolInterpretVO) => {
    return await request.put({ url: `/datacenter/public-pol-interpret/update`, data })
  },

  // 删除政策解读发布
  deletePublicPolInterpret: async (id: number) => {
    return await request.delete({ url: `/datacenter/public-pol-interpret/delete?id=` + id })
  },

  // 导出政策解读发布 Excel
  exportPublicPolInterpret: async (params) => {
    return await request.download({ url: `/datacenter/public-pol-interpret/export-excel`, params })
  },
}