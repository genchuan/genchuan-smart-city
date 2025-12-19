import request from '@/config/axios'

// 应用场景分类配置 VO
export interface AppSceneCategoryVO {
  id: number // 主键ID
  sceneCatCode: string // 应用场景分类代码
  sceneCatName: string // 应用场景分类名称
  catLevel: string // 分类层级：1-大类/2-中类/3-小类
  parentCatId: string // 父级分类ID
  parentCatName: string // 父级分类名称
  sceneCatDesc: string // 应用场景分类说明
  isExt: string // 是否扩展类：0-标准类/1-扩展类
  menuIds: string // 关联的菜单编号
  enableStatus: string // 启用状态：1-启用/0-禁用
  relCompCatId: string // 关联监测部件类型ID
  relCompCatName: string // 关联监测部件类型名称
  relEvtCatId: string // 关联监测事件类型ID
  relEvtCatName: string // 关联监测事件类型名称
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  updateUserBiz: string // 更新人(业务)
  updateTimeBiz: Date // 更新时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 应用场景分类配置 API
export const AppSceneCategoryApi = {
  // 查询应用场景分类配置分页
  getAppSceneCategoryPage: async (params: any) => {
    return await request.get({ url: `/datacenter/app-scene-category/page`, params })
  },

  // 查询应用场景分类配置详情
  getAppSceneCategory: async (id: number) => {
    return await request.get({ url: `/datacenter/app-scene-category/get?id=` + id })
  },

  // 新增应用场景分类配置
  createAppSceneCategory: async (data: AppSceneCategoryVO) => {
    return await request.post({ url: `/datacenter/app-scene-category/create`, data })
  },

  // 修改应用场景分类配置
  updateAppSceneCategory: async (data: AppSceneCategoryVO) => {
    return await request.put({ url: `/datacenter/app-scene-category/update`, data })
  },

  // 删除应用场景分类配置
  deleteAppSceneCategory: async (id: number) => {
    return await request.delete({ url: `/datacenter/app-scene-category/delete?id=` + id })
  },

  // 导出应用场景分类配置 Excel
  exportAppSceneCategory: async (params) => {
    return await request.download({ url: `/datacenter/app-scene-category/export-excel`, params })
  },

  //获取树形的应用场景列表
  getAppSceneCategoryTree: async () => {
    return await request.get({ url: `/datacenter/app-scene-category/tree` })
  },

  //从菜单管理剥离应用场景admin-api/system/menu/tree
  getAppSceneCategoryTreesByMenu: async () => {
    return await request.get({ url: `/system/menu/tree` })
  }
}
