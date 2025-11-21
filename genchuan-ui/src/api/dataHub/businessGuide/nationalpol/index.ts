import request from '@/config/axios'

// 国家政策信息 VO
export interface NationalPolVO {
  id: number // 主键ID
  nationalPolId: string // 政策ID
  polTitle: string // 政策标题
  polDocNo: string // 政策文号
  issueDeptCode: string // 发布部门代码
  issueDeptName: string // 发布部门名称
  issueTime: Date // 发布时间
  implementTime: Date // 实施时间
  polFileId: string // 政策文件ID
  polFilePath: string // 政策文件路径
  polStatus: string // 政策状态
  polSummary: string // 政策摘要
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 国家政策信息 API
export const NationalPolApi = {
  // 查询国家政策信息分页
  getNationalPolPage: async (params: any) => {
    return await request.get({ url: `/datacenter/national-pol/page`, params })
  },

  // 查询国家政策信息详情
  getNationalPol: async (id: number) => {
    return await request.get({ url: `/datacenter/national-pol/get?id=` + id })
  },

  // 新增国家政策信息
  createNationalPol: async (data: NationalPolVO) => {
    return await request.post({ url: `/datacenter/national-pol/create`, data })
  },

  // 修改国家政策信息
  updateNationalPol: async (data: NationalPolVO) => {
    return await request.put({ url: `/datacenter/national-pol/update`, data })
  },

  // 删除国家政策信息
  deleteNationalPol: async (id: number) => {
    return await request.delete({ url: `/datacenter/national-pol/delete?id=` + id })
  },

  // 导出国家政策信息 Excel
  exportNationalPol: async (params) => {
    return await request.download({ url: `/datacenter/national-pol/export-excel`, params })
  }
}