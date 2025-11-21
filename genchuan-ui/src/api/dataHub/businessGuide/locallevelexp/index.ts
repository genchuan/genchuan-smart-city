import request from '@/config/axios'

// 本级经验分享 VO
export interface LocalLevelExpVO {
  id: number // 主键ID
  localLevelExpId: string // 经验ID
  expTitle: string // 经验标题
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  deptCode: string // 关联部门代码
  deptName: string // 关联部门名称
  expContent: string // 经验详细内容
  implementationSteps: string // 经验实施步骤
  achievementData: string // 实施成果
  attachId: string // 附件ID
  attachPath: string // 附件存储路径
  publishStatus: string // 发布状态
  shareCount: number // 分享次数
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 本级经验分享 API
export const LocalLevelExpApi = {
  // 查询本级经验分享分页
  getLocalLevelExpPage: async (params: any) => {
    return await request.get({ url: `/datacenter/local-level-exp/page`, params })
  },

  // 查询本级经验分享详情
  getLocalLevelExp: async (id: number) => {
    return await request.get({ url: `/datacenter/local-level-exp/get?id=` + id })
  },

  // 新增本级经验分享
  createLocalLevelExp: async (data: LocalLevelExpVO) => {
    return await request.post({ url: `/datacenter/local-level-exp/create`, data })
  },

  // 修改本级经验分享
  updateLocalLevelExp: async (data: LocalLevelExpVO) => {
    return await request.put({ url: `/datacenter/local-level-exp/update`, data })
  },

  // 删除本级经验分享
  deleteLocalLevelExp: async (id: number) => {
    return await request.delete({ url: `/datacenter/local-level-exp/delete?id=` + id })
  },

  // 导出本级经验分享 Excel
  exportLocalLevelExp: async (params) => {
    return await request.download({ url: `/datacenter/local-level-exp/export-excel`, params })
  },
}