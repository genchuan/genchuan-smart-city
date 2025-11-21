import request from '@/config/axios'

// 巡查巡检结果公示 VO
export interface InspectResultPublicVO {
  id: number // 主键ID
  publicId: string // 公示ID
  publicCode: string // 公示编码
  publicTitle: string // 公示标题
  publicType: string // 公示类型
  areaCode: string // 所属区域代码
  areaName: string // 所属区域名称
  publicContent: string // 公示内容
  publicAttachUrl: string // 公示附件URL
  publicStartTime: Date // 公示开始时间
  publicEndTime: Date // 公示结束时间
  publicStatus: string // 公示状态
  publicDesc: string // 公示说明
  createUser: string // 创建人
  bizCreateTime: Date // 创建时间
  updateUser: string // 更新人
  bizUpdateTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检结果公示 API
export const InspectResultPublicApi = {
  // 查询巡查巡检结果公示分页
  getInspectResultPublicPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-result-public/page`, params })
  },

  // 查询巡查巡检结果公示详情
  getInspectResultPublic: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-result-public/get?id=` + id })
  },

  // 新增巡查巡检结果公示
  createInspectResultPublic: async (data: InspectResultPublicVO) => {
    return await request.post({ url: `/datacenter/inspect-result-public/create`, data })
  },

  // 修改巡查巡检结果公示
  updateInspectResultPublic: async (data: InspectResultPublicVO) => {
    return await request.put({ url: `/datacenter/inspect-result-public/update`, data })
  },

  // 删除巡查巡检结果公示
  deleteInspectResultPublic: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-result-public/delete?id=` + id })
  },

  // 导出巡查巡检结果公示 Excel
  exportInspectResultPublic: async (params) => {
    return await request.download({ url: `/datacenter/inspect-result-public/export-excel`, params })
  }
}
