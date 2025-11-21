import request from '@/config/axios'

// 典型场景案例库 VO
export interface TypicalScenarioCaseVO {
  id: number // 主键ID
  typicalCaseId: string // 案例ID
  caseName: string // 案例名称
  relSchemeId: string // 关联方案ID
  relEvalId: string // 关联评估ID
  caseTags: string // 案例标签
  applicableScenario: string // 适用场景
  coreMeasures: string // 核心措施
  resourceInput: string // 资源投入
  implementationEffect: string // 实施效果
  evalGrade: string // 评估等级
  successExp: string // 成功经验
  improveSuggs: string // 改进建议
  archUser: string // 归档人
  archTime: Date // 归档时间
  caseStatus: string // 案例状态
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 典型场景案例库 API
export const TypicalScenarioCaseApi = {
  // 查询典型场景案例库分页
  getTypicalScenarioCasePage: async (params: any) => {
    return await request.get({ url: `/datacenter/typical-scenario-case/page`, params })
  },

  // 查询典型场景案例库详情
  getTypicalScenarioCase: async (id: number) => {
    return await request.get({ url: `/datacenter/typical-scenario-case/get?id=` + id })
  },

  // 新增典型场景案例库
  createTypicalScenarioCase: async (data: TypicalScenarioCaseVO) => {
    return await request.post({ url: `/datacenter/typical-scenario-case/create`, data })
  },

  // 修改典型场景案例库
  updateTypicalScenarioCase: async (data: TypicalScenarioCaseVO) => {
    return await request.put({ url: `/datacenter/typical-scenario-case/update`, data })
  },

  // 删除典型场景案例库
  deleteTypicalScenarioCase: async (id: number) => {
    return await request.delete({ url: `/datacenter/typical-scenario-case/delete?id=` + id })
  },

  // 导出典型场景案例库 Excel
  exportTypicalScenarioCase: async (params) => {
    return await request.download({ url: `/datacenter/typical-scenario-case/export-excel`, params })
  },
}