import request from '@/config/axios'

// 自定义报表配置 VO
export interface CustomRptCfgVO {
  id: number // 主键ID
  customRptCfgId: string // 配置ID
  rptTemplateName: string // 报表模板名称
  rptType: string // 报表类型
  dataSourceTable: string // 数据来源表
  statDimensionCfg: string // 统计维度配置
  statIdxCfg: string // 统计指标配置
  displayStyleCfg: string // 展示样式配置
  filterCfg: string // 筛选条件配置
  templateStatus: string // 模板状态
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 自定义报表配置 API
export const CustomRptCfgApi = {
  // 查询自定义报表配置分页
  getCustomRptCfgPage: async (params: any) => {
    return await request.get({ url: `/datacenter/custom-rpt-cfg/page`, params })
  },

  // 查询自定义报表配置详情
  getCustomRptCfg: async (id: number) => {
    return await request.get({ url: `/datacenter/custom-rpt-cfg/get?id=` + id })
  },

  // 新增自定义报表配置
  createCustomRptCfg: async (data: CustomRptCfgVO) => {
    return await request.post({ url: `/datacenter/custom-rpt-cfg/create`, data })
  },

  // 修改自定义报表配置
  updateCustomRptCfg: async (data: CustomRptCfgVO) => {
    return await request.put({ url: `/datacenter/custom-rpt-cfg/update`, data })
  },

  // 删除自定义报表配置
  deleteCustomRptCfg: async (id: number) => {
    return await request.delete({ url: `/datacenter/custom-rpt-cfg/delete?id=` + id })
  },

  // 导出自定义报表配置 Excel
  exportCustomRptCfg: async (params) => {
    return await request.download({ url: `/datacenter/custom-rpt-cfg/export-excel`, params })
  },
}