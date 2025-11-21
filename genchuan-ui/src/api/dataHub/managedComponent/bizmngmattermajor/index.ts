import request from '@/config/axios'

// 管理事项大类 VO
export interface BizMngMatterMajorVO {
  id: number // 主键ID
  mngMatterMajorId: string // 管理事项大类ID
  matterMajorCode: string // 大类代码
  matterMajorName: string // 大类名称
  matterMajorDesc: string // 大类说明
  sortNum: number // 排序序号
  enableStatus: string // 启用状态
  createUser: string // 创建人
  updateUser: string // 更新人
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 管理事项大类 API
export const BizMngMatterMajorApi = {
  // 查询管理事项大类分页
  getBizMngMatterMajorPage: async (params: any) => {
    return await request.get({ url: `/datacenter/biz-mng-matter-major/page`, params })
  },

  // 查询管理事项大类详情
  getBizMngMatterMajor: async (id: number) => {
    return await request.get({ url: `/datacenter/biz-mng-matter-major/get?id=` + id })
  },

  // 新增管理事项大类
  createBizMngMatterMajor: async (data: BizMngMatterMajorVO) => {
    return await request.post({ url: `/datacenter/biz-mng-matter-major/create`, data })
  },

  // 修改管理事项大类
  updateBizMngMatterMajor: async (data: BizMngMatterMajorVO) => {
    return await request.put({ url: `/datacenter/biz-mng-matter-major/update`, data })
  },

  // 删除管理事项大类
  deleteBizMngMatterMajor: async (id: number) => {
    return await request.delete({ url: `/datacenter/biz-mng-matter-major/delete?id=` + id })
  },

  // 导出管理事项大类 Excel
  exportBizMngMatterMajor: async (params) => {
    return await request.download({ url: `/datacenter/biz-mng-matter-major/export-excel`, params })
  },
}
