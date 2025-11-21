import request from '@/config/axios'

// 实时监测数据接入 VO
export interface EvalRealTimeDataVO {
  id: number // 主键ID
  realTimeDataId: string // 数据ID
  evalTaskId: string // 关联任务ID
  evalTaskName: string // 关联任务名称
  idxItemId: string // 关联指标项ID
  idxItemName: string // 关联指标项名称
  deviceId: string // 设备ID
  deviceName: string // 设备名称
  dataValue: string // 数据值
  dataTime: Date // 数据时间
  accessStatus: string // 接入状态
  failReason: string // 失败原因
  syncFrequency: number // 同步频率
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 实时监测数据接入 API
export const EvalRealTimeDataApi = {
  // 查询实时监测数据接入分页
  getEvalRealTimeDataPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-real-time-data/page`, params })
  },

  // 查询实时监测数据接入详情
  getEvalRealTimeData: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-real-time-data/get?id=` + id })
  },

  // 新增实时监测数据接入
  createEvalRealTimeData: async (data: EvalRealTimeDataVO) => {
    return await request.post({ url: `/datacenter/eval-real-time-data/create`, data })
  },

  // 修改实时监测数据接入
  updateEvalRealTimeData: async (data: EvalRealTimeDataVO) => {
    return await request.put({ url: `/datacenter/eval-real-time-data/update`, data })
  },

  // 删除实时监测数据接入
  deleteEvalRealTimeData: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-real-time-data/delete?id=` + id })
  },

  // 导出实时监测数据接入 Excel
  exportEvalRealTimeData: async (params) => {
    return await request.download({ url: `/datacenter/eval-real-time-data/export-excel`, params })
  }
}
