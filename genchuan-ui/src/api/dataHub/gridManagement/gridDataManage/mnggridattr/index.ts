import request from '@/config/axios'

// 管理网格属性 VO
export interface MngGridAttrVO {
  id: number // 主键ID
  mgAttrId: string // 属性ID
  mngGridId: string // 管理网格ID
  mgGridCode: string // 管理网格编码
  area: number // 面积(m²)
  initTime: Date // 初始时间
  endTime: Date // 终止时间
  unitGridList: string // 所含单元网格列表
  gridUserId: string // 网格员ID
  gridUserName: string // 网格员姓名
  remark: string // 备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 管理网格属性 API
export const MngGridAttrApi = {
  // 查询管理网格属性分页
  getMngGridAttrPage: async (params: any) => {
    return await request.get({ url: `/datacenter/mng-grid-attr/page`, params })
  },

  // 查询管理网格属性详情
  getMngGridAttr: async (id: number) => {
    return await request.get({ url: `/datacenter/mng-grid-attr/get?id=` + id })
  },

  // 新增管理网格属性
  createMngGridAttr: async (data: MngGridAttrVO) => {
    return await request.post({ url: `/datacenter/mng-grid-attr/create`, data })
  },

  // 修改管理网格属性
  updateMngGridAttr: async (data: MngGridAttrVO) => {
    return await request.put({ url: `/datacenter/mng-grid-attr/update`, data })
  },

  // 删除管理网格属性
  deleteMngGridAttr: async (id: number) => {
    return await request.delete({ url: `/datacenter/mng-grid-attr/delete?id=` + id })
  },

  // 导出管理网格属性 Excel
  exportMngGridAttr: async (params) => {
    return await request.download({ url: `/datacenter/mng-grid-attr/export-excel`, params })
  },
}
