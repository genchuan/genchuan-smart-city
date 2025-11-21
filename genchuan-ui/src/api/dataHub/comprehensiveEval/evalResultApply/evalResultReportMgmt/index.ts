import request from '@/config/axios'

// 结果报表管理 VO
export interface EvalResultRptVO {
  id: number // 主键ID
  resultRptId: string // 报表ID
  resultRptName: string // 报表名称
  resultRptCode: string // 报表编码
  evalTaskId: string // 关联任务ID
  evalTaskName: string // 关联任务名称
  rptType: string // 报表类型
  statDimension: string // 统计维度
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  rptFields: string // 报表字段
  generateTime: Date // 生成时间
  rptStatus: string // 报表状态
  filePath: string // 文件路径
  generateUser: string // 生成人
  createTimeBiz: Date // 创建时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 结果报表管理 API
export const EvalResultRptApi = {
  // 查询结果报表管理分页
  getEvalResultRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-result-rpt/page`, params })
  },

  // 查询结果报表管理详情
  getEvalResultRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-result-rpt/get?id=` + id })
  },

  // 新增结果报表管理
  createEvalResultRpt: async (data: EvalResultRptVO) => {
    return await request.post({ url: `/datacenter/eval-result-rpt/create`, data })
  },

  // 修改结果报表管理
  updateEvalResultRpt: async (data: EvalResultRptVO) => {
    return await request.put({ url: `/datacenter/eval-result-rpt/update`, data })
  },

  // 删除结果报表管理
  deleteEvalResultRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-result-rpt/delete?id=` + id })
  },

  // 导出结果报表管理 Excel
  exportEvalResultRpt: async (params) => {
    return await request.download({ url: `/datacenter/eval-result-rpt/export-excel`, params })
  }
}
