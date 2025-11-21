import request from '@/config/axios'

// 巡查巡检人员信息 VO
export interface InspectPersonInfoVO {
  id: number // 主键
  personId: string // 人员ID
  personName: string // 人员姓名
  personAccount: string // 人员账号
  deptCode: string // 所属部门代码
  deptName: string // 所属部门名称
  personPhone: string // 联系电话
  gridId: string // 负责网格ID
  gridName: string // 负责网格名称
  personRole: string // 人员角色
  deviceCode: string // 关联设备编码
  personStatus: boolean // 人员状态
  entryDate: Date // 入职日期
  createUser: string // 创建人
  bizCreateTime: Date // 创建时间（业务）
  updateUser: string // 更新人
  bizUpdateTime: Date // 更新时间（业务）
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检人员信息 API
export const InspectPersonInfoApi = {
  // 查询巡查巡检人员信息分页
  getInspectPersonInfoPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-person-info/page`, params })
  },

  // 查询巡查巡检人员信息详情
  getInspectPersonInfo: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-person-info/get?id=` + id })
  },

  // 新增巡查巡检人员信息
  createInspectPersonInfo: async (data: InspectPersonInfoVO) => {
    return await request.post({ url: `/datacenter/inspect-person-info/create`, data })
  },

  // 修改巡查巡检人员信息
  updateInspectPersonInfo: async (data: InspectPersonInfoVO) => {
    return await request.put({ url: `/datacenter/inspect-person-info/update`, data })
  },

  // 删除巡查巡检人员信息
  deleteInspectPersonInfo: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-person-info/delete?id=` + id })
  },

  // 导出巡查巡检人员信息 Excel
  exportInspectPersonInfo: async (params) => {
    return await request.download({ url: `/datacenter/inspect-person-info/export-excel`, params })
  }
}
