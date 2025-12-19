import request from '@/config/axios'

// 管理部件小类配置 VO
export interface ManagedComponentMinorConfigVO {
  id: number // 主键ID
  minorId: string // 小类ID，唯一编码，采用UUID
  majorId: string // 关联的大类ID
  minorCode: string // 小类代码，001-999
  minorName: string // 小类名称，如上水井盖/污水井盖等
  minorDesc: string // 小类说明，描述具体定义及特征
  deptCode: string // 主管部门统一社会信用代码
  deptName: string // 主管部门全称
  isExtend: string // 是否扩展类：0-标准类/1-扩展类
  createUser: string // 创建人ID
  updateUser: string // 更新人ID
  extField1: string // 扩展字段1
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 管理部件小类配置 API
export const ManagedComponentMinorConfigApi = {
  // 查询管理部件小类配置分页
  getManagedComponentMinorConfigPage: async (params: any) => {
    return await request.get({ url: `/datacenter/managed-component-minor-config/page`, params })
  },

  // 查询管理部件小类配置详情
  getManagedComponentMinorConfig: async (id: number) => {
    return await request.get({ url: `/datacenter/managed-component-minor-config/get?id=` + id })
  },

  // 新增管理部件小类配置
  createManagedComponentMinorConfig: async (data: ManagedComponentMinorConfigVO) => {
    return await request.post({ url: `/datacenter/managed-component-minor-config/create`, data })
  },

  // 修改管理部件小类配置
  updateManagedComponentMinorConfig: async (data: ManagedComponentMinorConfigVO) => {
    return await request.put({ url: `/datacenter/managed-component-minor-config/update`, data })
  },

  // 删除管理部件小类配置
  deleteManagedComponentMinorConfig: async (id: number) => {
    return await request.delete({ url: `/datacenter/managed-component-minor-config/delete?id=` + id })
  },

  // 导出管理部件小类配置 Excel
  exportManagedComponentMinorConfig: async (params) => {
    return await request.download({ url: `/datacenter/managed-component-minor-config/export-excel`, params })
  }
}