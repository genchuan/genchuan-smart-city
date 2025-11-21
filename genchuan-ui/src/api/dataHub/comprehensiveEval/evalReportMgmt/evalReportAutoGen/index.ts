import request from '@/config/axios'

// 评价报告 VO
export interface EvalRptVO {
  id: number // 主键ID
  evalRptId: string // 报告ID
  evalRptName: string // 报告名称
  evalRptCode: string // 报告编码
  rptTemplateId: string // 关联模板ID
  rptTemplateName: string // 关联模板名称
  resultArchId: string // 关联存档ID
  evalObjectId: string // 评价对象ID
  evalObjectName: string // 评价对象名称
  rptFilePath: string // 报告文件路径
  rptStatus: string // 报告状态
  generateTime: Date // 生成时间
  suppleContent: string // 补充内容
  generateUser: string // 生成人
  createTimeBiz: Date // 创建时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 评价报告 API
export const EvalRptApi = {
  // 查询评价报告分页
  getEvalRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-rpt/page`, params })
  },

  // 查询评价报告详情
  getEvalRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-rpt/get?id=` + id })
  },

  // 新增评价报告
  createEvalRpt: async (data: EvalRptVO) => {
    return await request.post({ url: `/datacenter/eval-rpt/create`, data })
  },

  // 修改评价报告
  updateEvalRpt: async (data: EvalRptVO) => {
    return await request.put({ url: `/datacenter/eval-rpt/update`, data })
  },

  // 删除评价报告
  deleteEvalRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-rpt/delete?id=` + id })
  },

  // 导出评价报告 Excel
  exportEvalRpt: async (params) => {
    return await request.download({ url: `/datacenter/eval-rpt/export-excel`, params })
  }
}
