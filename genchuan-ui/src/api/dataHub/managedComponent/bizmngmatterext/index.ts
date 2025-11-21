import request from '@/config/axios'

// 管理事项扩展管理事项配置 VO
export interface BizMngMatterExtVO {
  id: number // 主键ID
  mngMatterExtId: string // 扩展ID
  matterMajorId: string // 归属大类ID
  matterMajorName: string // 归属大类名称
  extMinorCode: string // 扩展小类代码
  extMinorName: string // 扩展小类名称
  extMinorDesc: string // 扩展小类说明
  suggestDeptCode: string // 建议主管部门代码
  suggestDeptName: string // 建议主管部门名称
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 管理事项扩展管理事项配置 API
export const BizMngMatterExtApi = {
  // 查询管理事项扩展管理事项配置分页
  getBizMngMatterExtPage: async (params: any) => {
    return await request.get({ url: `/datacenter/biz-mng-matter-ext/page`, params })
  },

  // 查询管理事项扩展管理事项配置详情
  getBizMngMatterExt: async (id: number) => {
    return await request.get({ url: `/datacenter/biz-mng-matter-ext/get?id=` + id })
  },

  // 新增管理事项扩展管理事项配置
  createBizMngMatterExt: async (data: BizMngMatterExtVO) => {
    return await request.post({ url: `/datacenter/biz-mng-matter-ext/create`, data })
  },

  // 修改管理事项扩展管理事项配置
  updateBizMngMatterExt: async (data: BizMngMatterExtVO) => {
    return await request.put({ url: `/datacenter/biz-mng-matter-ext/update`, data })
  },

  // 删除管理事项扩展管理事项配置
  deleteBizMngMatterExt: async (id: number) => {
    return await request.delete({ url: `/datacenter/biz-mng-matter-ext/delete?id=` + id })
  },

  // 导出管理事项扩展管理事项配置 Excel
  exportBizMngMatterExt: async (params) => {
    return await request.download({ url: `/datacenter/biz-mng-matter-ext/export-excel`, params })
  },
}