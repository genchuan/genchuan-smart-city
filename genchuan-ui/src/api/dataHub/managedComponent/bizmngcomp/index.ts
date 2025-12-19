import request from '@/config/axios'

// 管理部件信息管理 VO
export interface BizMngCompVO {
  id: number // 主键ID
  mngCompId: string // 部件ID，唯一编码，UUID生成
  compCode: string // 部件标识码，格式6位行政码+2位大类码+3位小类码+5位顺序码，唯一
  compName: string // 部件名称，关联小类名称+位置，如“XX路电力井盖”，关联管理部件小类表(gc_biz_mng_comp_minor)
  minorId: string // 关联管理部件小类ID，关联管理部件小类表(gc_biz_mng_comp_minor)
  minorName: string // 关联管理部件小类名称，与小类ID同步，不可改，关联管理部件小类表(gc_biz_mng_comp_minor)
  deptCode: string // 主管部门代码，主管部门信用代码，关联部门信息表(sys_org)
  deptName: string // 主管部门名称，与部门代码同步，关联部门信息表(sys_org)
  gridId: string // 关联单元网格ID，关联网格信息表(biz_grid_info)
  gridName: string // 关联单元网格名称，与网格ID同步，关联网格信息表(biz_grid_info)
  compStatus: string // 部件状态，如完好/破损/丢失/废弃，关联部件状态字典表(sys_dict_mng_comp_state)
  initDate: Date // 部件普查日期，格式YYYYMMDD
  changeDate: Date // 状态/权属变更时更新日期，格式YYYYMMDD
  dataSource: string // 数据来源，如实测/人工上报/普查，关联数据来源字典表(sys_dict_data_source)
  createUser: string // 录入人账号，关联用户信息表(sys_user)
  updateUser: string // 修改人账号，关联用户信息表(sys_user)
  extCat1: string // 分类扩展字段1，预留，如“部件材质”
  extCat2: string // 分类扩展字段2，预留，如“部件材质”
  extCommon1: string // 通用扩展字段1，预留，如“安装时间”
  extCommon2: string // 通用扩展字段2，预留，如“安装时间”
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 管理部件信息管理 API
export const BizMngCompApi = {
  // 查询管理部件信息管理分页
  getBizMngCompPage: async (params: any) => {
    return await request.get({ url: `/datacenter/biz-mng-comp/page`, params })
  },

  // 查询管理部件信息管理详情
  getBizMngComp: async (id: number) => {
    return await request.get({ url: `/datacenter/biz-mng-comp/get?id=` + id })
  },

  // 新增管理部件信息管理
  createBizMngComp: async (data: BizMngCompVO) => {
    return await request.post({ url: `/datacenter/biz-mng-comp/create`, data })
  },

  // 修改管理部件信息管理
  updateBizMngComp: async (data: BizMngCompVO) => {
    return await request.put({ url: `/datacenter/biz-mng-comp/update`, data })
  },

  // 删除管理部件信息管理
  deleteBizMngComp: async (id: number) => {
    return await request.delete({ url: `/datacenter/biz-mng-comp/delete?id=` + id })
  },

  // 导出管理部件信息管理 Excel
  exportBizMngComp: async (params) => {
    return await request.download({ url: `/datacenter/biz-mng-comp/export-excel`, params })
  }
}