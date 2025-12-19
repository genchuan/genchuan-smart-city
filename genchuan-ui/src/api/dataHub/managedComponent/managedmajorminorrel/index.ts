import request from '@/config/axios'

// 管理部件大类小类关联 VO
export interface ManagedMajorMinorRelVO {
  id: number // 主键ID
  relId: string // 关联ID，唯一编码，采用UUID
  majorId: string // 关联的大类ID
  minorId: string // 关联的小类ID
  relStatus: string // 关联状态：0-无效/1-有效
  relTime: Date // 关联时间，格式：yyyy-MM-dd HH:mm:ss
  unrelTime: Date // 解除关联时间，格式：yyyy-MM-dd HH:mm:ss
  operateUser: string // 操作人ID
  remark: string // 备注信息
  extField1: string // 扩展字段1
  extField2: string // 扩展字段2
  extField3: string // 扩展字段3
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 管理部件大类小类关联 API
export const ManagedMajorMinorRelApi = {
  // 查询管理部件大类小类关联分页
  getManagedMajorMinorRelPage: async (params: any) => {
    return await request.get({ url: `/datacenter/managed-major-minor-rel/page`, params })
  },

  // 查询管理部件大类小类关联详情
  getManagedMajorMinorRel: async (id: number) => {
    return await request.get({ url: `/datacenter/managed-major-minor-rel/get?id=` + id })
  },

  // 新增管理部件大类小类关联
  createManagedMajorMinorRel: async (data: ManagedMajorMinorRelVO) => {
    return await request.post({ url: `/datacenter/managed-major-minor-rel/create`, data })
  },

  // 修改管理部件大类小类关联
  updateManagedMajorMinorRel: async (data: ManagedMajorMinorRelVO) => {
    return await request.put({ url: `/datacenter/managed-major-minor-rel/update`, data })
  },

  // 删除管理部件大类小类关联
  deleteManagedMajorMinorRel: async (id: number) => {
    return await request.delete({ url: `/datacenter/managed-major-minor-rel/delete?id=` + id })
  },

  // 导出管理部件大类小类关联 Excel
  exportManagedMajorMinorRel: async (params) => {
    return await request.download({ url: `/datacenter/managed-major-minor-rel/export-excel`, params })
  }
}