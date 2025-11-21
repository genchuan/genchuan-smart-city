import request from '@/config/axios'

// 经验库 VO
export interface ExpLibVO {
  id: number // 主键ID
  expId: string // 经验ID
  expNo: string // 经验编号
  expTitle: string // 经验标题
  evtTypeId: string // 关联事件类型ID
  evtTypeName: string // 关联事件类型名称
  expType: string // 经验类型
  expContent: string // 经验内容
  evalId: string // 关联评估ID
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 经验库 API
export const ExpLibApi = {
  // 查询经验库分页
  getExpLibPage: async (params: any) => {
    return await request.get({ url: `/datacenter/exp-lib/page`, params })
  },

  // 查询经验库详情
  getExpLib: async (id: number) => {
    return await request.get({ url: `/datacenter/exp-lib/get?id=` + id })
  },

  // 新增经验库
  createExpLib: async (data: ExpLibVO) => {
    return await request.post({ url: `/datacenter/exp-lib/create`, data })
  },

  // 修改经验库
  updateExpLib: async (data: ExpLibVO) => {
    return await request.put({ url: `/datacenter/exp-lib/update`, data })
  },

  // 删除经验库
  deleteExpLib: async (id: number) => {
    return await request.delete({ url: `/datacenter/exp-lib/delete?id=` + id })
  },

  // 导出经验库 Excel
  exportExpLib: async (params) => {
    return await request.download({ url: `/datacenter/exp-lib/export-excel`, params })
  },
}