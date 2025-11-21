import request from '@/config/axios'

// 管理事项小类 VO
export interface BizMngMatterMinorVO {
  id: number // 主键ID
  mngMatterMinorId: string // 管理事项小类ID，唯一编码，UUID生成
  parentMajorId: string // 所属大类ID，关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)
  parentMajorName: string // 所属大类名称，与所属大类ID同步，不可改，关联管理事项大类表(gc_biz_mng_matter_major)
  matterMinorCode: string // 小类代码，3位字符，001 - 999，标准类001 - 079、扩展类080 - 999倒排，同一大类下唯一
  matterMinorName: string // 小类名称，如私搭乱建/垃圾乱堆乱放等，国标名，扩展类加“（自定义）”
  matterMinorDesc: string // 小类说明，描述小类定义，如“私搭乱建：未经审批搭建”
  deptCode: string // 主管部门代码，主管部门信用代码，关联部门信息表(sys_org)
  deptName: string // 主管部门名称，与主管部门代码同步，关联部门信息表(sys_org)
  isExt: string // 是否扩展类，0（标准类）/1（扩展类），按代码自动判断
  enableStatus: string // 启用状态，1（启用）/0（禁用），默认继承大类状态
  createUser: string // 创建人，配置人账号，关联用户信息表(sys_user)
  updateUser: string // 更新人，修改人账号，关联用户信息表(sys_user)
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 管理事项小类 API
export const BizMngMatterMinorApi = {
  // 查询管理事项小类分页
  getBizMngMatterMinorPage: async (params: any) => {
    return await request.get({ url: `/datacenter/biz-mng-matter-minor/page`, params })
  },

  // 查询管理事项小类详情
  getBizMngMatterMinor: async (id: number) => {
    return await request.get({ url: `/datacenter/biz-mng-matter-minor/get?id=` + id })
  },

  // 新增管理事项小类
  createBizMngMatterMinor: async (data: BizMngMatterMinorVO) => {
    return await request.post({ url: `/datacenter/biz-mng-matter-minor/create`, data })
  },

  // 修改管理事项小类
  updateBizMngMatterMinor: async (data: BizMngMatterMinorVO) => {
    return await request.put({ url: `/datacenter/biz-mng-matter-minor/update`, data })
  },

  // 删除管理事项小类
  deleteBizMngMatterMinor: async (id: number) => {
    return await request.delete({ url: `/datacenter/biz-mng-matter-minor/delete?id=` + id })
  },

  // 导出管理事项小类 Excel
  exportBizMngMatterMinor: async (params) => {
    return await request.download({ url: `/datacenter/biz-mng-matter-minor/export-excel`, params })
  },
}