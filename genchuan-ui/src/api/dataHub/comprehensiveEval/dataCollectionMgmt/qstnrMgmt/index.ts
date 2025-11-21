import request from '@/config/axios'

// 问卷调查管理 VO
export interface EvalQuestionnaireVO {
  id: number // 主键ID
  questionnaireId: string // 问卷ID
  questionnaireName: string // 问卷名称
  questionnaireCode: string // 问卷编码
  evalTaskId: string // 关联任务ID
  evalTaskName: string // 关联任务名称
  idxItemId: string // 关联指标项ID
  idxItemName: string // 关联指标项名称
  targetScope: string // 调查对象范围
  releaseMethod: string // 发放方式
  questionnaireUrl: string // 问卷链接
  questionnaireQrcode: string // 问卷二维码
  startTime: Date // 开始时间
  endTime: Date // 结束时间
  questionnaireStatus: string // 问卷状态
  fillCount: number // 填写人数
  questionnaireContent: string // 问卷内容
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 问卷调查管理 API
export const EvalQuestionnaireApi = {
  // 查询问卷调查管理分页
  getEvalQuestionnairePage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-questionnaire/page`, params })
  },

  // 查询问卷调查管理详情
  getEvalQuestionnaire: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-questionnaire/get?id=` + id })
  },

  // 新增问卷调查管理
  createEvalQuestionnaire: async (data: EvalQuestionnaireVO) => {
    return await request.post({ url: `/datacenter/eval-questionnaire/create`, data })
  },

  // 修改问卷调查管理
  updateEvalQuestionnaire: async (data: EvalQuestionnaireVO) => {
    return await request.put({ url: `/datacenter/eval-questionnaire/update`, data })
  },

  // 删除问卷调查管理
  deleteEvalQuestionnaire: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-questionnaire/delete?id=` + id })
  },

  // 导出问卷调查管理 Excel
  exportEvalQuestionnaire: async (params) => {
    return await request.download({ url: `/datacenter/eval-questionnaire/export-excel`, params })
  }
}
