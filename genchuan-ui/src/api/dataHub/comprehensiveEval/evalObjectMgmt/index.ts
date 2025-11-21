import request from '@/config/axios'

// 评价对象管理 VO
export interface EvalObjectVO {
  id: number // 主键ID
  evalObjectId: string // 评价对象ID
  evalObjectName: string // 评价对象名称
  objectCode: string // 对象编码
  regionCode: string // 所属行政区划代码
  regionName: string // 所属行政区划名称
  gridId: string // 关联网格ID
  gridName: string // 关联网格名称
  deptId: string // 关联部门ID
  deptName: string // 关联部门名称
  leaderId: string // 负责人ID
  leaderName: string // 负责人姓名
  leaderContact: string // 负责人联系方式
  objectStatus: string // 对象状态
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  updateUserBiz: string // 更新人(业务)
  updateTimeBiz: Date // 更新时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 评价对象管理 API
export const EvalObjectApi = {
  // 查询评价对象管理分页
  getEvalObjectPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-object/page`, params })
  },

  // 查询评价对象管理详情
  getEvalObject: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-object/get?id=` + id })
  },

  // 新增评价对象管理
  createEvalObject: async (data: EvalObjectVO) => {
    return await request.post({ url: `/datacenter/eval-object/create`, data })
  },

  // 修改评价对象管理
  updateEvalObject: async (data: EvalObjectVO) => {
    return await request.put({ url: `/datacenter/eval-object/update`, data })
  },

  // 删除评价对象管理
  deleteEvalObject: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-object/delete?id=` + id })
  },

  // 导出评价对象管理 Excel
  exportEvalObject: async (params) => {
    return await request.download({ url: `/datacenter/eval-object/export-excel`, params })
  }
}
