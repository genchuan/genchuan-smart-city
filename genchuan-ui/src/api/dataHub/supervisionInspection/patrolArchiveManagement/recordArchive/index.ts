import request from '@/config/axios'

// 巡查巡检记录档案 VO
export interface InspectRecArchVO {
  id: number // 主键ID
  archId: string // 档案ID
  archCode: string // 档案编码
  archName: string // 档案名称
  archType: string // 档案类型
  areaCode: string // 所属区域代码
  areaName: string // 所属区域名称
  archCycle: string // 归档周期
  includeRecCount: number // 包含记录数
  archAttachUrls: string // 档案附件URLs
  archUser: string // 归档人
  archTime: Date // 归档时间
  archStatus: string // 档案状态
  destroyTime: Date // 销毁时间
  archDesc: string // 归档说明
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检记录档案 API
export const InspectRecArchApi = {
  // 查询巡查巡检记录档案分页
  getInspectRecArchPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-rec-arch/page`, params })
  },

  // 查询巡查巡检记录档案详情
  getInspectRecArch: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-rec-arch/get?id=` + id })
  },

  // 新增巡查巡检记录档案
  createInspectRecArch: async (data: InspectRecArchVO) => {
    return await request.post({ url: `/datacenter/inspect-rec-arch/create`, data })
  },

  // 修改巡查巡检记录档案
  updateInspectRecArch: async (data: InspectRecArchVO) => {
    return await request.put({ url: `/datacenter/inspect-rec-arch/update`, data })
  },

  // 删除巡查巡检记录档案
  deleteInspectRecArch: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-rec-arch/delete?id=` + id })
  },

  // 导出巡查巡检记录档案 Excel
  exportInspectRecArch: async (params) => {
    return await request.download({ url: `/datacenter/inspect-rec-arch/export-excel`, params })
  }
}
