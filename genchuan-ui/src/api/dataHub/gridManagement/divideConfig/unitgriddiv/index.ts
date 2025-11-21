import request from '@/config/axios'

// 单元网格划分 VO
export interface UnitGridDivVO {
  id: number
  unitGridId: string
  unitGridName: string
  commId: string
  area: number
  scale: string
  boundaryCoords: string
  divTime: Date
  updateTime: Date
  remark: string
  extCat1: string
  extCat2: string
  extCommon1: string
  extCommon2: string
  updateTimeSys: Date
}

// 单元网格划分 API
export const UnitGridDivApi = {
  getUnitGridDivPage: async (params: any) =>
    await request.get({ url: `/datacenter/unit-grid-div/page`, params }),

  getUnitGridDiv: async (id: number) =>
    await request.get({ url: `/datacenter/unit-grid-div/get?id=${id}` }),

  createUnitGridDiv: async (data: UnitGridDivVO) =>
    await request.post({ url: `/datacenter/unit-grid-div/create`, data }),

  updateUnitGridDiv: async (data: UnitGridDivVO) =>
    await request.put({ url: `/datacenter/unit-grid-div/update`, data }),

  deleteUnitGridDiv: async (id: number) =>
    await request.delete({ url: `/datacenter/unit-grid-div/delete?id=${id}` }),

  exportUnitGridDiv: async (params) =>
    await request.download({ url: `/datacenter/unit-grid-div/export-excel`, params }),

  /** 检查边界重叠 */
  checkOverlap: async (params: { boundaryCoords: string; excludeId?: number }) =>
    await request.get({
      url: '/datacenter/unit-grid-div/check-overlap',
      params
    }),

  /** 导入坐标批量创建网格 */
  importCoordinates: async (data: {
    unitGridName: string
    commId: string
    scale: string
    boundaryCoords: string
    remark?: string
  }) =>
    await request.post({
      url: '/datacenter/unit-grid-div/import-coordinates',
      data
    }),

  /** 按社区查询网格列表 */
  listByComm: async (commId: string) =>
    await request.get({
      url: `/datacenter/unit-grid-div/list-by-comm?commId=${commId}`
    }),

  /** 面积校验 */
  validateArea: async (params: { area: number; commId: string }) =>
    await request.post({
      url: '/datacenter/unit-grid-div/validate-area',
      params
    }),

  /** 边界合法性校验 */
  validateBoundary: async (data: {
    boundaryCoords: string
    commId: string
    excludeId?: number
  }) =>
    await request.post({
      url: '/datacenter/unit-grid-div/validate-boundary',
      data
    })
}
