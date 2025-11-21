import request from '@/config/axios'


// 管理网格划分 VO
export interface MngGridDivVO {
  id: number // 主键ID
  mngGridId: string // 管理网格ID
  mngGridName: string // 管理网格名称
  townStreetId: string // 所属乡镇ID
  includedUnitIds: string // 所含单元网格IDs
  area: number // 面积(m²)
  divTime: Date // 划分时间
  updateTime: Date // 更新时间
  remark: string // 备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1 放网格员staffId
  extCommon2: string // 通用扩展字段2 放网格员名字
  updateTimeSys: Date // 系统更新时间
}

// 管理网格划分 API
export const MngGridDivApi = {
  //  查询管理网格划分分页
  getMngGridDivPage: async (params: any) => {
    return await request.get({ url: `/datacenter/mng-grid-div/page`, params })
  },

  // 查询管理网格划分详情
  getMngGridDiv: async (id: number) => {
    return await request.get({ url: `/datacenter/mng-grid-div/get?id=` + id })
  },

  // 新增管理网格划分
  createMngGridDiv: async (data: MngGridDivVO) => {
    return await request.post({ url: `/datacenter/mng-grid-div/create`, data })
  },

  // 修改管理网格划分
  updateMngGridDiv: async (data: MngGridDivVO) => {
    return await request.put({ url: `/datacenter/mng-grid-div/update`, data })
  },

  // 删除管理网格划分
  deleteMngGridDiv: async (id: number) => {
    return await request.delete({ url: `/datacenter/mng-grid-div/delete?id=` + id })
  },

  // 导出管理网格划分 Excel
  exportMngGridDiv: async (params) => {
    return await request.download({ url: `/datacenter/mng-grid-div/export-excel`, params })
  },

  /** 根据单元网格IDs计算面积 */
  calculateArea: async (unitGridIds: string) => {
    return await request.post({
      url: `/datacenter/mng-grid-div/calculate-area`,
      params: { unitGridIds }
    })
  },

  /** 批量导入单元网格创建管理网格 */
  importUnitGrids: async (data: any) => {
    return await request.post({
      url: `/datacenter/mng-grid-div/import-unit-grids`,
      data
    })
  },

  /** 获取某乡镇下所有管理网格 */
  listByTown: async (townStreetId: string) => {
    return await request.get({
      url: `/datacenter/mng-grid-div/list-by-town`,
      params: { townStreetId }
    })
  },

  /** 按所含单元网格数量范围筛选 */
  listByUnitCount: async (params: { townStreetId: string; minUnits?: number; maxUnits?: number }) => {
    return await request.get({
      url: `/datacenter/mng-grid-div/list-by-unit-count`,
      params
    })
  },

  /** 更新管理网格的网格员 */
  updateStaff: async (id: number, staffId: string) => {
    return await request.post({
      url: `/datacenter/mng-grid-div/update-staff`,
      params: { id, staffId }
    })
  },

  /** 校验单元网格是否可以集成（相邻、跨社区检测） */
  validateUnitGrids: async (data: { unitGridIds: string[]; townStreetId: string; excludeMngGridId?: number }) => {
    return await request.post({
      url: `/datacenter/mng-grid-div/validate-unit-grids`,
      data
    })
  },

    //获取网格员下拉列表
    getGridPersons: async()=> {
    return await request.get({ url: `/system/user/list-all-simple`})
  },



  /** 批量查询管理网格信息 */
  getMngGridByIds: async(ids: string[]) => {
  return request.get
  ({
    url: '/data-hub/mng-grid-div/batch'
    ,
    params: { ids: ids.join(','
      ) }
  })
  },




}
