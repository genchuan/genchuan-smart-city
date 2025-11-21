import request from '@/config/axios'

// 巡查巡检验收档案 VO
export interface InspectAcceptArchVO {
  id: number // 主键ID
  acceptArchId: string // 档案ID
  acceptArchCode: string // 档案编码
  acceptArchName: string // 档案名称
  areaCode: string // 所属区域代码
  areaName: string // 所属区域名称
  acceptArchCycle: string // 归档周期
  includeAcceptCount: number // 包含验收记录数
  acceptQualifiedRate: number // 验收合格率
  acceptAttachUrls: string // 档案附件URLs
  acceptArchUser: string // 归档人
  acceptArchTime: Date // 归档时间
  acceptArchStatus: string // 档案状态
  acceptDestroyTime: Date // 销毁时间
  acceptArchDesc: string // 归档说明
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检验收档案 API
export const InspectAcceptArchApi = {
  // 查询巡查巡检验收档案分页
  getInspectAcceptArchPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-accept-arch/page`, params })
  },

  // 查询巡查巡检验收档案详情
  getInspectAcceptArch: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-accept-arch/get?id=` + id })
  },

  // 新增巡查巡检验收档案
  createInspectAcceptArch: async (data: InspectAcceptArchVO) => {
    return await request.post({ url: `/datacenter/inspect-accept-arch/create`, data })
  },

  // 修改巡查巡检验收档案
  updateInspectAcceptArch: async (data: InspectAcceptArchVO) => {
    return await request.put({ url: `/datacenter/inspect-accept-arch/update`, data })
  },

  // 删除巡查巡检验收档案
  deleteInspectAcceptArch: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-accept-arch/delete?id=` + id })
  },

  // 导出巡查巡检验收档案 Excel
  exportInspectAcceptArch: async (params) => {
    return await request.download({ url: `/datacenter/inspect-accept-arch/export-excel`, params })
  }
}
