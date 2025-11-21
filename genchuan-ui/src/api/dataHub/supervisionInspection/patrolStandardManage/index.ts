import request from '@/config/axios'

// 巡查巡检标准信息 VO
export interface InspectStdInfoVO {
  id: number // 主键ID
  stdId: string // 标准ID
  stdName: string // 标准名称
  stdCode: string // 标准编码
  stdType: string // 标准类型
  areaCode: string // 适用区域代码
  areaName: string // 适用区域名称
  ckItemList: string // 检查项列表
  scoreRule: string // 评分规则
  qualifiedThreshold: number // 合格阈值
  stdStatus: boolean // 标准状态
  effectiveTime: Date // 生效时间
  expireTime: Date // 失效时间
  createUser: string // 创建人
  bizCreateTime: Date // 创建时间
  updateUser: string // 更新人
  bizUpdateTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检标准信息 API
export const InspectStdInfoApi = {
  // 查询巡查巡检标准信息分页
  getInspectStdInfoPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-std-info/page`, params })
  },

  // 查询巡查巡检标准信息详情
  getInspectStdInfo: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-std-info/get?id=` + id })
  },

  // 新增巡查巡检标准信息
  createInspectStdInfo: async (data: InspectStdInfoVO) => {
    return await request.post({ url: `/datacenter/inspect-std-info/create`, data })
  },

  // 修改巡查巡检标准信息
  updateInspectStdInfo: async (data: InspectStdInfoVO) => {
    return await request.put({ url: `/datacenter/inspect-std-info/update`, data })
  },

  // 删除巡查巡检标准信息
  deleteInspectStdInfo: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-std-info/delete?id=` + id })
  },

  // 导出巡查巡检标准信息 Excel
  exportInspectStdInfo: async (params) => {
    return await request.download({ url: `/datacenter/inspect-std-info/export-excel`, params })
  }
}
