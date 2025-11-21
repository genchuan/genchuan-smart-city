import request from '@/config/axios'

// 平台上报数据 VO
export interface EvalPlatformRptDataVO {
  id: number // 主键ID
  platformRptDataId: string // 上报数据ID
  evalTaskId: string // 关联任务ID
  evalTaskName: string // 关联任务名称
  evalObjectId: string // 评价对象ID
  evalObjectName: string // 评价对象名称
  idxItemId: string // 关联指标项ID
  idxItemName: string // 关联指标项名称
  dataValue: string // 数据值
  dataUnit: string // 数据单位
  rptTime: Date // 上报时间
  rptUserId: string // 上报人ID
  rptUserName: string // 上报人姓名
  dataStatus: string // 数据状态
  verifyFailReason: string // 校验失败原因
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 平台上报数据 API
export const EvalPlatformRptDataApi = {
  // 查询平台上报数据分页
  getEvalPlatformRptDataPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-platform-rpt-data/page`, params })
  },

  // 查询平台上报数据详情
  getEvalPlatformRptData: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-platform-rpt-data/get?id=` + id })
  },

  // 新增平台上报数据
  createEvalPlatformRptData: async (data: EvalPlatformRptDataVO) => {
    return await request.post({ url: `/datacenter/eval-platform-rpt-data/create`, data })
  },

  // 修改平台上报数据
  updateEvalPlatformRptData: async (data: EvalPlatformRptDataVO) => {
    return await request.put({ url: `/datacenter/eval-platform-rpt-data/update`, data })
  },

  // 删除平台上报数据
  deleteEvalPlatformRptData: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-platform-rpt-data/delete?id=` + id })
  },

  // 导出平台上报数据 Excel
  exportEvalPlatformRptData: async (params) => {
    return await request.download({
      url: `/datacenter/eval-platform-rpt-data/export-excel`,
      params
    })
  }
}
