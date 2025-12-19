import request from '@/config/axios'

// 随手拍数据 VO
export interface PublicSnapShotVO {
  id: number // 主键ID
  snapShotId: string // 随手拍ID
  snapNo: string // 记录单号
  problemTypeCode: string // 问题类型编码
  problemTypeName: string // 问题类型名称
  coordX: number // 事发坐标X
  coordY: number // 事发坐标Y
  areaCode: string // 事发区域编码
  areaName: string // 事发区域名称
  problemDesc: string // 问题描述
  mediaPath: string // 媒体路径
  mediaType: number // 媒体类型
  rptName: string // 上报人姓名
  rptPhone: string // 上报人电话
  handleStatus: number // 处置状态
  handleResult: string // 处置结果
  rptTime: Date // 上报时间
  finishTime: Date // 办结时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 随手拍数据 API
export const PublicSnapShotApi = {
  // 查询随手拍数据分页
  getPublicSnapShotPage: async (params: any) => {
    return await request.get({ url: `/datacenter/public-snap-shot/page`, params })
  },

  // 查询随手拍数据详情
  getPublicSnapShot: async (id: number) => {
    return await request.get({ url: `/datacenter/public-snap-shot/get?id=` + id })
  },

  // 新增随手拍数据
  createPublicSnapShot: async (data: PublicSnapShotVO) => {
    return await request.post({ url: `/datacenter/public-snap-shot/create`, data })
  },

  // 修改随手拍数据
  updatePublicSnapShot: async (data: PublicSnapShotVO) => {
    return await request.put({ url: `/datacenter/public-snap-shot/update`, data })
  },

  // 删除随手拍数据
  deletePublicSnapShot: async (id: number) => {
    return await request.delete({ url: `/datacenter/public-snap-shot/delete?id=` + id })
  },

  // 导出随手拍数据 Excel
  exportPublicSnapShot: async (params) => {
    return await request.download({ url: `/datacenter/public-snap-shot/export-excel`, params })
  },
}