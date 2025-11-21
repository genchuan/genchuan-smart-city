import request from '@/config/axios'

// 评价网格划分 VO
export interface EvalGridDivVO {
  id: number // 主键ID
  evalGridId: string // 评价网格ID
  evalGridName: string // 评价网格名称
  townStreetId: string // 所属乡镇ID
  gridType: string // 评价网格类型(A/B/C)
  includedMgIds: string // 所含管理网格IDs
  area: number // 面积(m²)
  divTime: Date // 划分时间
  updateTime: Date // 更新时间
  remark: string // 备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  updateTimeSys: Date // 系统更新时间
}

// 评价网格划分 API
export const EvalGridDivApi = {
  // 查询评价网格划分分页
  getEvalGridDivPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-grid-div/page`, params })
  },

  //page-with-boundary
  // 查询评价网格划分分页
  getListWithBoundary: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-grid-div/page-with-boundary`, params })
  },

  // 查询评价网格划分
  getEvalGridDiv: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-grid-div/get?id=` + id })
  },

  // 新增评价网格划分
  createEvalGridDiv: async (data: EvalGridDivVO) => {
    return await request.post({ url: `/datacenter/eval-grid-div/create`, data })
  },

  // 修改评价网格划分
  updateEvalGridDiv: async (data: EvalGridDivVO) => {
    return await request.put({ url: `/datacenter/eval-grid-div/update`, data })
  },

  // 删除评价网格划分
  deleteEvalGridDiv: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-grid-div/delete?id=` + id })
  },

  // 导出评价网格划分 Excel
  exportEvalGridDiv: async (params) => {
    return await request.download({ url: `/datacenter/eval-grid-div/export-excel`, params })
  },

  // 计算评价网格面积
  calculateEvalGridArea: async (mngGridIds: string[]) => {
    const params = new URLSearchParams()
    mngGridIds.forEach(id => params.append('mngGridIds', id))
    return await request.post({
      url: `/datacenter/eval-grid-div/calculate-area`,
      data: params,
    })
  },

// 获取评价网格类型分布
  getEvalGridDistribution: async () => {
    return await request.get({
      url: `/datacenter/eval-grid-div/distribution`
    })
  },

// 获取评价网格详情
  getEvalGridDetail: async (id: number) => {
    return await request.get({
      url: `/datacenter/eval-grid-div/detail/${id}`
    })
  },

// 批量导入管理网格创建评价网格
  importMngGridsToEvalGrid: async (data: {
    evalGridName: string
    townStreetId: string
    gridType: string
    mngGridIds: string[]
    remark?: string
  }) => {
    return await request.post({
      url: `/datacenter/eval-grid-div/import-mng-grids`,
      data
    })
  },

// 校验评价网格

  /** 校验评价网格 */
  validateEvalGrid: async (data: { townStreetId: string;mngGridIds: string[] }) => {
    return await request.post({
      url: `/datacenter/eval-grid-div/validate`,
      data

    })
  },
  // headers: { 'Content-Type': 'application/x-www-form-urlencoded' }




  // 按所含管理网格数量筛选接口封装
  listByManaCount: async (params: { townStreetId: string; minUnits?: number; maxUnits?: number }) => {
    return await request.get({
      url: `/datacenter/eval-grid-div/list-by-mng-grid-count`,
      params
    })
  },

}
