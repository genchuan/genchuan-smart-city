import request from '@/config/axios'

// 政策解读信息 VO
export interface PolInterpretationVO {
  id: number // 主键ID
  polInterpretationId: string // 解读ID
  relatedPolType: string // 关联政策类型
  relatedPolId: string // 关联政策ID
  relatedPolTitle: string // 关联政策标题
  interpretationTitle: string // 解读标题
  interpretationContent: string // 解读详细内容
  interpreterId: string // 解读人ID
  interpreterName: string // 解读人姓名
  interpretationTime: Date // 解读时间
  interpretationStatus: string // 解读状态
  attachId: string // 附件ID
  attachPath: string // 附件路径
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 政策解读信息 API
export const PolInterpretationApi = {
  // 查询政策解读信息分页
  getPolInterpretationPage: async (params: any) => {
    return await request.get({ url: `/datacenter/pol-interpretation/page`, params })
  },

  // 查询政策解读信息详情
  getPolInterpretation: async (id: number) => {
    return await request.get({ url: `/datacenter/pol-interpretation/get?id=` + id })
  },

  // 新增政策解读信息
  createPolInterpretation: async (data: PolInterpretationVO) => {
    return await request.post({ url: `/datacenter/pol-interpretation/create`, data })
  },

  // 修改政策解读信息
  updatePolInterpretation: async (data: PolInterpretationVO) => {
    return await request.put({ url: `/datacenter/pol-interpretation/update`, data })
  },

  // 删除政策解读信息
  deletePolInterpretation: async (id: number) => {
    return await request.delete({ url: `/datacenter/pol-interpretation/delete?id=` + id })
  },

  // 导出政策解读信息 Excel
  exportPolInterpretation: async (params) => {
    return await request.download({ url: `/datacenter/pol-interpretation/export-excel`, params })
  }
}