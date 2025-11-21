import request from '@/config/axios'

// 部件分类 VO
export interface ComponentCategoryVO {
  id: number // 主键ID
  categoryId: string // 分类ID，UUID
  parentId: string // 父类ID，0表示大类
  level: string // 层级：1-大类/2-中类/3-小类
  code: string // 分类代码：01-99
  name: string // 分类名称
  description: string // 分类说明
  status: string // 状态：1-启用/0-禁用
  createUser: string // 创建人ID
  updateUser: string // 更新人ID
  ext1: string // 扩展字段1
  ext2: string // 扩展字段2
  ext3: string // 扩展字段3
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
  createTime: string; // 新增 createTime 属性
  //updateTime: string; // 新增 updateTime 属性
}

// 部件分类 API
export const ComponentCategoryApi = {
  // 查询部件分类分页
  getComponentCategoryPage: async (params: any) => {
    return await request.get({ url: `/gc/component-category/page`, params })
  },

  // 查询部件分类详情
  getComponentCategory: async (id: number) => {
    return await request.get({ url: `/gc/component-category/get?id=` + id })
  },

  // 新增部件分类
  createComponentCategory: async (data: ComponentCategoryVO) => {
    return await request.post({ url: `/gc/component-category/create`, data })
  },

  // 修改部件分类
  updateComponentCategory: async (data: ComponentCategoryVO) => {
    return await request.put({ url: `/gc/component-category/update`, data })
  },

  // 删除部件分类
  deleteComponentCategory: async (id: number) => {
    return await request.delete({ url: `/gc/component-category/delete?id=` + id })
  },

  // 导出部件分类 Excel
  exportComponentCategory: async (params) => {
    return await request.download({ url: `/gc/component-category/export-excel`, params })
  },
}
