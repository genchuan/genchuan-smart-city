import request from '@/config/axios'

// 巡查巡检台账档案 VO
export interface InspectLedgerArchVO {
  id: number // 主键ID
  ledgerArchId: string // 档案ID
  ledgerArchCode: string // 档案编码
  ledgerArchName: string // 档案名称
  areaCode: string // 所属区域代码
  areaName: string // 所属区域名称
  ledgerCycle: string // 台账周期
  coreIdxJson: string // 核心指标JSON
  includeArchTypes: string // 包含档案类型
  ledgerAttachUrl: string // 台账附件URL
  ledgerArchUser: string // 归档人
  ledgerArchTime: Date // 归档时间
  ledgerArchStatus: string // 档案状态
  ledgerDestroyTime: Date // 销毁时间
  ledgerArchDesc: string // 归档说明
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检台账档案 API
export const InspectLedgerArchApi = {
  // 查询巡查巡检台账档案分页
  getInspectLedgerArchPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-ledger-arch/page`, params })
  },

  // 查询巡查巡检台账档案详情
  getInspectLedgerArch: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-ledger-arch/get?id=` + id })
  },

  // 新增巡查巡检台账档案
  createInspectLedgerArch: async (data: InspectLedgerArchVO) => {
    return await request.post({ url: `/datacenter/inspect-ledger-arch/create`, data })
  },

  // 修改巡查巡检台账档案
  updateInspectLedgerArch: async (data: InspectLedgerArchVO) => {
    return await request.put({ url: `/datacenter/inspect-ledger-arch/update`, data })
  },

  // 删除巡查巡检台账档案
  deleteInspectLedgerArch: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-ledger-arch/delete?id=` + id })
  },

  // 导出巡查巡检台账档案 Excel
  exportInspectLedgerArch: async (params) => {
    return await request.download({ url: `/datacenter/inspect-ledger-arch/export-excel`, params })
  }
}
