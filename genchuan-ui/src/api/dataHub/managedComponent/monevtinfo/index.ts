import request from '@/config/axios'

// 监测事件信息 VO
export interface MonEvtInfoVO {
  id: number // 主键ID
  monEvtId: string // 事件ID
  evtCode: string // 事件标识码
  evtName: string // 事件名称
  evtCatId: string // 事件分类ID
  relCompId: string // 关联部件ID
  relCompName: string // 关联部件名称
  incidentPos: string // 事发位置
  incidentX: number // 事发坐标X
  incidentY: number // 事发坐标Y
  evtLevel: string // 事件等级
  handleStatus: string // 处置状态
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 监测事件信息 API
export const MonEvtInfoApi = {
  // 查询监测事件信息分页
  getMonEvtInfoPage: async (params: any) => {
    return await request.get({ url: `/datacenter/mon-evt-info/page`, params })
  },

  // 查询监测事件信息详情
  getMonEvtInfo: async (id: number) => {
    return await request.get({ url: `/datacenter/mon-evt-info/get?id=` + id })
  },

  // 新增监测事件信息
  createMonEvtInfo: async (data: MonEvtInfoVO) => {
    return await request.post({ url: `/datacenter/mon-evt-info/create`, data })
  },

  // 修改监测事件信息
  updateMonEvtInfo: async (data: MonEvtInfoVO) => {
    return await request.put({ url: `/datacenter/mon-evt-info/update`, data })
  },

  // 删除监测事件信息
  deleteMonEvtInfo: async (id: number) => {
    return await request.delete({ url: `/datacenter/mon-evt-info/delete?id=` + id })
  },

  // 导出监测事件信息 Excel
  exportMonEvtInfo: async (params) => {
    return await request.download({ url: `/datacenter/mon-evt-info/export-excel`, params })
  },
}