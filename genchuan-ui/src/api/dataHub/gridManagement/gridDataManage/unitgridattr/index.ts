import request from '@/config/axios'

// 单元网格属性 VO
export interface UnitGridAttrVO {
  id: number // 主键ID
  unitAttrId: string // 属性ID
  unitGridId: string // 单元网格ID
  unitGridCode: string // 单元网格编码
  area: number // 面积(m²)
  initTime: Date // 初始时间
  endTime: Date // 终止时间
  remark: string // 备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 单元网格属性 API
export const UnitGridAttrApi = {
  // 查询单元网格属性分页
  getUnitGridAttrPage: async (params: any) => {
    return await request.get({ url: `/datacenter/unit-grid-attr/page`, params })
  },

  // 查询单元网格属性详情
  getUnitGridAttr: async (id: number) => {
    return await request.get({ url: `/datacenter/unit-grid-attr/get?id=` + id })
  },

  // 新增单元网格属性
  createUnitGridAttr: async (data: UnitGridAttrVO) => {
    return await request.post({ url: `/datacenter/unit-grid-attr/create`, data })
  },

  // 修改单元网格属性
  updateUnitGridAttr: async (data: UnitGridAttrVO) => {
    return await request.put({ url: `/datacenter/unit-grid-attr/update`, data })
  },

  // 删除单元网格属性
  deleteUnitGridAttr: async (id: number) => {
    return await request.delete({ url: `/datacenter/unit-grid-attr/delete?id=` + id })
  },

  // 导出单元网格属性 Excel
  exportUnitGridAttr: async (params) => {
    return await request.download({ url: `/datacenter/unit-grid-attr/export-excel`, params })
  },
}
