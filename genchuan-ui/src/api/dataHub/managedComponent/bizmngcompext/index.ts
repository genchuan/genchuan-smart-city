import request from '@/config/axios'

// 管理部件扩展管理部件配置 VO
export interface BizMngCompExtVO {
  id: number // 主键ID
  mngCompExtId: string // 扩展ID
  majorId: string // 归属大类ID
  majorName: string // 归属大类名称
  extMinorCode: string // 扩展小类代码
  extMinorName: string // 扩展小类名称
  extMinorDesc: string // 扩展小类说明
  suggestDeptCode: string // 建议主管部门代码
  suggestDeptName: string // 建议主管部门名称
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 管理部件扩展管理部件配置 API
export const BizMngCompExtApi = {
  // 查询管理部件扩展管理部件配置分页
  getBizMngCompExtPage: async (params: any) => {
    return await request.get({ url: `/datacenter/biz-mng-comp-ext/page`, params })
  },

  // 查询管理部件扩展管理部件配置详情
  getBizMngCompExt: async (id: number) => {
    return await request.get({ url: `/datacenter/biz-mng-comp-ext/get?id=` + id })
  },

  // 新增管理部件扩展管理部件配置
  createBizMngCompExt: async (data: BizMngCompExtVO) => {
    return await request.post({ url: `/datacenter/biz-mng-comp-ext/create`, data })
  },

  // 修改管理部件扩展管理部件配置
  updateBizMngCompExt: async (data: BizMngCompExtVO) => {
    return await request.put({ url: `/datacenter/biz-mng-comp-ext/update`, data })
  },

  // 删除管理部件扩展管理部件配置
  deleteBizMngCompExt: async (id: number) => {
    return await request.delete({ url: `/datacenter/biz-mng-comp-ext/delete?id=` + id })
  },

  // 导出管理部件扩展管理部件配置 Excel
  exportBizMngCompExt: async (params) => {
    return await request.download({ url: `/datacenter/biz-mng-comp-ext/export-excel`, params })
  }
}