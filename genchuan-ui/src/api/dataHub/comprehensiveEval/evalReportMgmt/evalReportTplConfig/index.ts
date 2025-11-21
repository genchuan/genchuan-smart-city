import request from '@/config/axios'

// 报告模板配置 VO
export interface EvalRptTemplateVO {
  id: number // 主键ID
  rptTemplateId: string // 模板ID
  rptTemplateName: string // 模板名称
  rptTemplateCode: string // 模板编码
  applyTaskType: string // 适用任务类型
  templateFilePath: string // 模板文件路径
  rptStructure: string // 报告结构
  dynamicFieldMap: string // 动态字段映射
  fixedContent: string // 固定内容
  templateVersion: string // 模板版本
  enableStatus: string // 启用状态
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  updateUserBiz: string // 更新人(业务)
  updateTimeBiz: Date // 更新时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 报告模板配置 API
export const EvalRptTemplateApi = {
  // 查询报告模板配置分页
  getEvalRptTemplatePage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-rpt-template/page`, params })
  },

  // 查询报告模板配置详情
  getEvalRptTemplate: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-rpt-template/get?id=` + id })
  },

  // 新增报告模板配置
  createEvalRptTemplate: async (data: EvalRptTemplateVO) => {
    return await request.post({ url: `/datacenter/eval-rpt-template/create`, data })
  },

  // 修改报告模板配置
  updateEvalRptTemplate: async (data: EvalRptTemplateVO) => {
    return await request.put({ url: `/datacenter/eval-rpt-template/update`, data })
  },

  // 删除报告模板配置
  deleteEvalRptTemplate: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-rpt-template/delete?id=` + id })
  },

  // 导出报告模板配置 Excel
  exportEvalRptTemplate: async (params) => {
    return await request.download({ url: `/datacenter/eval-rpt-template/export-excel`, params })
  }
}
