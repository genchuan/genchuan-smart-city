import request from '@/config/axios'

// 巡查巡检整改档案 VO
export interface InspectRectifyArchVO {
  id: number // 主键ID
  rectifyArchId: string // 档案ID
  rectifyArchCode: string // 档案编码
  rectifyArchName: string // 档案名称
  areaCode: string // 所属区域代码
  areaName: string // 所属区域名称
  rectifyArchCycle: string // 归档周期
  problemTypeName: string // 关联问题类型
  includeTaskCount: number // 包含整改任务数
  rectifyCompleteRate: number // 整改完成率
  rectifyAttachUrls: string // 档案附件URLs
  rectifyArchUser: string // 归档人
  rectifyArchTime: Date // 归档时间
  rectifyArchStatus: string // 档案状态
  rectifyDestroyTime: Date // 销毁时间
  rectifyArchDesc: string // 归档说明
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检整改档案 API
export const InspectRectifyArchApi = {
  // 查询巡查巡检整改档案分页
  getInspectRectifyArchPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-rectify-arch/page`, params })
  },

  // 查询巡查巡检整改档案详情
  getInspectRectifyArch: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-rectify-arch/get?id=` + id })
  },

  // 新增巡查巡检整改档案
  createInspectRectifyArch: async (data: InspectRectifyArchVO) => {
    return await request.post({ url: `/datacenter/inspect-rectify-arch/create`, data })
  },

  // 修改巡查巡检整改档案
  updateInspectRectifyArch: async (data: InspectRectifyArchVO) => {
    return await request.put({ url: `/datacenter/inspect-rectify-arch/update`, data })
  },

  // 删除巡查巡检整改档案
  deleteInspectRectifyArch: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-rectify-arch/delete?id=` + id })
  },

  // 导出巡查巡检整改档案 Excel
  exportInspectRectifyArch: async (params) => {
    return await request.download({ url: `/datacenter/inspect-rectify-arch/export-excel`, params })
  }
}
