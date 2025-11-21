import request from '@/config/axios'

// 管理事项大小类关联 VO
export interface RelMngMatterMajMinVO {
  id: number // 主键ID
  mngMatterMajorMinorId: string // 关联ID
  majorId: string // 关联管理事项大类ID
  majorName: string // 关联管理事项大类名称
  minorId: string // 关联管理事项小类ID
  minorName: string // 关联管理事项小类名称
  relStatus: string // 关联状态
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 管理事项大小类关联 API
export const RelMngMatterMajMinApi = {
  // 查询管理事项大小类关联分页
  getRelMngMatterMajMinPage: async (params: any) => {
    return await request.get({ url: `/datacenter/rel-mng-matter-maj-min/page`, params })
  },

  // 查询管理事项大小类关联详情
  getRelMngMatterMajMin: async (id: number) => {
    return await request.get({ url: `/datacenter/rel-mng-matter-maj-min/get?id=` + id })
  },

  // 新增管理事项大小类关联
  createRelMngMatterMajMin: async (data: RelMngMatterMajMinVO) => {
    return await request.post({ url: `/datacenter/rel-mng-matter-maj-min/create`, data })
  },

  // 修改管理事项大小类关联
  updateRelMngMatterMajMin: async (data: RelMngMatterMajMinVO) => {
    return await request.put({ url: `/datacenter/rel-mng-matter-maj-min/update`, data })
  },

  // 删除管理事项大小类关联
  deleteRelMngMatterMajMin: async (id: number) => {
    return await request.delete({ url: `/datacenter/rel-mng-matter-maj-min/delete?id=` + id })
  },

  // 导出管理事项大小类关联 Excel
  exportRelMngMatterMajMin: async (params) => {
    return await request.download({ url: `/datacenter/rel-mng-matter-maj-min/export-excel`, params })
  },
}
