import request from '@/config/axios'

// 管理事项信息 VO
export interface BizMngMatterVO {
  id: number // 主键ID
  mngMatterId: string // 事项ID，唯一编码，UUID生成
  matterCode: string // 事项标识码，格式6位行政码+2位大类码+3位小类码+5位顺序码，唯一
  matterName: string // 事项名称，关联小类名称+位置，如“XX路私搭乱建”，关联管理事项小类表(gc_biz_mng_matter_minor)
  minorId: string // 关联管理事项小类ID，关联管理事项小类表(gc_biz_mng_matter_minor)
  minorName: string // 关联管理事项小类名称，与小类ID同步，不可修改，关联管理事项小类表(gc_biz_mng_matter_minor)
  gridId: string // 关联单元网格ID，关联网格信息表(biz_grid_info)
  gridName: string // 关联单元网格名称，与网格ID同步，关联网格信息表(biz_grid_info)
  matterStatus: string // 事项状态，如待处置/处置中/已办结/已驳回，关联问题状态字典表(sys_dict_problem_status)
  matterLevel: string // 事项等级，如一级/二级/三级，按影响范围判定
  deptCode: string // 主管部门代码，关联主管部门信用代码，关联部门信息表(sys_org)
  deptName: string // 主管部门名称，与部门代码同步，关联部门信息表(sys_org)
  incidentLocation: string // 事发位置，详细位置描述，如“XX路与XX路交叉口东北侧”
  createUser: string // 录入人账号，关联用户信息表(sys_user)
  updateUser: string // 修改人账号，关联用户信息表(sys_user)
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 管理事项信息 API
export const BizMngMatterApi = {
  // 查询管理事项信息分页
  getBizMngMatterPage: async (params: any) => {
    return await request.get({ url: `/datacenter/biz-mng-matter/page`, params })
  },

  // 查询管理事项信息详情
  getBizMngMatter: async (id: number) => {
    return await request.get({ url: `/datacenter/biz-mng-matter/get?id=` + id })
  },

  // 新增管理事项信息
  createBizMngMatter: async (data: BizMngMatterVO) => {
    return await request.post({ url: `/datacenter/biz-mng-matter/create`, data })
  },

  // 修改管理事项信息
  updateBizMngMatter: async (data: BizMngMatterVO) => {
    return await request.put({ url: `/datacenter/biz-mng-matter/update`, data })
  },

  // 删除管理事项信息
  deleteBizMngMatter: async (id: number) => {
    return await request.delete({ url: `/datacenter/biz-mng-matter/delete?id=` + id })
  },

  // 导出管理事项信息 Excel
  exportBizMngMatter: async (params) => {
    return await request.download({ url: `/datacenter/biz-mng-matter/export-excel`, params })
  },
}