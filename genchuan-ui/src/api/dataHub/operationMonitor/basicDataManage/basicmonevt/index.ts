import request from '@/config/axios'

// 基础监测事件 VO
export interface BasicMonEvtVO {
  id: number // 主键ID
  evtId: string // 事件ID
  evtCode: string // 事件编码
  evtType: string // 事件类型
  relPtId: string // 关联点位ID
  relPtName: string // 关联点位名称
  relIdxId: string // 关联指标ID
  relIdxName: string // 关联指标名称
  relDeviceCode: string // 关联设备编码
  relDeviceName: string // 关联设备名称
  evtValue: string // 事件数值
  evtTime: Date // 事发时间
  dealStatus: string // 处置状态
  deptCode: string // 派单部门代码
  deptName: string // 派单部门名称
  dealUserId: number // 处置人ID
  dealUserName: string // 处置人姓名
  dealResult: string // 处置结果
  dealTime: Date // 处置时间
  createTimeSys: Date // 创建时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 基础监测事件 API
export const BasicMonEvtApi = {
  // 查询基础监测事件分页
  getBasicMonEvtPage: async (params: any) => {
    return await request.get({ url: `/datacenter/basic-mon-evt/page`, params })
  },

  // 查询基础监测事件详情
  getBasicMonEvt: async (id: number) => {
    return await request.get({ url: `/datacenter/basic-mon-evt/get?id=` + id })
  },

  // 新增基础监测事件
  createBasicMonEvt: async (data: BasicMonEvtVO) => {
    return await request.post({ url: `/datacenter/basic-mon-evt/create`, data })
  },

  // 修改基础监测事件
  updateBasicMonEvt: async (data: BasicMonEvtVO) => {
    return await request.put({ url: `/datacenter/basic-mon-evt/update`, data })
  },

  // 删除基础监测事件
  deleteBasicMonEvt: async (id: number) => {
    return await request.delete({ url: `/datacenter/basic-mon-evt/delete?id=` + id })
  },

  // 导出基础监测事件 Excel
  exportBasicMonEvt: async (params) => {
    return await request.download({ url: `/datacenter/basic-mon-evt/export-excel`, params })
  }
}
