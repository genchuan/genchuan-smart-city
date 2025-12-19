import request from '@/config/axios'

// 管理部件大类配置表 VO
export interface ManagedComponentMajorConfigVO {
  id: number // 主键ID
  majorId: string // 大类ID，唯一编码，采用UUID
  majorCode: string // 大类代码，01-99
  majorName: string // 大类名称，如市政公用设施/交通设施等
  majorDesc: string // 大类说明，描述包含的管理部件范围
  sortNum: number // 排序序号，1-999
  createUser: string // 创建人ID
  updateUser: string // 更新人ID
  extField1: string // 扩展字段1
  extField2: string // 扩展字段2
  extField3: string // 扩展字段3
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 管理部件大类配置表 API
export const ManagedComponentMajorConfigApi = {
  // 查询管理部件大类配置表分页
  getManagedComponentMajorConfigPage: async (params: any) => {
    return await request.get({ url: `/datacenter/managed-component-major-config/page`, params })
  },

  // 查询管理部件大类配置表详情
  getManagedComponentMajorConfig: async (id: number) => {
    return await request.get({ url: `/datacenter/managed-component-major-config/get?id=` + id })
  },

  // 新增管理部件大类配置表
  createManagedComponentMajorConfig: async (data: ManagedComponentMajorConfigVO) => {
    return await request.post({ url: `/datacenter/managed-component-major-config/create`, data })
  },

  // 修改管理部件大类配置表
  updateManagedComponentMajorConfig: async (data: ManagedComponentMajorConfigVO) => {
    return await request.put({ url: `/datacenter/managed-component-major-config/update`, data })
  },

  // 删除管理部件大类配置表
  deleteManagedComponentMajorConfig: async (id: number) => {
    return await request.delete({ url: `/datacenter/managed-component-major-config/delete?id=` + id })
  },

  // 导出管理部件大类配置表 Excel
  exportManagedComponentMajorConfig: async (params) => {
    return await request.download({ url: `/datacenter/managed-component-major-config/export-excel`, params })
  }
}
