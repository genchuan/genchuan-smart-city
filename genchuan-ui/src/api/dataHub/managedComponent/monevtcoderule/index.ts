import request from '@/config/axios'

// 监测事件标识码规则 VO
export interface MonEvtCodeRuleVO {
  id: number // 主键ID
  monEvtRuleId: string // 规则ID
  ruleName: string // 规则名称
  adminCodeLen: number // 行政代码位数
  majorCodeLen: number // 大类代码位数
  midCodeLen: number // 中类代码位数
  minorCodeLen: number // 小类代码位数
  seqCodeLen: number // 顺序码位数
  seqGenRule: string // 顺序码生成规则
  enableStatus: string // 启用状态：1（启用）/0（禁用）
  createUser: string // 创建人
  updateUser: string // 更新人
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 监测事件标识码规则 API
export const MonEvtCodeRuleApi = {
  // 查询监测事件标识码规则分页
  getMonEvtCodeRulePage: async (params: any) => {
    return await request.get({ url: `/datacenter/mon-evt-code-rule/page`, params })
  },

  // 查询监测事件标识码规则详情
  getMonEvtCodeRule: async (id: number) => {
    return await request.get({ url: `/datacenter/mon-evt-code-rule/get?id=` + id })
  },

  // 新增监测事件标识码规则
  createMonEvtCodeRule: async (data: MonEvtCodeRuleVO) => {
    return await request.post({ url: `/datacenter/mon-evt-code-rule/create`, data })
  },

  // 修改监测事件标识码规则
  updateMonEvtCodeRule: async (data: MonEvtCodeRuleVO) => {
    return await request.put({ url: `/datacenter/mon-evt-code-rule/update`, data })
  },

  // 删除监测事件标识码规则
  deleteMonEvtCodeRule: async (id: number) => {
    return await request.delete({ url: `/datacenter/mon-evt-code-rule/delete?id=` + id })
  },

  // 导出监测事件标识码规则 Excel
  exportMonEvtCodeRule: async (params) => {
    return await request.download({ url: `/datacenter/mon-evt-code-rule/export-excel`, params })
  },
}