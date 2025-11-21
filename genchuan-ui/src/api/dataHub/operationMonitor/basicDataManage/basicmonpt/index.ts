import request from '@/config/axios'

// 基础监测点位 VO
export interface BasicMonPtVO {
  id: number // 主键ID
  ptId: string // 点位ID
  ptName: string // 点位名称
  ptCode: string // 点位编码
  ptType: string // 点位类型
  areaCode: string // 所属区域代码
  areaName: string // 所属区域名称
  deviceCode: string // 关联设备编码
  deviceName: string // 关联设备名称
  ptCoordX: number // 点位坐标X(度)
  ptCoordY: number // 点位坐标Y(度)
  mngrId: number // 负责人ID
  mngrName: string // 负责人姓名
  mngrPhone: string // 负责人联系方式
  ptStatus: boolean // 点位状态
  createTimeSys: Date // 创建时间
  updateTimeSys: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 基础监测点位 API
export const BasicMonPtApi = {
  // 查询基础监测点位分页
  getBasicMonPtPage: async (params: any) => {
    return await request.get({ url: `/datacenter/basic-mon-pt/page`, params })
  },

  // 查询基础监测点位详情
  getBasicMonPt: async (id: number) => {
    return await request.get({ url: `/datacenter/basic-mon-pt/get?id=` + id })
  },

  // 新增基础监测点位
  createBasicMonPt: async (data: BasicMonPtVO) => {
    return await request.post({ url: `/datacenter/basic-mon-pt/create`, data })
  },

  // 修改基础监测点位
  updateBasicMonPt: async (data: BasicMonPtVO) => {
    return await request.put({ url: `/datacenter/basic-mon-pt/update`, data })
  },

  // 删除基础监测点位
  deleteBasicMonPt: async (id: number) => {
    return await request.delete({ url: `/datacenter/basic-mon-pt/delete?id=` + id })
  },

  // 导出基础监测点位 Excel
  exportBasicMonPt: async (params) => {
    return await request.download({ url: `/datacenter/basic-mon-pt/export-excel`, params })
  }
}
